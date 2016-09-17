package com.github.carlinhafuji.iotserver.domain;

import javax.persistence.*;
import java.util.Set;

@Entity(name= "application_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Thing> things;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Mobile> mobiles;

    public User() { }

    public User(String email) {
        this.email = email;
    }

    public String email() {
        return email;
    }

    public Long id() {
        return id;
    }

    public Set<Thing> things() {
        return things;
    }

    public Set<Mobile> mobiles() {
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
