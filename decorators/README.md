# Decorators

A `CDI decorator` is a Java class that is annotated `javax.decorator.Decorator` and that has a corresponding decorators element in the `beans.xml` file. To demonstrate a decorator example, this repository contains a text normalizer example.

```xml
<decorators>
    <class>org.jugistanbul.decorator.TextDecorator</class>
</decorators>
```

```shell script
curl -XGET http://localhost:9080/api/decorator/Hüseyin%20Akdoğan
Huseyin Akdogan
This message normalized by org.jugistanbul.decorator.normalizer.TurkishNormalizer$Proxy$_$$_WeldSubclass
```

