package com.github.carlinhafuji.iotserver.application;

import com.github.carlinhafuji.iotserver.domain.Thing;
import com.github.carlinhafuji.iotserver.domain.ThingRepository;
import com.github.carlinhafuji.iotserver.domain.notification.Notification;
import com.github.carlinhafuji.iotserver.domain.notification.NotificationSender;
import org.springframework.stereotype.Service;

@Service
public class EventProcessor {

    private ThingRepository thingRepository;
    private NotificationSender notificationSender;

    public EventProcessor(ThingRepository thingRepository, NotificationSender notificationSender) {
        this.thingRepository = thingRepository;
        this.notificationSender = notificationSender;
    }

    public void processor(EventData eventData) {
        System.out.println(eventData.getThingId() + " - " + String.join(", " , eventData.getParams().keySet()));

        Thing thing = thingRepository().findOne(eventData.getThingId());

        //Notification n = new Notification("titulo", "corpo", thing.getOwner())
        //notificationSender().send(n);
    }

    private ThingRepository thingRepository() {
        return thingRepository;
    }
    private NotificationSender notificationSender() {
        return notificationSender;
    }
}
