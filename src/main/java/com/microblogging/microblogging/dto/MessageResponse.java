package com.microblogging.microblogging.dto;

import java.util.List;

/**
 * Represents a response object containing a list of messages.
 * This class is used to wrap the messages in an API response.
 */
public class MessageResponse {

    private List<Message> messages;

    /**
     * Constructs a new MessageResponse with the specified list of messages.
     *
     * @param messages the list of messages
     */
    public MessageResponse(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Retrieves the list of messages.
     *
     * @return the list of messages
     */
    public List<Message> getMessages() {
        return messages;
    }
}
