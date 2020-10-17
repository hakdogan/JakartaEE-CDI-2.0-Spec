package org.jugistanbul.alternative.resource;

import org.jugistanbul.alternative.programmer.Programmer;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 17.10.2020
 **/
@Path("alternative")
@Consumes(TEXT_PLAIN)
@Produces(TEXT_PLAIN)
public class AlternativeResource
{
    @Inject
    private Programmer coder;

    @GET
    public String getCoder(){
        return coder.info();
    }
}
