package com.github.carlinhafuji.iotserver.domain;

import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity(name= "application_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Thing> things;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Mobile> mobiles;

    public User() {
        mobiles = new ArrayList<>();
        things = new ArrayList<>();
    }

    public User(String email) {
        this();
        this.email = email;
    }

    public User(Long id, String email) {
        this(email);
        this.id = id;
    }

    public String email() {
        return email;
    }

    public Long id() {
        return id;
    }

    public List<Thing> things() {
        return things;
    }

    public List<Mobile> mobiles() {
        return mobiles;
    }

    public void addThing(Thing thing) {
        if (this.equals(thing.owner())) {
            things.add(thing);
        } else {
            throw new IllegalArgumentException("");
        }
    }

    public void addMobile(Mobile mobile) {
        if (this.equals(mobile.owner())) {
            mobiles.add(mobile);
        } else {
            throw new IllegalArgumentException("");
        }
    }
}
