package com.ftzp.controller.lc.workflow;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.User;
import com.ftzp.pojo.lc.Work;
import com.ftzp.pojo.lc.WorkStep;
import com.ftzp.service.lc.WorkService;
import com.ftzp.service.lc.WorkStepService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                    @RequestParam("wdesc") String wdesc, HttpServletRequest request, HttpSession session) throws Exception {
        String uploadPath = request.getServletContext().getRealPath("/upload");
        File dir = new File(uploadPath);
        Work w = new Work();
        if (!dir.exists()) {
            if (!dir.mkdirs()) throw new Exception("初始化文件夹失败");
        }
        if (file != null) {
            String wFileName = saveWorkFile(file, uploadPath);
            w.setwFile(wFileName);
        }
        User u = (User) redisObjCache.getValue(session.getId() + "u");
        w.setuId(u.getuId());
        w.setWfId(wfId);
        w.setwLength(wfLength);
        w.setWdesc(wdesc);
        workService.insertWork(w);
        return "redirect:/workManagement";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    String commitWork(@RequestParam("wId") Integer wId, @RequestParam("ranking") Integer ranking,
                      @RequestParam(value = "uploadFile", required = false) MultipartFile file,
                      HttpServletRequest request) throws IOException {
        String uploadPath = request.getServletContext().getRealPath("/upload");
        Work w = workService.getWork(wId).get(0);
        if (w.getwLength().equals(ranking)) {
            workService.deleteWork(w);
        } else {
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
    List<Work> commitWork(HttpSession session) {
        User u = (User) redisObjCache.getValue(session.getId() + "u");
        return workService.getWorkByUId(u.getuId());
    }

    @RequestMapping(value = "/needDoing", method = RequestMethod.GET)
    @ResponseBody
    List<WorkStep> showWork(HttpSession session) {
        //登陆时会把User信息存入session
        User u = (User) redisObjCache.getValue(session.getId() + "u");
        List<Work> works = workService.getWork(null);
        List<WorkStep> res = new ArrayList<>();
        for (Work w : works) {
            List<WorkStep> wss = workStepService.getWorkStep(w.getWfId());
            for (var ws : wss) {
                if (ws.getWfId() == w.getWfId() && ws.getRanking() == w.getRanking()) {
                    if (u.getrId() == ws.getrId()) {
                        ws.setwFile(w.getwFile());
                        ws.setwId(w.getwId());
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
            String suffix = originalFilename.split("\\.")[1];
            String uuid = UUID.randomUUID().toString();
            String wFileName = uuid.replace("-", "") + "." + suffix;
            file.transferTo(new File(uploadPath + File.separator + wFileName));
            return wFileName;
        }
        return null;
    }
}
