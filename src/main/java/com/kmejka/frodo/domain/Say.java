package com.kmejka.frodo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Say {

    private String message;

    @JsonCreator
    public Say() {
    }

    public Say(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
