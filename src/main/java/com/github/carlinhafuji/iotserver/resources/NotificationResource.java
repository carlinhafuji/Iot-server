package com.github.carlinhafuji.iotserver.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "notification")
public class NotificationResource {

    //NotificationRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public void add() {

    }
}
