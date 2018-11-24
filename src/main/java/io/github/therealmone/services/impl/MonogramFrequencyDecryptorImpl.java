package io.github.therealmone.services.impl;

import com.google.inject.Singleton;
import io.github.therealmone.model.NGramSet;
import io.github.therealmone.services.FrequencyDecryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class MonogramFrequencyDecryptorImpl implements FrequencyDecryptor {
    private final static Logger logger = LogManager.getLogger(MonogramFrequencyDecryptorImpl.class);

    @Override
    public String decryptText(final List<NGramSet> originalTextFrequency, final List<NGramSet> encryptedTextFrequency, final String encryptedText) {
        logger.debug("Decrypting text: \n{}", encryptedText);
        logger.debug("Original text frequency: \n{}", originalTextFrequency);
        logger.debug("Decrypted text frequency: \n{}", encryptedTextFrequency);

        final StringBuilder builder = new StringBuilder();
        final Map<Character, Character> matchingMap = matchingMapFrom(originalTextFrequency, encryptedTextFrequency);

        for (final Character character : encryptedText.toCharArray()) {
            if(matchingMap.containsKey(Character.toUpperCase(character))) {
                builder.append(
                        Character.isUpperCase(character)
                                ? String.valueOf(matchingMap.get(character))
                                : String.valueOf(Character.toLowerCase(matchingMap.get(Character.toUpperCase(character)))));
            } else {
                builder.append(character);
            }
        }

        return builder.toString();
    }

    protected Map<Character, Character> matchingMapFrom(final List<NGramSet> originalTextFrequency, final List<NGramSet> encryptedTextFrequency) {
        //Так как все NGram'ы в наборах упорядочены по частоте, то составляем карту по правилу original(i) -> encrypted(i)
        final NGramSet originalMonograms = originalTextFrequency.get(0);
        final NGramSet encryptedMonograms = encryptedTextFrequency.get(0);
        final Map<Character, Character> matchingMap = new HashMap<>();
        for (int i = 0; i < originalMonograms.getNGrams().size(); i++) {
            matchingMap.put(encryptedMonograms.getNGrams().get(i).getValue().charAt(0), originalMonograms.getNGrams().get(i).getValue().charAt(0));
        }
        return matchingMap;
    }
}
