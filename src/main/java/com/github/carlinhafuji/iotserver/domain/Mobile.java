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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mobile mobile = (Mobile) o;

        if (id != null ? !id.equals(mobile.id) : mobile.id != null) return false;
        if (!token.equals(mobile.token)) return false;
        return owner.equals(mobile.owner);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + token.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }
}
