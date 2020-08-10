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

    @RequestMapping("/workManagement")
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

}
