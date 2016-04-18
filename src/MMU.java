/**
 * Created by Kostas on 2016-04-15.
 */
public class MMU {

    private CPU cpu;
    private RealMemory memory;
    private RM rm;

    public MMU(CPU cpu, RealMemory realMemory, RM realMachine){
        this.cpu = cpu;
        this.memory = realMemory;
        this.rm = realMachine;
    }

    public Word read(int pc) {
        if(cpu.getMODE() == CPU.SUPERVISOR){
            return memory.read(pc);
        } else {
            //System.out.println("pc " + pc);
            int realAddress = virtualToRealAddress(pc);
            //System.out.println(realAddress);
            return memory.read(realAddress);
        }
    }

    public void write(Word word, int address){
        if(cpu.getMODE() == CPU.SUPERVISOR){
            memory.write(address, word);
        } else {
            int realAddress = virtualToRealAddress(address);
            memory.write(realAddress, word);
        }
    }

    public int virtualToRealAddress(int address){
        //System.out.println("PUSLAPIAVIMAS " + memory.read(rm.pageTableAddress).getByte(0));
        //System.out.println("a/16 " + address/16);
        int realBlockNum = memory.read(rm.pageTableAddress + address / 16).getByte(0);
        //System.out.println("realBlockNum " + realBlockNum);
        return realBlockNum * 16 + address % 16;
    }
}
