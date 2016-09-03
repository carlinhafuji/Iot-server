package com.github.carlinhafuji.iotserver.domain.notification;

import com.github.carlinhafuji.iotserver.domain.Mobile;

public class Notification {

    private String title;
    private String body;
    private Mobile recipient;

    public Notification(String title, String body, Mobile recipient) {
        this.body = body;
        this.title = title;
        this.recipient = recipient;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Mobile getRecipient() {
        return recipient;
    }
}
