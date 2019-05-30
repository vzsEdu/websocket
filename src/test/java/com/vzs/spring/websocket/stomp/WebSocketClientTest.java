package com.vzs.spring.websocket.stomp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

@RunWith(MockitoJUnitRunner.class)
public class WebSocketClientTest {
    private static final String URL = "ws://localhost:20001/gs-guide-websocket";
    @Test
    public void testSendMessage() {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompClient.connect(URL, sessionHandler);

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }
}
