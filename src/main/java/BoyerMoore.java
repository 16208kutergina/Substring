import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class BoyerMoore {
    static boolean isFoundSubstringIntoFile(String text, FileReader file) {
        try {
            int bufSize = 512;
            var in = new BufferedReader(file);
            int bytesRead;
            var myBuffer = new char[bufSize + text.length()];
            if ((bytesRead = in.read(myBuffer, 0, bufSize + text.length())) == -1) {
                return false;
            }
            if (isFoundSubstringBoyerMoore(text.toCharArray(), myBuffer)) {
                return true;
            }
            if (bytesRead == bufSize + text.length()) {
                System.arraycopy(myBuffer, bytesRead - text.length(), myBuffer, 0, text.length());
            }
            while ((bytesRead = in.read(myBuffer, text.length(), bufSize)) != -1) {
                if (isFoundSubstringBoyerMoore(text.toCharArray(), myBuffer)) {
                    return true;
                }
                if (bytesRead == bufSize) {
                    System.arraycopy(myBuffer, bytesRead, myBuffer, 0, text.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isFoundSubstringBoyerMoore(char[] pattern, char[] text) {
        var map = getShiftTable(pattern);
        int patternLength = pattern.length;
        int iterText = patternLength - 1;
        int iterPattern = patternLength - 1;
        while (iterText < text.length) {
            var tmpIterText = iterText;
            while (iterPattern >= 0) {
                if (text[tmpIterText] == pattern[iterPattern]) {
                    tmpIterText--;
                    iterPattern--;
                } else {
                    Integer countStep = map.get(text[tmpIterText]);
                    if (countStep == null) {
                        countStep = patternLength;
                    }
                    iterText += countStep;
                    break;
                }
            }
            if (iterPattern == -1) {
                return true;
            }
            iterPattern = patternLength - 1;
        }
        return false;
    }

    private static HashMap<Character, Integer> getShiftTable(char[] pattern) {
        var map = new HashMap<Character, Integer>();
        var patternLength = pattern.length;
        for (int i = 0; i < patternLength - 1; i++) {
            map.put(pattern[i], patternLength - i - 1);
        }
        return map;
    }
}
