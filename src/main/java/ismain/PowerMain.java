package ismain;

import javax.swing.*;

import aboutpanel.AboutPanel;
import leftpanel.LeftPanel;
import rightpanel.RightPanel;
import rightpanel2.RightPanel2;
import settingspanel.Settings;

import java.net.URL;
import java.util.ArrayList;
import java.awt.*;
public class PowerMain{
    static URL imageURL2 = PowerMain.class.getResource("/img/32PwdIcon.png");
    static URL imageURL3 = PowerMain.class.getResource("/img/64PwdIcon.png");
    static URL imageURL1 = PowerMain.class.getResource("/img/16PwdIcon.png");
    public static final String PAN_1 = "panel1";
    public static final String PAN_2 = "panel2";
    public static final String PAN_3 = "settings";
    public static final String PAN_4 = "about";
    public static CardLayout swipe = new CardLayout();
    public static JPanel container = new JPanel(swipe);
    public static void main(String[] args) {
        JFrame frame = new JFrame("PowerDash");
        ArrayList<Image> icons = new ArrayList<>();
        icons.add(new ImageIcon(imageURL1).getImage());
        icons.add(new ImageIcon(imageURL2).getImage());
        icons.add(new ImageIcon(imageURL3).getImage());
        frame.setIconImages(icons);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel leftPanel = new LeftPanel(); //left config
        JPanel rightPanel = new RightPanel(); //right config
        JPanel rightPanel2 = new RightPanel2();
        JPanel settings = new Settings();
        JPanel about = new AboutPanel();
        container.add(rightPanel,PAN_1);
        container.add(rightPanel2,PAN_2);
        container.add(settings,PAN_3);
        container.add(about,PAN_4);
        swipe.show(container, PAN_1);
        frame.add(leftPanel,BorderLayout.WEST);
        frame.add(container,BorderLayout.EAST);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    public static void pan2(){
        swipe.show(container,PAN_2);
    }
    public static void pan1(){
        swipe.show(container, PAN_1);
    }
    public static void settings(){
        swipe.show(container, PAN_3);
    }
    public static void about(){
        swipe.show(container, PAN_4);
    }
}