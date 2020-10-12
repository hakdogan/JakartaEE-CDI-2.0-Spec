package org.jugistanbul.interceptor.exception;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 12.10.2020
 **/

public class FaultToleranceTimeOutException extends RuntimeException
{
    public FaultToleranceTimeOutException(String errorMessage) {
        super(errorMessage);
    }
}
