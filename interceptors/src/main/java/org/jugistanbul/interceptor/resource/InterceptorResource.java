package org.jugistanbul.interceptor.resource;

import org.jugistanbul.interceptor.Breaker;
import org.jugistanbul.interceptor.accessor.FileAccessor;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2020
 **/
@Path("accessor")
@Consumes(TEXT_PLAIN)
@Produces(TEXT_PLAIN)
public class InterceptorResource
{
    @Inject
    private FileAccessor accessor;

    @GET
    @Path("{fileName}")
    @Breaker
    public String accessFile(@PathParam("fileName") String fileName){
        return accessor.readString(fileName);
    }
}
