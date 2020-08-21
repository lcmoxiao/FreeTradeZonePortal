package com.ftzp.controller.lc.work;

import com.ftzp.pojo.lc.workflow.WorkStep;
import com.ftzp.service.lc.work.WorkFlowService;
import com.ftzp.service.lc.work.WorkStepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/workstep")
public class WorkStepController {

    private static final Logger logger = LoggerFactory.getLogger(WorkStepController.class);

    @Resource(name = "workStepService")
    WorkStepService workStepService;
    @Resource(name = "workFlowService")
    WorkFlowService workFlowService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String insertWorkStep(WorkStep workStep, @RequestParam("rId") Integer rId) {
        workStep.setrId(rId);
        workStepService.insertWorkStep(workStep);
        workFlowService.addWorkFlowLength(workStep.getWfId());
        logger.info("insert ws:" + workStep.getWsName());
        return "insert workstep success";
    }

    @RequestMapping(value = "/{wsId}/{wfId}", method = RequestMethod.DELETE)
    @ResponseBody
    String deleteWorkStep(@PathVariable("wsId") Integer wsId, @PathVariable("wfId") Integer wfId) {
        workStepService.deleteWorkStep(wsId);
        workFlowService.subWorkFlowLength(wfId);
        logger.info("delete workstep wsId:" + wsId);
        return "delete workstep success";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    String updateWorkStep(WorkStep workStep, @RequestParam("rId") Integer rId) {
        workStep.setrId(rId);
        workStepService.updateWorkStep(workStep);
        logger.info("update workstep wsId:" + workStep.getWsId());
        return "update workstep success";
    }

}
