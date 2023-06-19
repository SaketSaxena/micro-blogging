package com.microblogging.microblogging.service;


import com.microblogging.microblogging.dto.Message;
import com.microblogging.microblogging.entity.Messages;
import com.microblogging.microblogging.repository.MessageRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    public void should_save_messages() {
        Message message = new Message("John Doe", "Hello world!", "Source");

        messageService.saveMessage(message);

        verify(messageRepository).save(any(Messages.class));
    }

    @Test
    public void should_get_messages() {
        // Arrange
        List<Messages> entities = Arrays.asList(
                new Messages("John Doe", "Hello world!", "Source1"),
                new Messages("Jane Smith", "Hi there!", "Source2")
        );
        when(messageRepository.findAll()).thenReturn(entities);

        // Act
        List<Message> messages = messageService.getMessages();

        // Assert
        assertThat(messages.size()).isEqualTo(entities.size());
        assertThat(messages)
                .extracting("author", "message", "source")
                .contains(Tuple.tuple("John Doe", "Hello world!", "Source1"),
                        Tuple.tuple("Jane Smith", "Hi there!", "Source2"));
    }
}
