package com.ftzp.pojo.rg;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Message {
    private Integer messageId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submitTime;
    private String messageContent;
    private String submitUserIp;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSubmitUserIp() {
        return submitUserIp;
    }

    public void setSubmitUserIp(String submitUserIp) {
        this.submitUserIp = submitUserIp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", submitTime=" + submitTime +
                ", messageContent='" + messageContent + '\'' +
                ", submitUserIp='" + submitUserIp + '\'' +
                '}';
    }
}
