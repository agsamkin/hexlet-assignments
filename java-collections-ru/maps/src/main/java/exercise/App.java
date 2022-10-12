package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> result = new HashMap();
        if (sentence.equals("")) {
            return result;
        }
        String[] words = sentence.split(" ");
        for (String word : words) {
            if (result.containsKey(word)) {
                int count = result.get(word);
                result.put(word, ++count);
            } else {
                result.put(word, 1);
            }
        }
        return result;
    }

    public static String toString(Map<String, Integer> wordCount) {
        StringBuilder sb = new StringBuilder("{");
        Set<String> keys = wordCount.keySet();
        for (String key : keys) {
            int count = wordCount.get(key);
            sb.append("\n  ").append(key).append(": ").append(count);
        }
        if (!keys.isEmpty()) {
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
//END
