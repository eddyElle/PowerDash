package rightpanel2;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import rightpanel.mediaplayer.Player;
import rightpanel2.asciiconverter.AsciiConverter;
import rightpanel2.navigation.Navigation;
import share.StdThings;
public class RightPanel2 extends JPanel{
    private JPanel mediaplayer = new Player();
    private JPanel asciiConverter = new AsciiConverter();
    private JPanel navigation = new Navigation(2);
    public RightPanel2(){
        this.setBackground(StdThings.getGray1());
        this.setPreferredSize(new Dimension(285,463));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(mediaplayer);
        this.add(asciiConverter);
        this.add(navigation);
    }
}
