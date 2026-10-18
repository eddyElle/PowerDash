package share;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
public class StdThings {
    private static Color color1 = new Color(29,29,32); //can be white or black
    private static Color textColor1 = Color.white; //but it can also be black
    private static Color gray1 = new Color(143,143,143);
    private static Insets Inset1 = new Insets(0,0,0,0);
    private static EmptyBorder stdMargin = new EmptyBorder(3,8,8,8);
    private static Dimension stdLeftDimension = new Dimension(280,70);
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
    public static Color getTextColor1(){
        return textColor1;
    }
    public static File getDirectory(){
        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");
        File directory;
        if(os.contains("win")){
            String appData = System.getenv("APPDATA");
            directory = new File(appData + File.separator + "PowerDashData");
        }
        else{
            directory = new File(userHome + File.separator + ".PowerDashData");
        }
        return directory;
    }
}
