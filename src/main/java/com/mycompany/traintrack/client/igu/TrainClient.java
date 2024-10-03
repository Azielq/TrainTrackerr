    package com.mycompany.traintrack.client.igu;

    import java.awt.Color;
import java.io.IOException;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.mycompany.traintrack.client.app.Connection;
import com.mycompany.traintrack.server.logica.ClockManager;


    public class TrainClient extends javax.swing.JFrame {

        private pnlTrainMap trainMap;
        private int monitoredTrainIndex = -1; // Initially, no train is monitored
        private Connection connection;
        private boolean trainsRunning = false;

        public TrainClient() {
            initComponents();
            initStyles();
            initContent();
            this.setTitle("TrainTrack - Cliente");
            svgLogo.setSvgImage("logo.svg", 250, 125);

            try {
                connection = new Connection("127.0.0.1", 9999); // Conectarse al servidor
                listenToServer(); // Iniciar la escucha del servidor
            } catch (IOException e) {
                System.err.println("Failed to connect to server: " + e.getMessage());
            }
        }

        public void initStyles() {
            FlatSVGIcon icon = new FlatSVGIcon("1.svg");
            setIconImage(icon.getImage());
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            txaConsole.setEditable(false);
            setResizable(false);
            checkButtonStates();
        }

        public void initContent() {
            try {
                
                trainMap = new pnlTrainMap();
                System.out.println("trainMap ha sido inicializado: " + (trainMap != null)); // Esto debe imprimir 'true'

                // Pasa la instancia de trainMap al servidor
            
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

        private void listenToServer() {
            new Thread(() -> {
                while (true) {
                    try {
                        String serverMessage = connection.receiveResponse();
                        handleServerMessage(serverMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();
        }
        
        

        private void handleServerMessage(String message) {
            System.out.println("Received message from server: " + message);
            String[] parts = message.split(" ");
            String command = parts[0];
            String currentTime = ClockManager.getCurrentTime(); // Obtener la hora actual
        
            if (command.equals("POSITION_UPDATE")) {
                int trainIndex = Integer.parseInt(parts[1]);
                int x = Integer.parseInt(parts[2]);
                int y = Integer.parseInt(parts[3]);
        
                SwingUtilities.invokeLater(() -> {
                    if (trainMap != null) {
                        trainMap.updateTrainPosition(trainIndex, x, y); // Actualizar la posición en el panel
                    }
                });
            } else if (command.equals("STATION_UPDATE")) {
                int trainIndex = Integer.parseInt(parts[1]);
                String stationName = parts[2];
        
                // Concatenar la hora actual al mensaje antes de mostrarlo en la consola
                String consoleMessage = currentTime + " - Train " + (trainIndex + 1) + " is at " + stationName + "\n";
        
                // Solo mostrar la actualización si el tren monitorizado es el seleccionado por el usuario
                if (trainIndex == monitoredTrainIndex) {
                    SwingUtilities.invokeLater(() -> txaConsole.append(consoleMessage));
                }
            }
        }

       // private void startTrains() {
        
            
//            Train train1 = new Train1(trainMap, Station.Belén, "Train1", 0);
//            Train train2 = new Train2(trainMap, Station.Paraíso, "Train2", 1);
//            Train train3 = new Train3(trainMap, Station.Estación_Atlántico, "Train3", 2);
//            Train train4 = new Train4(trainMap, Station.Estación_Atlántico, "Train4", 3);
//        
//            train1Runnable = new TrainRunnable(train1, Arrays.asList(
//                Station.Belén, Station.Pedregal, Station.Metrópolis, Station.Demasa,
//                Station.Pecosa, Station.Pavas_Centro, Station.Jacks, Station.AyA,
//                Station.La_Salle, Station.Contraloría, Station.Barrio_Cuba,
//                Station.Estación_Pacífico, Station.Plaza_Víquez, Station.La_Corte,
//                Station.Estación_Atlántico, Station.UCR, Station.U_Latina,
//                Station.Freses, Station.UACA, Station.Tres_Ríos,
//                Station.Cartago, Station.Los_Ángeles, Station.Oreamuno, Station.Paraíso),
//                Station.Estación_Atlántico, train2);
//        
//            train2Runnable = new TrainRunnable(train2, Arrays.asList(
//                Station.Paraíso, Station.Oreamuno, Station.Los_Ángeles,
//                Station.Cartago, Station.Tres_Ríos, Station.UACA, Station.Freses,
//                Station.U_Latina, Station.UCR, Station.Estación_Atlántico,
//                Station.La_Corte, Station.Plaza_Víquez, Station.Estación_Pacífico,
//                Station.Barrio_Cuba, Station.Contraloría, Station.La_Salle, Station.AyA,
//                Station.Jacks, Station.Pavas_Centro, Station.Pecosa,
//                Station.Demasa, Station.Metrópolis, Station.Pedregal, Station.Belén),
//                Station.Estación_Atlántico, train1);
//        
//            train3Runnable = new TrainRunnable(train3, Arrays.asList(
//                Station.Estación_Atlántico, Station.Calle_Blancos, Station.Colima,
//                Station.Santa_Rosa, Station.Miraflores, Station.Heredia, Station.San_Francisco,
//                Station.San_Joaquín, Station.Río_Segundo, Station.Bulevar_Aeropuerto,
//                Station.Alajuela), Station.Heredia, train4);
//        
//            train4Runnable = new TrainRunnable(train4, Arrays.asList(
//                Station.Estación_Atlántico, Station.Calle_Blancos, Station.Colima,
//                Station.Santa_Rosa, Station.Miraflores, Station.Heredia, Station.San_Francisco,
//                Station.San_Joaquín, Station.Río_Segundo, Station.Bulevar_Aeropuerto,
//                Station.Alajuela), Station.Heredia, train3);
//        

        //}



        

    

        
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
        svgLogo = new com.mycompany.traintrack.client.igu.SVGImage();
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
        btnTrain1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btnPause.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btnStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btnTrain2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btnTrain3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btnTrain4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
            monitoredTrainIndex = 0;
            txaConsole.setText(""); // Limpiar la consola
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
            pauseTrains();
            ClockManager.stopClock();
        }//GEN-LAST:event_btnPauseActionPerformed

        private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
            if (!trainsRunning) { // Si los trenes no están corriendo, empieza
                startTrains();
            } else { // Si los trenes están pausados, reanuda
                resumeTrains();
            }
        }//GEN-LAST:event_btnStartActionPerformed

        private void btnTrain2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrain2ActionPerformed
            txaConsole.setText(""); // Limpiar la consola
            monitoredTrainIndex = 1;
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
            txaConsole.setText(""); // Limpiar la consola
            monitoredTrainIndex = 2;
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
            txaConsole.setText(""); // Limpiar la consola
            monitoredTrainIndex = 3;
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
            resetTrains();
            txaConsole.setText(""); // Limpiar la consola
        }//GEN-LAST:event_btnResetActionPerformed

        private void checkButtonStates() {
            if (!btnStart.getBackground().equals(Color.decode("#30BA30"))) {
                btnPause.setEnabled(false);  // Deshabilita el botón Pause si Start no es verde
            }
            if (!btnStart.getBackground().equals(Color.decode("#30BA30")) && !btnPause.getBackground().equals(Color.decode("#FF1E26"))) {
                btnReset.setEnabled(false);  // Deshabilita el botón Reset si no cumple ninguna condición
                enableTrainButtons(false);   // Deshabilitar los botones de tren
            }
        }
        
        private void updateButtonStates() {
            // Actualizar los estados de los botones en el EDT
            btnStart.setBackground(Color.decode("#FFFFFF"));
            btnStart.setForeground(Color.decode("#30BA30"));
            btnPause.setBackground(Color.decode("#FFFFFF"));
            btnPause.setForeground(Color.decode("#FF1E26"));
        
            btnPause.setEnabled(false);  // El botón Pause se deshabilita
            btnReset.setEnabled(false);  // El botón Reset se deshabilita
            enableTrainButtons(false); 
        }

        private void enableTrainButtons(boolean enabled) {
            btnTrain1.setEnabled(enabled);
            btnTrain2.setEnabled(enabled);
            btnTrain3.setEnabled(enabled);
            btnTrain4.setEnabled(enabled);
        }
        
        @Override
        public void dispose() {
            // Ensure the connection is closed when the application exits
            if (connection != null) {
                connection.close();
            }
            super.dispose();
        }       

        private void startTrains() {
            try {
                connection.sendRequest("START_TRAINS");
                ClockManager.initialize(lblReloj);
                ClockManager.startClock();
                
                SwingUtilities.invokeLater(() -> {
                    btnStart.setBackground(Color.decode("#30BA30"));
                    btnPause.setBackground(Color.decode("#FFFFFF"));
                    btnStart.setForeground(Color.decode("#FFFFFF"));
                    btnPause.setForeground(Color.decode("#FF1E26"));
                    btnPause.setEnabled(true);
                    btnReset.setEnabled(true);
                    enableTrainButtons(true);
                });
                trainsRunning = true; 
            } catch (IOException e) {
                System.err.println("Error de comunicación: " + e.getMessage());
            }
        }
        
        private void resumeTrains() {
            try {
                connection.sendRequest("RESUME_TRAINS");
                SwingUtilities.invokeLater(() -> {
                    btnStart.setBackground(Color.decode("#30BA30"));
                    btnPause.setBackground(Color.decode("#FFFFFF"));
                    btnStart.setForeground(Color.decode("#FFFFFF"));
                    btnPause.setForeground(Color.decode("#FF1E26"));
                    btnPause.setEnabled(true);
                    btnReset.setEnabled(true);
                    enableTrainButtons(true);
                });
                trainsRunning = true;
            } catch (IOException e) {
                System.err.println("Error de comunicación: " + e.getMessage());
            }
        }
        
        private void pauseTrains() {
            try {
                
                connection.sendRequest("PAUSE_TRAINS");
                SwingUtilities.invokeLater(() -> {
                    btnStart.setBackground(Color.decode("#FFFFFF"));
                    btnPause.setBackground(Color.decode("#FF1E26"));
                    btnStart.setForeground(Color.decode("#30BA30"));
                    btnPause.setForeground(Color.decode("#FFFFFF"));
                    btnReset.setEnabled(true);
                    enableTrainButtons(false);
                });
                trainsRunning = false; 
            } catch (IOException e) {
                System.err.println("Error de comunicación: " + e.getMessage());
            }
        }
        
        private void resetTrains() {
            try {
                connection.sendRequest("RESET_TRAINS");
                SwingUtilities.invokeLater(() -> {
                    
                    updateButtonStates(); // Actualizar los estados de los botones
                    txaConsole.setText(""); // Limpiar la consola
                });
                trainsRunning = false; 
            } catch (IOException e) {
                System.err.println("Error de comunicación: " + e.getMessage());
            }
        }

        
    


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
    private com.mycompany.traintrack.client.igu.SVGImage svgLogo;
    private javax.swing.JTextArea txaConsole;
    // End of variables declaration//GEN-END:variables
    }
