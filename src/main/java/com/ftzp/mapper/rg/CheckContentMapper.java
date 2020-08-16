package com.ftzp.mapper.rg;

import com.ftzp.pojo.rg.Content;

import java.util.List;

public interface CheckContentMapper {
    List<Content> findCheckContent(Integer contentId);

    Integer addCheckContent(Content checkContent);

    boolean delCheckContent(Integer contentId);



}
