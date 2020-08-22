package com.ftzp.controller.rg;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.pojo.rg.Channel;


import com.ftzp.service.rg.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/webContent")
public class ChannelController {

    private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);

    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;
    @Resource
    ChannelService channelService;


    @RequestMapping(value = "/channels", method = RequestMethod.GET)
    @ResponseBody
    public List<Channel> channels(HttpServletRequest request) {
        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        System.out.println(u.getuId());
        List<Integer> channelId = channelService.findChannelIdsByUid(u.getuId());
        List<Channel> channels = channelService.getChannels(channelId);
        System.out.println(channels);
        return channels;
    }


    @ResponseBody
    @RequestMapping(value = "/channel/{channelId}", method = RequestMethod.DELETE)
    public String delChannel(@PathVariable("channelId") Integer channelId, HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "删除了频道channelId：" + channelId);

        boolean isDel = channelService.delChannel(channelId);
        if (isDel) {
            return "success";
        } else {
            return "failure";
        }
    }


    @ResponseBody
    @RequestMapping(value = "/channel/{channelId}", method = RequestMethod.POST)
    public String updateChannel(Channel channel, @PathVariable("channelId") Integer channelId, HttpServletRequest request) {
        channel.setChannelId(channelId);

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "更新了频道channelId：" + channelId);

        Integer isUpd = channelService.updateChannel(channel);
        if (isUpd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    @RequestMapping(value = "/channel/{channelId}", method = RequestMethod.GET)
    @ResponseBody
    public Channel getChannelById(@PathVariable("channelId") Integer channelId) {

        Channel channel = channelService.getChannelById(channelId);
        return channel;
    }

    @RequestMapping(value = "channel", method = RequestMethod.POST)
    @ResponseBody
    public String addChannel(Channel channel, HttpServletRequest request) {

        User u = (User) redisObjCache.getValue(getRemoteIP(request) + "u");
        logger.info(u.getuName() + "增加了频道channelId：" + channel.getChannelId());

        Integer isAdd = channelService.addChannel(channel);
        if (isAdd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }
}
