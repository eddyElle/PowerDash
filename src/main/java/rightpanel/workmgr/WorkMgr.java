package rightpanel.workmgr;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import rightpanel.quicksettings.QuickSett;
import share.StdButtons;
import share.StdLabels;
import share.StdTextFields;
import share.StdThings;
public class WorkMgr extends JPanel {
    private JPanel top = new JPanel();
    private JPanel bottom = new JPanel();
    private JLabel worked = new StdLabels("Worked:");
    private JLabel hoursMins = new StdLabels("0h 0min");
    private StdButtons playBtn = new StdButtons("Start");
    private StdTextFields copilot = new StdTextFields("Press start", new Dimension(144, 21));
    private JLabel brk = new StdLabels("F.Brks(m):");
    private StdButtons decreaseBrk = new StdButtons("-");
    private StdButtons increaseBrk = new StdButtons("+");
    private JButton finishDay = new StdButtons("End shift");
    private JLabel brkMins = new StdLabels(String.valueOf(QuickSett.config.getInt("Breaks_min")));
    private Timer timer;
    private Timer flashTimer;
    private int totalSeconds = 0;
    private int breakLimitMinutes = QuickSett.config.getInt("Breaks_min");
    private boolean isPaused = true;
    private boolean isBlinking = false;

    public WorkMgr() {
        if(QuickSett.config.getBoolean("Breaks") == false){
            brkMins.setForeground(StdThings.getGray1());
            increaseBrk.setEnabled(false);
            decreaseBrk.setEnabled(false);
        }
        this.setPreferredSize(StdThings.getStdLeftDimension());
        this.setBackground(StdThings.getColor1());
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        top.setBackground(StdThings.getColor1());
        top.setLayout(new FlowLayout());
        bottom.setBackground(StdThings.getColor1());
        bottom.setLayout(new FlowLayout());
        playBtn.setPreferredSize(new Dimension(46,22));
        finishDay.setPreferredSize(new Dimension(76,22));
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
        
        decreaseBrk.setPreferredSize(new Dimension(12, 21));
        
        top.add(worked);
        top.add(hoursMins);
        top.add(copilot);
        bottom.add(brk);
        bottom.add(decreaseBrk);
        bottom.add(brkMins);
        bottom.add(increaseBrk);
        bottom.add(playBtn);
        bottom.add(finishDay);
        copilot.setHorizontalAlignment(JTextField.CENTER);
        copilot.setEditable(false);
        initLogic();
    }

    private void initLogic() {
        // main timer
        timer = new Timer(1000, e -> {
            totalSeconds++;
            updateTimeLabel();
            checkStatus();
        });

        // blink timer
        flashTimer = new Timer(500, e -> {
            if (isBlinking) {
                Color currentBg = copilot.getBackground();
                boolean isOverworking = totalSeconds >= 28800;
                Color alertColor = isOverworking ? Color.RED : Color.YELLOW;

                if (currentBg.equals(alertColor)) {
                    copilot.setBackground(StdThings.getColor1());
                } else {
                    copilot.setBackground(alertColor);
                }
            }
        });

        playBtn.addActionListener(e -> toggleTimer());
        finishDay.addActionListener(e -> endShift());

        increaseBrk.addActionListener(e -> {
            breakLimitMinutes++;
            brkMins.setText(String.valueOf(breakLimitMinutes));
            QuickSett.config.setValue("Breaks_min", breakLimitMinutes);
        });

        decreaseBrk.addActionListener(e -> {
            if (breakLimitMinutes > 1) {
                breakLimitMinutes--;
                brkMins.setText(String.valueOf(breakLimitMinutes));
                QuickSett.config.setValue("Breaks_min", breakLimitMinutes);
            }
        });
    }

    private void toggleTimer() {
        if (isPaused) {
            timer.start();
            playBtn.setText("Pause");
            isPaused = false;
            updateStatus();
        } else {
            timer.stop();
            stopBlinking();
            playBtn.setText("Start");
            copilot.setText("Paused");
            isPaused = true;
        }
    }
    private void updateTimeLabel() {
        int hours = totalSeconds / 3600;
        int mins = (totalSeconds % 3600) / 60;
        hoursMins.setText(hours + "h " + mins + "min");
    }
    private void checkStatus() {
        int currentMins = totalSeconds / 60;
        int currentSecs = totalSeconds % 60;

        // updates Overworking state (8h+)
        updateStatus();

        if (QuickSett.config.getBoolean("Breaks")) {
            brkMins.setForeground(StdThings.getTextColor1());
            increaseBrk.setEnabled(true);
            decreaseBrk.setEnabled(true);
            if (currentMins > 0 && currentMins % breakLimitMinutes == 0 && currentSecs == 0) {
                startBlinking("TAKE A BREAK!", Color.YELLOW);
            }
        } else {

        if (copilot.getText().equals("TAKE A BREAK!")) {
            stopBlinking();
            copilot.setText("Working...");
        }
        brkMins.setForeground(StdThings.getGray1());
        increaseBrk.setEnabled(false);
        decreaseBrk.setEnabled(false);
        }
    }

    private void updateStatus() {
        if (!isPaused) {
            if (totalSeconds >= 28800) { // 8 Hours
                startBlinking("OVERWORKING!", Color.RED);
            } else if (!copilot.getText().equals("TAKE A BREAK!")) {
                stopBlinking();
                copilot.setText("Working...");
            }
        }
    }

    private void startBlinking(String text, Color bg) {
        copilot.setText(text);
        copilot.setForeground(Color.BLACK);
        isBlinking = true;
        if (!flashTimer.isRunning()) flashTimer.start();
    }

    private void stopBlinking() {
        isBlinking = false;
        flashTimer.stop();
        copilot.setBackground(StdThings.getColor1());
        copilot.setForeground(StdThings.getTextColor1());
    }

    private void endShift() {
        timer.stop();
        stopBlinking();
        saveToFile();
        
        totalSeconds = 0;
        updateTimeLabel();
        playBtn.setText("Start");
        copilot.setText("Shift ended");
        isPaused = true;
    }

    private void saveToFile() {
        String userHome = System.getProperty("user.home");
        File directory = new File(userHome + File.separator + "Documents" + File.separator + "PowerDash");
        
        if (!directory.exists()) {
            directory.mkdirs();
            JOptionPane.showMessageDialog(this, "Created directory in: " + directory.getAbsolutePath());
        }

        File logFile = new File(directory, "work_log.txt");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        int hours = totalSeconds / 3600;
        int mins = (totalSeconds % 3600) / 60;

        try (FileWriter fw = new FileWriter(logFile, true)) {
            fw.write("[" + dtf.format(now) + "] Session: " + hours + "h " + mins + "m\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}