package com.github.carlinhafuji.iotserver.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToOne
    private User owner;

    public Thing() { }

    public Thing(String name, User owner) {
        this.name = name;
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

    public Long getId() {
        return id;
    }
}
