package com.microblogging.microblogging.mastodon;

import com.google.gson.Gson;
import com.microblogging.microblogging.dto.Message;
import com.microblogging.microblogging.service.MessageService;
import com.sys1yagi.mastodon4j.MastodonClient;
import com.sys1yagi.mastodon4j.api.Handler;
import com.sys1yagi.mastodon4j.api.Shutdownable;
import com.sys1yagi.mastodon4j.api.entity.Notification;
import com.sys1yagi.mastodon4j.api.entity.Status;
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException;
import com.sys1yagi.mastodon4j.api.method.Streaming;
import jakarta.annotation.PreDestroy;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class for streaming Mastodon notifications and statuses.
 */
@Service
public class MastodonStreamService {

    private static final Logger LOG = LoggerFactory.getLogger(MastodonStreamService.class);
    private Shutdownable shutdownable;

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    /**
     * Constructs a MastodonStreamService with the provided instance URL and access token.
     *
     * @param instanceUrl       the URL of the Mastodon instance
     * @param accessToken       the access token for authentication
     * @param messagingTemplate messaging Template to send message to websocket
     * @param messageService
     */
    public MastodonStreamService(@Value("${mastodon.instanceUrl}") String instanceUrl,
                                 @Value("${mastodon.accessToken}") String accessToken,
                                 SimpMessagingTemplate messagingTemplate,
                                 MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        MastodonClient mastodonClient = new MastodonClient.Builder(instanceUrl, new OkHttpClient.Builder(), new Gson())
                .accessToken(accessToken)
                .useStreamingApi()
                .build();

        startStream(mastodonClient);
    }

    private void startStream(MastodonClient mastodonClient) {

        Streaming streaming = new Streaming(mastodonClient);

        Handler handler = new Handler() {
            @Override
            public void onNotification(@NotNull Notification notification) {

            }

            @Override
            public void onDelete(long l) {

            }

            @Override
            public void onStatus(Status status) {
                LOG.info("@" + status.getAccount().getUserName() + ": " + status.getContent());
                Message message = new Message(status.getAccount().getUserName(), status.getContent(), "Mastodon");
                messagingTemplate.convertAndSend("/receive/message", message);
                messageService.saveMessage(message);
            }

        };

        try {
            shutdownable = streaming.user(handler);
        } catch (Mastodon4jRequestException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Shuts down the Mastodon stream.
     */
    @PreDestroy
    public void shutdown() {
        if (shutdownable != null) {
            shutdownable.shutdown();
        }
    }
}
