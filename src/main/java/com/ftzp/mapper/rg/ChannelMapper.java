package com.ftzp.mapper.rg;

import com.ftzp.pojo.rg.Channel;

import java.util.List;

public interface ChannelMapper {
    List<Channel> findChannels(List channelId);

    Integer addChannel(Channel channel);

    Integer updateChannel(Channel channel);

    boolean delChannel(Integer channelId);

    Channel findChannelById(Integer channelId);

    List<Integer> findChannelIdsByUid(Integer uId);
}
