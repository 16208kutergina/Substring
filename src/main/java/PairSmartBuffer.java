public class PairSmartBuffer {
    private final int bufSize;
    private SmartBuffer[] buf;
    private int curIndexForWriting;
    private int curIndexForReading;

    public PairSmartBuffer(int bufSize) {
        this.curIndexForReading = 1;
        this.curIndexForWriting = 1;
        this.bufSize = bufSize;
        buf = new SmartBuffer[2];
        for(int i = 0; i < 2; i++) {
            buf[i] = new SmartBuffer(bufSize);
        }
    }

    public void copyTailToNext(int sizeTail){
        System.arraycopy(buf[curIndexForWriting].getBuf(), bufSize - sizeTail, buf[(curIndexForWriting+1)%2].getBuf(), 0, sizeTail);
    }

    private int getNextIndexForWriting(){
        curIndexForWriting = (curIndexForWriting+1)%2;
        return curIndexForWriting;
    }

    private int getNextIndexForReading(){
        curIndexForReading = (curIndexForReading+1)%2;
        return curIndexForReading;
    }

    public void finishWrite(){
        buf[curIndexForWriting].setReadyToRead(true);
    }

    public void finishRead(){
        buf[curIndexForReading].setReadyToRead(false);
    }

    public char[] getBufForWriting() {
        int nextIndexForReading = getNextIndexForReading();
        while (buf[nextIndexForReading].isReadyRead()){}
        return buf[getNextIndexForWriting()].getBuf();

    }

    public char[] getBufForReading() {
        int nextIndexForReading = getNextIndexForReading();
        while (!buf[nextIndexForReading].isReadyRead()){}
        return buf[nextIndexForReading].getBuf();

    }
}

class SmartBuffer {
    private  char[] buf;
    private boolean isReadyRead = true;

    char[] getBuf() {
        return buf;
    }

    public boolean isReadyRead() {
        return isReadyRead;
    }

    void setReadyToRead(boolean readyToRead) {
        isReadyRead = readyToRead;
    }

    SmartBuffer(int sizeBuf){
        buf = new char[sizeBuf];
    }
}
