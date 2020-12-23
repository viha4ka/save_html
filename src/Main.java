import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        var fileName = "out.html";
        var stringUrl = "https://istudent.urfu.ru/";
        var html = getHTML(stringUrl);
        writeToFile(html, fileName);
        System.out.println("HTML со страницы " + stringUrl + " записан в файл " + fileName);
    }

    private static String getHTML(String stringUrl) throws IOException {
        var urlConnection = new URL(stringUrl).openConnection();
        var buffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        var result = buffer.lines().collect(Collectors.joining("\n"));
        buffer.close();
        return result;
    }

    private static void writeToFile(String text, String fileName) throws IOException {
        var path = Paths.get(fileName);
        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            Files.delete(path);
            Files.createFile(path);
        }
        FileWriter fw = new FileWriter(fileName);
        fw.write(text);
        fw.close();
    }
}



