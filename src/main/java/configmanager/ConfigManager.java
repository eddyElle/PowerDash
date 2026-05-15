package configmanager;

import java.io.*;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ConfigManager {
    private static final String FILE_NAME = "config.txt";
    private Properties props = new Properties();
    // Variabile per gestire il percorso completo del file
    private File configFile;

    public ConfigManager() {
        // Questa parte gestisce la creazione della cartella Documents/PowerDash
        String userHome = System.getProperty("user.home");
        File directory = new File(userHome + File.separator + "Documents" + File.separator + "PowerDash");
        
        if (!directory.exists()) {
            directory.mkdirs();
            JOptionPane.showMessageDialog(null, "Created directory in: " + directory.getAbsolutePath());
        }

        // Il file ora punta sempre dentro la cartella PowerDash
        this.configFile = new File(directory, FILE_NAME);
        
        initializeConfig();
    }

    private void initializeConfig() {
        // Usa il riferimento configFile che punta alla cartella Documenti
        if (!configFile.exists()) {
            System.out.println("First run detected: generating configuration file...");
            setDefaultValues();
            saveToDisk();
        } else {
            loadFromDisk();
        }
    }

    private void setDefaultValues() {
        props.setProperty("Breaks", "true");
        props.setProperty("Breaks_min", "20");
    }

    private void loadFromDisk() {
        try (InputStream input = new FileInputStream(configFile)) {
            props.load(input);
        } catch (IOException ex) {
            System.err.println("Error reading config file: " + ex.getMessage());
        }
    }

    public void saveToDisk() {
        try (OutputStream output = new FileOutputStream(configFile)) {
            props.store(output, "User Settings - Automatically Generated");
        } catch (IOException io) {
            System.err.println("Error saving config file: " + io.getMessage());
        }
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key, "false"));
    }

    public int getInt(String key) {
        try {
            return Integer.parseInt(props.getProperty(key, "0"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setValue(String key, Object value) {
        props.setProperty(key, String.valueOf(value));
        saveToDisk(); // Mantiene il salvataggio automatico dopo la modifica
    }
}

