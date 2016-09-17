package com.github.carlinhafuji.iotserver.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.carlinhafuji.iotserver.domain.Thing;
import com.github.carlinhafuji.iotserver.domain.ThingRepository;
import com.github.carlinhafuji.iotserver.domain.notification.NotificationSender;

@Service
public class EventProcessor {

    private final ThingRepository thingRepository;
    private final NotificationSender notificationSender;

    @Autowired
    public EventProcessor(ThingRepository thingRepository, NotificationSender notificationSender) {
        this.thingRepository = thingRepository;
        this.notificationSender = notificationSender;
    }

    public void processor(EventData eventData) {
        System.out.println(eventData.getThingId() + " - " + String.join(", " , eventData.getParams().keySet()));

        Thing thing = thingRepository().findOne(eventData.getThingId());

        System.out.print(thing);
        //Notification n = new Notification("titulo", "corpo", thing.getOwner())
        //notificationSender().send(n);
    }
    
    private Map<String,String> thingMessage(Thing thing){
    	Map<String,String> msg = new HashMap<String,String>();
    	
    	return msg;
    }

    private ThingRepository thingRepository() {
        return thingRepository;
    }
    private NotificationSender notificationSender() {
        return notificationSender;
    }
}
