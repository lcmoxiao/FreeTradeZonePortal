package com.ftzp.mapper.lc.workflow;

import com.ftzp.pojo.lc.workflow.WorkFlow;

import java.util.List;

public interface WorkFlowMapper {

    List<WorkFlow> findWorkFlow(Integer wfId);

    void insertWorkFlow(WorkFlow workFlow);

    void deleteWorkFlow(Integer wfId);

    void updateWorkFlow(WorkFlow workFlow);
}