package com.kmejka.frodo;

import java.util.HashMap;

import com.kmejka.frodo.conf.FrodoConfiguration;
import com.kmejka.frodo.domain.InventoryObject;
import com.kmejka.frodo.domain.User;
import com.kmejka.frodo.resources.InfoResource;
import com.kmejka.frodo.resources.InventoryResource;
import com.kmejka.frodo.resources.TalkResource;
import com.kmejka.frodo.security.BasicAuthenticator;
import com.kmejka.frodo.security.BasicAuthorizer;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class FrodoApplication extends Application<FrodoConfiguration> {

    public static void main(String[] args) throws Exception {
        new FrodoApplication().run(args);
    }

    @Override
    public String getName() {
        return "Frodo Application";
    }

    @Override
    public void initialize(final Bootstrap<FrodoConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    public void run(final FrodoConfiguration frodoConfiguration, final Environment environment) throws Exception {
        final InfoResource infoResource = new InfoResource(getName());
        final TalkResource talkResource = new TalkResource(frodoConfiguration.getName());
        final HashMap<String, InventoryObject> frodoStorage = new HashMap<>();
        final InventoryResource inventoryResource = new InventoryResource(frodoStorage);
        environment.jersey().register(infoResource);
        environment.jersey().register(talkResource);
        environment.jersey().register(inventoryResource);

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new BasicAuthenticator())
                        .setAuthorizer(new BasicAuthorizer())
                        .setRealm("BASIC-AUTH-REALM")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
