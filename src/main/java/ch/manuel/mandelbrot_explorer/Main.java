//Autor: Manuel Schmocker
//Datum: 08.09.2014

package ch.manuel.mandelbrot_explorer;

public class Main {
    //Membervariablen
    public static NewJFrame myJFrame;
    
    public static void main(String[] args) {
        
        //Set Look and Feel
        MyUtilities.setLaF("Windows");
        
        //Fenster erstellen und Anzeigen (Hauptfenster)
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                myJFrame =new NewJFrame();
                myJFrame.setVisible(true);
            }
        });
    }
}
