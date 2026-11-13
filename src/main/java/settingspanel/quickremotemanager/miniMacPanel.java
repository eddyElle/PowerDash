package settingspanel.quickremotemanager;
import java.awt.GridLayout;

import javax.swing.*;

import share.StdButtons;
public class miniMacPanel extends JPanel {
    private JLabel nameLabel = new JLabel();
    private JButton edit = new StdButtons("edit");
    private JButton delete = new StdButtons("delete");
    miniMacPanel(String name, int index){
        edit.setText(name);
        this.setLayout(new GridLayout(3,0));
        this.add(nameLabel);
        this.add(delete);
        this.add(delete);
        edit.addActionListener(e -> {

        });
        delete.addActionListener(e -> {
            remoteManager.deletes(index);
        });
    }

}
