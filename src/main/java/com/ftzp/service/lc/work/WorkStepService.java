package com.ftzp.service.lc.work;

import com.ftzp.mapper.lc.work.WorkStepMapper;
import com.ftzp.pojo.lc.workflow.WorkStep;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("workStepService")
public class WorkStepService implements WorkStepMapper {

    @Resource
    WorkStepMapper workStepMapper;

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