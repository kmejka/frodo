package com.kmejka.frodo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public class InventoryObject {

    private String id;
    private String name;
    private String description;

    @JsonCreator
    public InventoryObject() {
    }

    public InventoryObject(final String name, final String id, final String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }
}
