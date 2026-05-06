package leftpanel.notes;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import share.StdButtons;
import share.StdLabels;
import share.StdThings;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class NotesPanel extends JPanel{
    private JTextArea textArea = new JTextArea();
    private JLabel notes = new StdLabels("Notes:");
    private JPanel top = new JPanel();
    private JButton save = new StdButtons("Save");
    private JButton load = new StdButtons("Load");
    private JScrollPane scroll = new JScrollPane(textArea);
    public NotesPanel(){
        scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(205,300));
        this.setBackground(StdThings.getColor1());
        this.setBorder(StdThings.getStdMargin());
        this.add(top,BorderLayout.NORTH);
        this.top.setLayout(new GridLayout(1,3));
        this.top.setBackground(StdThings.getColor1());
        this.top.setPreferredSize(new Dimension(50,20));
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
        textArea.setBackground(StdThings.getColor1());
        textArea.setForeground(StdThings.getTextColor1());
        textArea.setCaretColor(StdThings.getTextColor1());
        textArea.setLineWrap(true);
        scroll.setBackground(StdThings.getColor1());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll,BorderLayout.CENTER);
    }
    public void saveCont(JTextArea textArea) {
        JFileChooser file = new JFileChooser();
        file.setDialogTitle("Save file");
        int userSelection = file.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File toSave = file.getSelectedFile();
            String path = toSave.getAbsolutePath();
            if (!path.toLowerCase().endsWith(".txt")) {
                toSave = new File(path + ".txt");
            }
            try (FileWriter writer = new FileWriter(toSave)) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(null, "File saved!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred");
            }
        }
    }
    public void loadCont(JTextArea textArea) {
    JFileChooser file = new JFileChooser();
    file.setDialogTitle("Open file");
    file.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files (.txt)", "txt"));
    int userSelection = file.showOpenDialog(null);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
            File toRead = file.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(toRead))) {
                textArea.read(reader, null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
            }
        }
    }
}
