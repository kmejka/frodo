package com.kmejka.frodo.domain;

import java.security.Principal;

public class User implements Principal {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
