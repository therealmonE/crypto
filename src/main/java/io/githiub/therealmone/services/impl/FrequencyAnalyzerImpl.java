package io.githiub.therealmone.services.impl;

import com.google.inject.Singleton;
import io.githiub.therealmone.model.NGramSet;
import io.githiub.therealmone.services.FrequencyAnalyzer;
import com.google.inject.Inject;
import io.githiub.therealmone.model.NGram;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@Singleton
public class FrequencyAnalyzerImpl implements FrequencyAnalyzer {
    private final static Logger logger = LogManager.getLogger(FrequencyAnalyzerImpl.class);
    private final List<Character> alphabet;

    @Inject
    public FrequencyAnalyzerImpl(final List<Character> alphabet) {
        logger.debug("Alphabet: {}", alphabet);
        this.alphabet = alphabet;
    }

    @Override
    public List<NGramSet> getFrequency(final String text, final int countOfGram) {
        final String preparedText = prepareText(text);

        final List<NGramSet> nGramList = new ArrayList<>();
        for (int i = 1; i <= countOfGram; i++) {
            nGramList.add(generateNGrams(preparedText, i));
        }
        return nGramList;
    }

    private NGramSet generateNGrams(final String text, final int countOfGram) {
        logger.debug("Generating NGram with count: {}", countOfGram);
        //LinkedHashMap для упорядоченности NGram
        final Map<String, Integer> nGramHits = new LinkedHashMap<>();
        for (int i = 0; i < text.length() - countOfGram + 1; i++) {
            final String nGramValue = text.substring(i, i + countOfGram);

            //(Костыль: нельзя убирать пробелы из текста, так как без них будет другая частотная таблица,
            // соответственно, нужна проверка на наличие пробела в NGram'е. Если есть пробел, то пропускаем ее)
            if(nGramValue.contains(" ")) {
                continue;
            }

            if(nGramHits.containsKey(nGramValue.toUpperCase())) {
                nGramHits.replace(nGramValue.toUpperCase(), nGramHits.get(nGramValue.toUpperCase()) + 1);
            } else {
                nGramHits.put(nGramValue.toUpperCase(), 1);
            }
        }

        //Отбираем топ 10, если countOfGram != 1
        final List<NGram> nGramList = fromMap(nGramHits, text, countOfGram);
        if(countOfGram != 1) {
            nGramList.sort(Comparator.comparingDouble(NGram::getFrequency));

            while(nGramList.size() > 10) {
                nGramList.remove(10);
            }
        } else {
            //необходимо дополнить пропущенные монограмы
            fillMonograms(nGramList);
        }

        logger.debug("Generated NGrams: {}", nGramList);
        return new NGramSet(nGramList, countOfGram);
    }

    private List<NGram> fromMap(final Map<String, Integer> hitMaps, final String text, final int countOfGram) {
        final List<NGram> nGramList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: hitMaps.entrySet()) {
            nGramList.add(new NGram(entry.getKey(), (double) entry.getValue() / (text.length() - countOfGram + 1)));
        }
        return nGramList;
    }

    private String prepareText(final String text) {
        final StringBuilder stringBuilder = new StringBuilder();
        for(Character character : text.toCharArray()) {
            if(alphabet.contains(Character.toUpperCase(character)) || character.equals(' ')) {
                stringBuilder.append(character);
            }
        }
        return stringBuilder.toString();
    }

    private void fillMonograms(final List<NGram> monograms) {
        for(final Character character : alphabet) {
            if(!containsChar(monograms, character)) {
                monograms.add(new NGram(String.valueOf(character), 0));
            }
        }
    }

    private boolean containsChar(final List<NGram> nGrams, final Character character) {
        for(final NGram nGram : nGrams) {
            if(nGram.getValue().equals("" + character)) {
                return true;
            }
        }

        return false;
    }
}
