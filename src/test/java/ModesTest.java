import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ModesTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream old = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(old);
    }

    @Test
    void searchByDataTrueTest() {
        Modes.searchByData("searchByData","src\\main\\java\\Main.java");
        assertEquals("src\\main\\java\\Main.java", output.toString().strip());
    }

    @Test
    void searchByDataFalseTest() {
        Modes.searchByData("kjhkhklhkl","src\\main\\java\\Main.java");
        assertEquals("", output.toString().strip());
    }

    @Test
    void searchByNameTrueTest() {
        Modes.searchByName("Main.java",".");
        assertEquals(".\\src\\main\\java\\Main.java", output.toString().strip());
    }

    @Test
    void searchByNameFalseTest() {
        Modes.searchByName("jhgkjgjgkj",".");
        assertEquals("", output.toString().strip());
    }
}