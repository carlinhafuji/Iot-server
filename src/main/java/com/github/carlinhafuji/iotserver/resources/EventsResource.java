package com.github.carlinhafuji.iotserver.resources;

import com.github.carlinhafuji.iotserver.application.EventData;
import com.github.carlinhafuji.iotserver.application.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "events")
public class EventsResource {

    private EventProcessor processor;

    @Autowired
    public EventsResource(EventProcessor processor) {
        this.processor = processor;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody EventData eventData) {
        processor.processor(eventData);
    }
}
