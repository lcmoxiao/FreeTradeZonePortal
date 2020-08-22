package com.ftzp.service.rg;

import com.ftzp.mapper.rg.ChannelMapper;
import com.ftzp.pojo.rg.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    @Autowired
    private ChannelMapper channelMapper;

    public List<Channel> getChannels(List channelId) {
        List<Channel> channels = channelMapper.findChannels(channelId);
        return channels;
    }

    public boolean delChannel(Integer channelId) {
        boolean flag = channelMapper.delChannel(channelId);
        return flag;
    }

    public Integer updateChannel(Channel channel) {
        Integer flag = channelMapper.updateChannel(channel);
        return flag;
    }

    public Channel getChannelById(Integer channelId) {
        Channel channel = channelMapper.findChannelById(channelId);
        return channel;
    }

    public Integer addChannel(Channel channel) {
        Integer flag = channelMapper.addChannel(channel);
        return flag;
    }

    public List<Integer> findChannelIdsByUid(Integer uId){
        List<Integer> channelId=channelMapper.findChannelIdsByUid(uId);
        return channelId;
    }
}
