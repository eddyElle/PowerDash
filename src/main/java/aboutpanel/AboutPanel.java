package aboutpanel;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ismain.PowerMain;

import java.awt.datatransfer.*;
import share.StdThings;
public class AboutPanel extends JPanel {
    private JLabel powerDash = new JLabel("PowerDash"); //nomral labels because they are completly different from the std ones (excluding the font) 
    private JLabel description = new JLabel("created by EDL405 for all");
    private JLabel description1 = new JLabel("the ones who work in IT");
    private JLabel powered = new JLabel("powered by:");
    private URL imageURL = getClass().getResource("/img/duke.png");
    private ImageIcon logo = new ImageIcon(imageURL);
    private Image img = logo.getImage();
    private Image imgScaled = img.getScaledInstance(130, 190, Image.SCALE_SMOOTH);
    private ImageIcon finalDuke = new ImageIcon(imgScaled);
    private JLabel duke = new JLabel(finalDuke);
    private JButton copy = new JButton("copy github link");
    private JButton back = new JButton("back");
    private EmptyBorder padding = new EmptyBorder(5, 10, 5, 10);
    private LineBorder line = new LineBorder(Color.BLACK, 1);
    private JPanel grid = new JPanel();
    public AboutPanel(){
        this.setPreferredSize(new Dimension(285,463));
        this.setLayout(new FlowLayout());
        this.setBackground(StdThings.getGray1());
        this.setBorder(new EmptyBorder(30,30,30,30));
        powerDash.setFont(new Font("Dialog", Font.BOLD, 30));
        powerDash.setForeground(Color.BLACK);
        description.setFont(new Font("Dialog", Font.ITALIC, 12));
        description1.setFont(new Font("Dialog", Font.ITALIC, 12));
        powered.setFont(new Font("Dialog", Font.BOLD, 14));
        duke.setPreferredSize(new Dimension(200,200));
        copy.setBackground(StdThings.getGray1());
        copy.setForeground(Color.BLACK);
        copy.setFocusPainted(false);
        back.setBackground(StdThings.getGray1());
        back.setForeground(Color.BLACK);
        back.setFocusPainted(false);
        grid.setLayout(new GridLayout(2,0));
        grid.add(copy);
        grid.add(back);
        this.add(powerDash);
        this.add(description);
        this.add(description1);
        this.add(powered);
        this.add(duke);
        this.add(grid);
        copy.setBorder(new CompoundBorder(line, padding));
        copy.setOpaque(true);
        back.setBorder(new CompoundBorder(line, padding));
        back.setOpaque(true);
        copy.addActionListener(e -> {
            copyToClipBoard();
        });
        back.addActionListener(e -> {
            PowerMain.settings();
        });
    }
    private void copyToClipBoard(){
        String text = "https://github.com/eddyElle/PowerDash";
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
    }
}
