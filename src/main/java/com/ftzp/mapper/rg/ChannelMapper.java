package com.ftzp.mapper.rg;

import com.ftzp.pojo.rg.Channel;

import java.util.List;

public interface ChannelMapper {
    List<Channel> findChannels();

    Integer addChannel(Channel channel);

    Integer updateChannel(Channel channel);

    boolean delChannel(Integer channelId);

    Channel findChannelById(Integer channelId);
}
