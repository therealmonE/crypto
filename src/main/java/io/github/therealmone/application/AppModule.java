package io.github.therealmone.application;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.github.therealmone.services.FrequencyAnalyzer;
import io.github.therealmone.services.impl.BiGramFrequencyDecryptorImpl;
import io.github.therealmone.services.impl.FrequencyAnalyzerImpl;
import io.github.therealmone.services.impl.TextEncryptorImpl;
import io.github.therealmone.services.FrequencyDecryptor;
import io.github.therealmone.services.TextEncryptor;
import io.github.therealmone.utils.TextComparator;

import java.util.ArrayList;
import java.util.List;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TextEncryptor.class).to(TextEncryptorImpl.class);
        bind(FrequencyDecryptor.class).to(BiGramFrequencyDecryptorImpl.class);
        bind(FrequencyAnalyzer.class).to(FrequencyAnalyzerImpl.class);
        bind(TextComparator.class);
    }

    @Provides
    public List<Character> provideAlphabet() {
        return new ArrayList<Character>() {{
            add('А');
            add('Б');
            add('В');
            add('Г');
            add('Д');
            add('Е');
            add('Ё');
            add('Ж');
            add('З');
            add('И');
            add('Й');
            add('К');
            add('Л');
            add('М');
            add('Н');
            add('О');
            add('П');
            add('Р');
            add('С');
            add('Т');
            add('У');
            add('Ф');
            add('Х');
            add('Ц');
            add('Ч');
            add('Ш');
            add('Щ');
            add('Ъ');
            add('Ы');
            add('Ь');
            add('Э');
            add('Ю');
            add('Я');
        }};
    }
}
