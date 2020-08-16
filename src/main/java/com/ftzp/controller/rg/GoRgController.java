package com.ftzp.controller.rg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoRgController {

    @RequestMapping("/main")
    String main() {
        return "/rg/main";
    }

    @RequestMapping("/addCol")
    String addCol() {
        return "/rg/addCol";
    }

    @RequestMapping("/addChannel")
    String addChannel() {
        return "/rg/addChannel";
    }

    @RequestMapping("/edit")
    String edit() {
        return "/rg/edit";
    }

    @RequestMapping("/editCol")
    String editCol() {
        return "/rg/editCol";
    }

    @RequestMapping("/editChannel")
    String editChannel() {
        return "/rg/editChannel";
    }

    @RequestMapping("/naviManag")
    String naviManag() {
        return "/rg/naviManag";
    }

    @RequestMapping("/publish")
    String publish() {
        return "/rg/publish";
    }

    @RequestMapping("/viewCon")
    String viewCon() {
        return "/rg/viewCon";
    }

    @RequestMapping("/xxjs")
    String xxjs() {
        return "/rg/xxjs";
    }


    @RequestMapping("/ckMess")
    String ckMess() {
        return "/rg/ckMess";
    }

}
