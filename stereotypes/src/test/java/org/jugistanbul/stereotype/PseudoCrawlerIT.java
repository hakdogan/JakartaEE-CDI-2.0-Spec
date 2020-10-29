package org.jugistanbul.stereotype;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
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

@RunWith(Arquillian.class)
public class PseudoCrawlerIT
{
    private static final String PATH = "api/stereotypes";
    private static final String EXPECTED_MESSAGE = "pseudo content";
    private final Client client;

    public PseudoCrawlerIT() {
        this.client = ClientBuilder.newClient();
    }

    @Deployment
    public static WebArchive createDeployment() {
        var beans = Descriptors.create(BeansDescriptor.class)
                .getOrCreateAlternatives().stereotype("org.jugistanbul.stereotype.annotation.Mock").up();
        return ShrinkWrap.create(WebArchive.class, "arquillian-pseudo-crawler.war")
                .addPackages(true, "org.jugistanbul.stereotype")
                .addClass(RandomStringUtils.class)
                .addAsWebInfResource(new StringAsset(beans.exportAsString()), "beans.xml");
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    public void pseudoCrawlerTest(){
        var webTarget = client.target(baseURL.toString()).path(PATH);
        var result = webTarget.request(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN)
                .get().readEntity(String.class);

        Assert.assertEquals(EXPECTED_MESSAGE, result);
    }
}
