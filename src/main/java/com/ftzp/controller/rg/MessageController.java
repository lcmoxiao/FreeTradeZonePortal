package com.ftzp.controller.rg;

import com.ftzp.pojo.rg.Message;
import com.ftzp.service.rg.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.ftzp.TimeUtils.nowTime;
import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/webContent")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @ResponseBody
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public List<Message> message(Map<String, Object> map) {
        List<Message> message = messageService.getMessage();
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/message/{messageId}", method = RequestMethod.DELETE)
    public String delContent(@PathVariable("messageId") Integer messageId) {
        boolean isDel = messageService.delMessage(messageId);
        if (isDel) {
            return "success";
        } else {
            return "failure";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/message/{messageId}", method = RequestMethod.POST)
    public String updateContent(Message message, @PathVariable("messageId") Integer messageId) {
        message.setMessageId(messageId);
        Integer isUpd = messageService.updateMessage(message);
        if (isUpd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @ResponseBody
    public String addContent(Message message, HttpServletRequest re) {
        message.setSubmitUserIp(getRemoteIP(re));
        message.setSubmitTime(nowTime());
        Integer isAdd = messageService.addMessage(message);
        if (isAdd == 1) {
            return "success";
        } else {
            return "failure";
        }
    }
}
