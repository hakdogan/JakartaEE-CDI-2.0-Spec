package org.jugistanbul.decorator.resource;

import org.jugistanbul.decorator.normalizer.LanguageNormalizer;
import javax.inject.Inject;
import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 8.10.2020
 **/
@Path("decorator/{text}")
@Consumes(TEXT_PLAIN)
@Produces("text/plain; charset=utf-8")
public class DecoratorResource
{
    @Inject
    private LanguageNormalizer normalizer;

    @GET
    public String normalizeText(@PathParam("text") String text){
        return normalizer.normalize(text);
    }
}
