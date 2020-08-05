package com.ftzp.controller.lc;

import com.ftzp.pojo.lc.WorkStep;
import com.ftzp.service.lc.UserService;
import com.ftzp.service.lc.WorkFlowService;
import com.ftzp.service.lc.WorkService;
import com.ftzp.service.lc.WorkStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    WorkService workService;
    @Autowired
    WorkStepService workStepService;
    @Autowired
    UserService userService;
    @Autowired
    WorkFlowService workFlowService;

    @RequestMapping("/test")
    String test() {
        return "/lc/index";
    }

    @RequestMapping("test2")
    String test2() {


        return "/lc/index";
    }

    @RequestMapping("test3")
    String test3() {
        List<WorkStep> wss = workStepService.getWorkStep(1);
        System.out.println(wss);
        return "/lc/index";
    }



}
