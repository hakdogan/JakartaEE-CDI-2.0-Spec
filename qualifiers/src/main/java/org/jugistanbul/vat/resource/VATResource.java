package org.jugistanbul.vat.resource;

import org.jugistanbul.vat.VAT;
import org.jugistanbul.vat.annotation.HouseHoldAppliances;
import org.jugistanbul.vat.annotation.LuxuryConsumption;
import org.jugistanbul.vat.annotation.StapleFood;
import org.jugistanbul.vat.entity.Product;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.math.BigDecimal;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 7.10.2020
 **/
@Path("vat")
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

    @POST
    public BigDecimal calculateVAT(final Product product){

        return switch (product.getType()){
            case STAPLE_FOOD -> vat8.calculate(product.getPrice());
            case HOUSEHOLD_APPLIANCES -> vat18.calculate(product.getPrice());
            case LUXURY_CONSUMPTION -> vat25.calculate(product.getPrice());
        };
    }
}
