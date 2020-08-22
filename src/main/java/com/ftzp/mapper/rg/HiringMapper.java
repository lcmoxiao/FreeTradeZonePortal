package com.ftzp.mapper.rg;

import com.ftzp.pojo.rg.Columns;
import com.ftzp.pojo.rg.Hiring;

import java.util.List;

public interface HiringMapper {
    List<Hiring> findHiring();

    boolean delHiring(Integer hiringId);

    Integer addHiring(Hiring hiring);

    Hiring findHiringById(Integer hiringId);
}
