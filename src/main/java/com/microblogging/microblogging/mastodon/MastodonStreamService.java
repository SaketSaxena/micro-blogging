package com.microblogging.microblogging.mastodon;

import com.google.gson.Gson;
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
import org.springframework.stereotype.Service;

@Service
public class MastodonStreamService {

    private static final Logger LOG = LoggerFactory.getLogger(MastodonStreamService.class);
    private Shutdownable shutdownable;

    public MastodonStreamService(@Value("${mastodon.instanceUrl}") String instanceUrl,
                                 @Value("${mastodon.accessToken}") String accessToken) {
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
            }

        };

        try {
            shutdownable = streaming.user(handler);
        } catch (Mastodon4jRequestException e) {
            throw new RuntimeException(e);
        }

    }

    @PreDestroy
    public void shutdown() {
        if (shutdownable != null) {
            shutdownable.shutdown();
        }
    }
}
