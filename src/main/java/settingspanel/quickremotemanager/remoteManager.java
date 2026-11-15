package settingspanel.quickremotemanager;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import share.StdScroll;
import share.StdButtons;
import share.StdLabels;
import share.StdTextFields;
import share.StdThings;
import java.awt.event.ActionListener;
public class remoteManager extends JPanel {
    public static ArrayList<Machine> machines;
    private static String PANX_1 = "noMachines";
    private static String PANX_2 = "machinesOverview";
    private static String PANX_3 = "insertLocalMachine";
    private static String PANX_4 = "inserRemoteMachine";
    private static String  PANX_5 = "emptySwitchPanel";
    private static CardLayout autoswipe = new CardLayout();
    private static JPanel container = new JPanel(autoswipe);
    private JPanel noMachines = new JPanel();
    private static JPanel machinesOverview = new JPanel(new BorderLayout());
    private JPanel insertLocalMachines = new JPanel();
    private JPanel insertRemoteMachines = new JPanel();
    private JPanel emptySwitchPanel = new JPanel();
    public static JPanel overviewTable = new JPanel();
    private JLabel nothingFound = new StdLabels("no machines found");
    private JButton createMachine = new StdButtons("create machine");
    private JButton refreshMacOverview = new StdButtons("refresh");
    private static JButton SaveLocal = new StdButtons("save");
    private static JButton SaveRemote = new StdButtons("save");
    private JButton addMachine = new StdButtons("add");
    private JButton cancelLocal = new StdButtons("cancel");
    private JButton cancelRemote = new StdButtons("cancel");
    private static JButton switchToLocal = new StdButtons("switch to local");
    private static JButton switchToRemote = new StdButtons("switch to remote");
    private static StdTextFields nameLocal = new StdTextFields("name (max 6 char)", new Dimension(50,18));
    private static StdTextFields macAddressLocal = new StdTextFields("macAddress", new Dimension(50,18));
    private static StdTextFields execMethodLocal = new StdTextFields("execMethod", new Dimension(50,18));
    private static StdTextFields nameRemote = new StdTextFields("name (max 6 char)", new Dimension(50,18));
    private static StdTextFields macAddressRemote = new StdTextFields("macAddress", new Dimension(50,18));
    private static StdTextFields execMethodRemote = new StdTextFields("execMethod", new Dimension(50,18));
    private static StdTextFields relayIP = new StdTextFields("relayIP (IPV4)", new Dimension(50,18));
    private static StdTextFields relayPort = new StdTextFields("relayPort", new Dimension(50,18));
    private static int[] index = { 0 };
    private JScrollPane tableScroll = new JScrollPane(overviewTable);
    private static ActionListener saveNormalRemote = e -> {
        try {
            if((getChecked(nameRemote.getText(), macAddressRemote.getText()).equals("")) && getCheckedRemote(relayIP.getText(), relayPort.getText()).equals("")){
                machines.add(new Machine(nameRemote.getText(), macAddressRemote.getText(), true, execMethodRemote.getText(), relayIP.getText(), Integer.parseInt(relayPort.getText())));
                greatWriter.saveMachines(machines);
                refresh();
            }    
            else{
                throw new IllegalArgumentException(getChecked(nameRemote.getText(), macAddressRemote.getText()) + " |!| " + getCheckedRemote(relayIP.getText(), relayPort.getText()));
            }
        } catch (Exception y) {
            JOptionPane.showMessageDialog(null, y.getMessage());
        }
        panelInit();
    };
    private static ActionListener saveNormalLocal = e -> {
        try {
            if((getChecked(nameLocal.getText(), macAddressLocal.getText()).equals(""))){
                machines.add(new Machine(nameLocal.getText(), macAddressLocal.getText(), false, execMethodLocal.getText()));
            }
            else{
                throw new IllegalArgumentException(getChecked(nameLocal.getText(), macAddressLocal.getText()));
            }
            greatWriter.saveMachines(machines);
            refresh();    
        }catch(Exception y) {
            JOptionPane.showMessageDialog(null, y.getMessage());
        }
        panelInit();
    };
    private static ActionListener editesListenerRemote = e -> {
        try {
            if((getChecked(nameRemote.getText(), macAddressRemote.getText()).equals("")) && getCheckedRemote(relayIP.getText(), relayPort.getText()).equals("")){
                machines.remove(index[0]);
                machines.add(index[0], new Machine(nameRemote.getText(), macAddressRemote.getText(), true, execMethodRemote.getText(), relayIP.getText(), Integer.parseInt(relayPort.getText())));
                    greatWriter.saveMachines(machines);
                    refresh();
                }    
                else{
                    throw new IllegalArgumentException(getChecked(nameRemote.getText(), macAddressRemote.getText()) + " |!| " + getCheckedRemote(relayIP.getText(), relayPort.getText()));
                }
        }catch (Exception y) {
            JOptionPane.showMessageDialog(null, y.getMessage());
        }
        SaveRemote.addActionListener(saveNormalRemote);
        panelInit();
    };
    private static ActionListener editesListenerLocal = e -> {
        try {
            if((getChecked(nameLocal.getText(), macAddressLocal.getText()).equals(""))){
                machines.remove(index[0]);
                machines.add(index[0], new Machine(nameLocal.getText(), macAddressLocal.getText(), false, execMethodLocal.getText()));
                greatWriter.saveMachines(machines);
                refresh();    
            }
            else{
                throw new IllegalArgumentException(getChecked(nameLocal.getText(), macAddressLocal.getText()));
            }
        }catch(Exception y) {
            JOptionPane.showMessageDialog(null, y.getMessage());
        }
        SaveLocal.addActionListener(saveNormalLocal);
        panelInit();
    };
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
        container.add(emptySwitchPanel,PANX_5);
        //------------noMachines panel configutaration------
        noMachines.setLayout(new GridLayout(2,0));
        noMachines.setBackground(StdThings.getColor1());
        nothingFound.setHorizontalAlignment(JLabel.CENTER);
        nothingFound.setVerticalAlignment(JLabel.CENTER);
        noMachines.add(nothingFound);
        noMachines.add(createMachine);
        createMachine.addActionListener(e -> {
            autoswipe.show(container, PANX_3);
            nameLocal.clearAll();
            macAddressLocal.clearAll();
        execMethodLocal.clearAll();
        });
        
