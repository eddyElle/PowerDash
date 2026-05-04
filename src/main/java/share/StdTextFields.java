package share;
import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
public class StdTextFields extends JTextField {
    private String defaultText;
    public StdTextFields(String defaultText, Dimension dimension){
        this.defaultText = defaultText;
        this.setPreferredSize(dimension);
        this.setBackground(StdThings.getColor1());
        this.setForeground(Color.gray);
        this.setCaretColor(StdThings.getTextColor1());
        super.setText(defaultText);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            if (StdTextFields.this.getText().equals(defaultText) || StdTextFields.this.getText().contains("Err:")) {
                StdTextFields.this.setText("");
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (StdTextFields.this.getText().isEmpty()) {
                StdTextFields.this.setForeground(Color.gray);
                StdTextFields.super.setText(defaultText);
            }
        }
        });
    }
    public void clearAll(){
        this.setForeground(Color.gray);
        super.setText(defaultText);
    }
    @Override
    public void setText(String t) {
        StdTextFields.this.setForeground(StdThings.getTextColor1());;
        super.setText(t);
    }
}
