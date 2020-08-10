package com.ftzp.service.lc;

import com.ftzp.mapper.lc.workflow.WorkFlowMapper;
import com.ftzp.pojo.lc.WorkFlow;
import org.springframework.beans.factory.annotation.Autowired;
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
