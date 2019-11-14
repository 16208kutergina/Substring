public class Main {

    public static void main(String[] args) {
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
