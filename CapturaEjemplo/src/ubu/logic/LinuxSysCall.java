package ubu.logic;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.awt.Rectangle;

public class LinuxSysCall implements AbstractSysCall {
	
	private String comando1, comando2, salida;
	int x, y, height, width;
	
	public LinuxSysCall ()
	{
		comando1 = "wmctrl -lG | grep -E '^0x0*'`xprop -root | grep ";
		comando1 += "'_NET_ACTIVE_WINDOW(WINDOW)'  | awk -F ' window ";
		comando1 += "id # 0x' '{print $2}'` | cut -f 4- -d ' '";
		
		comando2 = "wmctrl -l | cut -f 5- -d ' '";
	}

	private void getInfo ()
	{
		ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", comando1);
		//pb = pb.redirectErrorStream(true);	// just for debugging
		InputStream is = null;
		String[] s;
		byte[] b = null;
		int[] nums = new int[4];
		int leidos = 0, previo = 0;
		
		try {
			Process p = pb.start();
			is = p.getInputStream();
			p.waitFor();
			b = new byte[is.available()];
			is.read(b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (is == null) {
			salida = null;
			return;	// there is no active window, nothing else to do
		} else {
			salida = new String(b);
		}

		s = salida.split(" ");
		salida = "";
		for (int i=0; i<s.length; ++i) {
			if (leidos < 4) {
				try {
					nums[leidos] = Integer.parseInt(s[i]);
					leidos++;
				} catch (Exception e) {}
			} else {
				salida += s[i]+" ";
			}
		}

		salida = salida.substring(salida.indexOf(" ", 2)+1);
		
		x = nums[0];
		y = nums[1];
		width = nums[2];
		height = nums[3];
	}
	
	/**
	 * Returns active window title.
	 */
	public String getCurrentWindowTitle ()
	{
		getInfo();
		if (salida == null)
			return "";
		return salida;
	}

	/**
	 * Returns the rectangle that defines the active window. Must be
	 * called after getCurrentWindowTitle().
	 **/
	public Rectangle getWindowRectangle ()
	{
		if (salida == null)
			return null;

		return new Rectangle(x, y, width, height);
	}
	
	public List<String> getWindowTitleList ()
	{
		List<String> lista = new ArrayList(6);
		ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", comando2);
		//pb = pb.redirectErrorStream(true);	// just for debugging
		InputStream is;
		BufferedReader bf;
		String s;
		
		try {
			Process p = pb.start();
			is = p.getInputStream();
			p.waitFor();
			bf = new BufferedReader(new InputStreamReader(is));
			s = bf.readLine();
			while (s != null) {
				lista.add(s);
				s = bf.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
}
