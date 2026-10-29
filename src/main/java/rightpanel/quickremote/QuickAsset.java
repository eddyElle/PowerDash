package rightpanel.quickremote;
import javax.swing.*;

import share.StdButtons;

import java.awt.*;
public class QuickAsset extends JPanel{
    private JLabel name;
    private StdButtons powerBtn = new StdButtons("Power");
    private StdButtons sshBtn = new StdButtons("ssh");
    private StdButtons execBtn = new StdButtons("Exec");
    QuickAsset(String Hname, String ip, String exec){
        name.setText(Hname);
        this.setLayout(new GridLayout(4,0));
        this.add(name);
        this.add(powerBtn);
        this.add(sshBtn);
        this.add(execBtn);
        
    }
}
