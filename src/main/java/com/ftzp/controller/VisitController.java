package com.ftzp.controller;

import com.ftzp.controller.lc.work.WorkFlowController;
import com.ftzp.pojo.lc.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
public class VisitController {

    private static final Logger logger = LoggerFactory.getLogger(VisitController.class);

    @RequestMapping("/visit")
    String showMess(HttpServletRequest request) {
        String IP = getRemoteIP(request);
        logger.info("IP:"+IP+"的游客访问了网站");


        return "/visit";
    }


}
