package com.vzs.spring.websocket.stomp.controller.message;

import com.vzs.spring.websocket.stomp.dto.Greeting;
import com.vzs.spring.websocket.stomp.dto.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import static com.vzs.spring.websocket.stomp.controller.message.MessageConstant.TOPIC_NAME;


@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo(TOPIC_NAME)
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
