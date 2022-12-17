//Erstellt am 31.08.2014
//Autor: Manuel Schmocker

package ch.manuel.mandelbrot_explorer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class Mandelbrot implements Runnable {
    //Membervariablen
    //Geometrie Plot
    static private BigDecimal centerX;              //Pos. Zentrum Ausschnitt, X-Wert
    static private BigDecimal centerY;              //Pos. Zentrum Ausschnitt, Y-Wert
    static private double intervalX;                //Breite Ausschnitt X-Achse

    //static private double plotRatio;      //Seitenverhältnis für Bildausschnitt (b : h)
    private final int pixelX;              //Anzahl Pixel X (Breite)
    private final int pixelY;              //Anzahl Pixel Y (Höhe)
    //Farbe
    static private float val_Color;
    static private float saturation;
    static private float brightness;
    static private int cutIter;
    //Berechnung
    static private int max_iter;                    //Maximale Berechnungsschritte (Iterationen)
    static private int max_val_double;              //Berechnung wechselt zu Apfloat sobald der exponent für das Interval X zu gross wird
    static private final double MAX_VAL = 4.0;      //Grösster Wert für Konvergenz (Abstand vom Ursprung r^2)
    private final int k;                            //Threat Nummer, pro Threat wird nur jede 4. Spalte berechnet
    private int nb_Threats;                         //Anzahl Threats
    //static private Apfloat zero;
    static private MathContext precision;
    static private int prec;
    
    private final BufferedImage image;
    
    //Konstruktor
    Mandelbrot( java.awt.Dimension size, BufferedImage image, int k ) {
        pixelX = size.width;
        pixelY = size.height;
        this.image = image;
        this.k = k;
    }
    
        
    //Plot zeichnen
    @Override
    public void run() {
    //public void startCalculation(){
        //Parameter für Bildausschnitt festlegen
        initCalculation();
        //Plot zeichnen (Auflösung von jPanel verwenden)
        setPixels( pixelX, pixelY);
        
        //Main.myJFrame.updateMyGUI();
        Main.myJFrame.setCalculationReady(k-1);
    }
    
    //Berechnung initialisieren
    private void initCalculation() {
        //Parameter für Bild und Farbe definieren
        precision = Main.myJFrame.getScale();
        prec = precision.getPrecision();
        centerX = NewJFrame.getCenter_X();
        centerY = NewJFrame.getCenter_Y();
        intervalX = NewJFrame.getIntervalX();
        val_Color = NewJFrame.getValColor();
        saturation = NewJFrame.getSaturation();
        brightness = NewJFrame.getBrightness();
        max_iter = NewJFrame.getIterations();
        max_val_double = NewJFrame.getMaxDouble();
        cutIter = (int) (NewJFrame.getBaseCut() * max_iter);
        nb_Threats = NewJFrame.getNbThreats();
        //Fortschirttbalken auf null setzen
        Main.myJFrame.setStatusBar(0);
    }
    
    //Plot erstellen
    //Auflösung festlegen:  w (Breite)
    //                      h (Höhe)
    private void setPixels( int w, int h) {
        
        //Paramter für Geometrie
        double intervalY = intervalX *(double) h/w;
        double stepX = intervalX / (w-1);
        double stepY = intervalY / (h-1);
        
        //Startwert
        //x0 = centerX - intervalX/2;
        //y0 = centerY - intervalY/2;
        BigDecimal x0 = centerX.subtract( new BigDecimal(intervalX / 2)).setScale(prec, RoundingMode.HALF_UP);
        BigDecimal y0 = centerY.subtract( new BigDecimal(intervalY / 2)).setScale(prec, RoundingMode.HALF_UP);
        BigDecimal xi, yi;
        int iter;
        double progress;
        
        //Entscheiden, ob BigDecimal oder double
        String dx = String.format("%e", intervalX);
        dx = dx.substring( (dx.indexOf("e") + 1), dx.length());
        int preci = Math.abs( Integer.parseInt(dx));
        
        //Berechnung mit Double
        if( preci < max_val_double ) {
            //Werte in Double umwandeln
            double x0_d = x0.doubleValue();
            double y0_d = y0.doubleValue();
            double xi_d, yi_d;
            
            //Pixel durchlaufen
            int line;
            for(int i = 0; i <= (int) w/nb_Threats; i++) {
                line = (nb_Threats*i + (k-1));
                xi_d = x0_d + line*stepX;
                if ( line >= w) break;     //Abbruch, wenn Anz. Pixel erreicht
                
                for(int j = 0; j < h; j++) {
                    yi_d = y0_d + j*stepY;
                    iter = pt_iteration( xi_d, yi_d );
                    
                    //Pixel einfärben
                    image.setRGB(line, j, getColor( iter, max_iter ));
                }
                progress = (double) (line+1) / w * 100;
                Main.myJFrame.setStatusBar( (int) progress );
            }
        //Berechnung mit BigDecimal
        } else {
            //Pixel durchlaufen
            int line;
            for(int i = 0; i <= (int) w/nb_Threats; i++) {
                line = (nb_Threats*i + (k-1));
                xi = x0.add(new BigDecimal(stepX *line, precision));       //xi = x0 + i*stepX;
                if ( line >= w) break;     //Abbruch, wenn Anz. Pixel erreicht
                
                for(int j = 0; j < h; j++) {
                    yi = y0.add(new BigDecimal(stepY * j, precision));     //yi = y0 + j*stepY
                    iter = pt_iteration( xi, yi );
                    
                    //Pixel einfärben
                    image.setRGB(line, j, getColor( iter, max_iter ));
                }
                progress = (double) (line+1) / w * 100;
                Main.myJFrame.setStatusBar( (int) progress );
            }
        }
    }
    
    //Mandelbrot-Menge
    private int pt_iteration ( double cx, double cy ) {
        double val = 0;     //Abstand zum Ursprung im Quadrat
        int iter = 0;       //Anzahl Iterationen
        double x = cx;
        double y = cy;
        double x_tmp;
        //double xt, yt;
        while ( (val <= MAX_VAL) && (iter < max_iter) ) {
            x_tmp = x * x - y * y + cx;
            y = 2 * x * y + cy;
            x = x_tmp;
            iter++;
            val = x*x + y*y;
        }
        return iter /*- Math.log(Math.log(val) / Math.log(MAX_VAL)) / Math.log(2)*/;
    }
    
    //Mandelbrot-Menge
    private int pt_iteration ( BigDecimal cx, BigDecimal cy ) {
        double val = 0;             //Abstand zum Ursprung im Quadrat
        int iter = 0;               //Anzahl Iterationen
        BigDecimal x = cx;
        BigDecimal y = cy;
        BigDecimal x_tmp;
        
        while ( (val <= MAX_VAL) && (iter < max_iter) ) {
            x_tmp = x.multiply(x).subtract(y.multiply(y)).add(cx).setScale(prec, RoundingMode.HALF_UP);
            y = x.multiply(y).multiply(new BigDecimal("2.0", precision)).add(cy).setScale(prec, RoundingMode.HALF_UP);
            x = x_tmp;
            iter++;
            val = x.multiply(x).add(y.multiply(y)).doubleValue();
            /*
            z = z.multiply(z).add(c);
            val = z.real().multiply(z.real()).add(z.imag().multiply(z.imag())).doubleValue();
            iter++;
            */
            //System.out.println();
        }
        return iter /*- Math.log(Math.log(val) / Math.log(MAX_VAL)) / Math.log(2)*/;
    }
    
    //Farbverlauf definieren
    private int getColor(int iter, int maxIter) {
        if ( iter == maxIter ) {
                return Color.BLACK.getRGB();
            }
        else {
            if (Main.myJFrame.useSingleColor) {
                float tmp_brightness = (float) (Math.pow(brightness*10, (double) iter / maxIter) - 1) / (brightness*10 - 1);
                return Color.getHSBColor(val_Color, saturation, tmp_brightness).getRGB();
            }
            else return Color.getHSBColor(val_Color + (float) (iter - cutIter)/(maxIter - cutIter), saturation, brightness).getRGB();
        }
    }
    
}
