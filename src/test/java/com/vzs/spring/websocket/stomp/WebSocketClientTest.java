package com.vzs.spring.websocket.stomp;

import com.vzs.spring.websocket.stomp.dto.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class WebSocketClientTest {
    private static final String URL = "ws://localhost:20001/gs-guide-websocket";
    @Test
    public void testSendMessage() throws InterruptedException, ExecutionException {
        ThreadPoolTaskScheduler te = new ThreadPoolTaskScheduler();
        te.setPoolSize(1);
        te.setThreadNamePrefix("wss-heartbeat-thread-");
        te.initialize();

        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setDefaultHeartbeat(new long[] {10000, 10000});
        stompClient.setTaskScheduler(te);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        ListenableFuture<StompSession> connect = stompClient.connect(URL, sessionHandler);
//        while (true) {
//            Thread.sleep(1000L);
//            connect.get().send("/app/hello", getSampleMessage());
//
//        }

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }


    private HelloMessage getSampleMessage() {
        HelloMessage helloMessage = new HelloMessage();
        helloMessage.setName("Hello Socket");
        helloMessage.setId(123L);
        return helloMessage;
    }

}
