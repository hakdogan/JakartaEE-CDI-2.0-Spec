package org.jugistanbul.vat;

import java.math.BigDecimal;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 7.10.2020
 **/

public interface VAT
{
    BigDecimal calculate(BigDecimal price);
}
