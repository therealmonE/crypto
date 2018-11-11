package impl;

import io.githiub.therealmone.model.NGram;
import io.githiub.therealmone.model.NGramSet;
import io.githiub.therealmone.services.FrequencyAnalyzer;
import io.githiub.therealmone.services.impl.FrequencyAnalyzerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FrequencyAnalyzerTest {
    @Test
    public void getFrequencyMap() {
        final String text = "=АаБб";
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzerImpl(new ArrayList<Character>() {{
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
        }});
        List<NGramSet> testListOfNGram;
        List<NGram> nGramSet;
        //monogram
        {
            testListOfNGram = frequencyAnalyzer.getFrequency(text, 1);
            assertEquals(1, testListOfNGram.size());

            nGramSet = testListOfNGram.get(0).getNGrams();
            assertEquals(2, nGramSet.size());
            assertEquals("А", nGramSet.get(0).getValue());
            assertEquals(0.5, nGramSet.get(0).getFrequency(), 0);
            assertEquals("Б", nGramSet.get(1).getValue());
            assertEquals(0.5, nGramSet.get(1).getFrequency(), 0);
        }

        //bigram
        {
            testListOfNGram = frequencyAnalyzer.getFrequency(text, 2);
            assertEquals(2, testListOfNGram.size());

            nGramSet = testListOfNGram.get(0).getNGrams();
            assertEquals(2, nGramSet.size());
            assertEquals("А", nGramSet.get(0).getValue());
            assertEquals(0.5, nGramSet.get(0).getFrequency(), 0);
            assertEquals("Б", nGramSet.get(1).getValue());
            assertEquals(0.5, nGramSet.get(1).getFrequency(), 0);

            nGramSet = testListOfNGram.get(1).getNGrams();
            assertEquals(3, nGramSet.size());
            assertEquals("АА", nGramSet.get(0).getValue());
            assertEquals(0.3333333333333333, nGramSet.get(0).getFrequency(), 0);
            assertEquals("АБ", nGramSet.get(1).getValue());
            assertEquals(0.3333333333333333, nGramSet.get(1).getFrequency(), 0);
            assertEquals("ББ", nGramSet.get(2).getValue());
            assertEquals(0.3333333333333333, nGramSet.get(2).getFrequency(), 0);
        }

        //trigram
        {
            testListOfNGram = frequencyAnalyzer.getFrequency(text, 3);
            assertEquals(3, testListOfNGram.size());

            nGramSet = testListOfNGram.get(0).getNGrams();
            assertEquals(2, nGramSet.size());
            assertEquals("А", nGramSet.get(0).getValue());
            assertEquals(0.5, nGramSet.get(0).getFrequency(), 0);
            assertEquals("Б", nGramSet.get(1).getValue());
            assertEquals(0.5, nGramSet.get(1).getFrequency(), 0);

            nGramSet = testListOfNGram.get(1).getNGrams();
            assertEquals(3, nGramSet.size());
            assertEquals("АА", nGramSet.get(0).getValue());
            assertEquals(0.3333333333333333, nGramSet.get(0).getFrequency(), 0);
            assertEquals("АБ", nGramSet.get(1).getValue());
            assertEquals(0.3333333333333333, nGramSet.get(1).getFrequency(), 0);
            assertEquals("ББ", nGramSet.get(2).getValue());
            assertEquals(0.3333333333333333, nGramSet.get(2).getFrequency(), 0);

            nGramSet = testListOfNGram.get(2).getNGrams();
            assertEquals(2, nGramSet.size());
            assertEquals("ААБ", nGramSet.get(0).getValue());
            assertEquals(0.5, nGramSet.get(0).getFrequency(), 0);
            assertEquals("АББ", nGramSet.get(1).getValue());
            assertEquals(0.5, nGramSet.get(1).getFrequency(), 0);
        }

        //tetragram
        {
            testListOfNGram = frequencyAnalyzer.getFrequency(text, 4);
            assertEquals(4, testListOfNGram.size());

            nGramSet = testListOfNGram.get(0).getNGrams();
            assertEquals(2, nGramSet.size());
            assertEquals("А", nGramSet.get(0).getValue());
            assertEquals(0.5, nGramSet.get(0).getFrequency(), 0);
            assertEquals("Б", nGramSet.get(1).getValue());
            assertEquals(0.5, nGramSet.get(1).getFrequency(), 0);

            nGramSet = testListOfNGram.get(1).getNGrams();
            assertEquals(3, nGramSet.size());
            assertEquals("АА", nGramSet.get(0).getValue());
            assertEquals(0.3333333333333333, nGramSet.get(0).getFrequency(), 0);
            assertEquals("АБ", nGramSet.get(1).getValue());
            assertEquals(0.3333333333333333, nGramSet.get(1).getFrequency(), 0);
            assertEquals("ББ", nGramSet.get(2).getValue());
            assertEquals(0.3333333333333333, nGramSet.get(2).getFrequency(), 0);

            nGramSet = testListOfNGram.get(2).getNGrams();
            assertEquals(2, nGramSet.size());
            assertEquals("ААБ", nGramSet.get(0).getValue());
            assertEquals(0.5, nGramSet.get(0).getFrequency(), 0);
            assertEquals("АББ", nGramSet.get(1).getValue());
            assertEquals(0.5, nGramSet.get(1).getFrequency(), 0);

            nGramSet = testListOfNGram.get(3).getNGrams();
            assertEquals(1, nGramSet.size());
            assertEquals("ААББ", nGramSet.get(0).getValue());
            assertEquals(1.0, nGramSet.get(0).getFrequency(), 0);
        }
    }
}
