package com.ftzp.service.lc.work;

import com.ftzp.mapper.lc.work.WorkFlowMapper;
import com.ftzp.pojo.lc.workflow.WorkFlow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("workFlowService")
public class WorkFlowService implements WorkFlowMapper {

    @Resource
    WorkFlowMapper workFlowMapper;

    @Override
    public  List<WorkFlow> findWorkFlow(Integer wfId) {
        return workFlowMapper.findWorkFlow(wfId);
    }

    @Override
    public void insertWorkFlow(WorkFlow workFlow) {
        workFlowMapper.insertWorkFlow(workFlow);
    }

    @Override
    public void deleteWorkFlow(Integer wfId) {
        workFlowMapper.deleteWorkFlow(wfId);
    }

    @Override
    public void updateWorkFlow(WorkFlow workFlow) {
        workFlowMapper.updateWorkFlow(workFlow);
    }
}
