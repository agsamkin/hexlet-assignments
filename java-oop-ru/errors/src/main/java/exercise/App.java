package exercise;

// BEGIN
class App {
    public static void printSquare(Circle circle) {
        try {
            System.out.println(circle.getSquare());
        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Вычисление окончено");
    }
}
// END
