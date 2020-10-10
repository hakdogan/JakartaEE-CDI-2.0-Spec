package org.jugistanbul.vat;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jugistanbul.vat.resource.VATResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URL;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 9.10.2020
 **/
@RunWith(Arquillian.class)
public class VatResourceIT {


    private static final String PATH = "api/vat";
    private final Client client;

    public VatResourceIT() {
        this.client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);
    }

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive archive = ShrinkWrap.create(WebArchive.class,
                "arquillian-qualifiers.war")
                .addPackages(true, "org.jugistanbul");
        return archive;
    }

    @ArquillianResource
    private URL baseURL;

    @Inject
    VATResource vatResource;

    @Test
    @RunAsClient
    public void HouseholdAppliancesConsumptionTest(){

        var product = Json.createObjectBuilder()
                .add("name", "Samsung UE50TU7000UXTK 50")
                .add("price", BigDecimal.valueOf(4500))
                .add("type", "HOUSEHOLD_APPLIANCES")
                .build();

        var response = postRequest(product);
        var result = response.readEntity(JsonObject.class);
        Assert.assertEquals(BigDecimal.valueOf(810.00).setScale(2), result.getJsonNumber("VAT").bigDecimalValue());
    }

    @Test
    @RunAsClient
    public void luxuryConsumptionTest() {

        var product = Json.createObjectBuilder()
                .add("name", "MacBook Pro")
                .add("price", BigDecimal.valueOf(10500))
                .add("type", "LUXURY_CONSUMPTION")
                .build();

        var response = postRequest(product);
        var result = response.readEntity(JsonObject.class);
        Assert.assertEquals(BigDecimal.valueOf(2625.00).setScale(2), result.getJsonNumber("VAT").bigDecimalValue());

    }

    public Response postRequest(final JsonObject product){
        var webTarget = client.target(baseURL.toString()).path(PATH);
        return webTarget.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(product, MediaType.APPLICATION_JSON));
    }
}
