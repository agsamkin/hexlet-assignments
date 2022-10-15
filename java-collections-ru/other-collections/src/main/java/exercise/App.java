package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {

    public static Map<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        return Stream.concat(data1.keySet().stream(), data2.keySet().stream())
                .flatMap(k -> Map.of(k, getValue(data1, data2, k)).entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (d1, d2) -> d2, LinkedHashMap::new));
    }

    private static String getValue(Map<String, Object> data1, Map<String, Object> data2, String key) {
        if (!data1.containsKey(key) && data2.containsKey(key)) {
            return "added";
        } else if (data1.containsKey(key) && !data2.containsKey(key)) {
            return "deleted";
        } else {
            Object v1 = data1.get(key);
            Object v2 = data2.get(key);
            if (v1.equals(v2)) {
                return "unchanged";
            } else {
                return "changed";
            }
        }
    }
}
//END
