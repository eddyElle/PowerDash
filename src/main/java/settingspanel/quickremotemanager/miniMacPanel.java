package settingspanel.quickremotemanager;
import java.awt.GridLayout;

import javax.swing.*;

import share.StdButtons;
import share.StdLabels;
import share.StdThings;
public class miniMacPanel extends JPanel {
    private JLabel nameLabel;
    private JButton edit = new StdButtons("edit");
    private JButton delete = new StdButtons("delete");
    miniMacPanel(String name, int index){
        this.nameLabel = new StdLabels(name);
        this.setLayout(new GridLayout(0,1));
        this.add(nameLabel);
        this.add(delete);
        this.add(edit);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        this.setBackground(StdThings.getGray1());
        edit.addActionListener(e -> {

        });
        delete.addActionListener(e -> {
            remoteManager.deletes(index);
        });
    }

}
