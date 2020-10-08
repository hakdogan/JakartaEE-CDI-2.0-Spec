package org.jugistanbul.vat;

import org.jugistanbul.vat.annotation.StapleFood;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 8.10.2020
 **/
@ApplicationScoped
@StapleFood
public class VAT8 implements VAT
{
    private final BigDecimal KDV = new BigDecimal(0.08);

    @Override
    public BigDecimal calculate(final BigDecimal price) {
        return price.multiply(KDV).setScale(2, RoundingMode.HALF_EVEN);
    }
}
