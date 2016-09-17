package com.github.carlinhafuji.iotserver.application;

import java.util.Map;

public class EventData {

    private Long thingId;
    private Map<String, String> params;

    public Map<String, String> getParams() {
        return params;
    }
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    public Long getThingId() {
        return thingId;
    }
    public void setThingId(Long thingId) {
        this.thingId = thingId;
    }
}
