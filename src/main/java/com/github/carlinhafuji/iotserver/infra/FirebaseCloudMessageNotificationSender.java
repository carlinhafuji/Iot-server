package com.github.carlinhafuji.iotserver.infra;

import com.github.carlinhafuji.iotserver.domain.notification.Notification;
import com.github.carlinhafuji.iotserver.domain.notification.NotificationSender;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class FirebaseCloudMessageNotificationSender implements NotificationSender {

    public String url() {
        return System.getenv("FCM_URL");
    }

    public String apiKey() {
        return System.getenv("FCM_API_KEY");
    }

    @Override
    public void send(Notification notification) {
        try {
            URL url = new URL(url());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + apiKey());
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream outputStream = conn.getOutputStream();
            String payload = getPayload(notification);
            System.out.println("Sending message --> " + payload);
            outputStream.write(payload.getBytes());

            /*TODO Implement ExponencialBackoff*/
            /*TODO Validate if message was sent successfully*/
            InputStream inputStream = conn.getInputStream();
            final String resp = convertStreamToString(inputStream);
            System.out.println(resp);
            /* --- */
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next().replace(",", ",\n") : "";
    }

    private String getPayload(Notification notification) {
        JSONObject payload = new JSONObject();
        payload.put("priority", "high");

        JSONObject jNotification = new JSONObject();
        jNotification.put("title", notification.title());
        jNotification.put("body", notification.body());
        jNotification.put("sound", "default");
        jNotification.put("badge", "1");
        payload.put("notification", jNotification);

        JSONArray tokens = new JSONArray();
        tokens.put(notification.recipient().token());
        payload.put("registration_ids", tokens);

        JSONObject jData = new JSONObject();
        payload.put("data", jData);

        return payload.toString();
    }
}
