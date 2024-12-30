package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
    public static String readCode(String filePath) {
        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read file on path: " + filePath, e);
        }
    }

    public static void writeCode(String code, String filePath) {
        try {
            Files.write(Paths.get(filePath), code.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
