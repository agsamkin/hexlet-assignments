package exercise;

import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(
            String sourcePath1, String sourcePath2, String resultPath) {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                return readFile(sourcePath1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                return readFile(sourcePath2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> futureResult = future1.thenCombine(future2, (str1, str2) -> {
            String result = str1 + str2;
            try {
                writeFile(resultPath, result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return result;

            // Обработка исключений
            // Если при работе задач возникли исключения
            // их можно обработать в методе exceptionally
        }).exceptionally(ex -> {
            System.out.println("NoSuchFileException");
            return null;
        });

        return futureResult;

    }

    public static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        System.out.println(path.toString());
        if (!Files.exists(path)) {
            throw new Exception("File " + filePath + " does not exist");
        }
        return Files.readString(path);
    }

    public static void writeFile(String filePath, String content) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        Files.writeString(path, content);
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles(
                "src\\main\\resources\\file1.txt",
                "src\\main\\resources\\file2.txt",
                "src\\main\\resources\\dest.txt");
        System.out.println(result.get());
        // END
    }
}

