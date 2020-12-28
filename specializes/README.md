# Specializes
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3Aspecializes&metric=coverage)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3Aspecializes)

In CDI, `Specialization` has a function that allows you to substitute one bean for another at development time as well as at runtime. 

```java
@Specializes
public class MockAsynchronousService extends AsynchronousService
{
    @Override
    public String info() {
        return "Mock asynchronous service";
    }
}
```

It also can be used to specialize in a `producer method`.

```java
    @Override
    @Specializes
    @Produces
    public Identifier getIdentifier() {
        return this;
    }
```
## Run to test
```shell
mvn liberty:create liberty:install-feature liberty:deploy verify
```