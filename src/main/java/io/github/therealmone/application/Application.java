package io.github.therealmone.application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.github.therealmone.services.FrequencyAnalyzer;
import io.github.therealmone.services.FrequencyDecryptor;
import io.github.therealmone.services.TextEncryptor;
import io.github.therealmone.utils.TextComparator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Application {
    private static final int ENCRYPTION_OFFSET = 13;
    private static final int COUNT_OF_GRAMS = 2;
    private static final Injector injector = Guice.createInjector(new AppModule());

    public static void main(String[] args) throws Exception {
        final TextEncryptor textEncryptor = injector.getInstance(TextEncryptor.class);
        final FrequencyDecryptor frequencyDecryptor = injector.getInstance(FrequencyDecryptor.class);
        final FrequencyAnalyzer frequencyAnalyzer = injector.getInstance(FrequencyAnalyzer.class);
        final TextComparator textComparator = injector.getInstance(TextComparator.class);

        final String part = readFullText("Tom1.txt");
        final String warAndPeace = readFullText("WarAndPeace.txt");
        final String encryptedText = textEncryptor.encryptText(part, ENCRYPTION_OFFSET);

        textComparator.compareTexts(
                part,
                frequencyDecryptor.decryptText(
                        frequencyAnalyzer.getFrequency(warAndPeace, COUNT_OF_GRAMS),
                        frequencyAnalyzer.getFrequency(encryptedText, COUNT_OF_GRAMS),
                        encryptedText)
        );
    }

    private static String readFullText(final String fileName) throws Exception {
        final StringBuilder stringBuilder = new StringBuilder();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName), Charset.forName("windows-1251")));
        String line;
        while((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }
}
