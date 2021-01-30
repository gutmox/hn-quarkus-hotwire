package org.hn.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;

@Path("/ranking")
public class RankingResource {
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance ranking(); 
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return Templates.ranking();
    }

}
