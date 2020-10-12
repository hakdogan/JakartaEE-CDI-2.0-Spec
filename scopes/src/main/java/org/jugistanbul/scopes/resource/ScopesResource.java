package org.jugistanbul.scopes.resource;

import javax.inject.Inject;
import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import java.util.List;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 8.10.2020
 **/
@Path("scopes")
@Consumes(TEXT_PLAIN)
@Produces(APPLICATION_JSON)
public class ScopesResource
{
    @Inject
    private List<Long> sessionScopedCount;

    @Inject
    private List<Integer> requestScopedCount;

    @GET
    @Path("/statefull/{number}")
    public List<Long> getStateFullNumberList(@PathParam("number") final Long number){
        sessionScopedCount.add(number);
        return sessionScopedCount;
    }

    @GET
    @Path("/stateless/{number}")
    public List<Integer> getStateLessNumberList(@PathParam("number") final Integer number){
        requestScopedCount.add(number);
        return requestScopedCount;
    }

}
