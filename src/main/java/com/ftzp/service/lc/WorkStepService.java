package com.ftzp.service.lc;

import com.ftzp.mapper.lc.WorkStepMapper;
import com.ftzp.pojo.lc.WorkStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkStepService implements WorkStepMapper {

    WorkStepMapper workStepMapper;

    @Autowired
    public void setWorkStepMapper(WorkStepMapper workStepMapper) {
        this.workStepMapper = workStepMapper;
    }

    @Override
    public List<WorkStep> getWorkStep(int wfId) {
        return workStepMapper.getWorkStep(wfId);
    }

    @Override
    public void insertWorkStep(WorkStep workStep) {
        workStepMapper.insertWorkStep(workStep);
    }

    @Override
    public void deleteWorkStep(Integer wfId) {
        workStepMapper.deleteWorkStep(wfId);
    }


}
