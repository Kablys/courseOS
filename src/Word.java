/**
 * Created by Kostas on 2016-04-15.
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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

    public static int wordToInt(Word word) {
        ByteBuffer bb = ByteBuffer.allocateDirect(SIZE);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.clear();
        for(int i = 0; i < SIZE; i++){
            bb.put(word.getByte(i));
        }
        bb.position(0);
        return bb.getInt();
    }

    public static Word intToWord(int value) {
        ByteBuffer bb = ByteBuffer.allocateDirect(SIZE);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.clear();
        bb.putInt(value);
        Word word = new Word();
        for(int i = 0; i < SIZE; i++){
            word.setByte(i, bb.get(i));
        }
        return word;
    }
}
