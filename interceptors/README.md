# Interceptors

A `CDI interceptor` allows common, cross-cutting concerns to be applied to beans via custom annotations. To demonstrate this behavior, this module includes a `Circuit Breaker` example to be applied to file access requests.

```java
@AroundInvoke
public Object checkFaultTolerance(InvocationContext ic) throws Exception {

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
```
```shell script
curl -XGET http://localhost:9080/api/accessor/readme.txt
Hello                                                   
                                                                                                        
curl -XGET http://localhost:9080/api/accessor/anyfile.txt
The file in /Users/hakdogan/Repository/CDI2.0/interceptors/src/main/resources/anyfile.txt path could not be read
```