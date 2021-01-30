package org.hn.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hn.service.HNService;
import org.jboss.resteasy.reactive.RestSseElementType;

import io.smallrye.mutiny.Multi;

@Path("/ranking")
public class RankingResource {
    @Inject
    HNService service;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestSseElementType("text/vnd.turbo-stream.html")
    @Path("/stream")
    public Multi<String> updateStream() {
        return service.updateStream(25, "Kai");
    }

}
