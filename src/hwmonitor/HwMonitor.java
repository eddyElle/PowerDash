package src.hwmonitor;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.share.StdThings;
public class HwMonitor extends JPanel {
    private JPanel cpu = new Gauge("cpu", "0%");
    private JPanel ram = new Gauge("ram","0%");
    private JPanel swap = new Gauge("swap","0%");
    private JPanel disk = new Gauge("disk","0%");
    private JPanel net = new Gauge("net", "0%");
    public HwMonitor(){
        this.setPreferredSize(new Dimension(280,86));
        this.setBackground(StdThings.getColor1());
        this.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));        
        this.setLayout(new GridLayout(0,5));
        this.add(cpu);
        this.add(ram);
        this.add(swap);
        this.add(disk);
        this.add(net);
    }    
}
