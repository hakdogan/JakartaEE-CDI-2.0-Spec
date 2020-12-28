package org.jugistanbul.specialize.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 28.12.2020
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Qualifier
public @interface AsyncIdentify
{
}
