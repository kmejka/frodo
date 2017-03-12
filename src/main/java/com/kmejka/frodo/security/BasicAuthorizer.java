package com.kmejka.frodo.security;

import com.kmejka.frodo.domain.User;
import io.dropwizard.auth.Authorizer;

public class BasicAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return "user".equals(user.getName()) && role.equals("ADMIN");
    }
}