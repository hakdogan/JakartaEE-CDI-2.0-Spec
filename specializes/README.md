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
 