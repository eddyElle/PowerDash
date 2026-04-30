package rightpanel.hwmonitor;
import java.awt.*;
import javax.swing.*;

import share.StdThings;
public class Gauge extends JPanel {
    private JPanel[] tiks = new JPanel[10];
    private JPanel tiksContainer = new JPanel();
    private JLabel defaultLabel = new JLabel();
    private JLabel defaultPercentage;
    private JPanel labelContainer = new JPanel();
    public Gauge(String name){
        this.setLayout(new BorderLayout());
        this.defaultPercentage = new JLabel("0%");
        tiksContainer.setBackground(StdThings.getColor1());
        labelContainer.setBackground(StdThings.getColor1());
        labelContainer.setLayout(new GridLayout(2,0));
        labelContainer.add(defaultLabel);
        labelContainer.add(defaultPercentage);
        defaultLabel.setHorizontalAlignment(JLabel.CENTER);
        defaultLabel.setVerticalAlignment(JLabel.CENTER);
        defaultPercentage.setHorizontalAlignment(JLabel.CENTER);
        defaultPercentage.setVerticalAlignment(JLabel.CENTER);
        defaultPercentage.setForeground(StdThings.getTextColor1());
        this.setBackground(StdThings.getColor1());
        tiksContainer.setLayout(new GridLayout(10,0));
        defaultLabel.setText(name);
        defaultLabel.setForeground(StdThings.getTextColor1());
        tiksContainer.setPreferredSize(new Dimension(50,50));
        this.add(labelContainer, BorderLayout.NORTH);
        this.add(tiksContainer, BorderLayout.SOUTH);
        for(int i=0;i<tiks.length;i++){
            tiks[i] = new JPanel();
            tiksContainer.add(tiks[i]);
            tiks[i].setBackground(StdThings.getGray1());
            tiks[i].setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }
    public void setPercentage(int percentage){
        defaultPercentage.setText(percentage + "%");
        int newPercentage = Math.round(percentage/10);
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
    }
}
