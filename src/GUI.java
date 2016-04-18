import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kostas on 2016-04-10.
 */
public class GUI extends JFrame{
    private JTextField reg_1;
    private JTextField reg_2;
    private JTextField reg_3;
    private JTextField reg_4;
    private JTextField reg_5;
    private JTextField reg_6;
    private JTextField reg_7;
    private JButton next_button;
    private JScrollPane memory_field;
    public JPanel rootPanel;
    private JPanel aa;
    private JPanel bb;

    private RM rm;
    private CPU cpu;

    public GUI(RM rm) {

        this.rm = rm;
        cpu = rm.getCPU();
        update();

    next_button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            rm.nextStep();
            update();
        }
    });
        //frameInit();

       /* setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    }

    public void setData(GUI data) {
    }

    public void getData(GUI data) {
    }

    public boolean isModified(GUI data) {
        return false;
    }

    public void update(){
        reg_1.setText(Integer.toString(cpu.getMODE())); //TODO: gauti registų reikšmes
    }
}
