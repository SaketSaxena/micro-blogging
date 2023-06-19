package com.microblogging.microblogging.dto;

import java.util.Objects;

/**
 * Represents a message with author, content, and source information.
 * This is an immutable record class.
 *
 * @param author  the author of the message
 * @param message the content of the message
 * @param source  the source of the message
 */
public record Message(String author, String message, String source) {

    /**
     * Constructs a new Message with the specified author, message, and source.
     *
     * @param author  the author of the message
     * @param message the content of the message
     * @param source  the source of the message
     * @throws NullPointerException if any of the arguments (author, message, source) is null
     */
    public Message {
        Objects.requireNonNull(author, "Author must not be null");
        Objects.requireNonNull(message, "Message must not be null");
        Objects.requireNonNull(source, "Source must not be null");
    }
}
