package org.jugistanbul.vat;

import org.jugistanbul.vat.annotation.LuxuryConsumption;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 8.10.2020
 **/
@ApplicationScoped
@LuxuryConsumption
public class VAT25 implements VAT
{
    private final BigDecimal KDV = new BigDecimal(0.25);

    @Override
    public BigDecimal calculate(BigDecimal price) {
        return price.multiply(KDV).setScale(2, RoundingMode.HALF_EVEN);
    }
}
