import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Modes {
    static void searchByData(String text, String path) {
        try {
            Files.find(Paths.get(path),
                    Integer.MAX_VALUE,
                    (filePath, fileAttributes) -> fileAttributes.isRegularFile())
                    .filter((filePath)-> {
                        try {
                            return BoyerMoore.isFoundSubstringIntoFile(text, new FileReader(filePath.toString()));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void searchByName(String text, String path) {
        try {
            Files.find(Paths.get(path),
                    Integer.MAX_VALUE,
                    (filePath, fileAttributes) -> fileAttributes.isRegularFile())
                    .filter(file -> file.getFileName().toString().contains(text))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
