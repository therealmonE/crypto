package io.github.therealmone.services.impl;

import io.github.therealmone.model.NGram;
import io.github.therealmone.model.NGramSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BiGramFrequencyDecryptorTest {

    @Test
    public void matching_map_init() {
        final BiGramFrequencyDecryptorImpl frequencyDecryptor = new BiGramFrequencyDecryptorImpl();

        final List<NGram> originalBiGrams = new ArrayList<NGram>() {{
            add(new NGram("Aa", 0.1));
            add(new NGram("aA", 0.2));
            add(new NGram("Bb", 0.3));
            add(new NGram("bB", 0.4));
        }};

        final List<NGram> encryptedBiGrams = new ArrayList<NGram>() {{
            add(new NGram("Bb", 0.1));
            add(new NGram("bB", 0.2));
            add(new NGram("Aa", 0.3));
            add(new NGram("aA", 0.4));
        }};

        final Map<Character, Character> matchingMap = frequencyDecryptor.matchingMapFrom(
                new ArrayList<NGramSet>() {{
                    add(new NGramSet(new ArrayList<>(), 0));
                    add(new NGramSet(originalBiGrams, 2));
                }},
                new ArrayList<NGramSet>() {{
                    add(new NGramSet(new ArrayList<>(), 0));
                    add(new NGramSet(encryptedBiGrams, 2));
                }}
        );

        assertEquals(4, matchingMap.size());
        assertEquals('A', matchingMap.get('B').charValue());
        assertEquals('a', matchingMap.get('b').charValue());
        assertEquals('B', matchingMap.get('A').charValue());
        assertEquals('b', matchingMap.get('a').charValue());
    }
}
