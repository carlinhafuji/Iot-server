package com.github.carlinhafuji.iotserver.application;


import com.github.carlinhafuji.iotserver.domain.*;
import com.github.carlinhafuji.iotserver.domain.notification.Notification;
import com.github.carlinhafuji.iotserver.domain.notification.NotificationSender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;

public class EventProcessorTest {
    EventProcessor eventProcessor;

    @Mock
    ThingRepository thingRepository;
    @Mock
    NotificationSender notificationSender;

    @Captor
    ArgumentCaptor<Notification> notificationCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventProcessor = new EventProcessor(thingRepository, notificationSender);
    }

    @Test
    public void should_send_notification() {
        Thing thing = createThing("thing", ThingType.BALANCA);
        when(thingRepository.findOne(anyLong())).thenReturn(thing);

        EventData data = new EventData();
        data.setThingId(30L);
        data.setParams(new HashMap<String, String>());

        eventProcessor.processor(data);

        Notification expected = new Notification("", "", thing.owner().mobiles().get(0));
        verify(notificationSender, times(1)).send(expected);
    }

    @Test
    public void should_send_notification_with_specic_title() {
        Thing thing = createThing("thing", ThingType.PLANTA);
        when(thingRepository.findOne(anyLong())).thenReturn(thing);

        EventData data = new EventData();
        data.setThingId(30L);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("UMIDADE", "40");
        data.setParams(params);

        eventProcessor.processor(data);

        verify(notificationSender).send(notificationCaptor.capture());
        assertEquals("Menssagem da Planta", notificationCaptor.getValue().title());
    }

    Thing createThing(String name, ThingType type) {
        User owner = createOwner();
        Thing t = new Thing(name, type, owner);
        owner.addThing(t);
        return t;
    }

    User createOwner() {
        User owner = new User("email@acme.com");
        owner.addMobile(new Mobile("token", owner));
        return owner;
    }
}
