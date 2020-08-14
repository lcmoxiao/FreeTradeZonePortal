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

    @RequestMapping("/myWork")
    String workManagement() {
        return "/lc/workflow/myWork";
    }

    @RequestMapping("/myPost")
    String myPost() {
        return "/lc/workflow/myPosted";
    }

    @RequestMapping("/workflowManagement")
    String workflowManagement() {
        return "/lc/workflow/workFlowManager";
    }

    @RequestMapping("/modelManagement")
    String modelManagement() {
        return "/lc/modelManager.";
    }

    @RequestMapping("/loginStatistic")
    String loginStatistic() {
        return "/lc/loginStatistic";
    }

}
