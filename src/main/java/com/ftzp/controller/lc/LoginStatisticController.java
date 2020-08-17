package com.ftzp.controller.lc;

import com.ftzp.pojo.lc.LoginStatistic;
import com.ftzp.service.lc.LoginStatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequestMapping("/statistic")
@Controller
public class LoginStatisticController {

    @Resource(name = "loginStatisticService")
    LoginStatisticService loginStatisticService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<LoginStatistic> getLoginStatistic(
            @RequestParam(value = "start", required = false) Date start
            , @RequestParam(value = "end", required = false) Date end) {

        return loginStatisticService.getStatistic(start, end);
    }

    @RequestMapping(value = "/getDays", method = RequestMethod.GET)
    @ResponseBody
    List<LoginStatistic> getLoginStatisticToDayNum(@RequestParam(value = "model", required = false) String model) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, +1);
        Date end = c.getTime();
        Date start;
        String dataFormat;

        if (model == null || model.equals("day")) {
            dataFormat = "'%Y-%m-%d %H:00:00'";
            c.add(Calendar.DATE, -1);
            start = c.getTime();
        } else if (model.equals("sevenDay")) {
            dataFormat = "'%Y-%m-%d 00:00:00'";
            c.add(Calendar.DATE, -7);
            start = c.getTime();
        } else if (model.equals("month")) {
            dataFormat = "'%Y-%m-%d 00:00:00'";
            c.add(Calendar.DATE, -30);
            start = c.getTime();
        } else {
            dataFormat = "'%Y-%m'";
            c.add(Calendar.YEAR, -1);
            start = c.getTime();
        }

        return loginStatisticService.getLoginStatisticToDayNum(dataFormat, start, end);
    }

}
