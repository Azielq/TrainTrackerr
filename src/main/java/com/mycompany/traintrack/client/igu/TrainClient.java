package com.mycompany.traintrack.client.igu;

import java.awt.Color;
import java.util.Arrays;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.mycompany.traintrack.client.igu.trains.Train1;
import com.mycompany.traintrack.client.igu.trains.Train2;
import com.mycompany.traintrack.client.igu.trains.Train3;
import com.mycompany.traintrack.client.igu.trains.Train4;


public class TrainClient extends javax.swing.JFrame {

    private pnlTrainMap trainMap;
    private TrainRunnable train1Runnable;
    private TrainRunnable train2Runnable;
    private TrainRunnable train3Runnable;
    private TrainRunnable train4Runnable;

    public TrainClient() {
        initComponents();
        initStyles();
        initContent();
        this.setTitle("TrainTrack - Cliente");
        svgLogo.setSvgImage("logo.svg", 250, 125);
    }

    public void initStyles() {
        FlatSVGIcon icon = new FlatSVGIcon("1.svg");
        setIconImage(icon.getImage());
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        txaConsole.setEditable(false);
        setResizable(false);
    }

    public void initContent() {
        try {
            trainMap = new pnlTrainMap();
            System.out.println("trainMap ha sido inicializado: " + (trainMap != null)); // Esto debe imprimir 'true'
        } catch (Exception e) {
            System.err.println("Ocurrió un error al inicializar trainMap: " + e.getMessage());
            e.printStackTrace();
        }
    
        if (trainMap != null) {
            trainMap.setSize(950, 730);
            trainMap.setLocation(0, 0);
            pnlTrain.removeAll();
            pnlTrain.add(trainMap);
            pnlTrain.revalidate();
            pnlTrain.repaint();
        }
    }

