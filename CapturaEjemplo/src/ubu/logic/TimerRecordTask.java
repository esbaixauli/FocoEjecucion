/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ubu.logic;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Rubén
 */
public class TimerRecordTask extends TimerTask {
    private String path;
    private String format;

    // number of the screenshot within the current capture
    int i;
    public TimerRecordTask(String path, String format, int i){
        this.path=path;
        this.format=format;
        this.i=i;
        
    }
    @Override
    public void run(){
        try {
            i++;
            long beg=System.currentTimeMillis();
            ScreenCapturer.captureScreen(path+i, format);
            long end=System.currentTimeMillis();           
            System.out.println("Elapsed: " + (end-beg) +" ms");
        } catch (Exception ex) {
           //LOG
            Logger log = Logger.getLogger("mainLog");
            log.log(Level.SEVERE, "IO capturing error in trecordtask:{-1}", ex);
     
        }
    }
    public int getScNumber(){
        return i;
    }
    
}
