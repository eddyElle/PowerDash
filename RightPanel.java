import javax.swing.*;
import java.awt.*;
public class RightPanel extends JPanel {
    JPanel calc = new Calc();
    public RightPanel(){
        this.setBackground(stdThings.gray1);
        this.setPreferredSize(new Dimension(285,500));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(calc);
    }
}
