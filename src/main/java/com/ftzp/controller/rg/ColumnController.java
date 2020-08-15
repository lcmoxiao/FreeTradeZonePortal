package com.ftzp.controller.rg;

import com.ftzp.pojo.rg.Columns;
import com.ftzp.service.rg.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ColumnController {
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
    public String delColumns(@PathVariable("columnId") Integer columnId) {
        boolean isDel = columnService.delColumns(columnId);
        if (isDel) {
            return "success";
        } else {
            return "failure";
        }
    }


    @ResponseBody
    @RequestMapping(value = "/columns/{columnId}", method = RequestMethod.POST)
    public String updateColumns(Columns columns, @PathVariable("columnId") Integer columnId) {
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
    public String addContent(Columns columns) {
        Integer isAdd = columnService.addColumns(columns);
        if (isAdd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }
}
