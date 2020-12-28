package org.jugistanbul.specialize.resource;

import org.jugistanbul.specialize.annotation.Asynchronous;
import org.jugistanbul.specialize.identify.Identifier;
import org.jugistanbul.specialize.service.Service;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 19.10.2020
 **/
@Path("service")
@Consumes(TEXT_PLAIN)
@Produces(TEXT_PLAIN)
public class SpecializeResource
{
    @Inject
    @Asynchronous
    private Service service;

    @Inject
    private Identifier identifier;

    @GET
    public String getServiceInfo(){
        return String.format("Service name [%s] service type [%s]",
                service.info(), identifier.typeInfo());
    }
}
