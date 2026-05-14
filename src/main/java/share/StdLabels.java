package share;
import java.awt.Font;

import javax.swing.*;
public class StdLabels extends JLabel {
    public StdLabels(String text){
        this.setForeground(StdThings.getTextColor1());
        this.setText(text);
        this.setFont(new Font("Dialog", Font.BOLD, 12));
    }
}
