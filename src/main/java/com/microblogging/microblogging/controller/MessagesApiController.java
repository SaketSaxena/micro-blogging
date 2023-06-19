package com.microblogging.microblogging.controller;

import com.microblogging.microblogging.dto.MessageResponse;
import com.microblogging.microblogging.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for managing messages API endpoints.
 * This class handles HTTP requests related to messages.
 */
@RestController
public class MessagesApiController {

    private final MessageService messageService;

    /**
     * Constructs a new MessagesApiController with the specified MessageService.
     *
     * @param messageService the service for managing messages
     */
    public MessagesApiController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Handles the HTTP GET request for retrieving messages.
     *
     * @return the ResponseEntity containing the message response
     */
    @GetMapping("/messages")
    public ResponseEntity<MessageResponse> getMessages() {
        return ResponseEntity.ok(new MessageResponse(messageService.getMessages()));
    }
}
