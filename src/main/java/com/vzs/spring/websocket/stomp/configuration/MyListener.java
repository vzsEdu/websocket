package com.vzs.spring.websocket.stomp.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class MyListener {
    @EventListener(SessionConnectEvent.class)
    public void handleWebsocketConnectListner(SessionConnectEvent event) {
        Message msg = event.getMessage();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(msg);
        String sessionId = accessor.getSessionId();
        log.info("Received a new web socket connection : " + sessionId);
    }

    @EventListener(SessionDisconnectEvent.class)
    public void handleWebsocketDisconnectListner(SessionDisconnectEvent event) {
        Message msg = event.getMessage();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(msg);
        String sessionId = accessor.getSessionId();
        log.info("session closed : " + sessionId);
    }
}
