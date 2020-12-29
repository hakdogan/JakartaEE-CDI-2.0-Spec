# Interceptors
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3Ainterceptors&metric=coverage)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3Ainterceptors)

A `CDI interceptor` allows common, cross-cutting concerns to be applied to beans via custom annotations. To demonstrate this behavior, this module includes a `Circuit Breaker` example to be applied to file access requests.

```java

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
```

```shell script
http -v GET localhost:9080/api/accessor/readme.txt
GET /api/accessor/readme.txt HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:9080
User-Agent: HTTPie/2.3.0

HTTP/1.1 200 OK
Content-Language: en-TR
Content-Length: 5
Content-Type: text/plain
Date: Tue, 29 Dec 2020 13:15:34 GMT
X-Powered-By: Servlet/4.0

Hello
                                                                                               
~ http -v GET localhost:9080/api/accessor/anyfile.txt
GET /api/accessor/anyfile.txt HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:9080
User-Agent: HTTPie/2.3.0

HTTP/1.1 200 OK
Content-Language: en-TR
Content-Length: 130
Content-Type: text/plain
Date: Tue, 29 Dec 2020 13:16:53 GMT
X-Powered-By: Servlet/4.0

The file in /Users/hakdogan/Repositories/JakartaEE-CDI-2.0-Spec/interceptors/src/main/resources/anyfile.txt path could not be read
```

## Run to test
```shell
mvn clean liberty:create liberty:install-feature verify
```