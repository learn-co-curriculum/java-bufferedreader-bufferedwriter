package read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileIOMain {
    public static void main(String[] args) {
        String content = "";
        File file = new File("simple.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((content = bufferedReader.readLine()) != null) {
                System.out.println(content);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
