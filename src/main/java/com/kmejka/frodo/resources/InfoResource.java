package com.kmejka.frodo.resources;

import javax.annotation.Nonnull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.google.common.base.Preconditions.checkNotNull;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class InfoResource {

    private final String applicationName;

    public InfoResource(@Nonnull final String applicationName) {
        this.applicationName = checkNotNull(applicationName, "applicationName can't be null!");
    }

    @GET
    public Response getApplicationName() {
        return Response.ok(applicationName).build();
    }

    @GET
    @Path("/info")
    public Response getBuildInformation() {
        return Response.noContent().build();
    }
}
