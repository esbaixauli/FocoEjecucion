package ubu.logic;

import java.util.List;
import java.awt.Rectangle;

public interface AbstractSysCall {
	
	/**
	 * Devuelve el titulo de la ventana activa.
	 */
	public abstract String getCurrentWindowTitle ();
	
	/**
	 * Devuelve una lista con los títulos de las ventanas.
	 */
	public abstract List<String> getWindowTitleList ();

	/**
	 * Returns the rectangle that defines the active window. Must be
	 * called after getCurrentWindowTitle().
	 **/
	public Rectangle getWindowRectangle ();
	
}
