package com.ftzp.controller.lc;

import com.ftzp.pojo.lc.Log;
import com.ftzp.service.lc.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {

    @Resource(name = "logService")
    LogService logService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<Log> getLog() {
        return logService.getLog();
    }

}
