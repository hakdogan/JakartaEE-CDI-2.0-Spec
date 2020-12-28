package org.jugistanbul.specialize.identify;

import org.jugistanbul.specialize.annotation.AsyncIdentify;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 28.12.2020
 **/
@ApplicationScoped
@AsyncIdentify
public class AsynchronousIdentifier implements Identifier
{
    @Override
    public String typeInfo() {
        return "concrete";
    }

    @Produces
    public Identifier getIdentifier() {
        return this;
    }
}
