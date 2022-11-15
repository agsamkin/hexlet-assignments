package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {
    private final String body;
    private final List<Tag> child;

    public PairedTag(String name, Map<String, String> attr, String body, List<Tag> child) {
        super(name, attr);
        this.body = body;
        this.child = child;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (body.isEmpty()) {
            for (Tag child : child) {
                sb.append(child.toString());
            }
        } else {
            sb.append(body);
        }
        sb.append("</").append(name).append(">");
        return sb.toString();
    }
}
// END
