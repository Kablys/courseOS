/**
 * Created by Kostas on 2016-04-15.
 */
public class RealMemory {

    private Word[] memory;

    public RealMemory(int size) {
        memory = new Word[size];
        for(int i = 0; i < size; i++){
            memory[i] = new Word();
        }
    }

    public Word read(int i){
        return memory[i];
    }

    public void write(int i, Word word){

        memory[i] = word.clone();
    }

}
