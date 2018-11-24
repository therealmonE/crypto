package io.github.therealmone.services.impl;

import io.github.therealmone.model.NGram;
import io.github.therealmone.model.NGramSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MonoGramFrequencyDecryptorTest {

    @Test
    public void matching_map_init() {
        final MonogramFrequencyDecryptorImpl frequencyDecryptor = new MonogramFrequencyDecryptorImpl();

        final List<NGram> originalMonograms = new ArrayList<NGram>() {{
            add(new NGram("A", 0.1));
            add(new NGram("a", 0.2));
            add(new NGram("B", 0.3));
            add(new NGram("b", 0.4));
        }};

        final List<NGram> encryptedMonograms = new ArrayList<NGram>() {{
            add(new NGram("B", 0.1));
            add(new NGram("b", 0.2));
            add(new NGram("A", 0.3));
            add(new NGram("a", 0.4));
        }};

        final Map<Character, Character> matchingMap = frequencyDecryptor.matchingMapFrom(
                new ArrayList<NGramSet>() {{add(new NGramSet(originalMonograms, 1));}},
                new ArrayList<NGramSet>() {{add(new NGramSet(encryptedMonograms, 1));}}
        );

        assertEquals('A', matchingMap.get('B').charValue());
        assertEquals('a', matchingMap.get('b').charValue());
        assertEquals('B', matchingMap.get('A').charValue());
        assertEquals('b', matchingMap.get('a').charValue());
    }
}
