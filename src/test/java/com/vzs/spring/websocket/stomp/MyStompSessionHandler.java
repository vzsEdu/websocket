package com.vzs.spring.websocket.stomp;

import com.vzs.spring.websocket.stomp.dto.Greeting;
import com.vzs.spring.websocket.stomp.dto.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

@Slf4j
public class MyStompSessionHandler implements StompSessionHandler {
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Client connection complete");
        session.subscribe("/topic/greetings", this);
        session.send("/app/hello", getSampleMessage());
    }

    private HelloMessage getSampleMessage() {
        HelloMessage helloMessage = new HelloMessage();
        helloMessage.setName("Hello Socket");
        return helloMessage;
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Got an exception", exception);
        log.error(exception.getMessage());
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {

    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Greeting msg = (Greeting)payload;
        log.info("Greeting message {}", msg);

    }
}
