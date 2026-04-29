package src.hwmonitor;
import java.awt.*;
import javax.swing.*;

import src.share.StdThings;
public class Gauge extends JPanel {
    private JPanel[] tiks = new JPanel[10];
    private JPanel tiksContainer = new JPanel();
    private JLabel defaultLabel = new JLabel();
    private JLabel defaultPercentage = new JLabel();
    private JPanel labelContainer = new JPanel();
    public Gauge(String name, String percentage){
        this.setLayout(new BorderLayout());
        tiksContainer.setBackground(StdThings.getColor1());
        labelContainer.setBackground(StdThings.getColor1());
        labelContainer.setLayout(new GridLayout(2,0));
        labelContainer.add(defaultLabel);
        labelContainer.add(defaultPercentage);
        defaultLabel.setHorizontalAlignment(JLabel.CENTER);
        defaultLabel.setVerticalAlignment(JLabel.CENTER);
        defaultPercentage.setHorizontalAlignment(JLabel.CENTER);
        defaultPercentage.setVerticalAlignment(JLabel.CENTER);
        defaultPercentage.setText(percentage);
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
        tiks[0].setBackground(Color.red);
    }
}
