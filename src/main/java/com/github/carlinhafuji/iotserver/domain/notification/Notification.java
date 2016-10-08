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

    public String title() {
        return title;
    }

    public String body() {
        return body;
    }

    public Mobile recipient() {
        return recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        if (!title.equals(that.title)) return false;
        if (!body.equals(that.body)) return false;
        return recipient.equals(that.recipient);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + recipient.hashCode();
        return result;
    }
}
