package share;
import javax.swing.*;
public class StdButtons extends JButton {
    public StdButtons(String name){
        this.setText(name);
        this.setMargin(StdThings.getInset1());
        this.setBackground(StdThings.getColor1());
        this.setForeground(StdThings.getTextColor1());
        this.setFocusPainted(false);
    }
}
