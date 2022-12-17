//Autor: Manuel Schmocker
//Erstellt am 28.08.2014

package ch.manuel.mandelbrot_explorer;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

//Klasse NewJFrame: Rahmen mit für BufferedImage erstellen
public class NewJFrame extends javax.swing.JFrame {
    //Membervariablen
    private Mandelbrot mandelbrotPlot1, mandelbrotPlot2, mandelbrotPlot3, mandelbrotPlot4;
    private final Mandelbrot mandelbrotPlot21, mandelbrotPlot22, mandelbrotPlot23, mandelbrotPlot24;
    private BufferedImage image;
    private final BufferedImage imageDetail;
    //Default Werte für Bildausschnitt
    private static final String DEF_CENTER_X = "-0.5";          //Standartwert Pos. Zentrum X
    private static final String DEF_CENTER_Y = "0.0";           //Standartwert Pos. Zentrum Y
    private static final String DEF_LEN_INTERVAL_X = "4.0";     //Länge Intervall X-Achse (Breite Plot)
    private static final String DEF_STATUS_CALCUL = "Ready..."; //Länge Intervall X-Achse (Breite Plot)
    private static final int DEF_COLOR = 73;                    //Standart-Wert für Farbe
    private static final int DEF_SATURATION = 70;               //Standart-Wert für Sättigung (70/100)
    private static final int DEF_BRIGHTNESS = 75;               //Standart-Wert für Helligkeit (70/100)
    //Werte für Bildausschnitt
    private static BigDecimal center_x;
    private static BigDecimal center_y;
    private static double interval_x;
    private static double ratio;                                //Breite : Höhe
    private static float valColor;                              //Für HSB Farbraum: Farbe
    private static float saturation;                            //Für HSB Farbraum: 0f - 1.0f
    private static float brightness;                            //Für HSB Farbraum: 0f - 1.0f
    private static float baseCut;
    public boolean useSingleColor;                              //Darstellung mit nur 1 Farbe
    //Parameter für Berechnung  
    private static int maxIter;                                 //Max. Anzahl Iterationen
    private static int maxExpDouble;                            //Max. Exponent für Double (10^-maxExpDouble) -> Wechsel zu BigDecimal
    private static int preci;                                   //Genauigkeit Berechnung (Anzahl Kommastellen)
    private static MathContext preci_MC;                        //Genauigkeit als Objekt "MathContext"
    //Kontrollparameter für Zeichnung
    private static boolean drawCircle;                          //Soll der Kreis gezeichnet werden (Auswahl für Zoom)
    private static boolean readyForCalculation;                 //Kontrolliert, dass nicht zwei Berechnungsvorgänge gleichzeitig gestartet werden
    private static boolean calculated[];                        //Threat abgeschlossen-> true
    //Threat
    private static int nbThreats;
    public static Thread t1;
    public static Thread t2;
    public static Thread t3;
    public static Thread t4;
    //Zeit
    private static double time0;
        
    //Konstruktor
    public NewJFrame() {
        //Standard Methode von NetBeans
        initComponents();
        
        //Werte für Bildausschnitt und Farbe festlegen
        //Standard-Werte festlegen
        preci = 20;
        preci_MC = new MathContext(preci);
        center_x = new BigDecimal(DEF_CENTER_X, preci_MC);
        center_y = new BigDecimal(DEF_CENTER_Y, preci_MC);
        interval_x = Double.parseDouble(DEF_LEN_INTERVAL_X);
        ratio = (double) jPanel1.getWidth() / jPanel1.getHeight();
        saturation = 0.7f;
        brightness = 0.75f;
        valColor = 0.73f;
        baseCut = 0.0f;
        useSingleColor = false;
        
        //Eigene Komponenten initialisieren
        //Normale Auflösung für JPanel
        image = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_ARGB);    //Bild erstellen
        mandelbrotPlot1 = new Mandelbrot( jPanel1.getSize(), image, 1 );        //Für Threat 1
        mandelbrotPlot2 = new Mandelbrot( jPanel1.getSize(), image, 2 );        //Für Threat 2
        mandelbrotPlot3 = new Mandelbrot( jPanel1.getSize(), image, 3 );        //Für Threat 3
        mandelbrotPlot4 = new Mandelbrot( jPanel1.getSize(), image, 4 );        //Für Threat 4
        //Hohe Auflösung für Bild speichern
        java.awt.Dimension sizeDetail = new java.awt.Dimension(4000, 3000);
        imageDetail = new BufferedImage(sizeDetail.width, sizeDetail.height, BufferedImage.TYPE_INT_ARGB);              //Bild erstellen
        mandelbrotPlot21 = new Mandelbrot( sizeDetail, imageDetail, 1 );              //Für Threat 1
        mandelbrotPlot22 = new Mandelbrot( sizeDetail, imageDetail, 2 );              //Für Threat 2
        mandelbrotPlot23 = new Mandelbrot( sizeDetail, imageDetail, 3 );              //Für Threat 3
        mandelbrotPlot24 = new Mandelbrot( sizeDetail, imageDetail, 4 );              //Für Threat 4

