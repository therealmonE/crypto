package io.github.therealmone.services;

import io.github.therealmone.model.NGramSet;

import java.util.List;

public interface FrequencyAnalyzer {
    /**
     *
     * Анализирует частоту NGram в заданном тексте
     * Метод возвращает список NGram, длина которых
     * варьируется от 1 до countOfNGram (все NGram'ы в верхнем регистре)
     *
     * Пример:
     * getFrequency("АаБ", 2) ->
     *  1 - NGram("А", 0.66)
     *  3 - NGram("Б", 0.33)
     *  4 - NGram("АА", 0.5)
     *  5 - NGram("АБ", 0.5)
     *
     *  Примечание:
     *  Для всех NGram, длина которых больше 1
     *  будут отобраны топ 10 по частоте
     *
     * @param text - текст, из которого необходимо составить список частот NGram
     * @param countOfNGram - максимальная длина NGram
     * @return список частот NGram из заданного текста
     */
    List<NGramSet> getFrequency(String text, int countOfNGram);
}
