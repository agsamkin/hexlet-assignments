package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class FileKV implements KeyValueStorage {
    private final String filepath;

    public FileKV(String filepath, Map<String, String> storage) {
        this.filepath = filepath;
        Utils.writeFile(filepath, Utils.serialize(storage));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> storage = Utils.unserialize(Utils.readFile(filepath));
        storage.put(key, value);
        Utils.writeFile(filepath, Utils.serialize(storage));
    }

    @Override
    public void unset(String key) {
        Map<String, String> storage = Utils.unserialize(Utils.readFile(filepath));
        storage.remove(key);
        Utils.writeFile(filepath, Utils.serialize(storage));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> storage = Utils.unserialize(Utils.readFile(filepath));
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(filepath));
    }
}
// END
