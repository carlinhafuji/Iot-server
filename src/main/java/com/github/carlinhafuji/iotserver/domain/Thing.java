package com.github.carlinhafuji.iotserver.domain;

import javax.persistence.*;

@Entity
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private ThingType type;
    @ManyToOne
    private User owner;

    public Thing() { }

    public Thing(String name, ThingType type, User owner) {
        this.name = name;
        this.type = type;
        this.owner = owner;
    }

    public String name() {
        return name;
    }
    public void name(String name) {
        this.name = name;
    }

    public User owner() {
        return owner;
    }

    public Long id() {
        return id;
    }

    public ThingType type() {
        return type;
    }
}
