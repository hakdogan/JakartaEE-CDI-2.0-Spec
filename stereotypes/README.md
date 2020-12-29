# Stereotypes

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.jugistanbul%3Astereotypes&metric=coverage)](https://sonarcloud.io/dashboard?id=org.jugistanbul%3Astereotypes)

In CDI, a `Stereotype` has a function that allows a developer to identify some recurring roles that produced by used architectural patterns and declare some common metadata for beans with that role in a central place. To demonstrate this behavior, this module includes a `Mock` stereotype example that uses in test time to crawling.

```java
@Alternative
@Stereotype
@Target(TYPE)
@Retention(RUNTIME)
public @interface Mock
{
}
```

```java
@Mock
public class Pseudo implements Crawler
{
    @Override
    public String content() {
        return "pseudo content";
    }
}
```

## Run to test
```shell
mvn clean liberty:create liberty:install-feature verify
```