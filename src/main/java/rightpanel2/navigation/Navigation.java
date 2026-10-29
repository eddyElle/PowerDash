package rightpanel2.navigation;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import ismain.PowerMain;
import share.StdButtons;
import share.StdThings;
import java.awt.*;
public class Navigation extends JPanel{
    private StdButtons nextPage = new StdButtons("next ->");
    private StdButtons prevPage = new StdButtons("<- back");
    private StdButtons settings = new StdButtons("Settings"); 
    public Navigation(int number){
        this.setPreferredSize(new Dimension(280,45));
        this.setBackground(StdThings.getColor1());
        this.setLayout(new FlowLayout());
        this.setBorder(new EmptyBorder(6,3,3,3));
        this.add(settings);
        this.add(prevPage);
        this.add(nextPage);
        think(nextPage, prevPage, number);
        nextPage.addActionListener(e ->{
            PowerMain.pan2();
        });
        prevPage.addActionListener(e ->{
            PowerMain.pan1();
        });
        settings.addActionListener(e -> {
            PowerMain.settings();
        });
    }
    private void think(JButton nextPage, JButton prevPage,int number){
        if(number == 1){
            prevPage.setEnabled(false);
            nextPage.setEnabled(true);
        }
        else{
            nextPage.setEnabled(false);
            prevPage.setEnabled(true);
        }
    }
    
    
}
