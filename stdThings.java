//import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public interface stdThings {
    final Color color1 = new Color(29,29,32);
    final Color gray1 = new Color(143,143,143);
    final Insets Inset1 = new Insets(0,0,0,0);
    final EmptyBorder stdMargin = new EmptyBorder(3,8,8,8);
    final Dimension stdLeftDimension = new Dimension(280,70);
    public static Color getColor1(){
        return color1;
    }
    public static Color getGray1(){
        return gray1;
    }
    public static Insets getInset1(){
        return Inset1;
    }
    public static EmptyBorder getStdMargin(){
        return stdMargin;
    }
    public static Dimension getStdLeftDimension(){
        return stdLeftDimension;
    }
    
}
