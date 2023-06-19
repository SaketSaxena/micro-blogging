package com.microblogging.microblogging.websocket;

import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

/**
 * Custom WebSocket handshake handler for clients.
 * Extends the DefaultHandshakeHandler class.
 */
public class ClientHandshakeHandler extends DefaultHandshakeHandler {

  private final Logger logger = LoggerFactory.getLogger(ClientHandshakeHandler.class);

  /**
   * Determines the user principal for the WebSocket handshake.
   *
   * @param req         the server HTTP request
   * @param weHandler   the WebSocket handler
   * @param attributes  the attributes associated with the WebSocket session
   * @return the user principal representing the client
   */
  @Override
  protected Principal determineUser(ServerHttpRequest req, WebSocketHandler weHandler, Map<String, Object> attributes) {
    final String randId = UUID.randomUUID().toString();
    logger.info("{}",attributes.get("name"));
    logger.info("User opened client unique ID {}, ipAddress {}",randId,req.getRemoteAddress());
    return new UserPrincipal(randId);
  }

}
