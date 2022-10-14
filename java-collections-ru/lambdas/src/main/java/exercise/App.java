package exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.function.BiFunction;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                // Удваиваем каждую строку
                .map(row -> new String[][]{row, row})
                .flatMap(row -> Arrays.stream(row))
                // Удваиваем каждый элемент каждой строки
                .map(row -> Arrays.stream(row)
                        .map(e -> new String[] {e ,e})
                        .flatMap(e -> Arrays.stream(e))
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }

}
// END
