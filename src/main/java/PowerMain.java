
import javax.swing.*;
import leftpanel.LeftPanel;
import rightpanel.RightPanel;
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;
public class PowerMain{
    static URL imageURL2 = PowerMain.class.getResource("/img/32PwdIcon.png");
    static URL imageURL3 = PowerMain.class.getResource("/img/64PwdIcon.png");
    static URL imageURL1 = PowerMain.class.getResource("/img/16PwdIcon.png");
    public static void main(String[] args) {
        JFrame frame = new JFrame("PowerDash");
        ArrayList<Image> icons = new ArrayList<>();
        icons.add(new ImageIcon(imageURL1).getImage());
        icons.add(new ImageIcon(imageURL2).getImage());
        icons.add(new ImageIcon(imageURL3).getImage());
        frame.setIconImages(icons);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel leftColumn = new LeftPanel(); //left config
        JPanel rightColumn = new RightPanel(); //right config
        frame.add(leftColumn,BorderLayout.WEST);
        frame.add(rightColumn,BorderLayout.EAST);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}