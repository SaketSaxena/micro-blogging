package com.microblogging.microblogging.service;

import com.microblogging.microblogging.dto.Message;
import com.microblogging.microblogging.entity.Messages;
import com.microblogging.microblogging.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing messages.
 * This class handles the logic for saving messages in the database.
 */
@Service
public class MessageService {
    private final MessageRepository messageRepository;

    /**
     * Constructs a new MessageService with the specified MessageRepository.
     *
     * @param messageRepository the repository for managing Message entities
     */
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Saves a message by mapping it to the corresponding entity and persisting it in the database.
     *
     * @param message the message to be saved
     */
    public void saveMessage(Message message) {
        messageRepository.save(mapToEntity(message));
    }

    /**
     * Maps a Message object to the corresponding entity object.
     *
     * @param message the message to be mapped
     * @return the mapped entity object
     */
    private Messages mapToEntity(Message message) {
        return new Messages(message.author(), message.message(), message.source());
    }

    /**
     * Retrieves a list of messages from the repository and maps them to a list of Message objects.
     *
     * @return a list of Message objects
     */
    public List<Message> getMessages() {
        return messageRepository.findAll().stream()
                .map(messages -> new Message(messages.getAuthor(), messages.getMessage(), messages.getSource()))
                .collect(Collectors.toList());
    }
}