    private void startTrains() {
        Train train1 = new Train1(trainMap, Station.Belén, "Train1", 0);
        Train train2 = new Train2(trainMap, Station.Paraíso, "Train2", 1);
        Train train3 = new Train3(trainMap, Station.Estación_Atlántico, "Train3", 2);
        Train train4 = new Train4(trainMap, Station.Estación_Atlántico, "Train4", 3);
    
        train1Runnable = new TrainRunnable(train1, Arrays.asList(
            Station.Belén, Station.Pedregal, Station.Metrópolis, Station.Demasa,
            Station.Pecosa, Station.Pavas_Centro, Station.Jacks, Station.AyA,
            Station.La_Salle, Station.Contraloría, Station.Barrio_Cuba,
            Station.Estación_Pacífico, Station.Plaza_Víquez, Station.La_Corte,
            Station.Estación_Atlántico, Station.UCR, Station.U_Latina,
            Station.Freses, Station.UACA, Station.Tres_Ríos,
            Station.Cartago, Station.Los_Ángeles, Station.Oreamuno, Station.Paraíso),
            Station.Estación_Atlántico, train2);
    
        train2Runnable = new TrainRunnable(train2, Arrays.asList(
            Station.Paraíso, Station.Oreamuno, Station.Los_Ángeles,
            Station.Cartago, Station.Tres_Ríos, Station.UACA, Station.Freses,
            Station.U_Latina, Station.UCR, Station.Estación_Atlántico,
            Station.La_Corte, Station.Plaza_Víquez, Station.Estación_Pacífico,
            Station.Barrio_Cuba, Station.Contraloría, Station.La_Salle, Station.AyA,
            Station.Jacks, Station.Pavas_Centro, Station.Pecosa,
            Station.Demasa, Station.Metrópolis, Station.Pedregal, Station.Belén),
            Station.Estación_Atlántico, train1);
    
        train3Runnable = new TrainRunnable(train3, Arrays.asList(
            Station.Estación_Atlántico, Station.Calle_Blancos, Station.Colima,
            Station.Santa_Rosa, Station.Miraflores, Station.Heredia, Station.San_Francisco,
            Station.San_Joaquín, Station.Río_Segundo, Station.Bulevar_Aeropuerto,
            Station.Alajuela), Station.Heredia, train4);
    
        train4Runnable = new TrainRunnable(train4, Arrays.asList(
            Station.Estación_Atlántico, Station.Calle_Blancos, Station.Colima,
            Station.Santa_Rosa, Station.Miraflores, Station.Heredia, Station.San_Francisco,
            Station.San_Joaquín, Station.Río_Segundo, Station.Bulevar_Aeropuerto,
            Station.Alajuela), Station.Heredia, train3);
    

    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBg = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        btnTrain1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaConsole = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnPause = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnTrain2 = new javax.swing.JButton();
        btnTrain3 = new javax.swing.JButton();
        btnTrain4 = new javax.swing.JButton();
        svgLogo = new com.mycompany.sneaksapp.igu.SVGImage();
        lblReloj = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnReset = new javax.swing.JButton();
        pnlTrain = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBg.setBackground(new java.awt.Color(255, 255, 255));

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));

        btnTrain1.setBackground(new java.awt.Color(255, 255, 255));
        btnTrain1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnTrain1.setForeground(new java.awt.Color(255, 81, 38));
        btnTrain1.setText("Train 1");
        btnTrain1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 81, 38)));
        btnTrain1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrain1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrain1ActionPerformed(evt);
            }
        });

        txaConsole.setColumns(20);
        txaConsole.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txaConsole.setForeground(new java.awt.Color(181, 181, 181));
        txaConsole.setRows(5);
        txaConsole.setText("Waiting Server...");
        txaConsole.setSelectionColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(txaConsole);

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Select a train to monitor");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        btnPause.setBackground(new java.awt.Color(255, 255, 255));
        btnPause.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnPause.setForeground(new java.awt.Color(255, 30, 38));
        btnPause.setText("Pause");
        btnPause.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 30, 38)));
        btnPause.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Train Status");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Select an option");

        btnStart.setBackground(new java.awt.Color(255, 255, 255));
        btnStart.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnStart.setForeground(new java.awt.Color(48, 186, 48));
        btnStart.setText("Start");
        btnStart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(48, 186, 48)));
        btnStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnTrain2.setBackground(new java.awt.Color(255, 255, 255));
        btnTrain2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnTrain2.setForeground(new java.awt.Color(255, 81, 38));
        btnTrain2.setText("Train 2");
        btnTrain2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 81, 38)));
        btnTrain2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrain2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrain2ActionPerformed(evt);
            }
        });

        btnTrain3.setBackground(new java.awt.Color(255, 255, 255));
        btnTrain3.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnTrain3.setForeground(new java.awt.Color(255, 81, 38));
        btnTrain3.setText("Train 3");
        btnTrain3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 81, 38)));
        btnTrain3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrain3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrain3ActionPerformed(evt);
            }
        });

        btnTrain4.setBackground(new java.awt.Color(255, 255, 255));
        btnTrain4.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnTrain4.setForeground(new java.awt.Color(255, 81, 38));
        btnTrain4.setText("Train 4");
        btnTrain4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 81, 38)));
        btnTrain4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrain4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrain4ActionPerformed(evt);
            }
        });

        svgLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblReloj.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblReloj.setForeground(new java.awt.Color(153, 153, 153));
        lblReloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReloj.setText("Waiting Server...");

        btnReset.setBackground(new java.awt.Color(255, 255, 255));
        btnReset.setFont(new java.awt.Font("Segoe UI Light", 0, 10)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 30, 38));
        btnReset.setText("Reset");
        btnReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 30, 38)));
        btnReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMenuLayout.createSequentialGroup()
                                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pnlMenuLayout.createSequentialGroup()
                                    .addComponent(btnTrain3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTrain4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlMenuLayout.createSequentialGroup()
                                    .addComponent(btnTrain1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(74, 74, 74)
                                    .addComponent(btnTrain2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                        .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                        .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))))
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(svgLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrain1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrain2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrain3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrain4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pnlTrain.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout pnlTrainLayout = new javax.swing.GroupLayout(pnlTrain);
        pnlTrain.setLayout(pnlTrainLayout);
        pnlTrainLayout.setHorizontalGroup(
            pnlTrainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        pnlTrainLayout.setVerticalGroup(
            pnlTrainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlBgLayout = new javax.swing.GroupLayout(pnlBg);
        pnlBg.setLayout(pnlBgLayout);
        pnlBgLayout.setHorizontalGroup(
            pnlBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBgLayout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTrain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlBgLayout.setVerticalGroup(
            pnlBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlTrain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlBg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrain1ActionPerformed
//        Train1Running = true;
//        Train2Running = false;
//        Train3Running = false;
//        Train4Running = false;

        btnTrain1.setBackground(Color.decode("#FF5126"));
        btnTrain2.setBackground(Color.decode("#ffffff"));
        btnTrain3.setBackground(Color.decode("#ffffff"));
        btnTrain4.setBackground(Color.decode("#ffffff"));

        btnTrain1.setForeground(Color.decode("#ffffff"));
        btnTrain2.setForeground(Color.decode("#FF5126"));
        btnTrain3.setForeground(Color.decode("#FF5126"));
        btnTrain4.setForeground(Color.decode("#FF5126"));
    }//GEN-LAST:event_btnTrain1ActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        ClockManager.stopClock();
        if (train1Runnable != null) train1Runnable.pause();
        if (train2Runnable != null) train2Runnable.pause();
        if (train3Runnable != null) train3Runnable.pause();
        if (train4Runnable != null) train4Runnable.pause();

        btnPause.setBackground(Color.decode("#FF1E26"));
        btnStart.setBackground(Color.decode("#FFFFFF"));
        btnPause.setForeground(Color.decode("#ffffff"));
        btnStart.setForeground(Color.decode("#30BA30"));
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
    // Initialize and start the shared clock
    
    
        // Asegura de que trainMap esté inicializado antes de iniciar los trenes
    if (trainMap != null) {
        ClockManager.initialize(lblReloj);
        ClockManager.startClock();
        if (train1Runnable == null || train2Runnable == null || train3Runnable == null || train4Runnable == null) {
            startTrains();
            new Thread(train1Runnable).start();
            new Thread(train2Runnable).start();
            new Thread(train3Runnable).start();
            new Thread(train4Runnable).start();
        } else {
            System.out.println("intentando Reanudar");
            train1Runnable.resume();
            train2Runnable.resume();
            train3Runnable.resume();
            train4Runnable.resume();
        }
    } else {
        System.err.println("trainMap es nulo, no se pueden iniciar los trenes.");
    }

        btnPause.setBackground(Color.decode("#FFFFFF"));
        btnStart.setBackground(Color.decode("#30BA30"));
        btnPause.setForeground(Color.decode("#FF1E26"));
        btnStart.setForeground(Color.decode("#ffffff"));
        
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnTrain2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrain2ActionPerformed

        btnTrain1.setBackground(Color.decode("#ffffff"));
        btnTrain2.setBackground(Color.decode("#FF5126"));
        btnTrain3.setBackground(Color.decode("#ffffff"));
        btnTrain4.setBackground(Color.decode("#ffffff"));

        btnTrain1.setForeground(Color.decode("#FF5126"));
        btnTrain2.setForeground(Color.decode("#ffffff"));
        btnTrain3.setForeground(Color.decode("#FF5126"));
        btnTrain4.setForeground(Color.decode("#FF5126"));
    }//GEN-LAST:event_btnTrain2ActionPerformed

    private void btnTrain3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrain3ActionPerformed

        btnTrain1.setBackground(Color.decode("#ffffff"));
        btnTrain2.setBackground(Color.decode("#ffffff"));
        btnTrain3.setBackground(Color.decode("#FF5126"));
        btnTrain4.setBackground(Color.decode("#ffffff"));

        btnTrain1.setForeground(Color.decode("#FF5126"));
        btnTrain2.setForeground(Color.decode("#FF5126"));
        btnTrain3.setForeground(Color.decode("#ffffff"));
        btnTrain4.setForeground(Color.decode("#FF5126"));
    }//GEN-LAST:event_btnTrain3ActionPerformed

    private void btnTrain4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrain4ActionPerformed
        

        btnTrain1.setBackground(Color.decode("#ffffff"));
        btnTrain2.setBackground(Color.decode("#ffffff"));
        btnTrain3.setBackground(Color.decode("#ffffff"));
        btnTrain4.setBackground(Color.decode("#FF5126"));

        btnTrain1.setForeground(Color.decode("#FF5126"));
        btnTrain2.setForeground(Color.decode("#FF5126"));
        btnTrain3.setForeground(Color.decode("#FF5126"));
        btnTrain4.setForeground(Color.decode("#ffffff"));
    }//GEN-LAST:event_btnTrain4ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        ClockManager.resetClock();
        if (train1Runnable != null) train1Runnable.pause();
        if (train2Runnable != null) train2Runnable.pause();
        if (train3Runnable != null) train3Runnable.pause();
        if (train4Runnable != null) train4Runnable.pause();
        
        if (train1Runnable != null) train1Runnable.reset();
        if (train2Runnable != null) train2Runnable.reset();
        if (train3Runnable != null) train3Runnable.reset();
        if (train4Runnable != null) train4Runnable.reset();

        // Resetear las variables de control
        train1Runnable = null;
        train2Runnable = null;
        train3Runnable = null;
        train4Runnable = null;
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnTrain1;
    private javax.swing.JButton btnTrain2;
    private javax.swing.JButton btnTrain3;
    private javax.swing.JButton btnTrain4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlTrain;
    private com.mycompany.sneaksapp.igu.SVGImage svgLogo;
    private javax.swing.JTextArea txaConsole;
    // End of variables declaration//GEN-END:variables
}
