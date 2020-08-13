package com.ftzp.controller.lc;

import com.ftzp.ZipUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

@Controller
@RequestMapping("/model")
public class ModelManagerController {

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
                System.out.println(filename);
            }
        }
        return paths;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String postModelPaths(@RequestParam(value = "fileFolder") MultipartFile[] files
            , @RequestParam(value = "nowPath") String nowPath
            , HttpServletRequest request) throws IOException {
        String uploadPath = request.getServletContext().getRealPath("/modelsUpload");
        //判断存储的文件夹是否存在
        File dir = new File(uploadPath);
        if (!dir.exists()) dir.mkdirs();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String wFileName = ((CommonsMultipartFile) file).getFileItem().getName();
                file.transferTo(new File(uploadPath + nowPath + File.separator + wFileName));
            }
        }
        return "ok";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    Boolean deleteFile(@RequestParam("path") String path, HttpServletRequest request) {
        String uploadPath = request.getServletContext().getRealPath("/modelsUpload");
        return DeleteFolder(uploadPath + File.separator + path);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    void downloadFile(@RequestParam("path") String path, HttpServletRequest request, HttpServletResponse response) {
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
            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(resName);
            // 创建输出流
            OutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            // 关闭文件流
            in.close();
            // 关闭输出流
            out.close();
        } catch (Exception e) {
            System.out.println("下载文件出错");
        } finally {
            deleteDirectory(sourceName + "/" + resName);
        }
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */

    public boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;

    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
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
