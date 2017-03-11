package com.kmejka.frodo.conf;

import io.dropwizard.Configuration;

public class FrodoConfiguration extends Configuration {

    private String name = "Frodo";

    public String getName() {
        return name;
    }
}
