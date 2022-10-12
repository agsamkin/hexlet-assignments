package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble(String letters, String word) {
        char[] lettersArray = letters.toLowerCase().toCharArray();
        List<Character> lettersList = new ArrayList<>();
        for (char ch : lettersArray) {
            lettersList.add(ch);
        }

        char[] wordArray = word.toLowerCase().toCharArray();
        for (char ch : wordArray) {
            if (lettersList.contains(ch)) {
                lettersList.remove(lettersList.indexOf(ch));
            } else {
                return false;
            }
        }

        return true;
    }
}
//END
