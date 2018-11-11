package impl;

import io.githiub.therealmone.services.TextEncryptor;
import io.githiub.therealmone.services.impl.TextEncryptorImpl;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TextEncryptorTest {
    @Test
    public void encryptText() {
        TextEncryptor textEncryptor = new TextEncryptorImpl(new ArrayList<Character>() {{
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
        final String testText = "Этот текст не несет никакого смысла и используется только для тестов";

        assertEquals("Юупу уёлту оё оётёу ойлблпдп тньтмб й йтрпмэифёута упмэлп ема уётупг", textEncryptor.encryptText(testText, 1));
        assertEquals("Мвюв вфъбв эф эфбфв эшъпъютю бькбып ш шбяюылчгфвбо вюылъю уыо вфбвюс", textEncryptor.encryptText(testText, 16));
        assertEquals("Ьснс сдйрс мд мдрдс мзйяйнвн рлъркя з зронкыжтдсрю снкыйн гкю сдрснб", textEncryptor.encryptText(testText, 32));
        assertEquals("Этот текст не несет никакого смысла и используется только для тестов", textEncryptor.encryptText(testText, 33));
        assertEquals("Юупу уёлту оё оётёу ойлблпдп тньтмб й йтрпмэифёута упмэлп ема уётупг", textEncryptor.encryptText(testText, 34));
    }
}
