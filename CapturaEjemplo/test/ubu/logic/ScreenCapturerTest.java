/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ubu.logic;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import junit.framework.TestCase;

/**
 *
 * @author Rubén
 */
public class ScreenCapturerTest extends TestCase {
    
    public ScreenCapturerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of captureScreen method, of class ScreenCapturer.
     */
    public void testCaptureScreen() throws Exception {
        System.out.println("captureScreen");
        String fileName ="./test";
        String format = "jpg";
        ScreenCapturer.captureScreen(fileName, format);
        
        Image f = ImageIO.read(new File("./test.jpg"));
        assertNotNull("Capturing error at screen printing",f);
    }
}
