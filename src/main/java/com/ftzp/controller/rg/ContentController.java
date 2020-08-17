package com.ftzp.controller.rg;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.pojo.rg.Content;
import com.ftzp.service.rg.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/webContent")
public class ContentController {

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);
    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;
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
    public String delContent(@PathVariable("contentId") Integer contentID, HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "删除了内容contentID：" + contentID);

        boolean isDel = contentService.delContent(contentID);
        if (isDel) {
            return "success";
        } else {
            return "failure";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/content/{contentId}", method = RequestMethod.POST)
    public String updateContent(Content content, @PathVariable("contentId") Integer contentID,HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "更新了内容contentID：" + contentID);

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
    public void addSum(Content content, @PathVariable("contentId") Integer contentID,HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "增加了点击量contentID：" + contentID);

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
    public String addContent(Content content,HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "增加了点击量contentID：" + content.getContentId());

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