        //Weitere Komponenten
        drawCircle = false;
        readyForCalculation = true;
        nbThreats = 4;
        calculated = new boolean[nbThreats];
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new MyJPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSlider3 = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();
        jLabel18 = new javax.swing.JLabel();
        jSlider4 = new javax.swing.JSlider();
        jCheckBox1 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 800));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 798, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("Plot");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 20));

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Progress:");

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField6.setText("Ready...");
        jTextField6.setBorder(null);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText(DEF_LEN_INTERVAL_X);

        jLabel5.setText("Length Interval ΔX:");

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText(DEF_CENTER_Y);

        jLabel4.setText("Pos. Center Y:");

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText(DEF_CENTER_X);

        jLabel3.setText("Pos. Center X:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Frame Position:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Cursor:");

        jLabel9.setText("Cursor position X:");

        jTextField4.setEditable(false);
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel11.setText("Cursor positionY:");

        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Calculation Parameter:");

        jLabel13.setText("Max. Iterations:");

        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setText("1000");

        jLabel14.setText("Change to Big Decimals:");

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setText("10");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2", "4" }));
        jComboBox1.setSelectedIndex(1);

        jLabel15.setText("Nb. of Threats:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Color Parameter:");

        jLabel17.setText("Color");

        jSlider3.setToolTipText(Integer.toString(jSlider3.getValue()));
        jSlider3.setValue(DEF_COLOR);

        jLabel7.setText("Saturation");

        jSlider1.setValue(DEF_SATURATION);

        jLabel8.setText("Brightness");

        jSlider2.setValue(DEF_BRIGHTNESS);

        jLabel18.setText("Base cut");

        jSlider4.setValue(0);

        jCheckBox1.setText("Use singel color");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(15, 15, 15)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSlider3, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(jSlider4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)
                                .addComponent(jLabel18)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(jTextField5))
                            .addComponent(jCheckBox1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jMenu1.setText("Export");

        jMenuItem1.setText("Export to PNG");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField6))
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Save buffered Image to PNG
        startCalculation(true);
        MyUtilities.savePlot();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Taste Plot: 
        //Parameter testen und Plot zeichnen
        if ( testParameters() ) {
            //Berechnung starten
            startCalculation();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Taste Reset
        //Default Bildausschnitt darstellen
        jTextField3.setText(DEF_CENTER_X);
        jTextField2.setText(DEF_CENTER_Y);
        jTextField1.setText(DEF_LEN_INTERVAL_X);
        jTextField7.setText("1000");
        jTextField8.setText("10");
        //Berechnung starten
        if ( testParameters() ) {
            //Berechnung starten
            startCalculation();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        //Position Maus
        double interval_y = interval_x / ratio;
        double posX_d = evt.getX() * interval_x / jPanel1.getWidth() - interval_x / 2;  //+ center_x
        BigDecimal posX = new BigDecimal(posX_d).add(center_x).setScale(preci, RoundingMode.HALF_UP);
        double posY_d = evt.getY() * interval_y / jPanel1.getHeight() - interval_y / 2; //+ center_y
        BigDecimal posY = new BigDecimal(posY_d).add(center_y).setScale(preci, RoundingMode.HALF_UP);

        //Kreis Zoom zeichnen (falls noch nicht geklickt wurde)
        if( !drawCircle ) {
            MyJPanel.setCenterCircle(evt.getX(), evt.getY());
            jTextField3.setText(posX.toString());
            jTextField2.setText(posY.toString());
        } else {
            double dx = MyJPanel.getRadiusCircle() / (double) jPanel1.getWidth() * interval_x; 
            jTextField1.setText(String.valueOf(dx));
        }
        drawCircle = !drawCircle;
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
        //Position Maus
        double interval_y = interval_x / ratio;
        double posX_d = evt.getX() * interval_x / jPanel1.getWidth() - interval_x / 2;  //+ center_x
        BigDecimal posX = new BigDecimal(posX_d).add(center_x).setScale(preci, RoundingMode.HALF_UP);
        double posY_d = evt.getY() * interval_y / jPanel1.getHeight() - interval_y / 2; //+ center_y
        BigDecimal posY = new BigDecimal(posY_d).add(center_y).setScale(preci, RoundingMode.HALF_UP);
        
        //In Textfeld schreiben
        jTextField4.setText(posX.toString());
        jTextField5.setText(posY.toString());
        
        //Kreis
        MyJPanel.setRadiusCircle(evt.getX(), evt.getY());
        jPanel1.repaint();
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        //Normale Auflösung für JPanel
        image = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_ARGB);    //Bild erstellen
        mandelbrotPlot1 = new Mandelbrot( jPanel1.getSize(), image, 1 );        //Für Threat 1
        mandelbrotPlot2 = new Mandelbrot( jPanel1.getSize(), image, 2 );        //Für Threat 2
        mandelbrotPlot3 = new Mandelbrot( jPanel1.getSize(), image, 3 );        //Für Threat 3
        mandelbrotPlot4 = new Mandelbrot( jPanel1.getSize(), image, 4 );        //Für Threat 4
        ratio = (double) jPanel1.getWidth() / jPanel1.getHeight();
    }//GEN-LAST:event_jPanel1ComponentResized

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        useSingleColor = jCheckBox1.isSelected();
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JSlider jSlider4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
    
    //Parameter für Bildausschnitt festlegen
    private boolean testParameters() {
        boolean inputOK = true;
        String str;
        
        //1. Textfelder auslesen
        //1.1 Zentrum Plot (X-Wert): jTextFeld3
        str = jTextField3.getText();
        if ( MyUtilities.isNumeric(str) ) center_x = new BigDecimal(str, preci_MC);   //valueOf(Double.parseDouble(str));
        else inputOK = false;
        //1.2 Zentrum Plot (Y-Wert): jTextFeld2
        str = jTextField2.getText();
        if ( MyUtilities.isNumeric(str) ) center_y = new BigDecimal(str, preci_MC);   //valueOf(Double.parseDouble(str));
        else inputOK = false;
        //1.3 Länge Intervall X-Achse: jTextFeld1
        str = jTextField1.getText();
        if ( MyUtilities.isNumeric(str) ) interval_x = Double.parseDouble(str);
        else inputOK = false;
        //2. Berechnungs-Optionen
        //2.1 Max. Anzahl Iterationen
        str = jTextField7.getText();
        if ( MyUtilities.isInteger(str) ) maxIter = Integer.parseInt(str);
        else inputOK = false;
        //2.2 Wert für Wechsel zu BigDecimel
        str = jTextField8.getText();
        if ( MyUtilities.isInteger(str) ) maxExpDouble = Integer.parseInt(str);
        else inputOK = false;
        //2.3 Anzahl Threats
        nbThreats = Integer.parseInt(jComboBox1.getSelectedItem().toString());
        //3. Slider auslesen
        //3.1 Sättigung
        saturation = (float) jSlider1.getValue() / ( jSlider1.getMaximum() - jSlider1.getMinimum() );
        //3.2 Helligkeit
        brightness = (float) jSlider2.getValue() / ( jSlider2.getMaximum() - jSlider2.getMinimum() );
        //3.3 Farbe
        valColor = (float) jSlider3.getValue() / ( jSlider3.getMaximum() - jSlider3.getMinimum() );
        //3.4 Iterationen Sklieren
        baseCut = (float) jSlider4.getValue() / ( jSlider4.getMaximum() - jSlider4.getMinimum() );

        //Falls Werte OK sind -> Plot zeichnen
        if ( !inputOK ) {
            MyUtilities.getErrorMsg("Eingabefehler", "Eingabe prüfen. Wert muss eine Zahl sein!");
        }

        return inputOK;
    }
    
    //Berechnung starten, ohne normale Auflösung
    private void startCalculation() {
        startCalculation(false);
    }
    
    //Berechnung starten
    //Für hohe Auflösung: True
    private void startCalculation(boolean bool) {
        setPrecision();
        calculated = new boolean[nbThreats];
        if( readyForCalculation ) {
            readyForCalculation = false;
            jTextField6.setText("Calculating...");
            time0 = System.currentTimeMillis();
            
            //Threats erstellen und starten
            //True: Berechnung mit hoher Auflösung
            if( bool ) {
                t1 = new Thread(mandelbrotPlot21);
                t2 = new Thread(mandelbrotPlot22);
                t1.start();
                t2.start();
                if ( nbThreats == 4 ) {
                    t3 = new Thread(mandelbrotPlot23);
                    t4 = new Thread(mandelbrotPlot24);
                    t3.start();
                    t4.start();
                }
            //False: Normale Berechnung
            } else {
                t1 = new Thread(mandelbrotPlot1);
                t2 = new Thread(mandelbrotPlot2);
                t1.start();
                t2.start();
                if ( nbThreats == 4 ) {
                    t3 = new Thread(mandelbrotPlot3);
                    t4 = new Thread(mandelbrotPlot4);
                    t3.start();
                    t4.start();
                }
            }
        } else {
            MyUtilities.getErrorMsg("Fehler", "Berechnung läuft bereits...");
        }
    }
    
    //Parameter für Bildausschnitt zurückgeben
    //Zentrum Plot (X-Wert)
    public static  BigDecimal getCenter_X() {
        return center_x;   
    }
    
    //Zentrum Plot (Y-Wert)
    public static BigDecimal getCenter_Y() {
        return center_y;   
    }
    
    //Intervall X-Achse
    public static double getIntervalX() {
        return interval_x;   
    }
    
    //Paramter für Farbe zurückgeben
    //Sättigung (HSB Farbraum)
    public static float getSaturation() {
        return saturation;
    }
    
    //Helligkeit (HSB Farbraum)
    public static float getBrightness() {
        return brightness;
    }
    
    //Farbe für Startwert HSB Farbraum
    public static float getValColor() {
        return valColor;
    }
    
    //Farbraumskalierung
    public static float getBaseCut() {
        return baseCut;
    }
    
    //Scale
    public MathContext getScale() {
        return preci_MC;
    }
    
    //Anzahl Iterationen
    public static int getIterations() {
        return maxIter;
    }
    
    //Max Exp. Double
    public static int getMaxDouble() {
        return maxExpDouble;
    }
    
    //Set Progress-Bar
    //Wert zwischen 0 und 1.0
    public void setStatusBar(int status) {
        int st = jProgressBar1.getValue();
        
        if (status > st ) {
            jProgressBar1.setValue( status );
            
            //Prognose Zeit
            double dt = System.currentTimeMillis() - time0;
            jTextField6.setText("Calculating..." + (int) (dt * (100 - status) / (status * 1000)) + " s Remaining");
            this.repaint();
        }
    }
    //Reset
    public void resetStatusBar() {
        jProgressBar1.setValue(0);
        this.repaint();
    }
    
    //Bild zurückgeben
    public BufferedImage getImage() {
        return image;
    }
    
    //Bild zurückgeben (mit hoher Auflösung)
    public BufferedImage getImage2() {
        return imageDetail;
    }
    
    //Status: Kreis zeichnen ja/nein
    public boolean getDrawCircle() {
        return drawCircle;
    }
    
    //Anzahl Threats für Berechnung
    public static int getNbThreats() {
        return nbThreats;
    }
    
    //Mitteilen, wenn der Prozess beendet ist
    public void setCalculationReady(int nb) {
        calculated[nb] = true;
        
        boolean tmp_bool = true;
        for( int i = 0; i < nbThreats; i++ ) {
            if( !calculated[i] ) tmp_bool = false;
        }
        if( tmp_bool ) {
            readyForCalculation = true;
            jTextField6.setText(DEF_STATUS_CALCUL);
            resetStatusBar();
        }
    }
    
    //Präzision für Berechnung mit BigDecimal festlegen
    private void setPrecision() {
        String dx = String.format("%e", interval_x);
        dx = dx.substring( (dx.indexOf("e") + 1), dx.length());
        preci = Math.abs( Integer.parseInt(dx)) + 6;
        preci_MC = new MathContext(preci);
    }

}
