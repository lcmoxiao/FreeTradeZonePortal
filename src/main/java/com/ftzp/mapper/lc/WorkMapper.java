package com.ftzp.mapper.lc;

import com.ftzp.pojo.lc.Work;

import java.util.List;

public interface WorkMapper {

    List<Work> getWork(Integer wId);

    List<Work> getWorkByUId(Integer uId);

    void insertWork(Work work);

    void deleteWork(Work work);

    void updateWork(Work w);
}