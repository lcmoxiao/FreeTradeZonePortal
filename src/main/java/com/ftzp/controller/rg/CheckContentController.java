package com.ftzp.controller.rg;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.pojo.lc.workflow.Work;
import com.ftzp.pojo.rg.Content;
import com.ftzp.service.lc.work.WorkService;
import com.ftzp.service.rg.CheckContentService;
import com.ftzp.service.rg.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
public class CheckContentController {
    @Autowired
    private CheckContentService checkContentService;
    @Resource(name = "workService")
    WorkService workService;
    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;
    @Autowired
    ContentService contentService;

    @RequestMapping(value = "/checkContent", method = RequestMethod.POST)
    @ResponseBody
    public String addCheckContent(Content checkContent,HttpServletRequest request) {
        Integer isAdd = checkContentService.addCheckContent(checkContent);
        Work w = new Work();
        String IP = getRemoteIP(request);
        User u = (User) redisObjCache.getValue(IP + "u");
        //是前端所显示的需要审核的内容
        w.setwContent(checkContent.getContentName()+"\n"+checkContent.getContent());
        w.setuId(u.getuId());
        w.setWfId(27);
        w.setwLength(2);
        w.setWdesc("网页内容发布");
        w.setRanking(2);
        w.setwUnCheckedContentId(checkContent.getContentId());
        workService.insertWork(w);
        if (isAdd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/checkContent/{contentId}/{wId}", method = RequestMethod.POST)
    public String checkContent(@PathVariable("contentId") Integer contentID,
                               @PathVariable("wId") Integer wId) {
        Content checkContent = checkContentService.getCheckContent(contentID).get(0);
        checkContent.setFlagCheck(1);
        System.out.println(checkContent);
        checkContentService.delCheckContent(contentID);
        contentService.addContent(checkContent);
        Work w = new Work();
        w.setwId(wId);
        workService.deleteWork(w);
        boolean isDel = checkContentService.delCheckContent(contentID);
        return "fuck";
    }

}
