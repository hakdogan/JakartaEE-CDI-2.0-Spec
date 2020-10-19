package org.jugistanbul.specialize.service;

import org.jugistanbul.specialize.annotation.Asynchronous;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 19.10.2020
 **/
@ApplicationScoped
@Asynchronous
public class AsynchronousService implements Service
{
    @Override
    public String info() {
        return "Asynchronous service";
    }
}
