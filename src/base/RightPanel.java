package src.base;
import javax.swing.*;

import src.calc.Calc;
import src.hwmonitor.HwMonitor;
import src.share.StdThings;

import java.awt.*;
public class RightPanel extends JPanel {
    JPanel calc = new Calc();
    JPanel hwMonitor = new HwMonitor();
    public RightPanel(){
        this.setBackground(StdThings.getGray1());
        this.setPreferredSize(new Dimension(285,500));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(calc);
        this.add(hwMonitor);
    }
}
