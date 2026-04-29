package src.hwmonitor;
import java.awt.*;
import javax.swing.*;
import src.share.StdThings;
public class HwMonitor extends JPanel {
    JPanel cpu = new JPanel();
    JPanel ram = new JPanel();
    JPanel swap = new JPanel();

    public HwMonitor(){
        this.setPreferredSize(StdThings.getStdLeftDimension());
        this.setBackground(StdThings.getColor1());
        this.setBorder(StdThings.getStdMargin());
        this.setLayout(new GridLayout(0,5));
    }    
}
