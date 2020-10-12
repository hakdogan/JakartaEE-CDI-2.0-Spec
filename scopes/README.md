# Scopes
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3Ascopes&metric=coverage)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3Ascopes)

A `CDI scope` determines the lifecycle of bean instances and which instances of the bean are visible to instances of other beans. A bean scope can be defined by annotating the bean class or producer method or field with a scope type. To demonstrate this behavior, this example contains producer fields that have different scopes and one of them determines its bean scope.

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
curl -XGET http://localhost:9080/api/scopes/statefull/1
[1]
curl -XGET http://localhost:9080/api/scopes/statefull/2
[1,2]
curl -XGET http://localhost:9080/api/scopes/stateless/1
[1]
curl -XGET http://localhost:9080/api/scopes/stateless/2
[2]
```
