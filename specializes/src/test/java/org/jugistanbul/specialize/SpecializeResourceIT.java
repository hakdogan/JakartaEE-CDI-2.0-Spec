package org.jugistanbul.specialize;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URL;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 19.10.2020
 **/
@RunWith(Arquillian.class)
public class SpecializeResourceIT
{
    private static final String PATH = "api/service";
    private static final String EXPECTED_MESSAGE = "Mock asynchronous service";
    private final Client client;

    public SpecializeResourceIT() {
        this.client = ClientBuilder.newClient();
    }

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "arquillian-specializes.war")
                .addPackages(true, "org.jugistanbul.specialize")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    @RunAsClient
    public void getHHH(){

        var webTarget = client.target(baseURL.toString()).path(PATH);
        var result = webTarget.request(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN)
                .get().readEntity(String.class);

        Assert.assertEquals(result, EXPECTED_MESSAGE);
    }
}
