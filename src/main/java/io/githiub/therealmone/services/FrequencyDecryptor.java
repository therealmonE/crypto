package io.githiub.therealmone.services;

import io.githiub.therealmone.model.NGramSet;

import java.util.List;

public interface FrequencyDecryptor {
    /**
     *
     * Расшифровывает текст при помощи частотного анализа
     * на основе двух частотных списков:
     * 1) Список частот NGram оригинального текста
     * 2) Список частот NGram защифрованного текста
     *
     * @param originalTextFrequency - список частот NGram оригинального текста
     * @param encryptedTextFrequency - список частот NGram зашифрованного текста
     * @param encryptedText - зашифрованный текст
     * @return текст, расшифрованный частотным анализом
     */
    String decryptText(List<NGramSet> originalTextFrequency, List<NGramSet> encryptedTextFrequency, String encryptedText);
}
