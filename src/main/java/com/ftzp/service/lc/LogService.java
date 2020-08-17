package com.ftzp.service.lc;

import com.ftzp.mapper.lc.LogMapper;
import com.ftzp.pojo.lc.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("logService")
public class LogService {

    @Resource
    LogMapper logMapper;

    public List<Log> getLog() {
        return logMapper.getLog();
    }

}
