package configmanager;
import java.io.*;
import java.util.Properties;

/**
 * Manages application configuration by reading/writing to a local file.
 * If the file doesn't exist, it creates one with default values.
 */
public class ConfigManager {
    private static final String FILE_NAME = "config.txt";
    private Properties props = new Properties();

    public ConfigManager() {
        initializeConfig();
    }

    private void initializeConfig() {
        File configFile = new File(FILE_NAME);

        if (!configFile.exists()) {
            System.out.println("First run detected: generating configuration file...");
            setDefaultValues();
            saveToDisk();
        } else {
            loadFromDisk();
        }
    }

    private void setDefaultValues() {
        // Define your default settings here
        props.setProperty("Breaks", "true");
        props.setProperty("Breaks_min", "20");
    }

    private void loadFromDisk() {
        try (InputStream input = new FileInputStream(FILE_NAME)) {
            props.load(input);
        } catch (IOException ex) {
            System.err.println("Error reading config file: " + ex.getMessage());
        }
    }

    public void saveToDisk() {
        try (OutputStream output = new FileOutputStream(FILE_NAME)) {
            props.store(output, "User Settings - Automatically Generated");
        } catch (IOException io) {
            System.err.println("Error saving config file: " + io.getMessage());
        }
    }

    // --- GETTERS ---

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key, "false"));
    }

    public int getInt(String key) {
        try {
            return Integer.parseInt(props.getProperty(key, "0"));
        } catch (NumberFormatException e) {
            return 0; // Returns 0 if the value in the file is not a valid number
        }
    }

    // --- SETTER ---
    public void setValue(String key, Object value) {
        props.setProperty(key, String.valueOf(value));
        saveToDisk();
    }
}
