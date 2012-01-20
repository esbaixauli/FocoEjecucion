
package ubu.view.localization;

import java.util.ListResourceBundle;
/**
 *
 * @author Rubén
 */
public class Bundles_es extends ListResourceBundle{
    @Override
      public Object[][] getContents() {
              return contents;
      }
    static final Object[][] contents = {
    {"aboutNameLabel","ElCidCapturador"},
    {"ubuLabel","Universidad de Burgos, EPS 2011"},
    {"groupLabel","Grupo Loquesea, PGP"},
    {"versionsLabel","Nuevas versiones disponibles en:"},
    {"aboutDialog","Acerca De..."},
    {"configMenu","Configuración"},
    {"loadMenuItem","Cargar preferencias"},
    {"saveMenuItem","Guardar preferencias"},
    {"exitMenuItem","Salir"},          
    {"helpMenu","Ayuda"},
    {"aboutMenuItem","Acerca de..."},
    {"appLabel","Aplicación a capturar:"},
    {"fpsLabel","Frames Por Segundo:"},
    {"locationLabel","Directorio destino:"},
    {"stoppedStatus","Estado: Desocupado"},
    {"recordingStatus","Estado: Grabando"},
    {"pausedStatus","Estado: Pausado"},
    {"recordPanel","Grabación en curso"},
    {"menuItemRestore","Restablecer Ventana"},
    {"minim","La aplicación se minimizará a la bandeja."},
    {"warning","Aviso"},
    {"notSet","Por favor, compruebe que ha escogido sus opciones de grabación"+
               " correctamente."},
    {"file","Nombre"},
    {"invalidXML","Fichero de configuración no válido o dañado."},
    {"invalidSave","Directorio de grabación o nombre de programa erróneos."},
    {"record","Grabar"},
    {"pause","Pausar"},
    {"stop","Detener"},
    {"success","Éxito"},
    {"vidSuccess","Grabación completada con éxito."},
};
}
