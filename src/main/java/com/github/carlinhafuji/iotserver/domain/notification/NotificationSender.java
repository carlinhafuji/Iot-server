package com.github.carlinhafuji.iotserver.domain.notification;

import org.springframework.stereotype.Service;

@Service
public interface NotificationSender {
    void send(Notification notification);
}
