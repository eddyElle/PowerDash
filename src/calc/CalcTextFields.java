package src.calc;
import javax.swing.*;

import src.share.StdThings;

import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
public class CalcTextFields extends JTextField {
    private String defaultText;
    CalcTextFields(String defaultText, Dimension dimension){
        this.defaultText = defaultText;
        this.setPreferredSize(dimension);
        this.setBackground(StdThings.getColor1());
        this.setForeground(Color.gray);
        this.setCaretColor(StdThings.getTextColor1());
        this.setText(defaultText);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            if (CalcTextFields.this.getText().equals(defaultText) || CalcTextFields.this.getText().contains("Err:")) {
                CalcTextFields.this.setText("");
                CalcTextFields.this.setForeground(StdThings.getTextColor1());
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (CalcTextFields.this.getText().isEmpty()) {
                CalcTextFields.this.setForeground(Color.gray);
                CalcTextFields.this.setText(defaultText);
            }
        }
        });
    }
    public void clearAll(){
        this.setForeground(Color.gray);
        this.setText(defaultText);
    }
}
