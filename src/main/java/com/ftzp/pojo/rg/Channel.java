package com.ftzp.pojo.rg;

public class Channel {
    private Integer channelId;
    private String channelName;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelName=" + channelName +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
