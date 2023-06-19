package com.microblogging.microblogging.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for WebSocket messaging.
 * Configures the message broker and Stomp endpoints.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WSConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * Configures the message broker registry.
   *
   * @param registry the message broker registry
   */
  @Override
  public void configureMessageBroker(final MessageBrokerRegistry registry){
    registry.enableSimpleBroker("/receive");
    registry.setApplicationDestinationPrefixes("/ws");
  }

  /**
   * Registers Stomp endpoints for WebSocket communication.
   *
   * @param registry the Stomp endpoint registry
   */
  @Override
  public void registerStompEndpoints(final StompEndpointRegistry registry) {
    registry.addEndpoint("/websocket")
        .setHandshakeHandler(new ClientHandshakeHandler())
        .withSockJS();
  }
}
