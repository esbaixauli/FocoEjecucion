package ubu.logic;

import java.util.List;
import java.awt.Rectangle;

public class SystemFacade {
	
	private static SystemFacade instance = null;
	private boolean isWindows;
	
	private AbstractSysCall system;
	
	private SystemFacade ()
	{
		String osName = System.getProperty("os.name");
		if (osName.indexOf("Windows")!=-1) {
			system = new WindowsSysCall();
			isWindows = true;
		} else if (osName.indexOf("Mac")!=-1) {
			system = new MacOsSysCall();
			isWindows = false;
		} else if (osName.equals("Linux")) {
			system = new LinuxSysCall();
			isWindows = false;
		} else {
			// error (new exception type)
		}
					
	}
	
	public static SystemFacade getInstance ()
	{
		if (instance == null)
			instance = new SystemFacade();
		
		return instance;
	}
	
	public String getCurrentWindowTitle ()
	{
		return system.getCurrentWindowTitle();
	}
	
	public List<String> getWindowTitleList ()
	{
		return system.getWindowTitleList();
	}
	
	public boolean isWindows ()
	{
		return isWindows;
	}

	public Rectangle getWindowRectangle ()
	{
		return system.getWindowRectangle();
	}

	public static void main (String args[])
	{
		SystemFacade sf = SystemFacade.getInstance();
		System.out.println(sf.getCurrentWindowTitle());
		Rectangle r = sf.getWindowRectangle();
		System.out.println("x: "+r.getX());
		System.out.println("y: "+r.getY());
		System.out.println("width: "+r.getWidth());
		System.out.println("height: "+r.getHeight());
	}
	
}
