package com.ftzp.controller.lc.workflow;

import com.ftzp.pojo.lc.WorkFlow;
import com.ftzp.pojo.lc.WorkStep;
import com.ftzp.service.lc.UserService;
import com.ftzp.service.lc.WorkFlowService;
import com.ftzp.service.lc.WorkStepService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/workflow")
public class WorkFlowController {

    WorkStepService workStepService;
    UserService userService;
    WorkFlowService workFlowService;

    @Autowired
    public void setWorkStepService(WorkStepService workStepService) {
        this.workStepService = workStepService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setWorkFlowService(WorkFlowService workFlowService) {
        this.workFlowService = workFlowService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String addWorkFlow(@RequestParam("uploadXML") MultipartFile multipartFile) {
        File file;
        try {
            file = File.createTempFile(String.valueOf(UUID.randomUUID()), ".xml");
            multipartFile.transferTo(file);
            Integer wfId = parseXmlAndSaveWorkFlow(file);
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
    String deleteWorkFlow(@PathVariable Integer wfId) {
        workFlowService.deleteWorkFlow(wfId);
        workStepService.deleteWorkStep(wfId);
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
