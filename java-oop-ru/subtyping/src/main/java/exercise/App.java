package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> map = storage.toMap();
        for (Entry<String, String> element : map.entrySet()) {
            storage.unset(element.getKey());
        }
        for (Entry<String, String> element : map.entrySet()) {
            storage.set(element.getValue(), element.getKey());
        }
    }
}
// END
