package org.jugistanbul.alternative.programmer;

import javax.enterprise.inject.Alternative;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 17.10.2020
 **/
@Alternative
public class CowboyProgrammer implements Programmer
{
    @Override
    public String info() {
        return "I do everything";
    }
}
