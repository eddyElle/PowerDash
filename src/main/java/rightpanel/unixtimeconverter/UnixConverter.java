package rightpanel.unixtimeconverter;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import share.StdButtons;
import share.StdLabels;
import share.StdTextFields;
import share.StdThings;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
public class UnixConverter extends JPanel {
    private JPanel top = new JPanel();
    private JPanel bottom = new JPanel();
    private JLabel unixTime = new StdLabels("UnixTime: ");
    private JLabel humansTime = new StdLabels("UserTime: ");
    private StdTextFields unixText = new StdTextFields("Insert time", new Dimension(169,18));
    private StdTextFields humansText = new StdTextFields("Press Enter to convert", new Dimension(187,18));
    private JButton clsBtn = new StdButtons("C");
    public UnixConverter(){
        this.setPreferredSize(StdThings.getStdLeftDimension());
        this.setBackground(StdThings.getColor1());
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(3,3,3,3));
        top.setBackground(StdThings.getColor1());
        top.setLayout(new FlowLayout());
        bottom.setBackground(StdThings.getColor1());
        bottom.setLayout(new FlowLayout());
        this.add(top,BorderLayout.NORTH);
        this.add(bottom,BorderLayout.SOUTH);
        top.add(unixTime);
        top.add(unixText);
        top.add(clsBtn);
        bottom.add(humansTime);
        bottom.add(humansText);
        unixText.addActionListener(e -> {
            unixToHumans(unixText.getText());
        });
        humansText.addActionListener(e -> {
            humansToUnix(humansText.getText());
        });
        clsBtn.addActionListener(e -> {
            unixText.clearAll();
            humansText.clearAll();
        });
    }
    public void humansToUnix(String toConvert){
        try {
            String normalizedInput = toConvert.trim().replace(" ", "T");
            DateTimeFormatter flexFormatter = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .optionalStart().appendOffsetId().optionalEnd() // Supports Z o +HH:mm
            .toFormatter();
            TemporalAccessor accessor = flexFormatter.parseBest(normalizedInput,OffsetDateTime::from, LocalDateTime::from);
            long seconds;
            if (accessor instanceof OffsetDateTime) {
                seconds = ((OffsetDateTime) accessor).toEpochSecond();
            } else {
                // no offset = your local time Dispalyed
                seconds = ((LocalDateTime) accessor).atZone(ZoneId.systemDefault()).toEpochSecond();
            }
            unixText.setText(String.valueOf(seconds));

        } catch (Exception e) {
            unixText.setText("Err: use ISO-8601[Z/±Off.]");
        }
    }
    public void unixToHumans(String toConvert){
        try {
            long unixSeconds = Long.parseLong(toConvert);
            Instant instant = Instant.ofEpochSecond(unixSeconds);
            if (toConvert.length() == 13) {
                instant = Instant.ofEpochMilli(unixSeconds);
            }
            ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
            String formatted = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            humansText.setText(formatted);

        } catch (Exception e) {
            humansText.setText("Err: valid value required");
        }
        
    }
}
