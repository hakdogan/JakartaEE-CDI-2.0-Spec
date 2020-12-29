# Scopes
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3Ascopes&metric=coverage)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3Ascopes)

A `CDI scope` determines the lifecycle of bean instances and which instances of the bean are visible to instances of other beans. A bean scope can be defined by annotating the bean class or producer method or field with a scope type. To demonstrate this behavior, this module contains producer fields that have different scopes and one of them determines its bean scope.

```java
public class ScopedNumbers
{
    @Produces
    @ApplicationScoped
    private List<Long> sessionScopedCount = new ArrayList<>();

    @Produces
    @RequestScoped
    private List<Integer> requestScopedCount = new ArrayList<>();
}
```

```shell script
~ http -v GET localhost:9080/api/scopes/statefull/1
GET /api/scopes/statefull/1 HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:9080
User-Agent: HTTPie/2.3.0

HTTP/1.1 200 OK
Content-Language: en-TR
Content-Length: 3
Content-Type: application/json
Date: Tue, 29 Dec 2020 13:37:15 GMT
X-Powered-By: Servlet/4.0

[
    1
]

~ http -v GET localhost:9080/api/scopes/statefull/2
GET /api/scopes/statefull/2 HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:9080
User-Agent: HTTPie/2.3.0

HTTP/1.1 200 OK
Content-Language: en-TR
Content-Length: 5
Content-Type: application/json
Date: Tue, 29 Dec 2020 13:37:43 GMT
X-Powered-By: Servlet/4.0

[
    1,
    2
]

http -v GET localhost:9080/api/scopes/stateless/1
GET /api/scopes/stateless/1 HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:9080
User-Agent: HTTPie/2.3.0

HTTP/1.1 200 OK
Content-Language: en-TR
Content-Length: 3
Content-Type: application/json
Date: Tue, 29 Dec 2020 13:38:08 GMT
X-Powered-By: Servlet/4.0

[
    1
]

~ http -v GET localhost:9080/api/scopes/stateless/2
GET /api/scopes/stateless/2 HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:9080
User-Agent: HTTPie/2.3.0

HTTP/1.1 200 OK
Content-Language: en-TR
Content-Length: 3
Content-Type: application/json
Date: Tue, 29 Dec 2020 13:38:47 GMT
X-Powered-By: Servlet/4.0

[
    2
]
```

## Run to test
```shell
mvn clean liberty:create liberty:install-feature verify
```