        //------------MachinesOverview panel configuration---
        JPanel overviewNav = new JPanel();
        
        machinesOverview.add(overviewNav,BorderLayout.NORTH);
        overviewNav.setLayout(new GridLayout(0,2));
        overviewNav.add(refreshMacOverview);
        overviewNav.add(addMachine);
        refresh();
        refreshMacOverview.addActionListener(e -> {
            refresh();
        });
        addMachine.addActionListener(e -> {
            autoswipe.show(container, PANX_3);
            nameLocal.clearAll();
            macAddressLocal.clearAll();
            execMethodLocal.clearAll();
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
            nameRemote.clearAll();
            macAddressRemote.clearAll();
            execMethodRemote.clearAll();
            relayIP.clearAll();
            relayPort.clearAll();

        });
        SaveLocal.addActionListener(saveNormalLocal);
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
            nameLocal.clearAll();
            macAddressLocal.clearAll();
            execMethodLocal.clearAll();
        });
        SaveRemote.addActionListener(saveNormalRemote);
        insertRemoteMachines.setBackground(StdThings.getColor1());   
        //-------------------emptySwitchPanelConfigurationn------------------------
        emptySwitchPanel.setBackground(StdThings.getColor1());
        //-------------------scrollPaneConfiguration
        tableScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.trackColor = StdThings.getColor1();
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                g.setColor(StdThings.getGray1());
                g.fillRect(thumbBounds.x + 2, thumbBounds.y + 2, 
                           thumbBounds.width - 4, thumbBounds.height - 4);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });
        tableScroll.setBackground(StdThings.getColor1());
        tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        machinesOverview.add(tableScroll,BorderLayout.CENTER);
        //----------------------------------------------------------
        panelInit();
        cancelRemote.addActionListener(e -> {
            SaveRemote.removeActionListener(saveNormalRemote);
            SaveRemote.addActionListener(saveNormalRemote);
            panelInit();
        });
        cancelLocal.addActionListener(e -> {
            SaveLocal.removeActionListener(saveNormalLocal);
            SaveLocal.addActionListener(saveNormalLocal);
            panelInit();

        });
    }
    public static void panelInit(){
        if(machines.isEmpty()){
            autoswipe.show(container, PANX_1);
        }
        else{
            SaveRemote.removeActionListener(editesListenerRemote);
            SaveLocal.removeActionListener(editesListenerLocal);
            switchToLocal.setEnabled(true);
            switchToRemote.setEnabled(true);
            autoswipe.show(container, PANX_2);
        }
    }
    public static void deletes(int i){
        autoswipe.show(container, PANX_5);
        machines.remove(i);
        greatWriter.saveMachines(machines);
        refresh();
        autoswipe.show(container, PANX_2);
    }
    public static void edites(int i){
        if(machines.get(i).getConnType()){
            autoswipe.show(container, PANX_4);
            nameRemote.setText(machines.get(i).getName());
            macAddressRemote.setText(machines.get(i).getMacAddress());
            execMethodRemote.setText(machines.get(i).getExecMethod());
            relayIP.setText(machines.get(i).getRelayIP());
            relayPort.setText( "" + machines.get(i).getRelayPort()); 
            SaveRemote.removeActionListener(saveNormalRemote);
            index[0] = i;
            SaveRemote.addActionListener(editesListenerRemote);
            switchToLocal.setEnabled(false);
            
        }
        else{
            autoswipe.show(container, PANX_3);
            nameLocal.setText(machines.get(i).getName());
            macAddressLocal.setText(machines.get(i).getMacAddress());
            execMethodLocal.setText(machines.get(i).getExecMethod());
            SaveLocal.removeActionListener(saveNormalLocal);
            index[0] = i;
            SaveLocal.addActionListener(editesListenerLocal);
            switchToRemote.setEnabled(false);

        }
    }
    public static  void refresh(){ //real machines panel configuaration
        overviewTable.removeAll();
        overviewTable.setLayout(new GridLayout(0,5));
        for(int i=0; i<machines.size();i++){
            overviewTable.add(new miniMacPanel(machines.get(i).getName(), i));

        }
        machinesOverview.revalidate();
        machinesOverview.repaint();
        
    }
    public static String getChecked(String name, String macAddress){
        if(macAddress.equals("macAddress") || name.equals("name (max 6 char)")){
            return "you must insert name and MAC address of the machine";
        }
        if(name.length() > 6){
            return "name must be 6 characters maximum";
        }
        try {
            String[] macCheker = macAddress.split(":");
            if(macCheker.length < 6 || macCheker.length > 6){
                return "invalid MAC address";
            }
            else{
                for(int i=0;i<macCheker.length;i++){
                    if(macCheker[i].length() > 2){
                        return "invalid MAC address";
                    }
                }
            }
            return "";
        } catch (Exception e) {
            return "something in the input was wrong " + e.getMessage();
        }
    }
    public static String getCheckedRemote(String ip, String port){
        try {
            if(ip.equals("relayIP (IPV4)") || port.equals("relayPort")){
                return "you must insert IP and Port of the relay server";
            }
            String[] ipChecker = ip.split("\\.");
            if(ipChecker.length > 4 || ipChecker.length < 4){
                return "invalid IP address" + ipChecker.length;
            }
            for(int i=0;i<ipChecker.length;i++){
                Integer.parseInt(ipChecker[i]);
                
            }
            Integer.parseInt(port);
            return "";
        } catch (Exception e) {
            return "IP and port must be composed by numbers";
        }
    }
}
