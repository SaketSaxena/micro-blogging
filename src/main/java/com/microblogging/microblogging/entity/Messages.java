package com.microblogging.microblogging.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a message entity with author, message, and source information.
 * This class is annotated with JPA annotations to be persisted in a database.
 */
@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String message;
    private String source;

    /**
     * Default constructor for the Messages entity.
     * Initializes the object with default values.
     */
    public Messages() {
    }

    /**
     * Constructs a new Messages entity with the specified author, message, and source.
     *
     * @param author  the author of the message
     * @param message the content of the message
     * @param source  the source of the message
     */
    public Messages(String author, String message, String source) {
        this.author = author;
        this.message = message;
        this.source = source;
    }

    /**
     * Retrieves the author of the message.
     *
     * @return the author of the message
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Retrieves the content of the message.
     *
     * @return the content of the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the source of the message.
     *
     * @return the source of the message
     */
    public String getSource() {
        return source;
    }
}