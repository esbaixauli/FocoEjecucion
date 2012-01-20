/*
 * Tray icon for the app.
 */
package ubu.view;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import ubu.logic.Mediator;

/**
 *
 * @author Rubén
 */
public class CaptureTray {

    private CaptureMainFrame captureMainFrame;
    private javax.swing.JMenuItem menuItemRestore;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemRec;
    private javax.swing.JMenuItem menuItemStop;
    private javax.swing.JPopupMenu popupContextual;
    private javax.swing.JSeparator separator;
    private SystemTray tray;
    private TrayIcon trayIcon;
    private ResourceBundle res;
    private Mediator m;

    public CaptureTray(CaptureMainFrame captureMainFrame, ResourceBundle res, 
            Mediator m) {
        this.m=m;
        initialize(captureMainFrame,res);
    }

    private void initialize(final CaptureMainFrame captureMainFrame,
                            final ResourceBundle res) {
        this.res=res;
        this.captureMainFrame = captureMainFrame;
        String path = "/res/logo_tray.png";
        if (SystemTray.isSupported()) {
            trayIcon = new TrayIcon(
                    new javax.swing.ImageIcon(getClass().
                    getResource(path)).getImage(), 
                    res.getString("aboutNameLabel"));
            popupContextual = new javax.swing.JPopupMenu();
            menuItemRestore = new javax.swing.JMenuItem();
            separator = new javax.swing.JSeparator();
            menuItemExit = new javax.swing.JMenuItem();
            menuItemRestore.setText(res.getString("menuItemRestore"));
            menuItemRestore.addActionListener
                    (new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuItemRestoreActionPerformed(evt);
                }
            });
            
            menuItemRec = new javax.swing.JMenuItem();
            if(captureMainFrame.isRecClicked()){
                menuItemRec.setText(res.getString("pause"));
            }else{
                menuItemRec.setText(res.getString("record")); 
            }
            menuItemRec.addActionListener
                    (new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuItemRecActionPerformed(evt);
                }
            }); 
            
            menuItemStop= new javax.swing.JMenuItem();
            menuItemStop.setText(res.getString("stop"));
            menuItemStop.addActionListener
                    (new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuItemStopActionPerformed(evt);
                }
            });          
            
            
            popupContextual.add(menuItemRestore);
            popupContextual.add(separator);
            popupContextual.add(menuItemRec); 
            popupContextual.add(menuItemStop);
            popupContextual.add(new javax.swing.JSeparator());
            
            menuItemExit.setText(res.getString("exitMenuItem"));
            menuItemExit.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuItemExitActionPerformed(evt);
                }
            });
            popupContextual.add(menuItemExit);


            tray = SystemTray.getSystemTray();
            captureMainFrame.setVisible(false);



            trayIcon.setImageAutoSize(true);
            trayIcon.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    popupContextual.setLocation(e.getX(), e.getY());
                    popupContextual.setInvoker(popupContextual);
                    popupContextual.setVisible(true);
                    if(captureMainFrame.isRecClicked()){
                        menuItemRec.setText(res.getString("pause"));
                    }else{
                        menuItemRec.setText(res.getString("record")); 
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

        } else {
            captureMainFrame.setVisible(true);
        }
    }

    private void menuItemRestoreActionPerformed(java.awt.event.ActionEvent e){
        captureMainFrame.setVisible(true);
        captureMainFrame.toFront();
        tray.remove(trayIcon);
    }

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    private void menuItemRecActionPerformed(java.awt.event.ActionEvent evt) {
        captureMainFrame.doClickStart();
        if(captureMainFrame.isRecClicked()){
                menuItemRec.setText(res.getString("pause"));
        }else{
                menuItemRec.setText(res.getString("record")); 
            }
    }  
    private void menuItemStopActionPerformed(java.awt.event.ActionEvent evt) {
        captureMainFrame.doClickStop();
    }  
    
    protected javax.swing.JMenuItem getMenuItemExit(){
        return menuItemExit;
    }

    protected void restoreTray() {
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            //LOG
            Logger log = Logger.getLogger("mainLog");
            log.log(Level.SEVERE, "Icon couldn't be added to tray:{7}", e);
            System.err.println();
            captureMainFrame.setVisible(true);
        }
    }
}
