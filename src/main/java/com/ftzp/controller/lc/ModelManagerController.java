package com.ftzp.controller.lc;

import com.ftzp.ZipUtils;
import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;
import static org.apache.commons.io.FileUtils.deleteDirectory;

@Controller
@RequestMapping("/model")
public class ModelManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ModelManagerController.class);
    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<FileAndModel> getModelPaths(@RequestParam("path") String path, HttpServletRequest request) {
        String uploadPath = request.getServletContext().getRealPath("/modelsUpload");
        List<FileAndModel> paths = new ArrayList<>();

        File dir;
        if (path != null) dir = new File(uploadPath + File.separator + path);
        else dir = new File(uploadPath);

        String[] children = dir.list();
        if (children == null) {
            System.out.println("目录不存在或它不是一个目录");
        } else {
            for (String filename : children) {
                paths.add(new FileAndModel(filename));
            }
        }
        return paths;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String postModelPaths(@RequestParam(value = "fileFolder") MultipartFile[] files
            , @RequestParam(value = "nowPath") String nowPath
            , HttpServletRequest request) throws IOException {
        String IP = getRemoteIP(request);
        User u = (User) redisObjCache.getValue(IP + "u");
        logger.info(u.getuName() + "提交了新的模板内容");
        String uploadPath = request.getServletContext().getRealPath("/modelsUpload");
        //判断存储的文件夹是否存在
        File dir = new File(uploadPath);
        if (!dir.exists()) dir.mkdirs();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String wFileName = ((CommonsMultipartFile) file).getFileItem().getName();
                file.transferTo(new File(uploadPath + nowPath + File.separator + wFileName));
                System.out.println(uploadPath + File.separator + wFileName);
            }
        }

        return "ok";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    void deleteFile(@RequestParam("path") String path, HttpServletRequest request) throws IOException {
        String IP = getRemoteIP(request);
        User u = (User) redisObjCache.getValue(IP + "u");
        logger.info(u.getuName() + "删除了模板内容path:" + path);
        String uploadPath = request.getServletContext().getRealPath("/modelsUpload");
        System.out.println(uploadPath + File.separator + path);
        File f = new File(uploadPath + File.separator + path);
        if (f.isDirectory()) deleteDirectory(f);
        else f.delete();
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    void downloadFile(@RequestParam("path") String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String IP = getRemoteIP(request);
        User u = (User) redisObjCache.getValue(IP + "u");
        logger.info(u.getuName() + "下载一个模板内容path:" + path);

        String uploadPath = request.getServletContext().getRealPath("/modelsUpload");
        String sourceName = uploadPath + File.separator + path;
        String resName = sourceName + "tmp.zip";
        ZipUtils.createZip(sourceName, resName);
        response.setContentType("application/x-zip-compressed;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            // 设置相应头，控制浏览器下载该文件，这里就是会出现当你点击下载后，出现的下载地址框
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode(resName, StandardCharsets.UTF_8));
            FileInputStream in = new FileInputStream(resName);
            OutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println("下载文件出错");
        } finally {
            new File(resName).delete();
        }
    }

    private static class FileAndModel {
        String fName;

        public FileAndModel(String fName) {
            this.fName = fName;
        }

        public String getfName() {
            return fName;
        }

        public void setfName(String fName) {
            this.fName = fName;
        }
    }
}
