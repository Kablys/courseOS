public class CPU {

    public final static byte ADD = 1;
    public final static byte SUB = 2;
    public final static byte MUL = 3;
    public final static byte DIV = 4;
    public final static byte MOD = 5;
    public final static byte MOVR1X = 6;
    public final static byte MOVR2X = 7;
    public final static byte MOVXR1 = 8;
    public final static byte MOVXR2 = 9;
    //public final static byte MOVRR = 8;
    //public final static byte HALT = 10;
    public final static byte PUSH = 10;
    public final static byte POP = 11;
    //public final static byte POPX = 12;
    //public final static byte RPUSH = 13;
    //public final static byte RPOP = 14;
    public final static byte CMP = 12;
    public final static byte JMP = 13;
    public final static byte JMPE = 14;
    public final static byte JMPM = 15;
    public final static byte JMPL = 16;


    private int MODE = 0;
    private int R1 = 0;     //bendro naudojimo
    private int R2 = 0;
    private int PC = 0;     //komandų pointeris
    private int PTR = 0;    //puslapių lentelės registras
    private int SP = 0;     //steko viršūnės registras
    private int TI = 0;     //timer'is
    private int SI = 0;     //supervizorinių pertraukimų registras
    private int PI = 0;     //programinių pertraukimų registras
    private int C = 0;
    private int CH1 = 0;
    private int CH2 = 0;
    private int CH3 = 0;

    public static final int SUPERVISOR = 0;
    public static final int USER = 1;
    private static final int TIME = 20;

    private MMU mmu;

    public CPU() {
        MODE = SUPERVISOR;
        TI = TIME;
    }

    public void setMMU(MMU mmu){
        this.mmu = mmu;
    }


    public void step() {
        //Word cmd = mmu.read(PC);
        try {
            interpretCommand(mmu.read(PC));
        } catch (Exception exeption){
            setPI(1);
        }
        TI--; // po kiekvienos instrukcijos
    }

    private void interpretCommand(Word word) {
        PC++;
        byte command = word.getByte(0);
        try{
            switch (command){
                case ADD: {
                    setR1(R1 + R2);
                    break;
                }
                case SUB: {
                    setR1(R1 - R2);
                    break;
                }
                case MUL: {
                    setR1(R1 * R2);
                    break;
                }
                case DIV: {
                    setR1(R1 / R2);
                    break;
                }
                case MOD: {
                    setR1(R1 % R2);
                    break;
                }
                case MOVXR1: {
                    int x = word.getByte(3);
                    setR1(Word.wordToInt(mmu.read(x)));
                    break;
                }
                case MOVXR2: {
                    int x = word.getByte(3);
                    setR2(Word.wordToInt(mmu.read(x)));
                    break;
                }
                case MOVR1X: {
                    int x = word.getByte(3);
                    mmu.write(Word.intToWord(getR1()), x);
                }
                case MOVR2X: {
                    int x = word.getByte(3);
                    mmu.write(Word.intToWord(getR2()), x);
                }
//                case MOVRR: {
//
//                }
//                case HALT: {
//                    SI = 3;
//                    break;
//                }
                case PUSH: {

                }
                case POP: {

                }
//                case POPX: {
//
//                }
//                case RPUSH:{
//
//                }
//                case RPOP: {
//
//                }
                case CMP: {
                    if(getR1() == getR1()){
                        setC(0);
                    }else if (getR1() > getR1()){
                        setC(1);
                    }else {
                        setC(2);
                    }
                }
                case JMP: {
                    int x = word.getByte(3);
                    //todo pakeisti sita eilute vietoje mmu.read(x)
                    setPC(Word.wordToInt(mmu.read(x)));
                    break;
                }
                case JMPE: {
                    if(getC() == 0){
                        int x = word.getByte(3);
                        setPC(Word.wordToInt(mmu.read(x)));
                    }
                    break;
                }
                case JMPM: {
                    if(getC() == 1){
                        int x = word.getByte(3);
                        setPC(Word.wordToInt(mmu.read(x)));
                    }
                    break;
                }
                case JMPL: {
                    if(getC() == 2){
                        int x = word.getByte(3);
                        setPC(Word.wordToInt(mmu.read(x)));
                    }
                    break;
                }
                default: {
                    PI = 2;
                    break;
                }
            }
        } catch (Exception ex) {
            PI = 1;
        }
    }

    public int getMODE() {
        return MODE;
    }

    public void setMODE(int MODE) {
        this.MODE = MODE;
    }

    public int getR1() {
        return R1;
    }

    public void setR1(int r1) {
        R1 = r1;
    }

    public int getR2() {
        return R2;
    }

    public void setR2(int r2) {
        R2 = r2;
    }

    public int getC() {
        return C;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getPTR() {
        return PTR;
    }

    public void setPTR(int PTR) {
        this.PTR = PTR;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public int getTI() {
        return TI;
    }

    public void setTI(int TI) {
        this.TI = TI;
    }

    public int getSI() {
        return SI;
    }

    public void setSI(int SI) {
        this.SI = SI;
    }

    public int getPI() {
        return PI;
    }

    public void setPI(int PI) {
        this.PI = PI;
    }

    public int getCH1() {
        return CH1;
    }

    public void setCH1(int CH1) {
        this.CH1 = CH1;
    }

    public int getCH2() {
        return CH2;
    }

    public void setCH2(int CH2) {
        this.CH2 = CH2;
    }

    public int getCH3() {
        return CH3;
    }

    public void setCH3(int CH3) {
        this.CH3 = CH3;
    }

}
