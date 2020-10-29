package org.jugistanbul.vat;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 9.10.2020
 **/
@RunWith(Arquillian.class)
public class VatResourceIT
{
    private static final BigDecimal KDV8 = BigDecimal.valueOf(0.08);
    private static final BigDecimal KDV18 = BigDecimal.valueOf(0.18);
    private static final BigDecimal KDV25 = BigDecimal.valueOf(0.25);
    private static final String PATH = "api/vat";
    private final Client client;

    public VatResourceIT() {
        this.client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);
    }

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,
                "arquillian-qualifiers.war")
                .addPackages(true, "org.jugistanbul");
    }

    @ArquillianResource
    private URL baseURL;

    @Test
    @RunAsClient
    public void StapleFoodConsumptionTest(){

        var price = BigDecimal.valueOf(getRandomPrice(200l, 1000l));
        var product = Json.createObjectBuilder()
                .add("name", "Feta Cheese")
                .add("price", price)
                .add("type", "STAPLE_FOOD")
                .build();

        var response = postRequest(product);
        var result = response.readEntity(JsonObject.class);
        var expected = price.multiply(KDV8).setScale(2, RoundingMode.HALF_EVEN);
        Assert.assertEquals(expected, result.getJsonNumber("VAT").bigDecimalValue());
    }

    @Test
    @RunAsClient
    public void HouseholdAppliancesConsumptionTest(){

        var price = BigDecimal.valueOf(getRandomPrice(1000l, 10000l));
        var product = Json.createObjectBuilder()
                .add("name", "Samsung UE50TU7000UXTK 50")
                .add("price", price)
                .add("type", "HOUSEHOLD_APPLIANCES")
                .build();

        var response = postRequest(product);
        var result = response.readEntity(JsonObject.class);
        var expected = price.multiply(KDV18).setScale(2, RoundingMode.HALF_EVEN);
        Assert.assertEquals(expected, result.getJsonNumber("VAT").bigDecimalValue());
    }

    @Test
    @RunAsClient
    public void luxuryConsumptionTest() {

        var price = BigDecimal.valueOf(getRandomPrice(5000l, 25000l));
        var product = Json.createObjectBuilder()
                .add("name", "MacBook Pro")
                .add("price", price)
                .add("type", "LUXURY_CONSUMPTION")
                .build();

        var response = postRequest(product);
        var result = response.readEntity(JsonObject.class);
        var expected = price.multiply(KDV25).setScale(2, RoundingMode.HALF_EVEN);
        Assert.assertEquals(expected, result.getJsonNumber("VAT").bigDecimalValue());

    }

    public Response postRequest(final JsonObject product){
        var webTarget = client.target(baseURL.toString()).path(PATH);
        return webTarget.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(product, MediaType.APPLICATION_JSON));
    }

    public Long getRandomPrice(final long origin, final long bound) {
        return ThreadLocalRandom.current().nextLong(origin, bound);
    }
}
