import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Assembler {

    RM rm;

    public Assembler(RM rm, String filename) {
        this.rm = rm;
        Path path = Paths.get("test.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
            String[] line;
            String s;
            List<Word> program = new ArrayList<Word>();
            HashMap<Integer, Word> data = new HashMap<Integer, Word>();
            //program.add(new Word());
            Boolean dataPart = false;
            Boolean codePart = false;
            while ((s = br.readLine()) != null) {
                line = s.toUpperCase().split(" ");
                if (line[0].equals("DATA")) {
                    if (dataPart == false) {
                        dataPart = true;
                    } else {
                        System.out.println("There can't be more than one DATA");
                        System.exit(1);
                    }

                } else if (line[0].equals("CODE")) {
                    if (codePart == false) {
                        codePart = true;
                    } else {
                        System.out.println("There can't be more than one CODE");
                        System.exit(1);
                    }

                } else if (codePart == false) {
                    //read data
                    data.put(Integer.parseInt(line[0]),// address
                            Word.intToWord(Integer.parseInt(line[1])));// Integer
                } else {
                    //read code
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
                            } else if (line[1].equals("R2")) {
                                program.add(simpleIns(7, Integer.parseInt(line[1])));
                            } else {
                                if (line[2].equals("R1")) {
                                    program.add(simpleIns(8, Integer.parseInt(line[1])));

                                } else if (line[2].equals("R2")) {
                                    program.add(simpleIns(9, Integer.parseInt(line[1])));
                                }
                            }
                            break;
                        // Stack
                        case "PUSH":
                            instruction.setByte(0, (byte) 10);
                            program.add(instruction);
                            break;
                        case "POP":
                            instruction.setByte(0, (byte) 11);
                            program.add(instruction);
                            break;
                        // Control
                        case "CMP":
                            instruction.setByte(0, (byte) 12);
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
                        case "HALT":
                            program.add(simpleIns(17, Integer.parseInt(line[0])));
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
            for (Integer addr : data.keySet()) {
                rm.realMemory.write(addr, data.get(addr));
                //System.out.println(addr, data.get(addr));


            }
            int i = 0;
            for (Word w : program) {
                rm.realMemory.write(i, w);
                i++;
                //realMemory.write(pageTableAddress + i, w);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Word simpleIns(int instructionCode, int addr) {
        Word instruction = new Word();
        instruction.setByte(0, (byte) instructionCode);
        instruction.setByte(3, (byte) addr);
        return instruction;

    }
}