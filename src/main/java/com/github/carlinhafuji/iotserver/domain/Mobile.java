package com.github.carlinhafuji.iotserver.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Mobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String deviceId;

    @ManyToOne
    private User owner;

    public Mobile() {
    }

    public Mobile(String deviceId, User owner) {
        this.deviceId = deviceId;
        this.owner = owner;
    }

    public String deviceId() {
        return deviceId;
    }

    public User owner() {
        return owner;
    }

    public Long id() {
        return id;
    }
}
