package com.ftzp.service.rg;

import com.ftzp.mapper.rg.CheckContentMapper;
import com.ftzp.pojo.rg.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckContentService {
    @Autowired
    private CheckContentMapper checkContentMapper;


    public List<Content> getCheckContent(Integer contentId) {
        List<Content> checkContent = checkContentMapper.findCheckContent(contentId);
        return checkContent;
    }

    public boolean delCheckContent(Integer contentId) {
        boolean flag = checkContentMapper.delCheckContent(contentId);
        return flag;
    }

    public Integer addCheckContent(Content checkContent) {
        Integer flag = checkContentMapper.addCheckContent(checkContent);
        return flag;
    }

}
