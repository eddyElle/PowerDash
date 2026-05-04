package rightpanel.quicksettings;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import share.StdButtons;
import share.StdThings;
import java.awt.*;
public class QuickSett extends JPanel{
    private StdButtons caffeine = new StdButtons("Caffeine:off");
    private StdButtons sounds = new StdButtons("Sounds:on");
    private StdButtons switcher = new StdButtons("ASCII table to mediaPlayer");
    private StdButtons about = new StdButtons("About");
    private StdButtons noBrk = new StdButtons("Breaks:on");
    public QuickSett(){
        this.setPreferredSize(new Dimension(280,66));
        this.setBackground(StdThings.getColor1());
        this.setLayout(new FlowLayout());
        this.setBorder(new EmptyBorder(3,3,3,3));
        this.add(caffeine);
        this.add(sounds);
        this.add(noBrk);
        this.add(switcher);
        this.add(about);

    }
}
