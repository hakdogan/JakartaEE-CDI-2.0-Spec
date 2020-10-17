# Alternatives
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3Aalternatives&metric=coverage)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3Aalternatives)

In CDI, an `alternative` is a bean that must be explicitly selected if it should be available for lookup, injection, or name resolution. By using `alternatives`, you can choose at deployment time without having to change your application's source code.

```java
@Alternative
public class CowboyProgrammer implements Programmer
{
    @Override
    public String info() {
        return "I do everything";
    }
}
```
