package com.ftzp.mapper.rg;

import com.ftzp.pojo.rg.Content;

import java.util.List;

public interface ContentMapper {
    List<Content> findContents();

    Content findContentById(Integer contentId);

    Integer addContent(Content content);

    boolean delContent(Integer contentId);

    Integer updateContent(Content content);

    void addSum(Content content);

    List<Content> findContentsByMess(String messages);

    List<Content> findContentss();
}
