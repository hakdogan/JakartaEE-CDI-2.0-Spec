package org.jugistanbul.vat.resource;

import org.jugistanbul.vat.VAT;
import org.jugistanbul.vat.annotation.HouseHoldAppliances;
import org.jugistanbul.vat.annotation.LuxuryConsumption;
import org.jugistanbul.vat.annotation.StapleFood;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import java.math.BigDecimal;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 7.10.2020
 **/
@Path("/vat")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class VATResource
{
    private VAT vat8;
    private VAT vat18;
    private VAT vat25;

    public VATResource() {}

    @Inject
    public VATResource(@StapleFood VAT vat8, @HouseHoldAppliances VAT vat18,
                       @LuxuryConsumption VAT vat25) {
        this.vat8 = vat8;
        this.vat18 = vat18;
        this.vat25 = vat25;
    }

    @GET
    public String deneme(){
        return "Test";
    }

    @POST
    public JsonObject calculateVAT(final JsonObject product){

        var price = BigDecimal.valueOf(product.getInt("price"));
        var vat = switch (product.getString("type")){
            case "STAPLE_FOOD" -> vat8.calculate(price);
            case "HOUSEHOLD_APPLIANCES" -> vat18.calculate(price);
            case "LUXURY_CONSUMPTION" -> vat25.calculate(price);
            default -> BigDecimal.valueOf(0);
        };
        return Json.createObjectBuilder().add("VAT", vat).build();
    }
}
