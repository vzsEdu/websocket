package com.vzs.spring.websocket.stomp;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Scanner;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class WebSocketConnectCountTest {
    private static final int LOOP = 2;

    @Test
    public void testSocketCount() {
        for (int i = 0 ; i < LOOP ; i++) {
            new WebSocketClientTest().startWebSocketClient();
        }
        new Scanner(System.in).nextLine(); // Don't close immediately.
    }
}
