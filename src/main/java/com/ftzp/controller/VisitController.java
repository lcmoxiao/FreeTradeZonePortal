package com.ftzp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.PriorityQueue;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
public class VisitController {

    private static final Logger logger = LoggerFactory.getLogger(VisitController.class);

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o1 > o2 ? -1 : 1);

        heap.add(1);
        heap.add(2);
        heap.offer(2);

        System.out.println(heap.peek());

    }

    @RequestMapping("/visit")
    String showMess(HttpServletRequest request) {
        String IP = getRemoteIP(request);
        logger.info("IP:" + IP + "的游客访问了网站");


        return "/visit";
    }

}
