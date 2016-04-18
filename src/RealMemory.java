/**
 * Created by Kostas on 2016-04-15.
 */
public class RealMemory {

    private Word[] memory;

    public RealMemory(int size) {
        memory = new Word[size];
    }

    public Word read(int i){
        return memory[i];
    }

    public void write(int i, Word word){

        memory[i] = word.clone();
    }

}
