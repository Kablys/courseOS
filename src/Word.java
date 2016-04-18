/**
 * Created by Kostas on 2016-04-15.
 */
public class Word {
    private static final int SIZE = 4; //numbet or bytes
    private byte[] data;

    public Word() {
        data = new byte[SIZE];
        data[0] = 0;
        data[1] = 0;
        data[2] = 0;
        data[3] = 0;
    }

    public Word(Word src){
        data = src.data.clone();
    }

    @Override
    public Word clone(){
        return new Word(this);
    }

    public byte getByte(int i) {
        return data[i];
    }

    public void setByte(int i, int d){
        data[i] = (byte)d;
    }
}
