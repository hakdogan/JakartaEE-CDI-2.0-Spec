package org.jugistanbul.specialize.service;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 19.10.2020
 **/
@Specializes
public class MockAsynchronousService extends AsynchronousService
{
    @Override
    public String info() {
        return "Mock asynchronous service";
    }
}
