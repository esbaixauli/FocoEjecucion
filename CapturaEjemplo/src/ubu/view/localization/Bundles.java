
package ubu.view.localization;

import java.util.ListResourceBundle;
/**
 *
 * @author Rubén
 */
public class Bundles extends ListResourceBundle{
    @Override
      public Object[][] getContents() {
              return contents;
      }
    static final Object[][] contents = {
    {"aboutNameLabel","ElCidCapturador"},
    {"ubuLabel","Universidad de Burgos, EPS 2011"},
    {"groupLabel","Grupo Loquesea, PGP"},
    {"versionsLabel","Check for updates at:"},
    {"aboutDialog","About..."},
    {"configMenu","Configuration"},
    {"loadMenuItem","Load preferences"},
    {"saveMenuItem","Save preferences"},
    {"exitMenuItem","Exit"},          
    {"helpMenu","Help"},
    {"aboutMenuItem","About..."},
    {"appLabel","Application to capture:"},
    {"fpsLabel","Frames Per Second:"},
    {"locationLabel","Recording folder:"},
    {"stoppedStatus","Status: Not recording"},
    {"recordingStatus","Status: Recording"},
    {"pausedStatus","Status: Paused"},
    {"recordPanel","Recording in progress"},
    {"menuItemRestore","Restore Window"},
    {"minim","Application will be minimized to tray."},
    {"warning","Warning"},
    {"notSet","Please check you have selected your recording settings properly"},
    {"file","File name"},
    {"invalidXML","Invalid or damaged settings file."},
    {"invalidSave","Erroneous output path or application name."},
    {"record","Record"},
    {"pause","Pause"},
    {"stop","Stop"},
    {"success","Success"},
    {"vidSuccess","Recording completed successfully."},
};
}
