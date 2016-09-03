package com.github.carlinhafuji.iotserver.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Thing {

    @Id
    private String id;
    private String name;
    @ManyToOne
    private User owner;

    public Thing(String id, User owner) {
        this.id = id;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }
}
