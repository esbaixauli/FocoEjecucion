/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ubu.view;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Rubén
 */
public class ConfigFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        
        return (f.getName().toLowerCase().endsWith(".fcml")
                || f.isDirectory());
    }

    @Override
    public String getDescription() {
        return "FocusCapture XML (*.fcml)";
    }
    
}
