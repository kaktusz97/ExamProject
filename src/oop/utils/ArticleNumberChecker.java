package oop.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import oop.exceptions.ArticleNumberException;

public class ArticleNumberChecker {

    public final static void checkArticleNumber(String articleNumber,
            String letters) {
        try {
            if (!(checkLetters(letters)
                    && checkDigits(articleNumber.substring(2)))) {
                throw new ArticleNumberException("wrong article number");
            }
        } catch (ArticleNumberException ex) {
            Logger.getLogger(Util.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    private static boolean checkLetters(String letters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static boolean checkDigits(String digits) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
