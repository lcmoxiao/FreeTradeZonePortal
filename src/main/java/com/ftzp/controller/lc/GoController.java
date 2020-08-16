package com.ftzp.controller.lc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoController {

    @RequestMapping("/error")
    String error() {
        return "/error";
    }

    //需要在权限中配置的
    @RequestMapping("/userManagement")
    String userManagement() {
        return "/lc/user/userManager";
    }

    @RequestMapping("/roleManagement")
    String roleManagement() {
        return "/lc/user/roleManager";
    }

    @RequestMapping("/permissionManagement")
    String permissionManagement() {
        return "/lc/user/permissionManager";
    }

    @RequestMapping("/workOfMine")
    String workManagement() {
        return "/lc/work/myWork";
    }

    @RequestMapping("/workOfMyPost")
    String myPost() {
        return "/lc/work/myPosted";
    }

    @RequestMapping("/workflowManagement")
    String workflowManagement() {
        return "/lc/work/workFlowManager";
    }

    @RequestMapping("/modelManagement")
    String modelManagement() {
        return "/lc/modelManager.";
    }

    @RequestMapping("/statisticOfLogin")
    String loginStatistic() {
        return "/lc/statisticOfLogin";
    }

}
