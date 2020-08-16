package com.ftzp.controller.rg;

import com.ftzp.pojo.rg.Content;
import com.ftzp.service.rg.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    @ResponseBody
    @RequestMapping(value = "/contents", method = RequestMethod.GET)
    public List<Content> contents(Map<String, Object> map) {

        List<Content> contents = contentService.getContents();
        System.out.println(contents);
        return contents;
    }

    @ResponseBody
    @RequestMapping(value = "/content/{contentId}", method = RequestMethod.DELETE)
    public String delContent(@PathVariable("contentId") Integer contentID) {
        boolean isDel = contentService.delContent(contentID);
        if (isDel) {
            return "success";
        } else {
            return "failure";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/content/{contentId}", method = RequestMethod.POST)
    public String updateContent(Content content, @PathVariable("contentId") Integer contentID) {
        content.setContentId(contentID);
        Integer isUpd = contentService.updateContent(content);
        if (isUpd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/contentss/{contentId}", method = RequestMethod.POST)
    public void addSum(Content content, @PathVariable("contentId") Integer contentID) {
        content.setContentId(contentID);

        contentService.addSum(content);
    }

    @RequestMapping(value = "/content/{contentId}", method = RequestMethod.GET)
    @ResponseBody
    public Content getContentById(@PathVariable("contentId") Integer contentId) {
        Content content = contentService.getContentById(contentId);
        return content;
    }

    @RequestMapping(value = "content", method = RequestMethod.POST)
    @ResponseBody
    public String addContent(Content content) {
        Integer isAdd = contentService.addContent(content);
        if (isAdd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    @RequestMapping(value = "/visit/content", method = RequestMethod.GET)
    @ResponseBody
    public List<Content> VisitContent(Content content) {
        List<Content> contents = contentService.getContentss();
        System.out.println(contents);
        return contents;
    }

    @ResponseBody
    @RequestMapping(value = "/contentss/{messages}", method = RequestMethod.GET)
    public List<Content> contentByMess(Map<String, Object> map,@PathVariable("messages") String messages) {
        List<Content> contents = contentService.getContentsByMess(messages);
        return contents;
    }

}