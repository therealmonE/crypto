package io.githiub.therealmone.services.impl;

import com.google.inject.Singleton;
import io.githiub.therealmone.services.TextEncryptor;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Singleton
public class TextEncryptorImpl implements TextEncryptor {
    private final static Logger logger = LogManager.getLogger(TextEncryptorImpl.class);
    private final List<Character> alphabet;

    @Inject
    public TextEncryptorImpl(final List<Character> alphabet) {
        logger.debug("Alphabet: {}", alphabet);
        this.alphabet = alphabet;
    }

    @Override
    public String encryptText(final String text, final int offset) {
        logger.debug("Encrypting text: \n{} \nwith offset: {}", text, offset);
        final StringBuilder stringBuilder = new StringBuilder();

        for(Character character : text.toCharArray()) {
            boolean upperChar = Character.isUpperCase(character);
            character = Character.toUpperCase(character);
            if(alphabet.contains(character)) {
                final int newIndex = (alphabet.indexOf(character) + offset) % alphabet.size();
                final char newChar = upperChar ? alphabet.get(newIndex) : Character.toLowerCase(alphabet.get(newIndex));
                stringBuilder.append(newChar);
            } else {
                stringBuilder.append(upperChar ? character : Character.toLowerCase(character));
            }
        }

        logger.debug("Encrypted text: \n{} \nwith offset: {}", stringBuilder.toString(), offset);
        return stringBuilder.toString();
    }
}
