package com.ftzp.service.rg;

import com.ftzp.mapper.rg.ContentMapper;
import com.ftzp.pojo.rg.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Autowired
    private ContentMapper contentMapper;


    public List<Content> getContents() {
        List<Content> contents = contentMapper.findContents();
        return contents;
    }

    public List<Content> getContentss() {
        List<Content> contents = contentMapper.findContentss();
        return contents;
    }

    public boolean delContent(Integer contentId) {
        boolean flag = contentMapper.delContent(contentId);
        return flag;
    }

    public Integer updateContent(Content content) {
        Integer flag = contentMapper.updateContent(content);
        return flag;
    }

    public Content getContentById(Integer contentId) {
        Content content = contentMapper.findContentById(contentId);
        return content;
    }

    public Integer addContent(Content content) {
        Integer flag = contentMapper.addContent(content);
        return flag;
    }

    public void addSum(Content content) {
        contentMapper.addSum(content);
    }

    public List<Content> getContentsByMess(String messages) {
        List<Content> contents = contentMapper.findContentsByMess(messages);
        return contents;
    }

}
 