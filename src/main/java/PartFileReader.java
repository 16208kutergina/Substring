import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartFileReader implements Runnable {
    private int patternSize;
    private int bufSize;
    private PairSmartBuffer pairSmartBuffer;
    private FileReader file;


    PartFileReader(int bufSize, int patternSize, FileReader file) {
        this.bufSize = bufSize;
        this.patternSize = patternSize;
        this.file = file;
        this.pairSmartBuffer = new PairSmartBuffer(bufSize + patternSize);
    }


    @Override
    public void run() {
        try {
            var in = new BufferedReader(file);
            int bytesRead;
            if ((bytesRead = in.read(pairSmartBuffer.getBufForWriting(), 0, bufSize + patternSize)) == -1) {
                file = null;
                return;
            }
            pairSmartBuffer.finishWrite();
            pairSmartBuffer.copyTailToNext(patternSize);

            while ((bytesRead = in.read(pairSmartBuffer.getBufForWriting(), patternSize, bufSize)) != -1) {
                pairSmartBuffer.finishWrite();
                if (bytesRead == bufSize) {
                    pairSmartBuffer.copyTailToNext(patternSize);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
