package com.ftzp.mapper.lc.work;

import com.ftzp.pojo.lc.workflow.WorkStep;

import java.util.List;

public interface WorkStepMapper {

    List<WorkStep> getWorkStep(int wfId);

    void insertWorkStep(WorkStep workStep);

    void deleteWorkStep(Integer wfId);
}