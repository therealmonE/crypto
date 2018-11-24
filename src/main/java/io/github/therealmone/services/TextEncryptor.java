package io.github.therealmone.services;

public interface TextEncryptor {
    /**
     *
     * Шифрует текст шифром Цезаря с заданным оффсетом
     *
     * @param text - текст, который необходимо зашифровать
     * @param offset - смещение в алфавите
     * @return текст, зашифрованный шифром Цезаря
     */
    String encryptText(final String text, final int offset);
}
