package com.ftzp.service.rg;

import com.ftzp.mapper.rg.ColumnsMapper;
import com.ftzp.pojo.rg.Columns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService {
    @Autowired
    private ColumnsMapper columnMapper;

    public List<Columns> getColumns() {
        List<Columns> columns = columnMapper.findColumns();
        return columns;
    }

    public boolean delColumns(Integer columnsId) {
        boolean flag = columnMapper.delColumns(columnsId);
        return flag;
    }

    public Integer updateColumns(Columns columns) {
        Integer flag = columnMapper.updateColumns(columns);
        return flag;
    }

    public Columns getColumnsById(Integer columnsId) {
        Columns columns = columnMapper.findColumnsById(columnsId);
        return columns;
    }

    public Integer addColumns(Columns columns) {
        Integer flag = columnMapper.addColumns(columns);
        return flag;
    }
}
