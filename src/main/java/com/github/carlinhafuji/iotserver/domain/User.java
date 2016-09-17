package com.github.carlinhafuji.iotserver.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Thing> things;

    public User() { }

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public Set<Thing> things() {
        return things;
    }

    public void addThing(Thing thing) {
        if (this.equals(thing.getOwner())) {
            things.add(thing);
        } else {
            throw new IllegalArgumentException("");
        }
    }
}
