package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) throws IOException {
        String json = car.serialize();
        Files.writeString(path, json);
    }

    public static Car extract(Path fixturePath) throws IOException {
        String json = Files.readString(fixturePath);
        return Car.unserialize(json);
    }
}
// END
