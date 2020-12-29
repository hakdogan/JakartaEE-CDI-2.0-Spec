# Decorators
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3Adecorators&metric=coverage)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3Adecorators)

A `CDI decorator` is a Java class that is annotated `javax.decorator.Decorator` and that has a corresponding decorators element in the `beans.xml` file. To demonstrate a decorator example, this module contains a text normalizer example.

```xml
<decorators>
    <class>org.jugistanbul.decorator.TextDecorator</class>
</decorators>
```

```shell script
http -v GET localhost:9080/api/decorator/Hüseyin
GET /api/decorator/H%C3%BCseyin HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:9080
User-Agent: HTTPie/2.3.0

HTTP/1.1 200 OK
Content-Language: en-TR
Content-Length: 113
Content-Type: text/plain;charset=utf-8
Date: Tue, 29 Dec 2020 13:06:39 GMT
X-Powered-By: Servlet/4.0

Huseyin

This message normalized by org.jugistanbul.decorator.normalizer.TurkishNormalizer$Proxy$_$$_WeldSubclass
```
## Run to test
```shell
mvn clean liberty:create liberty:install-feature verify
```
