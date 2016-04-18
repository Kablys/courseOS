import javax.swing.*;

public class Main {

        Assembler cpu = new Assembler("test.txt");
    private static RM rm;
    private static GUI gui;

    public static void main(String[] args) {
        //Sita eilute testuoti assembleriui
        //jeigu nereikia galima uzkomentuoti
        Assembler cpu = new Assembler("test.txt");

        rm = new RM();
        gui = new GUI(rm);
        rm.newVM();

        JFrame frame = new JFrame("MyForm");
        frame.setContentPane(gui.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        boolean run = true;


        //rm.loadProgram();
        rm.cpu.setMODE(1);

        /*int i = 0;
        while (i < 3) {
            run = rm.nextStep(); // gražins false, kai pabaiga
            gui.update();
            i++;
        }*/
    }
}
