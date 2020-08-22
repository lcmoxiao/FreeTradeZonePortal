package com.ftzp.controller.rg;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.pojo.rg.Hiring;
import com.ftzp.service.rg.HiringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/webContent")
public class HiringController {
    private static final Logger logger = LoggerFactory.getLogger(ColumnController.class);
    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;

    @Autowired
    private HiringService hiringService;

    @ResponseBody
    @RequestMapping(value = "/hiring", method = RequestMethod.GET)
    public List<Hiring> hiring(Map<String, Object> map) {
        System.out.println("12333333333333333");
        List<Hiring> hiring = hiringService.getHiring();
        System.out.println(hiring);
        return hiring;
    }

    @ResponseBody
    @RequestMapping(value = "/hiring/{hiringId}", method = RequestMethod.DELETE)
    public String delHiring(@PathVariable("hiringId") Integer hiringId, HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "删除了招聘信息hiringId：" + hiringId);

        boolean isDel = hiringService.delHiring(hiringId);
        if (isDel) {
            return "success";
        } else {
            return "failure";
        }
    }

    @RequestMapping(value = "/hiring/{hiringId}", method = RequestMethod.GET)
    @ResponseBody
    public String getHiringById(@PathVariable("hiringId") Integer hiringId,
                                HttpServletResponse response, HttpServletRequest request) throws Exception{
        Hiring hiring = hiringService.getHiringById(hiringId);
        String hiringFile=hiring.getHiringFile();
        //要下载的图片地址
        String path = request.getServletContext().getRealPath("/upload");
        String fileName = hiringFile;

        System.out.println(fileName);
        //1、设置response 响应头
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8"); //字符编码
        response.setContentType("multipart/form-data"); //二进制传输数据
        //设置响应头
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        File file = new File(path, fileName);
        //2、 读取文件--输入流
        InputStream input = new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();

        byte[] buff = new byte[1024];
        int index = 0;
        //4、执行 写出操作
        while ((index = input.read(buff)) != -1) {
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return null;

    }

    @RequestMapping(value = "/hiring", method = RequestMethod.POST)
    @ResponseBody
    public String addHiring(Hiring hiring,
                            @RequestParam("uploadHiringFile") MultipartFile file,
                            HttpServletRequest request) throws IOException {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "增添了招聘信息hiringId：" + hiring.getHiringId());

        /*创建保存附件的文件夹*/
        String uploadPath = request.getServletContext().getRealPath("/upload");
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.isEmpty()) {
            /*获取上传文件的原始文件名，例如 XXX.doc */
            String originalFilename = file.getOriginalFilename();
            /*获取文件的后缀名，例如jpg*/
            String suffix = originalFilename.split("\\.")[1];
            /*获取一个uuid*/
            String uuid = UUID.randomUUID().toString();
            /*拼装保存文件的名字*/
            String fileName = uuid.replace("-", "") + "." + suffix;
            /*保存文件*/
            file.transferTo(new File(uploadPath + File.separator + fileName));
            /*设置保存hiring对象中hiringFile属性的值，这里保存的是文件名字*/
            hiring.setHiringFile(fileName);
        }

        hiring.setHiringTime(new Date());
        Integer isAdd = hiringService.addHiring(hiring);
        if (isAdd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }
}
