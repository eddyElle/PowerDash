
import javax.swing.*;
import leftpanel.LeftPanel;
import rightpanel.RightPanel;
import java.awt.image.BufferedImage;
import java.awt.*;
public class PowerMain{
    public static void main(String[] args) {
        Image Icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        JFrame frame = new JFrame("PowerDash");
        frame.setIconImage(Icon);
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