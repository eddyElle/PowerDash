package rightpanel.quicksettings;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.net.URL;
import share.StdButtons;
import share.StdThings;
import java.awt.*;
public class QuickSett extends JPanel{
    private StdButtons caffeine = new StdButtons("Caffeine:off");
    private StdButtons sounds = new StdButtons("Sounds:on");
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
        this.add(about);
        about.addActionListener(e ->{
            popAbout();
        });
    }
    public void popAbout(){
        URL imageURL = getClass().getResource("/img/duke.png");
        JFrame frame2 = new JFrame("About");
        JLabel powerDash = new JLabel("PowerDash");
        JLabel description = new JLabel("created by EDL405 for all");
        JLabel description1 = new JLabel("the ones who work in IT");
        JLabel powered = new JLabel("powered by:");
        JPanel background = new JPanel();
        JPanel texts = new JPanel();
        ImageIcon logo = new ImageIcon(imageURL);
        Image img = logo.getImage();
        Image imgScaled = img.getScaledInstance(130, 190, Image.SCALE_SMOOTH);
        ImageIcon finalDuke = new ImageIcon(imgScaled);
        JLabel duke = new JLabel(finalDuke);
        JButton copy = new JButton("copy github link");
        frame2.setSize(250,400);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setResizable(false);
        frame2.setAlwaysOnTop(true);
        frame2.setBackground(StdThings.getColor1());
        frame2.add(background);
        background.setLayout(new FlowLayout());
        background.setBackground(StdThings.getGray1());
        background.setBorder(StdThings.getStdMargin());
        background.add(texts);
        background.setLayout(new FlowLayout());
        texts.setPreferredSize(new Dimension(200,400));
        texts.setLayout(new FlowLayout());
        texts.setBackground(StdThings.getGray1());
        powerDash.setFont(new Font(TOOL_TIP_TEXT_KEY, Font.BOLD, 30));
        powerDash.setForeground(Color.BLACK);
        description.setFont(new Font(TOOL_TIP_TEXT_KEY, Font.ITALIC, 12));
        description1.setFont(new Font(TOOL_TIP_TEXT_KEY, Font.ITALIC, 12));
        powered.setFont(new Font(TOOL_TIP_TEXT_KEY, Font.BOLD, 14));
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
        frame2.setVisible(true);
    }

}
