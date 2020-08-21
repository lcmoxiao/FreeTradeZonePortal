package com.ftzp.mapper.lc.work;

import com.ftzp.pojo.lc.workflow.WorkStep;

import java.util.List;

public interface WorkStepMapper {

    List<WorkStep> getWorkStepByWfId(int wfId);

    void insertWorkStep(WorkStep workStep);

    void updateWorkStep(WorkStep workStep);

    void deleteWorkStepByWfId(Integer wfId);

    void deleteWorkStep(Integer wsId);
}