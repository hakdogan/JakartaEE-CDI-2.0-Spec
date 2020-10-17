package org.jugistanbul.alternative;

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
 * Created on 17.10.2020
 **/
@RunWith(Arquillian.class)
public class AlternativeResourceIT
{

    private static final String GOOD_PROGRAMMER_PHRASE = "The team wins";
    private static final String PATH = "api/alternative";
    private final Client client;

    public AlternativeResourceIT() {
        this.client = ClientBuilder.newClient();
    }

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "arquillian-alternatives.war")
                .addPackages(true, "org.jugistanbul.alternative")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    @RunAsClient
    public void programmerTest(){

        var webTarget = client.target(baseURL.toString()).path(PATH);

        var response = webTarget.request(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN).get();

        var result = response.readEntity(String.class);
        Assert.assertEquals(GOOD_PROGRAMMER_PHRASE, result);
    }
}
