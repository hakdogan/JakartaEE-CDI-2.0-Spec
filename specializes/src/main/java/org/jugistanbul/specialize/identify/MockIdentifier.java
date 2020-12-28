package org.jugistanbul.specialize.identify;

import org.jugistanbul.specialize.annotation.MockIdentify;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 28.12.2020
 **/
@ApplicationScoped
@MockIdentify
public class MockIdentifier extends AsynchronousIdentifier
{
    @Override
    public String typeInfo() {
        return "mock";
    }

    @Override
    @Specializes
    @Produces
    public Identifier getIdentifier() {
        return this;
    }
}
