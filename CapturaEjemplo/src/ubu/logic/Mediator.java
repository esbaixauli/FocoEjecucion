package ubu.logic;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.MediaLocator;
import ubu.logic.FileIO.DocumentHandler;
import ubu.logic.video.JpegImagesToMovie;

public class Mediator {

    boolean isRecording = false;
    boolean isPaused = true;
    private SystemFacade fac;
    private TimerRecordTask tRecord;
    private Timer timerR;
    TimerTask tCheck;
    Timer timerC;
    int scNumber = 0;

    public Mediator() {
        fac = SystemFacade.getInstance();

    }

    // Start image capture
    public void start(final int frameRate, final String rec,
            int checkRate, final String path, final String format) {
        System.out.println("Recording at Framerate: "+ frameRate);
        try {
            tCheck = new TimerTask() {
//number of the screenshot within the current capture

                @Override
                public void run() {

                    String currentWindow =
                            fac.getCurrentWindowTitle().toLowerCase();
                    if (currentWindow.contains(rec.toLowerCase())
                            && isRecording == false) {
//The screenshot number must be known for the recording to resume
                        tRecord = new TimerRecordTask(path, format, scNumber);
                        timerR = new Timer();
                        timerR.scheduleAtFixedRate(tRecord, 0, frameRate);
                        isRecording = true;
                        isPaused = false;
                    } else if (!(currentWindow.contains(rec)) && isRecording) {
//If the desired window doesn't match the window on focus, pause
// Get the latest frame number and pause.
                        isRecording = false;
                        isPaused = true;
                        scNumber = tRecord.getScNumber();
                        timerR.cancel();
                        tRecord.cancel();
                    }
                }

                public int getScNumber() {
                    return scNumber;
                }
            };

            timerC = new Timer();
            timerC.scheduleAtFixedRate(tCheck, 0, checkRate);
        } catch (Exception edd) {
            //LOG
            Logger log = Logger.getLogger("mainLog");
            log.log(Level.SEVERE, "Window title error:{1}", edd);
        }
    }

    // Pauses image capture
    public void pause() {
        isRecording = false;
        isPaused = true;
        if (timerR != null) {
            timerR.cancel();
        }
        if (tRecord != null) {
            scNumber = tRecord.getScNumber();
            tRecord.cancel();
        }
        if (timerC != null) {
            timerC.cancel();
        }
        if (tCheck != null) {
            tCheck.cancel();
        }

    }

    // Stops image capture (Generates video)
    public void stop(String rec,int fps) {
        pause();
        generateVideo(rec,fps); //Comment this line to not generate video
        scNumber = 0;
    }

    private void generateVideo(String rec,int fps){
       int i;
       Vector imgs;
       imgs= new Vector();
       for(i=1;i<=scNumber;i++){
            imgs.addElement(rec+i+".jpg");
       }
       if(!(imgs.isEmpty())){
          JpegImagesToMovie jp= new JpegImagesToMovie();
          MediaLocator media = new MediaLocator("file:/"+
                  rec+".mov");
          Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
          jp.doIt((int)sz.getWidth(),(int)sz.getHeight(), fps, imgs, media);
       }
    
    }
    
    
    // Returns the focused window's title 
    public String getCurrentWindowTitle() {
        return fac.getCurrentWindowTitle();
    }

    // Returns a list of working windows
    public List<String> getWindowTitleList() {
        return fac.getWindowTitleList();
    }

    // Reads the Configuration file
    public List getConfig(String savePath) throws FileNotFoundException,
            Exception {
        return DocumentHandler.readConfig(savePath);
    }

    // Saves the configuration settings
    public void setConfig(String appName,String fps,String output,
            String writePath) throws Exception{
        DocumentHandler.writeConfig(appName, fps, output, writePath);
    }
    // True if Windows is the working OS

    public boolean isWindows() {
        return fac.isWindows();
    }
}
