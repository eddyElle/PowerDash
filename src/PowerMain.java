package src;
import javax.swing.*;
import src.base.LeftPanel;
import src.base.RightPanel;
import java.awt.*;
public class PowerMain{
    public static void main(String[] args) {
        JFrame frame = new JFrame("PowerDash");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel leftColumn = new LeftPanel(); //left config
        JPanel rightColumn = new RightPanel(); //right config
        frame.add(leftColumn,BorderLayout.WEST);
        frame.add(rightColumn,BorderLayout.EAST);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}