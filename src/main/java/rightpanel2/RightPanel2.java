package rightpanel2;
import java.awt.*;
import javax.swing.*;

import rightpanel.mediaplayer.Player;
import rightpanel.navigation.*;
import share.StdThings;
public class RightPanel2 extends JPanel{
    private JPanel mediaplayer = new Player();
    private JPanel navigation = new Navigation(2);
    public RightPanel2(){
        this.setBackground(StdThings.getGray1());
        this.setPreferredSize(new Dimension(285,463));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(mediaplayer);
        this.add(navigation);
    }
}
