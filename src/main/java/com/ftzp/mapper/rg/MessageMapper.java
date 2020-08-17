package com.ftzp.mapper.rg;

import com.ftzp.pojo.rg.Message;

import java.util.List;

public interface MessageMapper {

    List<Message> findMessage();

    Integer addMessage(Message message);

    boolean delMessage(Integer messageId);

    Integer updateMessage(Message message);
}
