package com.ftzp.controller.lc;

import com.ftzp.pojo.lc.User;
import com.ftzp.pojo.lc.Work;
import com.ftzp.pojo.lc.WorkStep;
import com.ftzp.service.lc.WorkService;
import com.ftzp.service.lc.WorkStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/w")
@Controller
public class WorkController {

    WorkService workService;
    WorkStepService workStepService;

    @Autowired
    public void setWorkStepService(WorkStepService workStepService) {
        this.workStepService = workStepService;
    }

    @Autowired
    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    @RequestMapping(value = "/initWork", method = RequestMethod.POST)
    String initWork(@RequestParam("wfLength") Integer wfLength, @RequestParam("wfId") Integer wfId, @RequestParam("uploadFile") MultipartFile file,
                    @RequestParam("wdesc") String wdesc, HttpServletRequest request) throws Exception {
        String uploadPath = request.getServletContext().getRealPath("/upload");
        File dir = new File(uploadPath);
        Work w = new Work();
        if (!dir.exists()) {
            if (!dir.mkdirs()) throw new Exception("初始化文件夹失败");
        }
        String wFileName = saveWorkFile(file, uploadPath);
        User u = (User) request.getSession().getAttribute("user");
        w.setuId(u.getuId());
        w.setwFile(wFileName);
        w.setWfId(wfId);
        w.setwLength(wfLength);
        w.setWdesc(wdesc);
        workService.insertWork(w);
        return "/lc/myWork";
    }

    @RequestMapping(value = "/commmitWork", method = RequestMethod.POST)
    String commitWork(@RequestParam("wId") Integer wId, @RequestParam("ranking") Integer ranking,
                      @RequestParam(value = "uploadFile", required = false) MultipartFile file,
                      HttpServletRequest request) throws IOException {
        String uploadPath = request.getServletContext().getRealPath("/upload");
        Work w = workService.getWork(wId).get(0);
        if (w.getwLength().equals(ranking)) {
            workService.deleteWork(w);
        } else {
            String wFileName = saveWorkFile(file, uploadPath);
            w.setwFile(wFileName);
            w.setRanking(ranking + 1);
            workService.updateWork(w);
        }
        return "/lc/myWork";
    }

    @RequestMapping(value = "/deleteWork/{wId}", method = RequestMethod.GET)
    String deleteWorkFlow(@PathVariable Integer wId) {
        Work w = new Work();
        w.setwId(wId);
        workService.deleteWork(w);
        return "redirect:/wf/goToWorkFlowManager";
    }

    @RequestMapping(value = "/showMyPosted", method = RequestMethod.GET)
    @ResponseBody
    List<Work> commitWork(HttpSession session) throws IOException {
        User u = (User) session.getAttribute("user");
        return workService.getWorkByUId(u.getuId());
    }

    @RequestMapping(value = "/showWorkStep", method = RequestMethod.GET)
    @ResponseBody
    List<WorkStep> showWork(HttpSession session) {
        //登陆时会把User信息存入session
        User user = (User) session.getAttribute("user");
        List<Work> works = workService.getWork(null);
        List<WorkStep> res = new ArrayList<>();
        for (Work w : works) {
            List<WorkStep> wss = workStepService.getWorkStep(w.getWfId());
            for (var ws : wss) {
                if (ws.getWfId() == w.getWfId() && ws.getRanking() == w.getRanking()) {
                    if (user.getrId() == ws.getrId()) {
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
