import javax.swing.*;
import java.awt.*;
public class Buttons extends JButton {
    public Buttons(String name){
        this.setText(name);
        this.setMargin(stdThings.getInset1());
        this.setBackground(stdThings.getColor1());
        this.setForeground(Color.white);
        this.setFocusPainted(false);
    }
}
