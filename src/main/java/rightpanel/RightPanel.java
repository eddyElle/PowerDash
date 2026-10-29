package rightpanel;
import javax.swing.*;

import rightpanel.calc.Calc;
import rightpanel.hwmonitor.HwMonitor;
import rightpanel.quickremote.QuickRemote;
import rightpanel.unixtimeconverter.UnixConverter;
import rightpanel.workmgr.WorkMgr;
import rightpanel2.navigation.Navigation;
import share.StdThings;
import java.awt.*;
public class RightPanel extends JPanel {
    private JPanel calc = new Calc();
    private JPanel hwMonitor = new HwMonitor();
    private JPanel unixTimeConverter = new UnixConverter();
    private JPanel workMgr = new WorkMgr();
    private JPanel quickSett = new Navigation(1);
    private JPanel quickRemote = new QuickRemote();
    public RightPanel(){
        this.setBackground(StdThings.getGray1());
        this.setPreferredSize(new Dimension(285,463));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(calc);
        this.add(hwMonitor);
        this.add(quickRemote);
        this.add(unixTimeConverter);
        this.add(workMgr);
        this.add(quickSett);
    }
}
