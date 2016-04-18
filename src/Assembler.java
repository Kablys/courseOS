import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Assembler {

    public Assembler(String filename) {
        Path path = Paths.get("test.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
            String[] line;
            String s;
            List<Word> program = new ArrayList<Word>();
            //program.add(new Word());
            Boolean dataPart = false;
            Boolean codePart = false;
            while ((s = br.readLine()) != null) {
                line = s.toUpperCase().split(" ");
                if (line[0].equals("DATA" )) {
                    if (dataPart == false){
                        dataPart = true;
                    }
                    else{
                        System.out.println("There can't be more than one DATA");
                        System.exit(1);
                    }

                }else if (line[0].equals("CODE") ) {
                    if (codePart == false){
                        codePart = true;
                    }
                    else{
                        System.out.println("There can't be more than one CODE");
                        System.exit(1);
                    }

                }else if (codePart == false){
                    //read data
                }else {
                    //read code
                    System.out.println("s");
                    Word instruction = new Word();
                    switch (line[0]) {
                        // Arithmetic
                        case "ADD":
                            program.add(simpleIns(1, 0));
                            break;
                        case "SUB":
                            program.add(simpleIns(2, 0));
                            break;
                        case "MUL":
                            program.add(simpleIns(3, 0));
                            break;
                        case "DIV":
                            program.add(simpleIns(4, 0));
                            break;
                        case "MOD":
                            program.add(simpleIns(5, 0));
                            break;
                        // MOV
                        case "MOV":
                            if (line[1].equals("R1")) {
                                program.add(simpleIns(6, Integer.parseInt(line[1])));
                            }else if(line[1].equals("R2")) {
                                program.add(simpleIns(7, Integer.parseInt(line[1])));
                            }else {
                                if (line[2].equals("R1")) {
                                    program.add(simpleIns(8, Integer.parseInt(line[1])));

                                } else if (line[2].equals("R2")) {
                                    program.add(simpleIns(9, Integer.parseInt(line[1])));
                                }
                            }
                            break;
                        // Stack
                        case "PUSH":
                            instruction.setByte(0, (byte)10);
                            program.add(instruction);
                            break;
                        case "POP":
                            instruction.setByte(0, (byte)11);
                            program.add(instruction);
                            break;
                        // Control
                        case "CMP":
                            instruction.setByte(0, (byte)12);
                            program.add(instruction);
                            break;
                        case "JMP":
                            program.add(simpleIns(13, Integer.parseInt(line[1])));
                            break;
                        case "JMPE":
                            program.add(simpleIns(14, Integer.parseInt(line[1])));
                            break;
                        case "JMPM":
                            program.add(simpleIns(15, Integer.parseInt(line[1])));
                            break;
                        case "JMPL":
                            program.add(simpleIns(16, Integer.parseInt(line[1])));
                            break;
                        // I/O
                        case "RD":
                            break;
                        case "WR":
                            break;
                            // Register
                        case "CHMOD":
                            break;
                        case "SETTI":
                            break;
                        default:
                            System.out.printf("Non existing instruction");
                    }

                }
            }
            for (Word w: program){
                //realMemory.write(pageTableAddress + i, w);
            }
            System.out.println("test");

    } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Word simpleIns(int instructionCode, int addr){
        Word instruction = new Word();
        instruction.setByte(0, (byte)instructionCode);
        instruction.setByte(3, (byte)addr);
        return instruction;

    }


//    private void cmp() {
//        if(R1 == R2){
//            C = 0;
//        }else if (R1 > R2){
//            C = 1;
//        }else {
//            C = 2;
//        }
//        System.out.println("cmp");
//    }
//
//    private void jmpe(String s) {
//        if(C == 0){
//            IC = Integer.parseInt(s);
//        }
//    }
//    private void jmpm(String s) {
//        if(C == 1){
//            IC = Integer.parseInt(s);
//        }
//    }
//    private void jmpl(String s) {
//        if(C == 2){
//            IC = Integer.parseInt(s);
//        }
//    }
//
//    private void jmp(String s) {
//        cpu.setIC(Integer.parseInt(s));
//        IC = Integer.parseInt(s);
//    }
//
//    private void chmod() {
//        if (MODE == true){
//            MODE = false;
//        }else {
//            MODE = true;
//        }
//    }
//
//    private void mod() {
//        R1 = R1 % R2;
//    }
//
//    private void div() {
//        R1 = R1 / R2;
//    }
//
//    private void mul() {
//        R1 = R1 * R2;
//    }
//
//    private void sub() {
//        R1 = R1 - R2;
//    }
//
//    private void add() {
//        R1 = R1 + R2;
//    }
//
//    public void setti(String ti) {
//        TI = Integer.parseInt(ti);
//    }
//
//    public Integer setIC(Integer vmAddr){
//        mmu.conver(vmAddr);
//
//
//    }
}
