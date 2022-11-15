package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
class Tag {
    protected final String name;
    protected final Map<String, String> attr;

    public Tag(String name, Map<String, String> attr) {
        this.name = name;
        this.attr = attr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(name);
        for (Map.Entry<String, String> entry : attr.entrySet()) {
            sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        sb.append(">");
        return sb.toString();
    }
}
// END
