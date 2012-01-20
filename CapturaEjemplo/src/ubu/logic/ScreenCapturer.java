package ubu.logic;

/**
 *
 * @author Rubén
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class ScreenCapturer {

    static SystemFacade sf = SystemFacade.getInstance();

    public static void captureScreen(String fileName, String format)
            throws Exception {

        Robot robot = new Robot();
        Rectangle rec = sf.getWindowRectangle();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        if (rec == null) {
            rec = new Rectangle(screensize);
        }
        int h = (int) screensize.getHeight();
        int w = (int) screensize.getWidth();
        BufferedImage image = robot.createScreenCapture(rec);
        BufferedImage black = 
                new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = black.getGraphics();
        g.drawImage(image,0,0,null);


        ImageIO.write(black, format, new File(fileName + "." + format));
    }
}
