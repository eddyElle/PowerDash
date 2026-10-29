package rightpanel.quickremote;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import share.StdThings;
public class QuickRemote extends JPanel{
    public QuickRemote(){
        this.setPreferredSize(new Dimension(280, 86));
        this.setBackground(StdThings.getColor1());
        this.setBorder(new EmptyBorder(new Insets(3, 3, 3, 10)));
        this.setLayout(new BorderLayout());
        
    }
}
