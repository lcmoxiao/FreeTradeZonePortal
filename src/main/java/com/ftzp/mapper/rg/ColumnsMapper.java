package com.ftzp.mapper.rg;

import com.ftzp.pojo.rg.Columns;

import java.util.List;

public interface ColumnsMapper {
    List<Columns> findColumns();

    Integer addColumns(Columns column);

    Integer updateColumns(Columns column);

    boolean delColumns(Integer id);

    Columns findColumnsById(Integer columnId);
}
