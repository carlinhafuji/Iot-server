package com.github.carlinhafuji.iotserver.domain;


//@Entity
public class User {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
