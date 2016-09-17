package com.github.carlinhafuji.iotserver.domain.notification;

public interface NotificationSender {
    void send(Notification notification);
}
