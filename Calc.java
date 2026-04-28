import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class Calc extends JPanel {
    private JPanel top = new JPanel();
    private JPanel bottom = new JPanel();
    private JLabel calc = new JLabel("Calc:");
    private JTextField input= new CalcTextFields("Es: 4+4", new Dimension(199,18));
    private JTextField dec = new CalcTextFields("DEC" , new Dimension(45,18));
    private JTextField hex = new CalcTextFields("HEX", new Dimension(65,18));
    private JTextField oct = new CalcTextFields("OCT", new Dimension(45,18));
    private JTextField bin = new CalcTextFields("BIN", new Dimension(85,18));
    private JButton calcBtn = new Buttons("=");
    public Calc(){
        this.setPreferredSize(stdThings.getStdLeftDimension());
        this.setBackground(stdThings.getColor1());
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 8, 8, 8));
        calc.setForeground(Color.white);
        top.setLayout(new FlowLayout());
        top.setBackground(stdThings.getColor1());
        top.add(calc);
        top.add(input);
        top.add(calcBtn);
        bottom.setLayout(new FlowLayout());
        bottom.setBackground(stdThings.getColor1());
        bottom.add(dec);
        bottom.add(oct);
        bottom.add(hex);
        bottom.add(bin);
        this.add(top,BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
        calcBtn.addActionListener(e -> {
            doMath(input,dec,hex,oct,bin);
        });    
    }
    public void doMath(JTextField input, JTextField dec, JTextField hex, JTextField oct, JTextField bin){
        String[] mathArr = new String[0];
        String value = input.getText();
        String regex = "(?<=[+-*/])|(?=[+-*/])";
        try {
            mathArr = value.split(regex);
            
        } catch (Exception e) {
            input.setText("Err");
        }

        
    }
}
