package com.ftzp.controller.rg;

import com.ftzp.pojo.rg.Channel;
import com.ftzp.service.rg.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = "/channels", method = RequestMethod.GET)
    @ResponseBody
    public List<Channel> channels(Map<String, Object> map) {
        List<Channel> channels = channelService.getChannels();
        System.out.println(channels);
        return channels;
    }


    @ResponseBody
    @RequestMapping(value = "/channel/{channelId}", method = RequestMethod.DELETE)
    public String delChannel(@PathVariable("channelId") Integer channelId) {
        boolean isDel = channelService.delChannel(channelId);
        if (isDel) {
            return "success";
        } else {
            return "failure";
        }
    }


    @ResponseBody
    @RequestMapping(value = "/channel/{channelId}", method = RequestMethod.POST)
    public String updateChannel(Channel channel, @PathVariable("channelId") Integer channelId) {
        channel.setChannelId(channelId);
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
    public String addChannel(Channel channel) {
        Integer isAdd = channelService.addChannel(channel);
        if (isAdd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }
}
