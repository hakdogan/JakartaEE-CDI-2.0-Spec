package org.jugistanbul.interceptor;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.beans10.BeansDescriptor;
import org.jugistanbul.interceptor.exception.FaultToleranceTimeOutException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.10.2020
 **/
@RunWith(Arquillian.class)
public class InterceptorResourceIT
{
    private static final String FILE_CONTENT = "Hello";
    private static final String EXISTING_FILE = "readme.txt";
    private static final String NON_EXISTING_FILE = "anyfile.txt";
    private static final String PATH = "api/accessor/{fileName}";
    private Client client;

    public InterceptorResourceIT() {
        this.client = ClientBuilder.newClient();
    }

    @Deployment
    public static WebArchive createDeployment() {
        var beans = Descriptors.create(BeansDescriptor.class)
                .getOrCreateInterceptors().clazz("org.jugistanbul.interceptor.CircuitBreaker").up();
        return ShrinkWrap.create(WebArchive.class, "arquillian-interceptors.war")
                .addPackages(true, "org.jugistanbul.interceptor")
                .addAsResource("test-config.properties", "META-INF/microprofile-config.properties")
                .addAsResource("readme.txt", "readme.txt")
                .addAsWebInfResource(new StringAsset(beans.exportAsString()), "beans.xml");
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    @InSequence(1)
    public void accessToAnExistingFileTest(){
        var webTarget = client.target(baseURL.toString())
                .path(PATH).resolveTemplate("fileName", EXISTING_FILE);

        var response = webTarget.request(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN)
                .get();

        var result = response.readEntity(String.class);
        Assert.assertEquals("Hello", result);
    }

    @Test
    @InSequence(2)
    public void accessToNonExistingFileTest(){
        var webTarget = client.target(baseURL.toString())
                .path(PATH).resolveTemplate("fileName", NON_EXISTING_FILE);

        IntStream.rangeClosed(0, 4).forEach(r -> {
            var result = webTarget.request(MediaType.TEXT_PLAIN)
                    .accept(MediaType.TEXT_PLAIN)
                    .get().readEntity(String.class);

            Assert.assertTrue(result.contains("could not be read"));
        });
    }

    @Test
    @InSequence(3)
    public void faultToleranceTimeOutExceptionTest() throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);

        var webTarget = client.target(baseURL.toString())
                .path(PATH).resolveTemplate("fileName", NON_EXISTING_FILE);

        var result = webTarget.request(MediaType.TEXT_PLAIN)
                    .accept(MediaType.TEXT_PLAIN).get().readEntity(String.class);

        Assert.assertTrue(result.contains("could not be read"));
    }


}
