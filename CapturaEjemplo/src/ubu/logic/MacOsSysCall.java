package ubu.logic;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MacOsSysCall implements AbstractSysCall{
	
	private static String app;
	  
	public String getCurrentWindowTitle(){
	    try{
	        String[] command={"osascript","-e", "tell application \"System Events\"",
	        "-e", "set frontApp to title of first application process whose frontmost is true",
	        "-e","end tell"};
	        final Process process = Runtime.getRuntime().exec(command);
	      //Thread in which the result of executing the command is obtained
	        new Thread(){
	            public void run(){
	                try{
	                    InputStream is = process.getInputStream();
	                    byte[] buffer = new byte[1024];
	                    for(int count = 0; (count = is.read(buffer)) >= 0;){
	                        System.out.write(buffer, 0, count);
	                    }
	                    app = new String(buffer);
	                }catch(Exception e){
                            Logger log = Logger.getLogger("mainLog");
                            log.log(Level.SEVERE, "Timer error:{2}", e);
	                }
	            }
	        }.start();
	        //If an error occurs while executing the command
	        new Thread(){
	            public void run(){
	                try{
	                    InputStream is = process.getErrorStream();
	                    byte[] buffer = new byte[1024];
	                    for(int count = 0; (count = is.read(buffer)) >= 0;){
	                        System.err.write(buffer, 0, count);
	                    }
	                }catch(Exception e){
                            Logger log = Logger.getLogger("mainLog");
                            log.log(Level.SEVERE, "Exec error:{3}", e);
	                }
	            }
	        }.start();

	        int returnCode = process.waitFor();
	        System.out.println("Return code = " + returnCode+" "+app);
	    }catch (Exception e){
	        e.printStackTrace();
	    } 
	    return app;
	  }
	  
	  public List<String> getWindowTitleList ()
	  {
		  return null;
	  }
          
    @Override
          public Rectangle getWindowRectangle(){
      /*          String[] command={"osascript","-e", "tell application "
                        +getCurrentWindowTitle(),
	        "-e", "get bounds of front window",
	        "-e","end tell"};*/
                   return null;
               
          }
}
