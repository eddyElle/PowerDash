package rightpanel.mediaplayer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import rightpanel.hwmonitor.Gauge;
import share.StdButtons;
import share.StdThings;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Player extends JPanel {
    private JPanel vMeter = new JPanel();
    private JPanel controls = new JPanel();
    private Gauge chl = new Gauge("CHL");
    private Gauge typ = new Gauge("TYP");
    private Gauge chr = new Gauge("CHR");
    
    private StdButtons play = new StdButtons("\u25B6");
    private StdButtons back = new StdButtons("\u25C0\u25C0");
    private StdButtons next = new StdButtons("\u25B6\u25B6");

    private ScheduledExecutorService executor;
    private boolean isPlaying = false;

    private double curL = 0, curR = 0, curT = 0;
    private int targetL = 0, targetR = 0, targetT = 0;

    // Identificazione OS
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public Player() {
        this.setPreferredSize(new Dimension(280, 86));
        this.setBackground(StdThings.getColor1());
        this.setBorder(new EmptyBorder(new Insets(3, 5, 3, 18)));
        this.setLayout(new BorderLayout());

        vMeter.setBackground(StdThings.getColor1());
        vMeter.setLayout(new GridLayout(0, 3));
        vMeter.add(chl);
        vMeter.add(typ);
        vMeter.add(chr);
        this.add(vMeter, BorderLayout.WEST);

        controls.setBackground(StdThings.getColor1());
        controls.setLayout(new GridLayout(0, 3));
        controls.setPreferredSize(new Dimension(90, 30));
        controls.setBorder(new EmptyBorder(15, 0, 15, 0));
        controls.add(back);
        controls.add(play);
        controls.add(next);
        this.add(controls, BorderLayout.EAST);

        // Listener dei pulsanti
        play.addActionListener(e -> {
            sendMediaCommand("playpause");
            togglePlayback();
        });

        back.addActionListener(e -> sendMediaCommand("prev"));
        next.addActionListener(e -> sendMediaCommand("next"));
    }

    /**
     * Invia il comando multimediale al Sistema Operativo
     */
    private void sendMediaCommand(String action) {
        try {
            if (OS.contains("win")) {
                String key = switch (action) {
                    case "playpause" -> "[char]179";
                    case "next" -> "[char]176";
                    case "prev" -> "[char]177";
                    default -> "";
                };
                new ProcessBuilder("powershell", "-Command", "(New-Object -ComObject wscript.shell).SendKeys(" + key + ")").start();
            
            } else if (OS.contains("mac")) {
                String code = switch (action) {
                    case "playpause" -> "131";
                    case "next" -> "124";
                    case "prev" -> "123";
                    default -> "";
                };
                new ProcessBuilder("osascript", "-e", "tell application \"System Events\" to key code " + code).start();
            
            } else if (OS.contains("nix") || OS.contains("nux")) {
                String mpris = switch (action) {
                    case "playpause" -> "PlayPause";
                    case "next" -> "Next";
                    case "prev" -> "Previous";
                    default -> "";
                };
                new ProcessBuilder("bash", "-c", "dbus-send --dest=org.mpris.MediaPlayer2.Player /org/mpris/MediaPlayer2 org.mpris.MediaPlayer2.Player." + mpris).start();
            }
        } catch (IOException e) {
            System.err.println("Errore nell'invio del comando media: " + e.getMessage());
        }
    }

    private void togglePlayback() {
        if (isPlaying) stopAnimation();
        else startAnimation();
    }

    private void startAnimation() {
        isPlaying = true;
        play.setText("\u23F8"); // Icona pausa
        executor = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r);
        // Diciamo al Sistema Operativo che questo thread è più importante degli altri
        t.setPriority(Thread.MAX_PRIORITY); 
        // Fondamentale: permette al thread di chiudersi se chiudi l'app
        t.setDaemon(true); 
        return t;
    });

        Runnable task = () -> {
            if (isPlaying) {
                if (Math.random() > 0.7) { 
                    targetL = (int) (Math.random() * 101);
                    targetR = (int) (Math.random() * 101);
                    targetT = (int) (Math.random() * 101);
                }

                curL = approach(curL, targetL, 10);
                curR = approach(curR, targetR, 10);
                curT = approach(curT, targetT, 10);

                SwingUtilities.invokeLater(() -> {
                    chl.setPercentage(calcDb((int)curL), (int)curL, "db");
                    chr.setPercentage(calcDb((int)curR), (int)curR, "db");
                    typ.setPercentage(calcDb((int)curT), (int)curT, "db");
                });
            }
        };
        executor.scheduleAtFixedRate(task, 0, 15, TimeUnit.MILLISECONDS);
    }

    private double approach(double current, int target, int step) {
        if (Math.abs(current - target) < step) return target;
        return current < target ? current + step : current - step;
    }

    public void stopAnimation() {
        isPlaying = false;
        play.setText("\u25B6"); // Icona play
        if (executor != null) {
            executor.shutdownNow();
            executor = null;
        }
        resetPositions();
        turnOff();
    }

    private void resetPositions() {
        curL = 0; curR = 0; curT = 0;
        targetL = 0; targetR = 0; targetT = 0;
    }

    public int calcDb(int puntoScala) {
        return (int) (Math.round(((puntoScala / 100.0 * 6) - 3)));
    }

    public void turnOff() {
        SwingUtilities.invokeLater(() -> {
            chl.setPercentage(-3, 0, "db");
            typ.setPercentage(-3, 0, "db");
            chr.setPercentage(-3, 0, "db");
        });
    }
}