package org.jugistanbul.stereotype.resource;

import org.jugistanbul.stereotype.crawl.Crawler;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("stereotypes")
@Consumes(TEXT_PLAIN)
@Produces(TEXT_PLAIN)
public class StereotypeResource
{
    @Inject
    private Crawler crawler;

    @GET
    public String getContent(){
        return crawler.content();
    }
}
