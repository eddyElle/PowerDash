package settingspanel.quickremotemanager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import share.StdButtons;
import share.StdTextFields;
import share.StdThings;

import java.util.ArrayList;
import java.awt.*;
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
    private JPanel InsertRemoteMachines = new JPanel();
    private JLabel nothingFound = new JLabel("no machines found");
    private JButton createMachine = new StdButtons("create machine");
    private JButton refreshMacOverview = new StdButtons("refresh");
    private JButton LocalSave = new StdButtons("save");
    private JButton RemoteSave = new StdButtons("save");
    private JButton addMachine = new StdButtons("add");
    private JButton switchToRemote = new StdButtons("switch to remote");
    private JButton switchToLocal = new StdButtons("switch to local");
    private JTextField name = new StdTextFields("name", getSize());
    private JTextField macAddress = new StdTextFields("macAddress", getSize());
    private JTextField execMethod = new StdTextFields("execMethod", getSize());
    private JTextField relayIP = new StdTextFields("relayIP", getSize());
    private JTextField relayPort = new StdTextFields("relayPort", getSize());
    public remoteManager(){
        machines = greatWriter.loadMachines();
        this.setPreferredSize(new Dimension(280,130));
        this.setBackground(StdThings.getColor1());
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 8, 8, 8));
        this.add(container);
        container.add(noMachines,PANX_1);
        container.add(machinesOverview,PANX_2);
        container.add(insertLocalMachines,PANX_3);
        container.add(InsertRemoteMachines,PANX_4);
        //------------noMachines panel configutaration------
        noMachines.setLayout(new GridLayout(2,0));
        noMachines.add(nothingFound);
        noMachines.add(createMachine);
        createMachine.addActionListener(e -> {
            autoswipe.show(container, PANX_3);
        });
        //------------MachinesOverview panel configuration---
        refresh();
        refreshMacOverview.addActionListener(e -> {
            refresh();
        });
        addMachine.addActionListener(e -> {
            autoswipe.show(container, PANX_3);
        });
        //-----------InsertLocalMachine---------------
        insertLocalMachines.add(switchToRemote);
        switchToRemote.addActionListener(e -> {
            autoswipe.show(container, PANX_4);
        });
        insertLocalMachines.add(name);
        insertLocalMachines.add(macAddress);
        insertLocalMachines.add(execMethod);
        insertLocalMachines.add(LocalSave);
        LocalSave.addActionListener(e -> {
            if((name.getText() != "") && macAddress.getText() != "" ){
                machines.add(new Machine(name.getText(), macAddress.getText(), false, execMethod.getText()));
            }
            else{
                JOptionPane.showMessageDialog(null, "you must insert name and MAC address of the machine");
            }
            greatWriter.saveMachines(machines);
        });
        //-----------InsertRemoteMachine--------------
        InsertRemoteMachines.add(switchToLocal);
        switchToLocal.addActionListener(e -> {
            autoswipe.show(container, PANX_3);
        });
        InsertRemoteMachines.add(name);
        InsertRemoteMachines.add(macAddress);
        InsertRemoteMachines.add(relayIP);
        InsertRemoteMachines.add(relayPort);
        InsertRemoteMachines.add(execMethod);
        InsertRemoteMachines.add(RemoteSave);
        RemoteSave.addActionListener(e -> {
            try {
                if((name.getText() != "") && macAddress.getText() != "" && relayIP.getText() != "" && relayPort.getText() != ""){
                    machines.add(new Machine(name.getText(), macAddress.getText(), true, execMethod.getText(), relayIP.getText(), Integer.parseInt(relayPort.getText())));
                }    
                else{
                    JOptionPane.showMessageDialog(null, "you must insert name and MAC address of the machine you want to reach, and IP addres and port of the server you need to contact before");
                }
            } catch (Exception y) {
                JOptionPane.showMessageDialog(null, "check portIP, it should be a number " + y.getMessage());
            }
            greatWriter.saveMachines(machines);
        });
        //---------------------------------------------------------
        if(machines.isEmpty()){
            autoswipe.show(container, PANX_1);
        }
        else{
            autoswipe.show(container, PANX_2);
        }
    }
    public static void deletes(int i){
        machines.remove(i);
    }
    public void refresh(){ //real machines panel configuaration
        machinesOverview.removeAll();
        machinesOverview.setLayout(new GridLayout(rows(),5));
        machinesOverview.add(refreshMacOverview);
        machinesOverview.add(addMachine);
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
