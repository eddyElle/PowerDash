package rightpanel.hwmonitor;
import java.awt.*;
import javax.swing.*;

import share.StdLabels;
import share.StdThings;
public class Gauge extends JPanel {
    private JPanel[] tiks = new JPanel[10];
    private JPanel tiksContainer = new JPanel();
    private JLabel defaultLabel = new StdLabels("");
    private JLabel defaultPercentage = new StdLabels("0");
    private JPanel labelContainer = new JPanel();
    public Gauge(String name){
        this.setLayout(new BorderLayout());
        this.setBackground(StdThings.getColor1());
        this.add(labelContainer, BorderLayout.NORTH);
        this.add(tiksContainer, BorderLayout.SOUTH);
        tiksContainer.setBackground(StdThings.getColor1());
        tiksContainer.setLayout(new GridLayout(10,0));
        tiksContainer.setPreferredSize(new Dimension(50,50));
        for(int i=0;i<tiks.length;i++){
            tiks[i] = new JPanel();
            tiksContainer.add(tiks[i]);
            tiks[i].setBackground(StdThings.getGray1());
            tiks[i].setBorder(BorderFactory.createLineBorder(Color.black));
        }
        labelContainer.setBackground(StdThings.getColor1());
        labelContainer.setLayout(new GridLayout(2,0));
        labelContainer.add(defaultLabel);
        labelContainer.add(defaultPercentage);
        defaultLabel.setText(name);
        defaultLabel.setHorizontalAlignment(JLabel.CENTER);
        defaultLabel.setVerticalAlignment(JLabel.CENTER);
        defaultPercentage.setHorizontalAlignment(JLabel.CENTER);
        defaultPercentage.setVerticalAlignment(JLabel.CENTER);
    }
    public int setPercentage(int percentage){
        defaultPercentage.setText(percentage + "%");
        int newPercentage = Math.round(percentage/10);
        if(newPercentage == 10){
            for(int i=0;i<tiks.length;i++){
                tiks[i].setBackground(Color.RED);
            }
            return 0;
        }
        for(int i = 0;i<newPercentage;i++){
            int select = 9 - i;
            if(select > 5){
                tiks[select].setBackground(Color.GREEN);
            }
            else if(select <= 5 && select > 1){
                tiks[select].setBackground(Color.YELLOW);
            }
            else{
                tiks[select].setBackground(Color.RED);
                }
        }
        for(int i = (newPercentage);i<tiks.length;i++){
            int select = 9 - i;
            if(select == 9){
                if(percentage > 0){
                    tiks[select].setBackground(Color.GREEN);    
                }
            }
            else{
                tiks[select].setBackground(StdThings.getGray1());
            }
        }
        return 1;   
    }
    public int setPercentage(int measure1, int measure2, String unit){ //for thing that aren't actual percentages
        defaultPercentage.setText(measure1 + unit);
        int newMesure2 = Math.round(measure2/10);
        if(newMesure2 == 10){
            for(int i=0;i<tiks.length;i++){
                tiks[i].setBackground(Color.RED);
            }
            return 0;
        }
        for(int i = 0;i<newMesure2;i++){
            int select = 9 - i;
            if(select > 5){
                tiks[select].setBackground(Color.GREEN);
            }
            else if(select <= 5 && select > 1){
                tiks[select].setBackground(Color.YELLOW);
            }
            else{
                tiks[select].setBackground(Color.RED);
                }
        }
        for(int i = (newMesure2);i<tiks.length;i++){
            int select = 9 - i;
            if(select == 9){
                if(measure1 == 0){
                    tiks[select].setBackground(StdThings.getGray1());    
                }
                else{
                    tiks[select].setBackground(Color.GREEN);
                }
            }
            else{
                tiks[select].setBackground(StdThings.getGray1());
            }
        }
        return 1;
    }
}
