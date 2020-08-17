package com.ftzp.controller.rg;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.pojo.rg.Columns;
import com.ftzp.service.rg.ColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/webContent")
public class ColumnController {

    private static final Logger logger = LoggerFactory.getLogger(ColumnController.class);
    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;

    @Autowired
    private ColumnService columnService;

    @ResponseBody
    @RequestMapping(value = "/columns", method = RequestMethod.GET)
    public List<Columns> columns(Map<String, Object> map) {
        List<Columns> columns = columnService.getColumns();
        System.out.println(columns);
        return columns;
    }

    @ResponseBody
    @RequestMapping(value = "/columns/{columnId}", method = RequestMethod.DELETE)
    public String delColumns(@PathVariable("columnId") Integer columnId, HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "删除了栏目columnId：" + columnId);

        boolean isDel = columnService.delColumns(columnId);
        if (isDel) {
            return "success";
        } else {
            return "failure";
        }
    }


    @ResponseBody
    @RequestMapping(value = "/columns/{columnId}", method = RequestMethod.POST)
    public String updateColumns(Columns columns, @PathVariable("columnId") Integer columnId,HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "更新了栏目columnId：" + columnId);

        columns.setColumnId(columnId);
        Integer isUpd = columnService.updateColumns(columns);
        if (isUpd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    @RequestMapping(value = "/columns/{columnId}", method = RequestMethod.GET)
    @ResponseBody
    public Columns getColumnsById(@PathVariable("columnId") Integer columnId) {

        Columns columns = columnService.getColumnsById(columnId);
        return columns;
    }

    @RequestMapping(value = "columns", method = RequestMethod.POST)
    @ResponseBody
    public String addContent(Columns columns,HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "增添了栏目columnId：" + columns.getColumnId());

        Integer isAdd = columnService.addColumns(columns);
        if (isAdd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }
}
