package rightpanel;
import javax.swing.*;

import rightpanel.calc.Calc;
import rightpanel.hwmonitor.HwMonitor;
import rightpanel.mediaplayer.Player;
import share.StdThings;

import java.awt.*;
public class RightPanel extends JPanel {
    JPanel calc = new Calc();
    JPanel hwMonitor = new HwMonitor();
    JPanel mediaPlayer = new Player();
    public RightPanel(){
        this.setBackground(StdThings.getGray1());
        this.setPreferredSize(new Dimension(285,500));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(calc);
        this.add(hwMonitor);
        this.add(mediaPlayer);
    }
}
