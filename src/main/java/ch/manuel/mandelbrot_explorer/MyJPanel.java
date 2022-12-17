//Erstellt am 31.08.2014
//Autor: Manuel Schmocker

package ch.manuel.mandelbrot_explorer;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Manuel
 */
public class MyJPanel extends JPanel {
    private static int x;       //Pos. x Kreis
    private static int y;       //Pos. y Kreis
    private static int r;       //Radius Kreis
    
    MyJPanel() {
        x = 0;
        y = 0;
        r = 0;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Main.myJFrame.getImage(), 0, 0, null);
        if( Main.myJFrame.getDrawCircle() ) {
            g.setColor(Color.RED);
            g.drawOval(x-r, y-r, 2*r, 2*r);
        }
    }
    
    //Zentrum Kreis festlegen
    public static void setCenterCircle(int xCenter, int yCenter) {
        x = xCenter;
        y = yCenter;
    }
    
    //Radius festlegen
    public static void setRadiusCircle(int xPos, int yPos) {
        r = (int) Math.sqrt(Math.pow((x - xPos), 2) + Math.pow((y - yPos), 2));
    }
    
    //Radius zurückgeben
    public static int getRadiusCircle() {
        return r;
    }
}
