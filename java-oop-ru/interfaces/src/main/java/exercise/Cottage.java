package exercise;

import java.util.Locale;

// BEGIN
class Cottage implements Home {
    private final double area;
    private final int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        return Double.compare(this.getArea(), another.getArea());
    }

    @Override
    public String toString() {

        return String.format(Locale.US, "%d этажный коттедж площадью %.1f метров", floorCount, getArea());
    }
}
// END
