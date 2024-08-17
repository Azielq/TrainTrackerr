package com.mycompany.traintrack.client.igu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.imgscalr.Scalr;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.image.BufferedImage;

public class pnlTrainMap extends javax.swing.JPanel {

    private BufferedImage bg;
    private FlatSVGIcon bgIcon;
    //private Image scaledImage;
    private FlatSVGIcon[] trainIcons;
    //private Train[] trains;

    
    
    public pnlTrainMap() {
        
        initComponents();
        //bg = new ImageIcon(getClass().getResource("/Map.jpg")).getImage();
        try {
        bg = ImageIO.read(getClass().getResource("/Map.png"));
        bg = Scalr.resize(bg, Scalr.Method.ULTRA_QUALITY, 950, 730);


        trainIcons = new FlatSVGIcon[4];
        trainIcons[0] = new FlatSVGIcon("1.svg", 50, 50);  // (ancho x alto)
        trainIcons[1] = new FlatSVGIcon("2.svg", 50, 50);
        trainIcons[2] = new FlatSVGIcon("3.svg", 50, 50);
        trainIcons[3] = new FlatSVGIcon("4.svg", 50, 50);

        setLayout(null);
        setSize(950, 730);
          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            
        if (bg != null) {
            g.drawImage(bg, 0, 0, this);
        }

        // Dibujar los iconos SVG en el panel
        // Ejemplo de cómo dibujar los iconos en posiciones específicas
        trainIcons[0].paintIcon(this, g, 42, 298);  // Dibuja Train1.svg en (100, 100)
        trainIcons[1].paintIcon(this, g, 855, 235);  // Dibuja Train2.svg en (200, 200)
        trainIcons[2].paintIcon(this, g, 480, 237);  // Dibuja Train3.svg en (300, 300)
        trainIcons[3].paintIcon(this, g, 480, 239);  // Dibuja Train4.svg en (400, 400)
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
