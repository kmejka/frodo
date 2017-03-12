package com.kmejka.frodo.health;

import java.util.Map;

import com.codahale.metrics.health.HealthCheck;
import com.kmejka.frodo.domain.InventoryObject;
import com.kmejka.frodo.resources.InventoryResource;

import static com.google.common.base.Preconditions.checkNotNull;

public class FrodoHealthcheck extends HealthCheck {

    private Map<String, InventoryObject> storage;

    public FrodoHealthcheck(final Map<String, InventoryObject> storage) {
        this.storage = checkNotNull(storage, "storage can't be null!");
    }

    @Override
    protected Result check() throws Exception {
        if (storage.containsKey(InventoryResource.ONE_RING_KEY)) {
            return Result.healthy();
        } else {
            return Result.unhealthy("The ring got stolen!");
        }
    }
}
