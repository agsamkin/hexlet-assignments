package exercise;

// BEGIN
class MinThread extends Thread {

    private final int[] numbers;
    private int min;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            min = Math.min(number, min);
        }
    }
}
// END
