package com.ftzp.service.rg;

import com.ftzp.mapper.rg.MessageMapper;
import com.ftzp.pojo.rg.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;


    public List<Message> getMessage() {
        List<Message> message = messageMapper.findMessage();
        return message;
    }

    public boolean delMessage(Integer messageId) {
        boolean flag = messageMapper.delMessage(messageId);
        return flag;
    }

    public Integer updateMessage(Message message) {
        Integer flag = messageMapper.updateMessage(message);
        return flag;
    }

    public Integer addMessage(Message message) {
        Integer flag = messageMapper.addMessage(message);
        return flag;
    }


}
