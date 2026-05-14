package rightpanel.quicksettings;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import aboutframe.AboutFrame;
import configmanager.ConfigManager;
import share.StdButtons;
import share.StdLabels;
import share.StdThings;
import java.awt.*;
public class QuickSett extends JPanel{
    private StdButtons about = new StdButtons("About");
    private StdButtons noBrk = new StdButtons("Breaks:on");
    private JLabel settings = new StdLabels("Settings:");
    public static ConfigManager config = new ConfigManager();
    public QuickSett(){
        showInitialStatus(noBrk, "Breaks");
        this.setPreferredSize(new Dimension(280,45));
        this.setBackground(StdThings.getColor1());
        this.setLayout(new FlowLayout());
        this.setBorder(new EmptyBorder(6,3,3,3));
        this.add(settings);
        this.add(noBrk);
        this.add(about);
        noBrk.setPreferredSize(new Dimension(90,22));
        about.setPreferredSize(new Dimension(60,22));
        noBrk.addActionListener(e -> {
            switchStatus(noBrk, "Breaks");
        });
        about.addActionListener(e ->{
            popAbout();
        });
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
    private void popAbout(){
        JFrame frame2 = new AboutFrame();
        frame2.setVisible(true);
    }

}
