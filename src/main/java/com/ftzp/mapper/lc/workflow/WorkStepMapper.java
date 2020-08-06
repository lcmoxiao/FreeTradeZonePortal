package com.ftzp.mapper.lc.workflow;

import com.ftzp.pojo.lc.WorkStep;

import java.util.List;

public interface WorkStepMapper {

    List<WorkStep> getWorkStep(int wfId);

    void insertWorkStep(WorkStep workStep);

    void deleteWorkStep(Integer wfId);
}