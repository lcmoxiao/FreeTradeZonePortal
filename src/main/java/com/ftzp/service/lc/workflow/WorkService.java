package com.ftzp.service.lc.workflow;

import com.ftzp.mapper.lc.workflow.WorkMapper;
import com.ftzp.pojo.lc.workflow.Work;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ftzp.TimeUtils.nowTime;

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
    public void insertWork(Work w) {
        w.setwPostTime(nowTime());
        w.setwLastDoTime(nowTime());
        workMapper.insertWork(w);
    }

    @Override
    public void deleteWork(Work work) {
        workMapper.deleteWork(work);
    }

    @Override
    public void updateWork(Work w) {
        w.setwLastDoTime(nowTime());
        workMapper.updateWork(w);
    }
}
