package org.jugistanbul.decorator.normalizer;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hakdogan (hakdogan@kodcu.com)
 * Created on 8.10.2020
 **/
@ApplicationScoped
public class TurkishNormalizer implements LanguageNormalizer {

    public static Map<String, String> turkishCharacters = new HashMap<>();

    static {
        turkishCharacters.put("ç", "c");
        turkishCharacters.put("Ç", "c");
        turkishCharacters.put("ğ", "g");
        turkishCharacters.put("Ğ", "g");
        turkishCharacters.put("ı", "i");
        turkishCharacters.put("ş", "s");
        turkishCharacters.put("Ş", "s");
        turkishCharacters.put("ü", "u");
        turkishCharacters.put("Ü", "u");
    }

    @Override
    public String normalize(final String text) {
        final StringBuffer buffer = new StringBuffer();
        text.chars().mapToObj(Character::toChars).forEach(c -> {
            if (turkishCharacters.get(String.valueOf(c)) != null) {
                buffer.append(turkishCharacters.get(String.valueOf(c)));
            } else {
                buffer.append(String.valueOf(c));
            }
        });
        return buffer.toString();
    }
}
