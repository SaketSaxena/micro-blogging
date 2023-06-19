package com.microblogging.microblogging.controller;

import com.microblogging.microblogging.dto.Message;
import com.microblogging.microblogging.dto.MessageResponse;
import com.microblogging.microblogging.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessagesApiControllerTest {

    @Test
    void getMessages_ReturnsResponseWithMessages_Success() {
        MessageService messageService = mock(MessageService.class);
        MessagesApiController messagesApiController = new MessagesApiController(messageService);

        List<Message> messages = Arrays.asList(
                new Message("John Doe", "Hello world!", "Source1"),
                new Message("Jane Smith", "Hi there!", "Source2")
        );
        when(messageService.getMessages()).thenReturn(messages);

        ResponseEntity<MessageResponse> response = messagesApiController.getMessages();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessages()).isEqualTo(messages);
    }
}
