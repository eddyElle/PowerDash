import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class NotesPanel extends JPanel {
    private JTextArea textArea = new JTextArea();
    private JLabel notes = new JLabel("Notes:");
    private JPanel top = new JPanel();
    private Color color1 = new Color(29,29,32);
    private JButton save = new JButton("save");
    private JButton load = new JButton("load");
    public NotesPanel(){
        this.setPreferredSize(new Dimension(205,300));
        this.setBackground(color1);
        this.setBorder(new EmptyBorder(3,8,8,8));
        this.notes.setForeground(Color.white);
        this.notes.setText("Notes:");
        this.notes.setPreferredSize(new Dimension(50,20));
        this.top.setPreferredSize(new Dimension(50,20));
        this.top.setBackground(color1);
        this.top.setLayout(new GridLayout(1,3));
        this.top.add(notes);
        this.top.add(save);
        this.top.add(load);
        this.setLayout(new BorderLayout());
        this.add(top,BorderLayout.NORTH);
        textArea.setBackground(color1);
        textArea.setForeground(Color.white);
        textArea.setCaretColor(Color.white);
        textArea.setLineWrap(true);
        this.add(textArea,BorderLayout.CENTER);
    }
}
