package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        System.out.println(App.getMinMax(numbers)); // => {min=-100, max=100}
    }

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers)  {
        Map<String, Integer> result = new HashMap<>();

        MinThread minThread = new MinThread(numbers);
        minThread.start();
        LOGGER.info("Thread " + minThread.getName() + " started");

        MaxThread maxThread = new MaxThread(numbers);
        maxThread.start();
        LOGGER.info("Thread " + maxThread.getName() + " started");

        try {
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Thread " + minThread.getName() + " finished");

        try {
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Thread " + maxThread.getName() + " finished");

        result.put("min", minThread.getMin());
        result.put("max", maxThread.getMax());

        return result;
    }
    // END
}
