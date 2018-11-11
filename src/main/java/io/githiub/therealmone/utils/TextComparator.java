package io.githiub.therealmone.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;

@Singleton
public class TextComparator {
    private final static Logger logger = LogManager.getLogger(TextComparator.class);

    public void compareTexts(final String originalText, final String decryptedText) {
        logger.info("ORIGINAL TEXT: \n\n" + originalText + "\n\n");
        logger.info("DECRYPTED TEXT: \n\n" + decryptedText + "\n\n");

        int hits = 0;
        for (int i = 0; i < decryptedText.length(); i++) {
            if(new Character(decryptedText.charAt(i)).equals(originalText.charAt(i))) {
                hits++;
            }
        }

        logger.info("Correct chars: " + ((double) hits / originalText.length() * 100) + "%");
    }
}
