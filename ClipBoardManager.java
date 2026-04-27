import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.util.Objects;
public class ClipBoardManager extends Thread {
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private String lastText = "";
    private int currentIndex = 0;
    public ClipBoardManager(JButton btn1, JButton btn2, JButton btn3){
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
    }
    @Override
    public void run() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        while (true) {
            try {
                Transferable content = clipboard.getContents(null);
                if (content != null && content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String copyed = (String) content.getTransferData(DataFlavor.stringFlavor);
                    if (!Objects.equals(copyed, lastText)) {
                        lastText = copyed;
                        SwingUtilities.invokeLater(() -> {
                            switch (currentIndex) {
                                case 0:
                                    btn1.setText(copyed);
                                    break;
                                case 1:
                                    btn2.setText(copyed);
                                    break;
                                case 2:
                                    btn3.setText(copyed);
                                    break;
                                default:
                                    throw new IllegalArgumentException("something went wrong");
                            }
                            currentIndex++;
                            if(currentIndex == 3){
                                currentIndex = 0;
                            }
                        });
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
    
}
