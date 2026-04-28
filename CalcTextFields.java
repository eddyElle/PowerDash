import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
public class CalcTextFields extends JTextField {
    CalcTextFields(String Example, Dimension dimension){
        this.setPreferredSize(dimension);
        this.setBackground(stdThings.color1);
        this.setForeground(Color.gray);
        this.setCaretColor(Color.white);
        this.setText(Example);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            if (CalcTextFields.this.getText().equals(Example)) {
                CalcTextFields.this.setText("");
                CalcTextFields.this.setForeground(Color.white);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (CalcTextFields.this.getText().isEmpty()) {
                CalcTextFields.this.setForeground(Color.gray);
                CalcTextFields.this.setText(Example);
            }
        }
        });
    }
}
