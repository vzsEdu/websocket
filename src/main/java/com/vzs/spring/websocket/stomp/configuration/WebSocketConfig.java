package com.vzs.spring.websocket.stomp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Value("${stomp.mq.addresses}")
    private String mqAddresses;
    @Value("${stomp.mq.port}")
    private int mqPort;
    @Value("${stomp.mq.login}")
    private String login;
    @Value("${stomp.mq.passcode}")
    private String passcode;


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableStompBrokerRelay("/topic")
                .setRelayHost(mqAddresses)
                .setRelayPort(mqPort)
                .setSystemLogin(login)
                .setSystemPasscode(passcode)
                .setClientLogin(login)
                .setClientPasscode(passcode);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
        registry.addEndpoint("/gs-guide-websocket");
    }

}