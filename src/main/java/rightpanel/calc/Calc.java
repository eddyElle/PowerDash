package rightpanel.calc;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import share.StdButtons;
import share.StdLabels;
import share.StdThings;
import share.StdTextFields;

import java.util.ArrayList;
public class Calc extends JPanel { //ricorda di lavorare con il .jar
    private JPanel top = new JPanel();
    private JPanel bottom = new JPanel();
    private JLabel calc = new StdLabels("Calc:");
    private StdTextFields input= new StdTextFields("Es: 4+4", new Dimension(179,18));
    private StdTextFields dec = new StdTextFields("DEC" , new Dimension(45,18));
    private StdTextFields hex = new StdTextFields("HEX", new Dimension(65,18));
    private StdTextFields oct = new StdTextFields("OCT", new Dimension(45,18));
    private StdTextFields bin = new StdTextFields("BIN", new Dimension(85,18));
    private JButton calcBtn = new StdButtons("=");
    private JButton clsBtn = new StdButtons("C");
    private ArrayList<StdTextFields> fields = new ArrayList<>();
    public Calc(){
        fields.add(input);
        fields.add(dec);
        fields.add(oct);
        fields.add(hex);
        fields.add(bin);
        this.setPreferredSize(new Dimension(280,70));
        this.setBackground(StdThings.getColor1());
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 8, 8, 8));
        top.setLayout(new FlowLayout());
        top.setBackground(StdThings.getColor1());
        top.add(calc);
        top.add(fields.get(0));
        top.add(calcBtn);
        top.add(clsBtn);
        bottom.setLayout(new FlowLayout());
        bottom.setBackground(StdThings.getColor1());
        for(int i=1;i<fields.size();i++){
            bottom.add(fields.get(i));
        }
        this.add(top,BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
        for(int i=0;i<fields.size();i++){
            fields.get(i).addActionListener(e ->{
                doMath(input, dec, hex, oct, bin);
            });
        }
        calcBtn.addActionListener(e -> {
            doMath(input,dec,hex,oct,bin);
        });  
        clsBtn.addActionListener(e -> {
            unDoMath(input,dec,hex,oct,bin);
        });  
    }
    public void doMath(StdTextFields input, StdTextFields dec, StdTextFields hex, StdTextFields oct, StdTextFields bin){
        ArrayList<StdTextFields> fields = new ArrayList<>();
        int hist = 0; //history
        int sel = 0; //selector
        String toConvert = "";
        fields.add(dec);
        fields.add(oct);
        fields.add(hex);
        fields.add(bin);
        try {
            String text = input.getText(); 
            if((text.equals("")) || (text.equals(null)) || (text.contains("Err:")) || (text.contains("Es:"))){
                for(int i = 0;i<fields.size();i++){
                    if(!fields.get(i).getText().equals("OCT") && !fields.get(i).getText().equals("BIN") && !fields.get(i).getText().equals("HEX") && !fields.get(i).getText().equals("DEC")){
                        sel = i;
                        hist++;
                    }
                }
                if(hist == 1){
                    switch (sel) {
                        case 0:
                            
                            input.setText("Err: decToDec convertion?");
                            break;
                        case 1:
                            toConvert = fields.get(1).getText();
                            
                            input.setText(Integer.parseInt(toConvert,8) + "");
                            break;
                        case 2:
                            toConvert = fields.get(2).getText();
                            
                            input.setText(Integer.parseInt(toConvert,16) + "");
                            break;
                        case 3:
                            toConvert = fields.get(3).getText();
                            
                            input.setText(Integer.parseInt(toConvert,2) + "");
                        default:
                            break;
                    }
                }
                else if(hist > 1){
                    
                    input.setText("Err: moreInputsToConvert");
                }
            }
            else{
                Expression e = new ExpressionBuilder(text).build();
                double result = e.evaluate();
                fields.get(0).setText(result + "");
                Integer floored = (int)Math.round(result);
                fields.get(1).setText(Integer.toOctalString(floored));
                fields.get(2).setText(Integer.toHexString(floored));
                fields.get(3).setText(Integer.toBinaryString(floored));
            }
        } catch (Exception e) {
            input.setText("Err: invalidExpression");
            System.out.println(e.getMessage());
        }
    }
    public void unDoMath(StdTextFields input, StdTextFields dec, StdTextFields hex, StdTextFields oct, StdTextFields bin){
        ArrayList<StdTextFields> fields = new ArrayList<>();
        fields.add(dec);
        fields.add(oct);
        fields.add(hex);
        fields.add(bin);
        input.clearAll();
        for(int i = 0;i<fields.size();i++){
            fields.get(i).clearAll();
        }
    }
}
