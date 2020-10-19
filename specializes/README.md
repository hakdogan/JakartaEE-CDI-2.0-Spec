# Specializes

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
 