package rightpanel.quickremote;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridLayout;
import java.util.ArrayList;
import settingspanel.quickremotemanager.greatWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ismain.PowerMain;
import settingspanel.quickremotemanager.Machine;
import share.StdButtons;
import share.StdLabels;
import share.StdScroll;
import share.StdThings;
public class QuickRemote extends JPanel{
    public static ArrayList<Machine> machines;
    public static JPanel overviewTable = new JPanel();
    public static JPanel overviewNav = new JPanel();
    private JButton refreshMacOverview = new StdButtons("refresh");
    private JButton edit = new StdButtons("edit");
    private JScrollPane tableScroll = new StdScroll(overviewTable);
    public QuickRemote(){
        machines = greatWriter.loadMachines();
        this.setPreferredSize(new Dimension(280, 86));
        this.setBackground(StdThings.getColor1());
        this.setBorder(new EmptyBorder(new Insets(3, 3, 3, 10)));
        this.setLayout(new BorderLayout());
        this.add(overviewNav,BorderLayout.WEST);
        this.add(tableScroll, BorderLayout.EAST);
        overviewTable.setBackground(StdThings.getColor1());
        overviewNav.setBackground(StdThings.getColor1());
        overviewNav.setLayout(new GridLayout(0,1));
        overviewNav.add(refreshMacOverview);
        overviewNav.add(edit);
        tableScroll.setPreferredSize(new Dimension(215,80));
        edit.addActionListener(e -> {
            PowerMain.settings();
        });
        refreshMacOverview.addActionListener(e -> {
            refresh();
        });
        refresh();
        
    }
    public static  void refresh(){ //real machines panel configuaration
        machines = greatWriter.loadMachines();
        overviewTable.removeAll();
        if(machines.isEmpty()){
            overviewTable.setLayout(new BorderLayout());
            overviewTable.setPreferredSize(new Dimension(180,60));
            JLabel nothing = new StdLabels(" no Machine"); //investigate
            
            overviewTable.add(nothing, BorderLayout.CENTER);
            nothing.setHorizontalAlignment(JLabel.CENTER);
            nothing.setVerticalAlignment(JLabel.CENTER);
        }
        overviewTable.setLayout(new GridLayout(0,3));
        for(int i=0; i<machines.size();i++){
            overviewTable.add(new realMacPanel(machines.get(i)));
        }
        
    }
}
