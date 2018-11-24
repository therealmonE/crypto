package io.github.therealmone.services.impl;

import com.google.inject.Singleton;
import io.github.therealmone.model.NGram;
import io.github.therealmone.model.NGramSet;
import io.github.therealmone.services.FrequencyDecryptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class BiGramFrequencyDecryptorImpl extends MonogramFrequencyDecryptorImpl implements FrequencyDecryptor {
    @Override
    protected Map<Character, Character> matchingMapFrom(final List<NGramSet> originalTextFrequency, final List<NGramSet> encryptedTextFrequency) {
        final Map<Character, Character> matchingMap = fromBiGrams(originalTextFrequency.get(1), encryptedTextFrequency.get(1));
        final NGramSet encryptedMonoGrams = encryptedTextFrequency.get(0);
        final NGramSet originalMonoGrams = originalTextFrequency.get(0);
        for (int i = 0; i < encryptedMonoGrams.getNGrams().size(); i++) {
            final Character encryptedMonoGram = encryptedMonoGrams.getNGrams().get(i).getValue().charAt(0);
            final Character originalMonoGram = originalMonoGrams.getNGrams().get(i).getValue().charAt(0);
            if(!matchingMap.containsKey(encryptedMonoGram)) {
                matchingMap.put(encryptedMonoGram, originalMonoGram);
            }
        }
        return matchingMap;
    }

    private Map<Character, Character> fromBiGrams(final NGramSet originalBiGrams, final NGramSet encryptedBiGrams) {
        final Map<Character, Character> matchingMap = new HashMap<>();
        for (int i = 0; i < encryptedBiGrams.getNGrams().size(); i++) {
            final NGram encryptedBiGram = encryptedBiGrams.getNGrams().get(i);
            final NGram originalBiGram = originalBiGrams.getNGrams().get(i);

            if(!matchingMap.containsKey(encryptedBiGram.getValue().charAt(0))) {
                matchingMap.put(encryptedBiGram.getValue().charAt(0), originalBiGram.getValue().charAt(0));
            }

            if(!matchingMap.containsKey(encryptedBiGram.getValue().charAt(1))) {
                matchingMap.put(encryptedBiGram.getValue().charAt(1), originalBiGram.getValue().charAt(1));
            }
        }
        return matchingMap;
    }
}
