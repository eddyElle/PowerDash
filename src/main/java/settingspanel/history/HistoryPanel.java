package settingspanel.history;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import share.StdButtons;
import share.StdLabels;
import share.StdThings;
public class HistoryPanel extends JPanel {
    private JTextArea textArea = new JTextArea();
    private JLabel history = new StdLabels("History:");
    private JPanel top = new JPanel();
    private JButton clear = new StdButtons("Clear");
    private JButton locate = new StdButtons("Locate");
    private JButton referesh = new StdButtons("Refresh");
    private JScrollPane scroll = new JScrollPane(textArea);
    public HistoryPanel(){
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
        this.setPreferredSize(new Dimension(280,130));
        this.setBackground(StdThings.getColor1());
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 8, 8, 8));
        this.add(top,BorderLayout.NORTH);
        this.top.setLayout(new GridLayout(1,4));
        this.top.setBackground(StdThings.getColor1());
        this.top.setPreferredSize(new Dimension(50,20));
        this.history.setPreferredSize(new Dimension(50,20));
        this.top.add(history);
        this.top.add(referesh); 
        this.top.add(clear);
        this.top.add(locate);
        loadCont(textArea);
        clear.addActionListener(e -> {
            clearCont();
        });
        locate.addActionListener(e -> {
            locate();
        });
        referesh.addActionListener(e -> {
            loadCont(textArea);
        });
        textArea.setEditable(false);
        textArea.setBackground(StdThings.getColor1());
        textArea.setForeground(StdThings.getTextColor1());
        textArea.setCaretColor(StdThings.getTextColor1());
        textArea.setLineWrap(true);
        scroll.setBackground(StdThings.getColor1());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scroll,BorderLayout.CENTER);
    }
    private void loadCont(JTextArea textArea){
        try {
            File directory = StdThings.getDirectory();
            Path path = Path.of(directory.getAbsolutePath() + "/work_log.txt");
            String content = Files.readString(path);
            textArea.setText(content);    
        } catch (Exception e) {
            textArea.setText("an Error occcured: " + e.getMessage());
        }
    }
    private void clearCont(){
        File directory = StdThings.getDirectory();
        try {
            Path path = Path.of(directory.getAbsolutePath() + "/work_log.txt");
            Files.deleteIfExists(path);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "failed to clear history: " + e.getMessage());
        }
        if (!directory.exists()) {
            directory.mkdirs();
            JOptionPane.showMessageDialog(this, "Created directory in: " + directory.getAbsolutePath());
        }
        File logFile = new File(directory, "work_log.txt");
        try (FileWriter fw = new FileWriter(logFile, true)) {
            fw.write("Working History:\n");
        } catch (IOException e) {
             JOptionPane.showMessageDialog(this, "failed to clear history: " + e.getMessage());
        }
        loadCont(textArea);
    }
    private void locate(){
        File directory = StdThings.getDirectory();
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")){
            JOptionPane.showMessageDialog(this, "Worklog file in: " + directory.getAbsolutePath());
        }
        else{
            JOptionPane.showMessageDialog(this, "Worklog file in: " + directory.getAbsolutePath() + "\n CTRL+H to show hidden folders like this");
        }
    }

}
