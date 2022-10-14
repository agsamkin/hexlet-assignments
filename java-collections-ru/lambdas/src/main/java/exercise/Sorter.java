package exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> "male".equals(user.get("gender")))
                .sorted(Comparator.comparing(o -> getBirthday(o.get("birthday"))))
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
    }

    private static Date getBirthday(String birthday) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(birthday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
