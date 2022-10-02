package write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOMain {
    public static void main(String[] args) {
        File file = new File("simple.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("example of writing to a file.");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
