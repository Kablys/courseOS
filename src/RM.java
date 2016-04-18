/**
 * Created by Kostas on 2016-04-15.
 */
public class RM {

    public CPU cpu;
    private RealMemory realMemory;
    private MMU mmu;

    //private RealMemory externalMemory;
    //private InputDevice inputDevice;
    //private OutputDevice outputDevice;

    public final static int USER_MEMORY_SIZE = 256; //blocks
    public final static int BLOCK_SIZE = 16; //blocks
    public final static int pageTableAddress = 15*16*16; //3840

    public RM(){
        cpu = new CPU();
        realMemory = new RealMemory(USER_MEMORY_SIZE * BLOCK_SIZE);
        mmu = new MMU(cpu, realMemory, this);
        cpu.setMMU(mmu);

        //externalMemory = new RealMemory(mmu.BLOCKSIZE*SWAP_SIZE);
        //inputDevice = new InputDevice();
        //outputDevice = new OutputDevice();
    }

    public void newVM(){
        //PROGRAMOS KODAS
        Word w = new Word();
        w.setByte(0, 1);
        realMemory.write(0, w);
        w.setByte(0, 3);
        realMemory.write(1, w);
        w.setByte(0, 5);
        realMemory.write(2, w);

        //PUSLAPIAVIMAS
        for(int i = 0; i < 16; i++){
            w.setByte(0, i);
            realMemory.write(pageTableAddress + i, w);
        }




        /*int i = 0;
        while (mmu.wordToInt(realMemory.read(pageTableAddress + i*16 + 1)) != 0) {
            i++;
        }
        fillPageTable(i);
        cpu.setPTR(i*16 + pageTableAddress);*/

    }

    /*private void fillPageTable(int index){ // i - laisvas blokas puslapių lentelėje
        int block = 0; //realus blokas

        for(int i = 0; i < 16; i++){ //
            boolean taken = false;
            while(true) {
                for (int j = 0; j < 240; j++) {
                    if (realMemory.read(pageTableAddress + j)) == block){
                        taken = true;
                        break;
                    }
                }
                if (!taken) {
                    realMemory.write(block);
                    break;
                }
                block++;
            }
        }
    }*/

    public boolean nextStep(){
        cpu.step();
        proccessInterrupts();
        return true;
    }

    private void proccessInterrupts() {
        if(cpu.getPI() == 1){

        } else if (cpu.getPI() == 2){

        } else if (cpu.getPI() == 3){

        } //else if ...
    }

    public CPU getCPU() {
        return cpu;
    }
}
