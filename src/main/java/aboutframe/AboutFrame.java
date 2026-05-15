package aboutframe;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.datatransfer.*;
import share.StdThings;
public class AboutFrame extends JFrame {
    private URL imageURL2 = getClass().getResource("/img/32PwdIcon.png");
    private URL imageURL3 = getClass().getResource("/img/64PwdIcon.png");
    private URL imageURL1 = getClass().getResource("/img/16PwdIcon.png");
    private ArrayList<Image> icons = new ArrayList<>();
    private JLabel powerDash = new JLabel("PowerDash"); //nomral labels because they are completly different from the std ones (excluding the font) 
    private JLabel description = new JLabel("created by EDL405 for all");
    private JLabel description1 = new JLabel("the ones who work in IT");
    private JLabel powered = new JLabel("powered by:");
    private JPanel background = new JPanel();
    private JPanel texts = new JPanel();
    private URL imageURL = getClass().getResource("/img/duke.png");
    private ImageIcon logo = new ImageIcon(imageURL);
    private Image img = logo.getImage();
    private Image imgScaled = img.getScaledInstance(130, 190, Image.SCALE_SMOOTH);
    private ImageIcon finalDuke = new ImageIcon(imgScaled);
    private JLabel duke = new JLabel(finalDuke);
    private JButton copy = new JButton("copy github link");
    private EmptyBorder padding = new EmptyBorder(5, 10, 5, 10);
    private LineBorder line = new LineBorder(Color.BLACK, 1);
    public AboutFrame(){
        this.setTitle("About");
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
        icons.add(new ImageIcon(imageURL1).getImage());
        icons.add(new ImageIcon(imageURL2).getImage());
        icons.add(new ImageIcon(imageURL3).getImage());
        this.setIconImages(icons);
    }
    private void copyToClipBoard(){
        String text = "https://github.com/eddyElle/PowerDash";
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
    }
}
