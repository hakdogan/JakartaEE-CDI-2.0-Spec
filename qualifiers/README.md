# Qualifiers
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3Aqualifiers&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3Aqualifiers)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3Aqualifiers&metric=coverage)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3Aqualifiers)

A `CDI qualifier` is an annotation to indicate the kind of a bean the class is. It uses to determine to CDI which bean implementation should be used at an injection point when multiple implementations exist. To demonstrate this behavior, this module contains a VAT calculation example.

```shell script
http -v POST http://localhost:9080/api/vat \
"name"="MacBook Pro" \
"price":=10500 \
"type"="LUXURY_CONSUMPTION"
POST /api/vat HTTP/1.1
Accept: application/json, */*;q=0.5
Accept-Encoding: gzip, deflate
Connection: keep-alive
Content-Length: 69
Content-Type: application/json
Host: localhost:9080
User-Agent: HTTPie/2.3.0

{
    "name": "MacBook Pro",
    "price": 10500,
    "type": "LUXURY_CONSUMPTION"
}


HTTP/1.1 200 OK
Content-Language: en-TR
Content-Length: 15
Content-Type: application/json
Date: Tue, 29 Dec 2020 13:32:05 GMT
X-Powered-By: Servlet/4.0

{
    "VAT": 2625.0
}

http -v POST http://localhost:9080/api/vat \
"name"="Samsung UE50TU7000UXTK 50" \
"price":=4500 \
"type"="HOUSEHOLD_APPLIANCES"
POST /api/vat HTTP/1.1
Accept: application/json, */*;q=0.5
Accept-Encoding: gzip, deflate
Connection: keep-alive
Content-Length: 84
Content-Type: application/json
Host: localhost:9080
User-Agent: HTTPie/2.3.0

{
    "name": "Samsung UE50TU7000UXTK 50",
    "price": 4500,
    "type": "HOUSEHOLD_APPLIANCES"
}


HTTP/1.1 200 OK
Content-Language: en-TR
Content-Length: 14
Content-Type: application/json
Date: Tue, 29 Dec 2020 13:33:28 GMT
X-Powered-By: Servlet/4.0

{
    "VAT": 810.0
}
```

## Run to test
```shell
mvn clean liberty:create liberty:install-feature verify
```
