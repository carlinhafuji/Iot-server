package com.github.carlinhafuji.iotserver.resources;

import com.github.carlinhafuji.iotserver.domain.entity.Notification;
import com.github.carlinhafuji.iotserver.domain.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "notifications")
public class NotificationResource {

    private NotificationRepository repository;

    @Autowired
    public NotificationResource(NotificationRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Notification> get() {
        Notification n = new Notification("Notificação");
        return ResponseEntity.ok(n);
    }

    private NotificationRepository repository() {
        return repository;
    }
}
