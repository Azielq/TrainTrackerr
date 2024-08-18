package com.mycompany.traintrack.client.igu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.SwingUtilities;

public class pnlTrainMap extends javax.swing.JPanel {


    private BufferedImage bg;
    private FlatSVGIcon[] trainIcons;
    private int[][] trainPositions;


    
    
    public pnlTrainMap() {
        
        initComponents();
        

        try {
            bg = ImageIO.read(getClass().getResource("/Map.png"));
            bg = Scalr.resize(bg, Scalr.Method.ULTRA_QUALITY, 950, 730);

            trainIcons = new FlatSVGIcon[4];
            trainIcons[0] = new FlatSVGIcon("1.svg", 50, 50);
            trainIcons[1] = new FlatSVGIcon("2.svg", 50, 50);
            trainIcons[2] = new FlatSVGIcon("3.svg", 50, 50);
            trainIcons[3] = new FlatSVGIcon("4.svg", 50, 50);

            trainPositions = new int[4][2];
            trainPositions[0] = new int[]{42, 298};
            trainPositions[1] = new int[]{855, 237};
            trainPositions[2] = new int[]{480, 237};
            trainPositions[3] = new int[]{480, 237};

            setLayout(null);
            setSize(950, 730);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTrainPosition(int trainIndex, int x, int y) {
        trainPositions[trainIndex][0] = x;
        trainPositions[trainIndex][1] = y;
        System.out.println("Posición de " + trainIndex + " actualizada a: (" + x + ", " + y + ")");
        SwingUtilities.invokeLater(this::repaint); // Asegura que el repintado ocurra en el hilo de la GUI
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bg != null) {
            g.drawImage(bg, 0, 0, this);
        }

        for (int i = 0; i < trainIcons.length; i++) {
            trainIcons[i].paintIcon(this, g, trainPositions[i][0], trainPositions[i][1]);
        }
    }
    
        

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(950, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
