package com.ftzp.service.lc;

import com.ftzp.mapper.lc.workflow.WorkMapper;
import com.ftzp.pojo.lc.Work;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("workService")
public class WorkService implements WorkMapper {

    @Resource
    WorkMapper workMapper;

    @Override
    public List<Work> getWork(Integer wId) {
        return workMapper.getWork(wId);
    }

    @Override
    public List<Work> getWorkByUId(Integer uId) {
        return workMapper.getWorkByUId(uId);
    }

    @Override
    public void insertWork(Work work) {
        workMapper.insertWork(work);
    }

    @Override
    public void deleteWork(Work work) {
        workMapper.deleteWork(work);
    }

    @Override
    public void updateWork(Work w) {
        workMapper.updateWork(w);
    }
}
