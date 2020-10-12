package org.jugistanbul.scopes;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
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
import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URL;
import java.util.stream.IntStream;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.10.2020
 **/
@RunWith(Arquillian.class)
public class ScopesResourceIT
{
    private static final String PATH = "api/scopes";
    private final Client client;

    public ScopesResourceIT() {
        this.client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);
    }

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "arquillian-scopes.war")
                .addPackages(true, "org.jugistanbul.scopes")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    @RunAsClient
    public void stateLessNumberListTest(){
        var webTarget = client.target(baseURL.toString())
                .path(String.join("/", PATH, "stateless/{number}"))
                .resolveTemplate("number", 1);

        IntStream.range(0, 3).forEach(r -> {
            var size = webTarget.request(MediaType.TEXT_PLAIN)
                    .accept(MediaType.APPLICATION_JSON).get()
                    .readEntity(JsonArray.class).size();
            Assert.assertEquals(1, size);
        });
    }

    @Test
    @RunAsClient
    public void stateFullNumberListTest(){
        var webTarget = client.target(baseURL.toString())
                .path(String.join("/", PATH, "statefull/{number}"))
                .resolveTemplate("number", 1);

        IntStream.range(1, 5).forEach(r -> {
            var size = webTarget.request(MediaType.TEXT_PLAIN)
                    .accept(MediaType.APPLICATION_JSON).get()
                    .readEntity(JsonArray.class).size();
            Assert.assertEquals(r , size);
        });
    }
}
