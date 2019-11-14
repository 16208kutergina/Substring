import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class BoyerMooreTest {

    @Test
    void isFoundSubstringIntoFileTrueTest() {
        try {
            FileReader fileReader = new FileReader("src\\main\\java\\Main.java");
            assertTrue(BoyerMoore.isFoundSubstringIntoFile("searchByData", fileReader));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isFoundSubstringIntoFileFalseTest() {
        try {
            FileReader fileReader = new FileReader("src\\main\\java\\Main.java");
            assertFalse(BoyerMoore.isFoundSubstringIntoFile("jhgkjgjgg", fileReader));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}