package com.ftzp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VisitController {


    @RequestMapping("/visit")
    String showMess() {
        return "/visit";
    }


}
