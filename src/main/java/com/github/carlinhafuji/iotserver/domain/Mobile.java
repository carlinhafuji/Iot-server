package com.github.carlinhafuji.iotserver.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Mobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String token;

    @ManyToOne
    private User owner;

    public Mobile() {
    }

    public Mobile(String token, User owner) {
        this.token = token;
        this.owner = owner;
    }

    public User owner() {
        return owner;
    }

    public Long id() {
        return id;
    }

    public String token() {
        return token;
    }
}
