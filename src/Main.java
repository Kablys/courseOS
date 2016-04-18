import javax.swing.*;

/**
 * Created by Kostas on 2016-04-15.
 */
public class Main {

    private static RM rm;
    private static GUI gui;

    public static void main(String[] args) {
        rm = new RM();
        gui = new GUI(rm);

        JFrame frame = new JFrame("MyForm");
        frame.setContentPane(gui.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        boolean run = true;

        rm.newVM();
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
