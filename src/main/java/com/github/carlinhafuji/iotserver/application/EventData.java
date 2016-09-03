package com.github.carlinhafuji.iotserver.application;

import java.util.Map;

public class EventData {

    private String thingId;
    private Map<String, String> params;

    public Map<String, String> getParams() {
        return params;
    }
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    public String getThingId() {
        return thingId;
    }
    public void setThingId(String thingId) {
        this.thingId = thingId;
    }
}
