import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
public class ClipboardPanel extends JPanel{
    private JLabel clipBoard = new JLabel("ClipBoard:");
    private JButton btn1 = new Buttons("nothing to show");
    private JButton btn2 = new Buttons("nothing to show");
    private JButton btn3 = new Buttons("nothing to show");
    private ClipBoardManager clippy = new ClipBoardManager(btn1,btn2,btn3);
    public ClipboardPanel(){
        this.setPreferredSize(new Dimension(205,148));
        this.setBackground(stdThings.getColor1());
        this.setLayout(new GridLayout(4,0));
        this.setBorder(stdThings.getStdMargin());
        clipBoard.setForeground(Color.white);
        this.add(clipBoard);
        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        btn1.addActionListener(e -> {
            String text = btn1.getText();
            StringSelection selection = new StringSelection(text);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
        });
        btn2.addActionListener(e -> {
            String text = btn2.getText();
            StringSelection selection = new StringSelection(text);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
        });
        btn3.addActionListener(e -> {
            String text = btn3.getText();
            StringSelection selection = new StringSelection(text);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
        });
        clippy.setDaemon(true);
        clippy.start();
        
    }
}
