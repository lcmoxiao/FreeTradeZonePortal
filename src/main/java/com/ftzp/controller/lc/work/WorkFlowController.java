package com.ftzp.controller.lc.work;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.controller.lc.LoginController;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.pojo.lc.workflow.WorkFlow;
import com.ftzp.pojo.lc.workflow.WorkStep;
import com.ftzp.service.lc.user.UserService;
import com.ftzp.service.lc.work.WorkFlowService;
import com.ftzp.service.lc.work.WorkStepService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/workflow")
public class WorkFlowController {

    private static final Logger logger = LoggerFactory.getLogger(WorkFlowController.class);
    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;
    @Resource(name = "workStepService")
    WorkStepService workStepService;
    @Resource(name = "workFlowService")
    WorkFlowService workFlowService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String addWorkFlow(@RequestParam("uploadXML") MultipartFile multipartFile, HttpServletRequest request) {

        File file;
        try {
            file = File.createTempFile(String.valueOf(UUID.randomUUID()), ".xml");
            multipartFile.transferTo(file);
            Integer wfId = parseXmlAndSaveWorkFlow(file);
            User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
            logger.info(u.getuName() + "提交了工作流，生成的工作流ID为：" + wfId);
            file.deleteOnExit();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return "redirect:/workflowManagement";
    }

    //获取指定工作流的流程信息或者所有工作流的信息
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<WorkFlow> findWorkFlow() {
        return workFlowService.findWorkFlow(null);
    }

    //获取指定工作流的流程信息或者所有工作流的信息
    @RequestMapping(value = "/{wfId}", method = RequestMethod.GET)
    @ResponseBody
    List<WorkFlow> findWorkFlow(@PathVariable(value = "wfId", required = false) Integer wfId) {
        if (wfId == null) return workFlowService.findWorkFlow(null);
        else return workFlowService.findWorkFlow(wfId);
    }

    @RequestMapping(value = "/{wfId}", method = RequestMethod.DELETE)
    @ResponseBody
    String deleteWorkFlow(@PathVariable Integer wfId,HttpServletRequest re) {

        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "删除了工作流wfId：" + wfId);

        workFlowService.deleteWorkFlow(wfId);
        workStepService.deleteWorkStepByWfId(wfId);
        return "redirect:/workflowManagement";
    }

    public Integer parseXmlAndSaveWorkFlow(File f) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document d = reader.read(f);
        Element root = d.getRootElement();
        Iterator<Element> i = root.elementIterator();
        WorkFlow wf = new WorkFlow();
        int wfLength = 0;
        while (i.hasNext()) {
            Element e = i.next();
            switch (e.getName()) {
                case "wfName" -> {
                    wf.setWfName(e.getStringValue());
                    workFlowService.insertWorkFlow(wf);
                }
                case "wfdesc" -> {
                    wf.setWfdesc(e.getStringValue());
                }
                case "WorkStep" -> {
                    Iterator<Element> ii = e.elementIterator();
                    WorkStep ws = new WorkStep();
                    ws.setWfId(wf.getWfId());
                    wfLength++;
                    while (ii.hasNext()) {
                        Element ee = ii.next();
                        switch (ee.getName()) {
                            case "wsName" -> ws.setWsName(ee.getStringValue());
                            case "ranking" -> ws.setRanking(Integer.parseInt(ee.getStringValue()));
                            case "rId" -> ws.setrId(Integer.parseInt(ee.getStringValue()));
                            case "desc" -> ws.setWsdesc(ee.getStringValue());
                            case "wsType" -> ws.setWsType(ee.getStringValue());
                        }
                    }
                    workStepService.insertWorkStep(ws);
                }
            }
            wf.setWfLength(wfLength);
            workFlowService.updateWorkFlow(wf);
            System.out.println("成功解析获得" + wf);
        }

        return wf.getWfId();
    }


}
