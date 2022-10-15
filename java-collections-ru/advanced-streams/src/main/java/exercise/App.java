package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static String getForwardedVariables(String data) {
        return Arrays.stream(data.split("\n"))
                .filter(s -> s.startsWith("environment="))
                .map(s -> s.replace("environment=", ""))
                .map(s -> s.replace("\"", ""))
                .map(s -> s.split(","))
                .flatMap(strings -> Arrays.stream(strings))
                .filter(s -> s.startsWith("X_FORWARDED_"))
                .map(s -> s.replace("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
