package org.xiqi.notifyme.listener;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xiqi.notifyme.domain.NotifyMe;
import org.xiqi.notifyme.event.NotifyBaseEvent;
import org.xiqi.notifyme.strategy.CommentStrategy;
import org.xiqi.notifyme.strategy.NotifyStrategy;
import org.xiqi.notifyme.strategy.PostStrategy;
import run.halo.app.core.extension.content.Comment;
import run.halo.app.core.extension.content.Post;
import run.halo.app.extension.ConfigMap;
import run.halo.app.extension.Extension;
import run.halo.app.extension.ReactiveExtensionClient;

import java.util.Optional;

@Slf4j
@Async
@Component
public class NotifyListener implements ApplicationListener<NotifyBaseEvent> {


    private final PostStrategy postStrategy;
    private final CommentStrategy commentStrategy;
    private final ReactiveExtensionClient client;
    private final ObjectMapper objectMapper;


    public NotifyListener(PostStrategy postStrategy, CommentStrategy commentStrategy,
                          ReactiveExtensionClient client) {
        this.postStrategy = postStrategy;
        this.commentStrategy = commentStrategy;
        this.client = client;
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public void onApplicationEvent(@NonNull NotifyBaseEvent event) {
        NotifyStrategy strategy = getStrategyForExtension(event.getExtension());
        if (strategy != null) {
            Optional<NotifyMe> config = readConfig();
            config.ifPresent(c -> strategy.process(event, c));
        }
    }

    private Optional<NotifyMe> readConfig() {
        return client.fetch(ConfigMap.class, "notifyme-config")
            .map(cm -> {
                String data = cm.getData().getOrDefault("default", "{}");
                try {
                    return objectMapper.readValue(data, NotifyMe.class);
                } catch (Exception e) {
                    log.error("Failed to parse config", e);
                    return new NotifyMe();
                }
            })
            .blockOptional();
    }

    private NotifyStrategy getStrategyForExtension(Extension extension) {
        if (extension instanceof Post) {
            return postStrategy;
        }
        if (extension instanceof Comment) {
            return commentStrategy;
        }
        return null;
    }

}
