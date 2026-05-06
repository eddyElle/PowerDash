package rightpanel;
import javax.swing.*;
import rightpanel.asciiconverter.AsciiConverter;
import rightpanel.calc.Calc;
import rightpanel.hwmonitor.HwMonitor;
import rightpanel.quicksettings.QuickSett;
import rightpanel.unixtimeconverter.UnixConverter;
import rightpanel.workmgr.WorkMgr;
import share.StdThings;
import java.awt.*;
public class RightPanel extends JPanel {
    private JPanel calc = new Calc();
    private JPanel hwMonitor = new HwMonitor();
    private JPanel asciiConverter = new AsciiConverter();
    private JPanel unixTimeConverter = new UnixConverter();
    private JPanel workMgr = new WorkMgr();
    private JPanel quickSett = new QuickSett();
    public RightPanel(){
        this.setBackground(StdThings.getGray1());
        this.setPreferredSize(new Dimension(285,500));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(calc);
        this.add(hwMonitor);
        this.add(asciiConverter);
        this.add(unixTimeConverter);
        this.add(workMgr);
        this.add(quickSett);
    }
}
