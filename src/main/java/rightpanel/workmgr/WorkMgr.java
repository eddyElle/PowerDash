package rightpanel.workmgr;
import java.awt.*;
import javax.swing.*;

import share.StdButtons;
import share.StdLabels;
import share.StdTextFields;
import share.StdThings;
public class WorkMgr extends JPanel {
    private JPanel top = new JPanel();
    private JPanel bottom = new JPanel();
    private JLabel worked = new StdLabels("Worked: ");
    private JLabel hoursMins = new StdLabels("0h 0min");
    private StdButtons playBtn = new StdButtons("Start");
    private StdTextFields copilot = new StdTextFields("Press start", new Dimension(130,21));
    private JLabel brk = new StdLabels("Break(min):");
    private StdButtons decreaseBrk = new StdButtons("-");
    private JLabel brkMins = new StdLabels("20");
    private StdButtons increaseBrk = new StdButtons("+");
    private JButton finishDay = new StdButtons("End day");
    public WorkMgr(){
        this.setPreferredSize(StdThings.getStdLeftDimension());
        this.setBackground(StdThings.getColor1());
        this.setLayout(new BorderLayout());
        top.setBackground(StdThings.getColor1());
        top.setLayout(new FlowLayout());
        bottom.setBackground(StdThings.getColor1());
        bottom.setLayout(new FlowLayout());
        this.add(top,BorderLayout.NORTH);
        this.add(bottom,BorderLayout.SOUTH);
        decreaseBrk.setPreferredSize(new Dimension(15,21));
        top.add(worked);
        top.add(hoursMins);
        top.add(copilot);
        bottom.add(brk);
        bottom.add(decreaseBrk);
        bottom.add(brkMins);
        bottom.add(increaseBrk);
        bottom.add(playBtn);
        bottom.add(finishDay);
    }
}
