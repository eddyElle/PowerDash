import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class NotesPanel extends JPanel{
    private JTextArea textArea = new JTextArea();
    private JLabel notes = new JLabel("Notes:");
    private JPanel top = new JPanel();
    private JButton save = new Buttons("save");
    private JButton load = new Buttons("load");
    public NotesPanel(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(205,300));
        this.setBackground(stdThings.getColor1());
        this.setBorder(new EmptyBorder(3,8,8,8));
        this.add(top,BorderLayout.NORTH);
        this.top.setLayout(new GridLayout(1,3));
        this.top.setBackground(stdThings.getColor1());
        this.top.setPreferredSize(new Dimension(50,20));
        this.notes.setForeground(Color.white);
        this.notes.setPreferredSize(new Dimension(50,20));
        this.top.add(notes); 
        this.top.add(save);
        this.save.addActionListener(e ->{
            saveCont(textArea);
        });
        this.top.add(load);
        this.load.addActionListener(e -> {
            loadCont(textArea);
        });
        textArea.setBackground(stdThings.getColor1());
        textArea.setForeground(Color.white);
        textArea.setCaretColor(Color.white);
        textArea.setLineWrap(true);
        this.add(textArea,BorderLayout.CENTER);
    }
    public void saveCont(JTextArea textArea) {
        JFileChooser file = new JFileChooser();
        file.setDialogTitle("Salva file");
        int userSelection = file.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File toSave = file.getSelectedFile();
            String path = toSave.getAbsolutePath();
            if (!path.toLowerCase().endsWith(".txt")) {
                toSave = new File(path + ".txt");
            }
            try (FileWriter writer = new FileWriter(toSave)) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(null, "File salvato!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Errore durante il salvataggio");
            }
        }
    }
    public void loadCont(JTextArea textArea) {
    JFileChooser file = new JFileChooser();
    file.setDialogTitle("Seleziona il file da aprire");
    file.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("File di testo (.txt)", "txt"));
    int userSelection = file.showOpenDialog(null);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
            File toRead = file.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(toRead))) {
                textArea.read(reader, null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Errore nella lettura del file: " + e.getMessage());
            }
        }
    }
}
