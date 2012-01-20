package ubu.logic;

/*JNA is an open library available at Sun's website.
Both the jna.jar and the platform.jar must be present*/
import com.sun.jna.*;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.*;
import java.awt.Rectangle;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WindowsSysCall implements AbstractSysCall {

    public interface User32 extends StdCallLibrary {

        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);

        HWND GetForegroundWindow();

        int GetWindowTextA(PointerType hWnd, byte[] lpString, int nMaxCount);

        int GetWindowRect(HWND handle, int[] rect);
    }
    private String comando;

    public WindowsSysCall() {
        comando = System.getenv("windir") + "\\system32\\" + "tasklist.exe";
    }

    /**
     * Devuelve el titulo de la ventana activa.
     */
    @Override
    public String getCurrentWindowTitle() {
        byte[] windowText = new byte[512];

        PointerType hwnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);
        return Native.toString(windowText);
    }

    public Rectangle getWindowRectangle() {
        byte[] windowText = new byte[512];

        HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        int[] rect = {0, 0, 0, 0};
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        if (rect[0] < 0) {
            rect[0] = 0;
        }
        if (rect[1] < 0) {
            rect[1] = 0;
        }
        Rectangle r = new Rectangle(rect[0], rect[1], rect[2] - rect[0], rect[3] - rect[1]);
        System.out.println("Rectangle: " + r);
        return r;
    }

    @Override
    public List<String> getWindowTitleList() {
        List<String> lista = new ArrayList(6);

        try {
            String line;
            Process p = Runtime.getRuntime().exec(comando);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                lista.add(line);
            }
            input.close();
        } catch (Exception err) {
            Logger log = Logger.getLogger("mainLog");
            log.log(Level.SEVERE, "Window title error:{1}", err);
        }

        return lista;
    }
}
