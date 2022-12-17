//Autor: Manuel Schmocker
//Datum: 24.02.2014

package ch.manuel.mandelbrot_explorer;

//Klasse mit verschiedenen Hilfsmittel

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MyUtilities {
    
    //Kann der String in eine Zahl umgewandelt werden
    //Gibt true zurück, falls kein Fehler auftritt
    public static boolean isNumeric(String str) {
        try {  
            Double.parseDouble(str);  
        } catch(NumberFormatException nfe) {  
            return false;  
        }  
        return true;  
    }
    
    //Kann der String in einen Int umgewandelt werden
    //Gibt true zurück, falls kein Fehler auftritt
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    
    //Plot speichern
    public static void savePlot() {
        String fileEXT = ".png";
        String nameDatei = getSaveFileDialog(new java.awt.Frame(), "Datei speichern", "D:\\", "mandelbrot.png");
         
        //Speichervorgang wird nur fortgesetzt, wenn der Pfad OK ist
        if ( !(nameDatei == null)) {
            //Endung ergänzen, falls notwendig
            String endDatei = nameDatei.substring(nameDatei.length() - fileEXT.length(), nameDatei.length());
            if ( !endDatei.equals(fileEXT) ){
                nameDatei = nameDatei + fileEXT;
            }
            
            try {
                NewJFrame.t1.join();
                NewJFrame.t2.join();
                NewJFrame.t3.join();
                NewJFrame.t4.join();
                //Bild speichern
                try{
                    File outputfile = new File(nameDatei);
                    ImageIO.write(Main.myJFrame.getImage2(), "png", outputfile);
                } catch (IOException e) {

                }
                
            } catch (InterruptedException ex) {
             
            }
            
            
        }

    }
    
    //Fehler bei Eingabe: Fenster mit Fehlercode
    public static void getErrorMsg(String titel, String initTxt){
        JOptionPane.showMessageDialog(null, initTxt, titel , JOptionPane.ERROR_MESSAGE);
    }
    
    //Look and Feel festlegen
    public static void setLaF(String lf){
        //Setze das Look & Feel: "Windows" falls vorhanden
        String lookandfeel = lf;     //Nimbus, Metal, Windows, Windows Classic, CDE/Motif
        boolean isSupported = false;        //Wird das gewünschte L&F unterstützt
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (lookandfeel.equals(info.getName())) {
                isSupported = true;
                lookandfeel = info.getClassName();
                break;
            }
        }
        //System.out.println(UIManager.getLookAndFeel().getName());
        try {
            if (isSupported){
                UIManager.setLookAndFeel(lookandfeel);
            }
            else{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        //System.out.println(UIManager.getLookAndFeel().getName());
    }
    
    // Dialog zum Speichern der Datei (wird von der Methode "saveFile()" aufgerufen
    private static String getSaveFileDialog(java.awt.Frame f, String title, String defDir, String fileType) {
        java.awt.FileDialog fd = new java.awt.FileDialog(f, title, java.awt.FileDialog.SAVE);
        fd.setFile(fileType);
        fd.setDirectory(defDir);
        fd.setLocation(50, 50);
        fd.setVisible(true);
        if ( fd.getDirectory() == null) {
            return null;
        } else {
            return fd.getDirectory() + fd.getFile();
        }
    }

}
