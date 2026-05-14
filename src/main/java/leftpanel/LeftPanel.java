package leftpanel;
import javax.swing.*;
import leftpanel.clipboard.ClipboardPanel;
import leftpanel.notes.NotesPanel;
import share.StdThings;
import java.awt.*;
public class LeftPanel extends JPanel {
    public LeftPanel(){
        this.setBackground(StdThings.getGray1());
        this.setPreferredSize(new Dimension(215,463));
        this.add(new NotesPanel());
        this.add(new ClipboardPanel());
    }
}
