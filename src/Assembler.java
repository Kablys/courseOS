public class Assembler {
    //Registers
    //private int PTR;
    private int IC;
    private int TI;
    private int R1;
    private int R2;
    private int C;
    private boolean MODE; // true = supervisor, false = vm

    public Assembler() {
        MODE = true;
        IC = 0;
        R1 = 0;
        R2 = 0;
    }



    public void execute (String[] instruction){
        //System.out.println(instruction.length);
        
        switch (instruction[0]) {
            // Arithmetic
            case "ADD": add();break;
            case "SUB": sub();break;
            case "MUL": mul();break;
            case "DIV": div();break;
            case "MOD": mod();break;
            // MOV
            case "MOV": mod();break;
            // Stack
            case "PUSH": mod();break;
            case "POP": mod();break;
            // Control
            case "CMP": cmp();break;
            case "JMP": jmp(instruction[1]);break;
            case "JMPE": jmpe(instruction[1]);break;
            case "JMPM": jmpm(instruction[1]);break;
            case "JMPL": jmpl(instruction[1]);break;
            // I/O
//            case "RD": mod(instruction[1]);
//            case "WR": mod(instruction[1]);
            // Register
            case "CHMOD": chmod();break;
            case "SETTI": setti(instruction[1]);break;
            default:
                System.out.printf("Non existing instruction");
        }
        
    }

    private void cmp() {
        if(R1 == R2){
            C = 0;
        }else if (R1 > R2){
            C = 1;
        }else {
            C = 2;
        }
        System.out.println("cmp");
    }

    private void jmpe(String s) {
        if(C == 0){
            IC = Integer.parseInt(s);
        }
    }
    private void jmpm(String s) {
        if(C == 1){
            IC = Integer.parseInt(s);
        }
    }
    private void jmpl(String s) {
        if(C == 2){
            IC = Integer.parseInt(s);
        }
    }

    private void jmp(String s) {
        cpu.setIC(Integer.parseInt(s));
        IC = Integer.parseInt(s);
    }

    private void chmod() {
        if (MODE == true){
            MODE = false;
        }else {
            MODE = true;
        }
    }

    private void mod() {
        R1 = R1 % R2;
    }

    private void div() {
        R1 = R1 / R2;
    }

    private void mul() {
        R1 = R1 * R2;
    }

    private void sub() {
        R1 = R1 - R2;
    }

    private void add() {
        R1 = R1 + R2;
    }

    public void setti(String ti) {
        TI = Integer.parseInt(ti);
    }

    public Integer setIC(Integer vmAddr){
        mmu.conver(vmAddr);


    }
}
