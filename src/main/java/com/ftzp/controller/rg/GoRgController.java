package com.ftzp.controller.rg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webContent")
public class GoRgController {

    @RequestMapping("/main")
    String main() {
        return "/rg/webContent/main";
    }

    @RequestMapping("/addCol")
    String addCol() {
        return "/rg/webContent/addCol";
    }

    @RequestMapping("/addChannel")
    String addChannel() {
        return "/rg/webContent/addChannel";
    }

    @RequestMapping("/edit")
    String edit() {
        return "/rg/webContent/edit";
    }

    @RequestMapping("/editCol")
    String editCol() {
        return "/rg/webContent/editCol";
    }

    @RequestMapping("/editChannel")
    String editChannel() {
        return "/rg/webContent/editChannel";
    }

    @RequestMapping("/naviManag")
    String naviManag() {
        return "/rg/webContent/naviManag";
    }

    @RequestMapping("/publish")
    String publish() {
        return "/rg/webContent/publish";
    }

    @RequestMapping("/viewCon")
    String viewCon() {
        return "/rg/webContent/viewCon";
    }

    @RequestMapping("/xxjs")
    String xxjs() {
        return "/rg/webContent/xxjs";
    }


    @RequestMapping("/ckMess")
    String ckMess() {
        return "/rg/webContent/ckMess";
    }


    @RequestMapping("/viewMessage")
    String viewMessage() {
        return "/rg/webContent/viewMessage";
    }

    @RequestMapping("/editMessage")
    String editMessage() {
        return "/rg/webContent/editMessage";
    }

    @RequestMapping("/submitMessage")
    String submitMessage() {
        return "/rg/webContent/submitMessage";
    }
}
