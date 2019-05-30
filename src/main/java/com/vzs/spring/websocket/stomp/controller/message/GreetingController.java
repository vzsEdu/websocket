package com.vzs.spring.websocket.stomp.controller.message;

import com.vzs.spring.websocket.stomp.dto.Greeting;
import com.vzs.spring.websocket.stomp.dto.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import static com.vzs.spring.websocket.stomp.controller.message.MessageConstant.TOPIC_NAME;


@Controller
@Slf4j
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo(TOPIC_NAME)
    public Greeting greeting(SimpMessageHeaderAccessor simpMessageHeaderAccessor, HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        log.info("handle hello topic with session id {} sessionId {}", message.getId(), simpMessageHeaderAccessor.getSessionId());
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
