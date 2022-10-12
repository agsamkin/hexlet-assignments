package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {

    public static void main(String[] args) {
        String s = "info@gmail.com";
        System.out.println(1);
    }

    private static List<String> freeDomains = Arrays.asList("gmail.com", "yandex.ru", "hotmail.com");
    public static long getCountOfFreeEmails(List<String> emailsList) {
        return emailsList.stream()
                .map(s -> s.split("@"))
                .filter(arr -> arr.length == 2)
                .map(arr -> arr[1])
                .filter(s -> freeDomains.contains(s)).count();
    }
}
// END
