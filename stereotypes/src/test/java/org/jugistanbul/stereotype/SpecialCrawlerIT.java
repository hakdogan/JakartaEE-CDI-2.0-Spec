package org.jugistanbul.stereotype;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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

@RunWith(Arquillian.class)
public class SpecialCrawlerIT
{
    private static final String PATH = "api/stereotypes";
    private static final String UNEXPECTED_MESSAGE = "pseudo content";
    private final Client client;

    public SpecialCrawlerIT() {
        this.client = ClientBuilder.newClient();
    }

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "arquillian-special-crawler.war")
                .addPackages(true, "org.jugistanbul.stereotype")
                .addClass(RandomStringUtils.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    public void specialCrawlerTest(){
        var webTarget = client.target(baseURL.toString()).path(PATH);
        var result = webTarget.request(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN)
                .get().readEntity(String.class);

        Assert.assertNotEquals(UNEXPECTED_MESSAGE, result);
    }

}
