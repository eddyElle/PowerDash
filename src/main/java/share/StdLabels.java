package share;
import javax.swing.*;
public class StdLabels extends JLabel {
    public StdLabels(String text){
        this.setForeground(StdThings.getTextColor1());
        this.setText(text);
    }
}
