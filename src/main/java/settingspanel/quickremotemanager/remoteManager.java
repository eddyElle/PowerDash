package settingspanel.quickremotemanager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import share.StdButtons;
import share.StdLabels;
import share.StdTextFields;
import share.StdThings;
public class remoteManager extends JPanel {
    public static ArrayList<Machine> machines;
    private String PANX_1 = "noMachines";
    private String PANX_2 = "machinesOverview";
    private String PANX_3 = "insertLocalMachine";
    private String PANX_4 = "inserRemoteMachine";
    private CardLayout autoswipe = new CardLayout();
    private JPanel container = new JPanel(autoswipe);
    private JPanel noMachines = new JPanel();
    private JPanel machinesOverview = new JPanel();
    private JPanel insertLocalMachines = new JPanel();
    private JPanel insertRemoteMachines = new JPanel();
    private JPanel overviewTable = new JPanel();
    private JLabel nothingFound = new StdLabels("no machines found");
    private JButton createMachine = new StdButtons("create machine");
    private JButton refreshMacOverview = new StdButtons("refresh");
    private JButton SaveLocal = new StdButtons("save");
    private JButton SaveRemote = new StdButtons("save");
    private JButton addMachine = new StdButtons("add");
    private JButton cancelLocal = new StdButtons("cancel");
    private JButton cancelRemote = new StdButtons("cancel");
    private JButton switchToLocal = new StdButtons("switch to local");
    private JButton switchToRemote = new StdButtons("switch to remote");
    private JTextField nameLocal = new StdTextFields("name", new Dimension(50,18));
    private JTextField macAddressLocal = new StdTextFields("macAddress", new Dimension(50,18));
    private JTextField execMethodLocal = new StdTextFields("execMethod", new Dimension(50,18));
    private JTextField nameRemote = new StdTextFields("name", new Dimension(50,18));
    private JTextField macAddressRemote = new StdTextFields("macAddress", new Dimension(50,18));
    private JTextField execMethodRemote = new StdTextFields("execMethod", new Dimension(50,18));
    private JTextField relayIP = new StdTextFields("relayIP", new Dimension(50,18));
    private JTextField relayPort = new StdTextFields("relayPort", new Dimension(50,18));
    public remoteManager(){
        machines = greatWriter.loadMachines();
        this.setPreferredSize(new Dimension(280,170));
        this.setBackground(StdThings.getColor1());
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 8, 8, 8));
        this.add(container);
        container.add(noMachines,PANX_1);
        container.add(machinesOverview,PANX_2);
        container.add(insertLocalMachines,PANX_3);
        container.add(insertRemoteMachines,PANX_4);
        //------------noMachines panel configutaration------
        noMachines.setLayout(new GridLayout(2,0));
        noMachines.setBackground(StdThings.getColor1());
        nothingFound.setHorizontalAlignment(JLabel.CENTER);
        nothingFound.setVerticalAlignment(JLabel.CENTER);
        noMachines.add(nothingFound);
        noMachines.add(createMachine);
        createMachine.addActionListener(e -> {
            autoswipe.show(container, PANX_3);
        });
        
        //------------MachinesOverview panel configuration---
        JPanel overviewNav = new JPanel();
        
        machinesOverview.add(overviewNav,BorderLayout.NORTH);
        machinesOverview.add(overviewTable,BorderLayout.CENTER);
        overviewNav.setLayout(new GridLayout(0,2));
        overviewNav.add(refreshMacOverview);
        overviewNav.add(addMachine);
        refresh();
        refreshMacOverview.addActionListener(e -> {
            refresh();
        });
        addMachine.addActionListener(e -> {
            autoswipe.show(container, PANX_3);
        });
        overviewNav.setBackground(StdThings.getColor1());
        overviewTable.setBackground(StdThings.getColor1());
        machinesOverview.setBackground(StdThings.getColor1());
        
        //-----------InsertLocalMachine---------------
        JPanel localNav = new JPanel();
        localNav.add(switchToRemote);
        localNav.add(cancelLocal);
        localNav.add(SaveLocal);
        localNav.setBackground(StdThings.getColor1());
        insertLocalMachines.setLayout(new GridLayout(4,0));
        insertLocalMachines.add(localNav);
        insertLocalMachines.add(nameLocal);
        insertLocalMachines.add(macAddressLocal);
        insertLocalMachines.add(execMethodLocal);
        switchToRemote.addActionListener(e -> {
            autoswipe.show(container, PANX_4);
        });
        SaveLocal.addActionListener(e -> {
            if((nameLocal.getText() != "") && macAddressLocal.getText() != "" ){
                machines.add(new Machine(nameLocal.getText(), macAddressLocal.getText(), false, execMethodLocal.getText()));
            }
            else{
                JOptionPane.showMessageDialog(null, "you must insert name and MAC address of the machine");
            }
            greatWriter.saveMachines(machines);
        });
        insertLocalMachines.setBackground(StdThings.getColor1());
        //-----------InsertRemoteMachine--------------
        JPanel remoteNav = new JPanel();
        remoteNav.add(switchToLocal);
        remoteNav.add(cancelRemote);
        remoteNav.add(SaveRemote);
        remoteNav.setBackground(StdThings.getColor1());
        insertRemoteMachines.setLayout(new GridLayout(6,0));
        insertRemoteMachines.add(remoteNav);
        insertRemoteMachines.add(nameRemote);
        insertRemoteMachines.add(macAddressRemote);
        insertRemoteMachines.add(execMethodRemote);
        insertRemoteMachines.add(relayIP);
        insertRemoteMachines.add(relayPort);
        switchToLocal.addActionListener(e -> {
            autoswipe.show(container, PANX_3);
        });
        SaveRemote.addActionListener(e -> {
            try {
                if((nameRemote.getText() != "") && macAddressRemote.getText() != "" && relayIP.getText() != "" && relayPort.getText() != ""){
                    machines.add(new Machine(nameRemote.getText(), macAddressRemote.getText(), true, execMethodRemote.getText(), relayIP.getText(), Integer.parseInt(relayPort.getText())));
                }    
                else{
                    JOptionPane.showMessageDialog(null, "you must insert name and MAC address of the machine you want to reach, and IP addres and port of the server you need to contact before");
                }
            } catch (Exception y) {
                JOptionPane.showMessageDialog(null, "check portIP, it should be a number " + y.getMessage());
            }
            greatWriter.saveMachines(machines);
        });
       
        insertRemoteMachines.setBackground(StdThings.getColor1());
        
        //---------------------------------------------------------
        panelInit();
        cancelRemote.addActionListener(e -> {
            panelInit();
        });
        cancelLocal.addActionListener(e -> {
            panelInit();
        });
    }
    public void panelInit(){
        if(machines.isEmpty()){
            autoswipe.show(container, PANX_1);
        }
        else{
            autoswipe.show(container, PANX_2);
        }
    }
    public static void deletes(int i){
        machines.remove(i);
        greatWriter.saveMachines(machines);
    }
    public void refresh(){ //real machines panel configuaration
        overviewTable.removeAll();
        overviewTable.setLayout(new GridLayout(rows(),5));
        for(int i=0; i<machines.size();i++){
            machinesOverview.add(new miniMacPanel(machines.get(i).getName(), i));

        }
    }
    public int rows(){
        int row = 0;
        int i2= 0;
        for(int i = 0 ;i<machines.size();i++){
            i2++;
            if(i2 == 5){
                i2 = 0;
                row++;
            }
        }
        return row;
    }


}
