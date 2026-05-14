package aboutframe;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.datatransfer.*;
import share.StdThings;
public class AboutFrame extends JFrame {
    JLabel powerDash = new JLabel("PowerDash"); //nomral labels because they are completly different from the std ones (excluding the font) 
    JLabel description = new JLabel("created by EDL405 for all");
    JLabel description1 = new JLabel("the ones who work in IT");
    JLabel powered = new JLabel("powered by:");
    JPanel background = new JPanel();
    JPanel texts = new JPanel();
    URL imageURL = getClass().getResource("/img/duke.png");
    ImageIcon logo = new ImageIcon(imageURL);
    Image img = logo.getImage();
    Image imgScaled = img.getScaledInstance(130, 190, Image.SCALE_SMOOTH);
    ImageIcon finalDuke = new ImageIcon(imgScaled);
    JLabel duke = new JLabel(finalDuke);
    JButton copy = new JButton("copy github link");
    EmptyBorder padding = new EmptyBorder(5, 10, 5, 10);
    LineBorder line = new LineBorder(Color.BLACK, 1);
    public AboutFrame(){
        this.setTitle("About");
        this.setSize(250,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(StdThings.getColor1());
        this.add(background);
        background.setLayout(new FlowLayout());
        background.setBackground(StdThings.getGray1());
        background.setBorder(StdThings.getStdMargin());
        background.add(texts);
        background.setLayout(new FlowLayout());
        texts.setPreferredSize(new Dimension(200,400));
        texts.setLayout(new FlowLayout());
        texts.setBackground(StdThings.getGray1());
        powerDash.setFont(new Font("Dialog", Font.BOLD, 30));
        powerDash.setForeground(Color.BLACK);
        description.setFont(new Font("Dialog", Font.ITALIC, 12));
        description1.setFont(new Font("Dialog", Font.ITALIC, 12));
        powered.setFont(new Font("Dialog", Font.BOLD, 14));
        duke.setPreferredSize(new Dimension(200,200));
        copy.setBackground(StdThings.getGray1());
        copy.setForeground(Color.BLACK);
        copy.setFocusPainted(false);
        texts.add(powerDash);
        texts.add(description);
        texts.add(description1);
        texts.add(powered);
        texts.add(duke);
        texts.add(copy);
        copy.setBorder(new CompoundBorder(line, padding));
        copy.setOpaque(true);
        copy.addActionListener(e -> {
            copyToClipBoard();
        });
    }
    private void copyToClipBoard(){
        String text = "https://github.com/eddyElle/PowerDash";
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
    }
}
