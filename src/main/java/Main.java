import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
//        try {
//            FileReader fileReader = new FileReader("C:\\Users\\kuter\\yourfilehere.dat");
//            int bufSize = 512;
//            var in = new BufferedReader(fileReader);
//            var myBuffer = new char[bufSize];
//                int read = in.read(myBuffer, 0, bufSize);
//            System.out.println(myBuffer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (args.length < 3) {
            printOptions();
            return;
        }
        for (String arg : args) {
            if (arg.equals("-h")) {
                printOptions();
                return;
            }
        }
        switch (args[0]) {
            case "--name":
                Modes.searchByName(args[1], args[2]);
                break;
            case "--data":
                Modes.searchByData(args[1], args[2]);
                break;
            default:
                printOptions();
        }
    }

    private static void printOptions() {
        String options = "For search by file input: --name \"<text>\" <folder>\n" +
                "For search by data input: --data \"<text>\" <folder|file>\n " +
                "Help: -h";
        System.out.println(options);
    }
}
