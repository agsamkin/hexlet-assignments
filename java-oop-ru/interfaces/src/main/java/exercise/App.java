package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static void main(String[] args) {
        List<Home> appartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> result = App.buildAppartmentsList(appartments, 3);
        System.out.println(result); // =>
    }

    public static List<String> buildAppartmentsList(List<Home> appartments, int count) {
        return appartments.stream().limit(count).sorted(Comparator.comparing(Home::getArea)).map(a -> a.toString()).collect(Collectors.toList());
    }
}
// END
