package com.github.carlinhafuji.iotserver.infra;

import com.github.carlinhafuji.iotserver.domain.notification.NotificationSender;
import com.github.carlinhafuji.iotserver.domain.notification.Notification;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class FirebaseCloudMessageNotificationSender implements NotificationSender {

    private RestTemplate restTemplate;

    @Autowired
    public FirebaseCloudMessageNotificationSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate() {
        return restTemplate;
    }

    public String url() {
        return System.getenv("FCM_URL");
    }

    public String apiKey() {
        return System.getenv("FCM_API_KEY");
    }

    @Override
    public void send(Notification notification) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "key=" + apiKey());
        headers.put("Content-Type","application/json");

        restTemplate.postForEntity(url(), getPayload(notification), Object.class, headers);
    }

    private JSONObject getPayload(Notification notification) {
        JSONObject info = new JSONObject();
        info.put("title", notification.getTitle());
        info.put("body", notification.getBody());

        JSONObject payload = new JSONObject();
        payload.put("to", notification.getRecipient().getDeviceId());
        payload.put("notification", info);
        return payload;
    }
}
