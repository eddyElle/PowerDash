package src.base;
import javax.swing.*;

import src.clipboard.ClipboardPanel;
import src.notes.NotesPanel;
import src.share.StdThings;

import java.awt.*;
public class LeftPanel extends JPanel {
    public LeftPanel(){
        this.setBackground(StdThings.getGray1());
        this.setPreferredSize(new Dimension(215,500));
        //this.setLayout(new GridLayout(2, 1, 10, 10));
        this.add(new NotesPanel());
        this.add(new ClipboardPanel());
    }
}
