package settingspanel.quickremotemanager;

import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import share.StdThings;

public abstract class greatWriter {

    public static void saveMachines(ArrayList<Machine> machines) {
        File directory = StdThings.getDirectory();
        if (!directory.exists()) {
            directory.mkdirs();
            JOptionPane.showMessageDialog(null, "Created directory in: " + directory.getAbsolutePath());
        }
        File saveFile = new File(directory, "machines.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            oos.writeObject(machines);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Machine> loadMachines() {
        File directory = StdThings.getDirectory();
        File saveFile = new File(directory, "machines.dat");
        
        if (!saveFile.exists()) {
            return new ArrayList<Machine>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            return (ArrayList<Machine>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Machine>(); 
        }
    }
}
