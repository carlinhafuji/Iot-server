package com.github.carlinhafuji.iotserver.domain;

import org.hibernate.validator.constraints.NotEmpty;

//@Entity
public class Mobile {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@NotEmpty
    private String deviceId;

    private User owner;

    public Mobile(String deviceId, User owner) {
        this.deviceId = deviceId;
        this.owner = owner;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public User getOwner() {
        return owner;
    }
}
