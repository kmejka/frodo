package com.kmejka.frodo.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import com.kmejka.frodo.domain.InventoryObject;

import static com.google.common.base.Preconditions.checkNotNull;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("ADMIN")
public class InventoryResource {

    public static final String ONE_RING_KEY = "ONE-RING";

    private Map<String, InventoryObject> storage;

    public InventoryResource(final Map<String, InventoryObject> storage) {
        this.storage = checkNotNull(storage, "storage can't be null!");
    }

    @GET
    public Collection<InventoryObject> getWholeInventory() {
        return storage.values();
    }

    @POST
    public Response saveObjectInInventory(final InventoryObject object, final @Context UriInfo uriInfo) {
        final String uuid = UUID.randomUUID().toString();
        object.setId(uuid);
        this.storage.put(uuid, object);

        final UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(uuid);
        return Response.created(builder.build()).build();
    }

    @GET
    @Path("/{key}")
    public InventoryObject getObjectFromInventory(@PathParam("key") final String key, final InventoryObject object) {
        return this.storage.get(key);
    }

    @PUT
    @Path("/ring")
    public Response saveRing(final InventoryObject theRing, final @Context UriInfo uriInfo) {
        theRing.setId(ONE_RING_KEY);
        this.storage.put(ONE_RING_KEY, theRing);

        final UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        return Response.created(builder.build())
                .build();
    }

    @GET
    @Path("/ring")
    public Response seeTheRing(@QueryParam("friend") final boolean isFriend) {
        if (isFriend) {
            return Response.ok(storage.get(ONE_RING_KEY))
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You're not my friend, I won't show you my precious")
                    .build();
        }
    }

    @DELETE
    @Path("/ring")
    public Response stealRing(@QueryParam("force") final boolean useForce) {
        if (useForce) {
            storage.remove(ONE_RING_KEY);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You can't take the ring from me!")
                    .build();
        }
    }
}
