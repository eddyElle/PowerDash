package rightpanel.quicksettings;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import aboutframe.AboutFrame;
import configmanager.ConfigManager;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;
import share.StdButtons;
import share.StdThings;
import java.awt.*;
public class QuickSett extends JPanel{
    private StdButtons caffeine = new StdButtons("Caffeine");
    private StdButtons sounds = new StdButtons("Sounds:on");
    private StdButtons about = new StdButtons("About");
    private StdButtons noBrk = new StdButtons("Breaks:on");
    private ScheduledExecutorService executor;
    public static ConfigManager config = new ConfigManager();
    
    private Robot robot;
    public QuickSett(){
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            System.err.println("Error: Keyboard simulation not supported.");
            e.printStackTrace();
        }
        showInitialStatus(caffeine, "Caffeine");
        showInitialStatus(sounds, "Sounds");
        showInitialStatus(noBrk, "Breaks");
        this.setPreferredSize(new Dimension(280,66));
        this.setBackground(StdThings.getColor1());
        this.setLayout(new FlowLayout());
        this.setBorder(new EmptyBorder(3,3,3,3));
        this.add(caffeine);
        this.add(sounds);
        this.add(noBrk);
        this.add(about);
        getCaffeinated();
        caffeine.addActionListener( e -> {
            switchStatus(caffeine, "Caffeine");
            getCaffeinated();
        });
        sounds.addActionListener(e -> {
            switchStatus(sounds, "Sounds");
        });
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
    private void getCaffeinated(){
        if(config.getBoolean("Caffeine")){
            startCaffeine(); 
        }
        else{
            stopCaffeine();
        }
    }
    private void startCaffeine(){
        executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            try {
                robot.keyPress(KeyEvent.VK_F15);
                robot.keyRelease(KeyEvent.VK_F15);
                System.out.println("F15 Pressed at: " + new java.util.Date());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        };
        System.out.println("Service started, first press in 0s, then every 60s...");
        executor.scheduleAtFixedRate(task, 0, 60, TimeUnit.SECONDS);
    }
    private void stopCaffeine(){
        if (executor != null) {
            executor.shutdownNow();
            executor = null;
        }
    }
    private void popAbout(){
        JFrame frame2 = new AboutFrame();
        frame2.setVisible(true);
    }

}
