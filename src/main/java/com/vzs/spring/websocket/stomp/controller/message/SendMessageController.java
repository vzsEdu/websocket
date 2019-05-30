package com.vzs.spring.websocket.stomp.controller.message;

import com.vzs.spring.websocket.stomp.dto.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.vzs.spring.websocket.stomp.controller.message.MessageConstant.TOPIC_NAME;

@RestController
@RequestMapping("/message")
public class SendMessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        simpMessagingTemplate.convertAndSend(TOPIC_NAME, new Greeting(message));
        return "Sent : " + message;
    }

}
