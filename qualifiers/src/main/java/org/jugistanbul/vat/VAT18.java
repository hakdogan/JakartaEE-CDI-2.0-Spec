package org.jugistanbul.vat;

import org.jugistanbul.vat.annotation.HouseHoldAppliances;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 7.10.2020
 **/
@ApplicationScoped
@HouseHoldAppliances
public class VAT18 implements VAT
{
    private static final BigDecimal KDV = BigDecimal.valueOf(0.18);

    @Override
    public BigDecimal calculate(final BigDecimal price) {
        return price.multiply(KDV).setScale(2, RoundingMode.HALF_EVEN);
    }
}
