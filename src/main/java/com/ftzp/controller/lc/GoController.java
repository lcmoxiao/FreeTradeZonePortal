package com.ftzp.controller.lc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoController {

    @RequestMapping("/")
    String defualt() {
        return "/index";
    }

    @RequestMapping("/index")
    String index() {
        return "/index";
    }

    @RequestMapping("/home")
    String home() {
        return "/home";
    }

    @RequestMapping("/userManagement")
    String goToUserManager() {
        return "/lc/user/userManager";
    }

    @RequestMapping("/roleManagement")
    String goRoleManager() {
        return "/lc/user/roleManager";
    }

    @RequestMapping("/permissionManagement")
    String goPermissionManager() {
        return "/lc/user/permissionManager";
    }

    @RequestMapping("/workManagement")
    String goToMyWork() {
        return "/lc/workflow/myWork";
    }

    @RequestMapping("/workflowAdd")
    String goToAddWorkFlow() {
        return "/lc/workflow/addWorkFlow";
    }

    @RequestMapping("/myPost")
    String goToInitWorkFlow() {
        return "/lc/workflow/myPosted";
    }

    @RequestMapping("/workflowManagement")
    String goToWorkFlowManager() {
        return "/lc/workflow/workFlowManager";
    }


}
