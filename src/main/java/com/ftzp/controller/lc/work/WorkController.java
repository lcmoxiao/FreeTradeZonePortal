package com.ftzp.controller.lc.work;

import com.ftzp.ZipUtils;
import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.pojo.lc.workflow.Work;
import com.ftzp.pojo.lc.workflow.WorkStep;
import com.ftzp.service.lc.work.WorkService;
import com.ftzp.service.lc.work.WorkStepService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@RequestMapping("/work")
@Controller
public class WorkController {

    @Resource(name = "workService")
    WorkService workService;
    @Resource(name = "workStepService")
    WorkStepService workStepService;
    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String initWork(@RequestParam("wfLength") Integer wfLength, @RequestParam("wfId") Integer wfId,
                    @RequestParam(value = "uploadFile", required = false) MultipartFile file,
                    @RequestParam("wdesc") String wdesc,
                    @RequestParam("wContent") String wContent,
                    HttpServletRequest request, HttpSession session) throws Exception {
        String uploadPath = request.getServletContext().getRealPath("/worksUpload");
        File dir = new File(uploadPath);
        Work w = new Work();
        if (!dir.exists()) {
            if (!dir.mkdirs()) throw new Exception("初始化文件夹失败");
        }
        if (file != null) {
            String wFileName = saveWorkFile(file, uploadPath);
            w.setwFile(wFileName);
        }
        String IP = getRemoteIP(request);
        User u = (User) redisObjCache.getValue(IP + "u");
        w.setwContent(wContent);
        w.setuId(u.getuId());
        w.setWfId(wfId);
        w.setwLength(wfLength);
        w.setWdesc(wdesc);
        workService.insertWork(w);
        return "redirect:/workManagement";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    void downloadFile(@RequestParam("path") String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uploadPath = request.getServletContext().getRealPath("/worksUpload");
        String sourceName = uploadPath + File.separator + path;

        String resName = sourceName + "tmp.zip";
        ZipUtils.createZip(sourceName, resName);

        response.setContentType("application/x-zip-compressed;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            // 设置相应头，控制浏览器下载该文件，这里就是会出现当你点击下载后，出现的下载地址框
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode(path, StandardCharsets.UTF_8));
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

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    String commitWork(@RequestParam("wId") Integer wId, @RequestParam("ranking") Integer ranking,
                      @RequestParam("wContent") String wContent,
                      @RequestParam(value = "uploadFile", required = false) MultipartFile file,
                      HttpServletRequest request) throws Exception {
        String uploadPath = request.getServletContext().getRealPath("/worksUpload");
        Work w = workService.getWork(wId).get(0);
        w.setwContent(wContent);
        if (w.getwLength().equals(ranking)) {
            workService.deleteWork(w);
        } else {
            System.out.println(uploadPath);
            System.out.println(file);
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                if (!dir.mkdirs()) throw new Exception("初始化文件夹失败");
            }
            if (file != null) {
                String wFileName = saveWorkFile(file, uploadPath);
                w.setwFile(wFileName);
            }
            w.setRanking(ranking + 1);
            workService.updateWork(w);
        }
        return "redirect:/workManagement";
    }

    @RequestMapping(value = "/{wId}", method = RequestMethod.DELETE)
    @ResponseBody
    String deleteWorkFlow(@PathVariable Integer wId) {
        Work w = new Work();
        w.setwId(wId);
        workService.deleteWork(w);
        return "redirect:/workManagement";
    }

    @RequestMapping(value = "/myPosted", method = RequestMethod.GET)
    @ResponseBody
    List<Work> myPosted(HttpServletRequest hsr) {
        String IP = getRemoteIP(hsr);
        User u = (User) redisObjCache.getValue(IP + "u");
        return workService.getWorkByUId(u.getuId());
    }

    @RequestMapping(value = "/needDoing", method = RequestMethod.GET)
    @ResponseBody
    List<WorkStep> showWork(HttpServletRequest hsr) {
        //登陆时会把User信息存入session
        String IP = getRemoteIP(hsr);
        User u = (User) redisObjCache.getValue(IP + "u");
        List<Work> works = workService.getWork(null);
        List<WorkStep> res = new ArrayList<>();
        for (Work w : works) {
            List<WorkStep> wss = workStepService.getWorkStep(w.getWfId());
            for (var ws : wss) {
                if (ws.getWfId() == w.getWfId() && ws.getRanking() == w.getRanking()) {
                    if (u.getrId() == ws.getrId()) {
                        ws.setwFile(w.getwFile());
                        ws.setwId(w.getwId());
                        ws.setwLastDoTime(w.getwLastDoTime());
                        ws.setwPostTime(w.getwPostTime());
                        ws.setwContent(w.getwContent());
                        ws.setWdesc(w.getWdesc());
                        res.add(ws);
                    }
                }
            }
        }
        return res;
    }


    private String saveWorkFile(MultipartFile file, String uploadPath) throws IOException {
        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            file.transferTo(new File(uploadPath + File.separator + originalFilename));
            return originalFilename;
        }
        return null;
    }
}
