package settingspanel;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import configmanager.ConfigManager;
import ismain.PowerMain;
import share.StdButtons;
import share.StdThings;
public class Settings extends JPanel {
    private StdButtons close = new StdButtons("close");
    private StdButtons noBrk = new StdButtons("Breaks:on");
    private StdButtons about = new StdButtons("about");
    private StdButtons help = new StdButtons("help");
    public static ConfigManager config = new ConfigManager();
    private JPanel options = new JPanel();
    private JPanel betweenOP = new JPanel();
    public Settings(){
        this.setBackground(StdThings.getGray1());
        this.setPreferredSize(new Dimension(285,463));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        showInitialStatus(noBrk, "Breaks");
        noBrk.setPreferredSize(new Dimension(90,22));
        noBrk.addActionListener(e -> {
            switchStatus(noBrk, "Breaks");
        });
        close.addActionListener(e -> {
            PowerMain.pan1();
        });
        close.setBackground(new Color(178, 34, 34));
        about.addActionListener(e -> {
            PowerMain.about();
        });
        help.addActionListener(e -> {
            openWebPage("https://github.com/eddyElle/PowerDash/blob/main/README.md");
        });
        options.setPreferredSize(new Dimension(280,80));
        options.setBackground(StdThings.getColor1());
        options.setLayout(new GridLayout(3,0));
        options.setBorder(new EmptyBorder(3,3,3,3));
        options.add(close);
        options.add(betweenOP);
        betweenOP.setBackground(StdThings.getColor1());
        betweenOP.setLayout(new GridLayout(0,2));
        betweenOP.add(about);
        betweenOP.add(help);
        options.add(noBrk);
        this.add(options);
    }
    private void showInitialStatus(StdButtons button, String key){
        if(config.getBoolean(key)){
            button.setText(key + ":on");
        }
        else{
            button.setText(key + ":off");
        }
    }
    private void switchStatus(StdButtons button, String key){
        if(config.getBoolean(key)){
            button.setText(key + ":off");
            config.setValue(key, false);
        }
        else{
            button.setText(key + ":on");
            config.setValue(key, true);
        }
    }
    private void openWebPage(String url){
        try {         
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch (java.io.IOException e) {
            System.out.println(e.getMessage()); //remember needs graphics
        }
    }
}
