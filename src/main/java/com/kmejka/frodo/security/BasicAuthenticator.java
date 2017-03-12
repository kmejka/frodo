package com.kmejka.frodo.security;


import java.util.Optional;

import com.kmejka.frodo.domain.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if ("pass".equals(credentials.getPassword()) && "user".equals(credentials.getUsername())) {
            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.empty();
    }
}