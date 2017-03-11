package com.kmejka.frodo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Strings;
import com.kmejka.frodo.domain.Say;

@Path("/talk")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TalkResource {

    private final String name;

    public TalkResource(final String name) {
        this.name = name;
    }

    @GET
    public Say respond(@QueryParam("name") final String inputName) {
        if (Strings.isNullOrEmpty(inputName)) {
            return new Say("Hello, I'm "+ this.name +", what's your name?");
        } else {
            return new Say("Hello "+inputName+", I'm " + this.name);
        }
    }
}
