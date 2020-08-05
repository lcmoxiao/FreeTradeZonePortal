package com.ftzp.mapper.lc;

import com.ftzp.pojo.lc.WorkFlow;

import java.util.List;

public interface WorkFlowMapper {

    List<WorkFlow> findWorkFlow(Integer wfId);

    void insertWorkFlow(WorkFlow workFlow);

    void deleteWorkFlow(Integer wfId);

    void updateWorkFlow(WorkFlow workFlow);
}