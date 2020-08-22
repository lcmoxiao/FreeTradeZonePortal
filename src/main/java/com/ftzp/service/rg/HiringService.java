package com.ftzp.service.rg;

import com.ftzp.mapper.rg.HiringMapper;

import com.ftzp.pojo.rg.Columns;
import com.ftzp.pojo.rg.Hiring;
import com.ftzp.pojo.rg.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiringService {
    @Autowired
    private HiringMapper hiringMapper;


    public List<Hiring> getHiring() {
        List<Hiring> hiring = hiringMapper.findHiring();
        return hiring;
    }

    public boolean delHiring(Integer hiringId) {
        boolean flag = hiringMapper.delHiring(hiringId);
        return flag;
    }

    public Integer addHiring(Hiring hiring) {
        Integer flag = hiringMapper.addHiring(hiring);
        return flag;
    }

    public Hiring getHiringById(Integer hiringId) {
        Hiring hiring = hiringMapper.findHiringById(hiringId);
        return hiring;
    }

}
