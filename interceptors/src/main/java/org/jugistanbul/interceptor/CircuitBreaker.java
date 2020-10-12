package org.jugistanbul.interceptor;

import org.slf4j.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.FileNotFoundException;
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
    public Object faultToleranceControl(InvocationContext ic) throws Exception {

        var fileName = Arrays.stream(ic.getParameters()).findFirst().get().toString();
        var checkerMap = checker.getCheckerMap();
        var faultObject = checkerMap.get(fileName);

        if (null == faultObject || faultObject.getInt("count") < checker.getFaultTolerant()) {
            logger.info("The file access request can be executed!");
        } else if (null != faultObject) {
            var lastTime = LocalDateTime.parse(faultObject.getString("time"));
            var millis = lastTime.until(LocalDateTime.now(), ChronoUnit.MILLIS);

            if(millis >= checker.getTimeOutMs()){
                checkerMap.remove(fileName);
                logger.info("The file access request can be executed because time out of fault-tolerant passed!");
            } else {
                logger.info("The file access request can't be executed!");
                throw new FileNotFoundException();
            }
        } else {
            logger.info("The file access request can't be executed!");
            throw new FileNotFoundException();
        }
        return ic.proceed();
    }
}
