package org.jugistanbul.decorator;

import org.jugistanbul.decorator.normalizer.LanguageNormalizer;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 8.10.2020
 **/
@Decorator
public class TextDecorator implements LanguageNormalizer
{
    @Inject
    @Delegate
    private LanguageNormalizer delegate;

    @Override
    public String normalize(final String text) {
        return delegate.normalize(text) + "\n\rThis message normalized by " + delegate.getClass().getName();
    }
}
