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
    private JTextField reg_8;
    private JTextField reg_9;
    private JTextField reg_10;
    private JTextField reg_11;
    private JTextField reg_12;
    private JButton next_button;
    private JScrollPane memory_field;
    public JPanel rootPanel;
    private JPanel aa;
    private JPanel bb;
    private JTextArea textArea1;

    private RM rm;
    private CPU cpu;

    public GUI(RM r) {

        this.rm = r;
        this.cpu = rm.getCPU();
        update();

        next_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rm.nextStep()) System.exit(777);
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
        reg_2.setText(Integer.toString(cpu.getR1()));
        reg_3.setText(Integer.toString(cpu.getR2()));
        reg_4.setText(Integer.toString(cpu.getPC()));
        reg_5.setText(Integer.toString(cpu.getPTR()));
        reg_6.setText(Integer.toString(cpu.getSP()));
        reg_7.setText(Integer.toString(cpu.getTI()));
        reg_8.setText(Integer.toString(cpu.getSI()));
        reg_9.setText(Integer.toString(cpu.getPI()));
        reg_10.setText(Integer.toString(cpu.getCH1()));
        reg_11.setText(Integer.toString(cpu.getCH2()));
        reg_12.setText(Integer.toString(cpu.getCH3()));

        String mem = "";
        String num;
        for(int i = 0; i < 256; i++){
            num = String.format("%03d:", i);
            Word w;
            try {
                w = rm.mmu.read(i);

                mem = mem + num +  "    "
                        + String.format("%03d ", w.getByte(0))
                        + String.format("%03d ", w.getByte(1))
                        + String.format("%03d ", w.getByte(2))
                        + String.format("%03d ", w.getByte(3)) + "\n";

            } catch ( Exception ex ){
                System.out.println("memmory error GUI");
            }
            textArea1.setText(mem);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
