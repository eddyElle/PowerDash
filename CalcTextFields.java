import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
public class CalcTextFields extends JTextField {
    private String defaultText;
    CalcTextFields(String defaultText, Dimension dimension){
        this.defaultText = defaultText;
        this.setPreferredSize(dimension);
        this.setBackground(stdThings.color1);
        this.setForeground(Color.gray);
        this.setCaretColor(Color.white);
        this.setText(defaultText);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            if (CalcTextFields.this.getText().equals(defaultText) || CalcTextFields.this.getText().contains("Err:")) {
                CalcTextFields.this.setText("");
                CalcTextFields.this.setForeground(Color.white);
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
