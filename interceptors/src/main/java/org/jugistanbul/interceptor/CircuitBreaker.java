package org.jugistanbul.interceptor;

import org.jugistanbul.interceptor.exception.FaultToleranceTimeOutException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 11.10.2020
 **/
@Breaker
@Interceptor
public class CircuitBreaker
{
    @Inject
    private Checker checker;

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object checkFaultTolerance(InvocationContext ic) throws Exception {

        var fileName = Arrays.stream(ic.getParameters()).findFirst().orElse("");
        var checkerMap = checker.getCheckerMap();
        var faultObject = checkerMap.get(fileName);

        if (null == faultObject || faultObject.getInt("count") < checker.getFaultTolerant()) {
            logger.info("The file access request can be executed!");
        } else if (faultObject.getInt("count") >= checker.getFaultTolerant()){
            var lastTime = LocalDateTime.parse(faultObject.getString("time"));
            var millis = lastTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);

            if(millis >= checker.getTimeOutMs()){
                checkerMap.remove(fileName);
                logger.info("The file access request can be executed because time out of fault-tolerant passed!");
            } else {
                logger.info("The file access request can be executed after the timeout has been reset!");
                throw new FaultToleranceTimeOutException("The file access request can be executed after the timeout has been reset!");
            }
        }
        return ic.proceed();
    }
}
