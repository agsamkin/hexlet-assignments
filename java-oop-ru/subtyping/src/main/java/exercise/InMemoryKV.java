package exercise;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

// BEGIN
class InMemoryKV implements KeyValueStorage {
    private final Map<String, String> storage = new HashMap<>();

    public InMemoryKV(Map<String, String> storage) {
        storage.entrySet().stream().forEach(e -> this.storage.put(e.getKey(), e.getValue()));
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return storage.entrySet().stream()
                .flatMap(e -> Map.of(e.getKey(), e.getValue()).entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
// END
