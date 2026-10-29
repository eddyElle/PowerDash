package rightpanel2.asciiconverter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import share.StdButtons;
import share.StdLabels;
import share.StdTextFields;
import share.StdThings;
import java.awt.datatransfer.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
public class AsciiConverter extends JPanel{
    private LinkedHashMap<String,Integer> chars = new LinkedHashMap<>();
    private JPanel container = new JPanel();
    private JPanel active = new JPanel();
    private JLabel ascii = new StdLabels("ascii:");
    private StdTextFields asciiText = new StdTextFields("", new Dimension(74,20));
    private StdButtons copyBtn = new StdButtons("copy char");
    private String copyBtnValue = "";
    private ArrayList<StdButtons> btns = new ArrayList<>();
    public AsciiConverter(){
        this.setPreferredSize(new Dimension(280, 86));
        this.setBackground(StdThings.getColor1());
        this.setBorder(new EmptyBorder(new Insets(3, 3, 3, 10)));
        this.setLayout(new BorderLayout());
        chars.put("$", 36); 
        chars.put("|", 124);
        chars.put(">", 62);
        chars.put("<", 60);
        chars.put("*", 42); 
        chars.put(".", 46);
        chars.put(":", 58);
        chars.put(",", 44);
        chars.put("^", 94);
        chars.put("?", 63);
        chars.put("/", 47);
        chars.put("\"", 34);
        chars.put("\\",92);
        chars.put("~", 126);
        chars.put("%", 37);
        chars.put("&", 38);
        chars.put(";", 59);
        chars.put("!", 33);
        chars.put("#", 35);
        chars.put("@", 64);
        chars.put("-", 45);
        chars.put("_", 95);
        chars.put("(", 40);
        chars.put(")", 41);
        chars.put("[", 91);
        chars.put("]", 93);
        chars.put("{", 123);
        chars.put("}", 125);
        container.setLayout(new GridLayout(4,7));
        container.setBackground(StdThings.getColor1());
        active.setBackground(StdThings.getColor1());
        copyBtn.setPreferredSize(new Dimension(74,20));
        active.add(ascii);
        active.add(asciiText);
        active.add(copyBtn);
        asciiText.setEditable(false);
        asciiText.setHorizontalAlignment(JTextField.CENTER);
        active.setPreferredSize(new Dimension(80,80));
        this.add(active,BorderLayout.EAST);
        for(String i : chars.keySet()){
            btns.add(new StdButtons(i));
        }
        for(int i = 0;i<btns.size();i++){
            int[] i2 = new int[1];
            i2[0] = i;
            btns.get(i).addActionListener(e -> {
                printAscii(btns.get(i2[0]));
            });
            container.add(btns.get(i));
        }
        container.setPreferredSize(new Dimension(180,80));
        this.add(container,BorderLayout.WEST);
        copyBtn.addActionListener(e -> {
            copyChar();
        });
    }
    private void printAscii(StdButtons button){
        asciiText.setText(String.valueOf(chars.get(button.getText())));
        copyBtnValue = button.getText();
    }
    private void copyChar(){
        StringSelection selection = new StringSelection(copyBtnValue);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
    }
}
