package org.xiqi.notifyme.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.xiqi.notifyme.domain.NotifyMe;
import org.xiqi.notifyme.domain.PushDo;

@Slf4j
@Component
public class PushUtil {

    public void sendRequest(PushDo pushDo, NotifyMe setting) {
        String template = setting.getPublishUrl();
        if (template == null || template.isBlank()) {
            log.warn("publishUrl 为空，跳过推送");
            return;
        }

        String url = template
            .replace("{apiKey}", val(setting.getApiKey()))
            .replace("{title}", val(pushDo.getTitle()))
            .replace("{content}", val(pushDo.getContent()))
            .replace("{type}", "markdown")
            .replace("{extValue}", val(setting.getExtValue()));

        log.info("推送请求: {}", url);

        WebClient.create(url)
            .get()
            .retrieve()
            .bodyToMono(String.class)
            .doOnSuccess(response -> log.info("推送响应: {}", response))
            .doOnError(error -> log.error("推送失败: {}", error.getMessage()))
            .subscribe();
    }

    private static String val(String s) {
        return s == null ? "" : s;
    }
}
