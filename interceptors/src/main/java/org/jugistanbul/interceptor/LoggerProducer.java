package org.jugistanbul.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2020
 **/

public class LoggerProducer
{
    @Produces
    public Logger exposeLogger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
