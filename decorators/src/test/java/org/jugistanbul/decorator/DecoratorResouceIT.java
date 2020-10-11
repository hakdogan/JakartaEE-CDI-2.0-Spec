package org.jugistanbul.decorator;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.beans10.BeansDescriptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URL;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2020
 **/
@RunWith(Arquillian.class)
public class DecoratorResouceIT
{
    private static final String PATH = "api/decorator/{text}";
    private final Client client;

    public DecoratorResouceIT() {
        this.client = ClientBuilder.newClient();
    }

    @Deployment
    public static WebArchive createDeployment() {
        BeansDescriptor beans = Descriptors.create(BeansDescriptor.class)
                .getOrCreateDecorators().clazz("org.jugistanbul.decorator.TextDecorator").up();
        return ShrinkWrap.create(WebArchive.class, "arquillian-decorators.war")
                .addPackages(true, "org.jugistanbul.decorator")
                .addAsWebInfResource(new StringAsset(beans.exportAsString()), "beans.xml");
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    public void normalizeTextTest(){

        var webTarget = client.target(baseURL.toString())
                .path(PATH).resolveTemplate("text", "hüseyin");

        var response = webTarget.request(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN).get();

        var result = response.readEntity(String.class);
        Assert.assertTrue(result.contains("org.jugistanbul.decorator.normalizer.TurkishNormalizer"));
    }
}
