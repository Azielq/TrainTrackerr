package com.mycompany.traintrack.client.igu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.Timer;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.mycompany.traintrack.server.logica.ServerThread;


public class TrainClient extends javax.swing.JFrame {

//    private boolean Train1Running = false;
//    private boolean Train2Running = false;
//    private boolean Train3Running = false;
//    private boolean Train4Running = false;
//
//    private boolean Start = false;
//    private boolean Stop = false;
    
    private Timer relojTimer;
    private Calendar calendario;
    

    public TrainClient() {
        initComponents();
        initStyles();
        initContent();
        this.setTitle("TrainTrack - Cliente");
        // svgLogo.setSvgImage("logo.svg", 250, 125);
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
        pnlTrainMap mapPanel = new pnlTrainMap();
        mapPanel.setSize(950, 730);
        mapPanel.setLocation(0, 0);

        pnlTrain.removeAll(); // Remueve cualquier contenido existente en el panel
        pnlTrain.add(mapPanel); // Añade el nuevo panel
        pnlTrain.revalidate(); // Reorganiza el layout
        pnlTrain.repaint(); // Redibuja el panel para que el cambio sea visible
        
    }


    private void startClock() {

        // Configuramos el Timer para que actualice el JLabel cada segundo (equivalente a 1 minuto en el reloj)
        relojTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Incrementamos el calendario en 1 minuto
                calendario.add(Calendar.MINUTE, 1);
                // Actualizamos el JLabel con el nuevo tiempo
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
                lblReloj.setText(formatoHora.format(calendario.getTime()));
            }
        });
    }

    private void resetClock() {
        // Inicializamos el calendario con las 5:00 AM
        calendario = Calendar.getInstance();
        calendario.set(Calendar.HOUR_OF_DAY, 5);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);
        
        // Iniciamos o reiniciamos el Timer
        if (relojTimer != null) {
            relojTimer.stop();
        }
        startClock();

        relojTimer.start();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBg = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        btnTrain1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaConsole = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnStop = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnTrain2 = new javax.swing.JButton();
        btnTrain3 = new javax.swing.JButton();
        btnTrain4 = new javax.swing.JButton();
        // svgLogo = new com.mycompany.sneaksapp.igu.SVGImage();
        lblReloj = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
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
                System.out.println("test");
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

        btnStop.setBackground(new java.awt.Color(255, 255, 255));
        btnStop.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnStop.setForeground(new java.awt.Color(255, 30, 38));
        btnStop.setText("Stop");
        btnStop.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 30, 38)));
        btnStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
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

        // svgLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblReloj.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblReloj.setForeground(new java.awt.Color(153, 153, 153));
        lblReloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReloj.setText("Waiting Server...");

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
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
                                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pnlMenuLayout.createSequentialGroup()
                                    .addComponent(btnTrain1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(74, 74, 74)
                                    .addComponent(btnTrain2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlMenuLayout.createSequentialGroup()
                                    .addComponent(btnTrain3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTrain4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                        //  .addComponent(svgLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                        .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))))
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                // .addComponent(svgLog, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
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


   
   






    // -----------------handling button events-------------------------->






    
    


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

        System.out.println("the button 1 its working ");
        // calling the function to receive the data through the button
        return_server_data();

    }//GEN-LAST:event_btnTrain1ActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // Detener el reloj cuando se presiona el botón "Stop"
        if (relojTimer != null) {
            relojTimer.stop();
        }
        
        btnStop.setBackground(Color.decode("#FF1E26"));
        btnStart.setBackground(Color.decode("#FFFFFF"));
        btnStop.setForeground(Color.decode("#ffffff"));
        btnStart.setForeground(Color.decode("#30BA30"));
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        resetClock();


        btnStop.setBackground(Color.decode("#FFFFFF"));
        btnStart.setBackground(Color.decode("#30BA30"));
        btnStop.setForeground(Color.decode("#FF1E26"));
        btnStart.setForeground(Color.decode("#ffffff"));
        
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnTrain2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrain2ActionPerformed
//        Train1Running = false;
//        Train2Running = true;
//        Train3Running = false;
//        Train4Running = false;

        btnTrain1.setBackground(Color.decode("#ffffff"));
        btnTrain2.setBackground(Color.decode("#FF5126"));
        btnTrain3.setBackground(Color.decode("#ffffff"));
        btnTrain4.setBackground(Color.decode("#ffffff"));

        btnTrain1.setForeground(Color.decode("#FF5126"));
        btnTrain2.setForeground(Color.decode("#ffffff"));
        btnTrain3.setForeground(Color.decode("#FF5126"));
        btnTrain4.setForeground(Color.decode("#FF5126"));

        System.out.println("the button 3 its working ");
    }//GEN-LAST:event_btnTrain2ActionPerformed

    private void btnTrain3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrain3ActionPerformed
//        Train1Running = false;
//        Train2Running = false;
//        Train3Running = true;
//        Train4Running = false;

        btnTrain1.setBackground(Color.decode("#ffffff"));
        btnTrain2.setBackground(Color.decode("#ffffff"));
        btnTrain3.setBackground(Color.decode("#FF5126"));
        btnTrain4.setBackground(Color.decode("#ffffff"));

        btnTrain1.setForeground(Color.decode("#FF5126"));
        btnTrain2.setForeground(Color.decode("#FF5126"));
        btnTrain3.setForeground(Color.decode("#ffffff"));
        btnTrain4.setForeground(Color.decode("#FF5126"));
        
        txaConsole.setText("");
        txaConsole.append("test");
    }//GEN-LAST:event_btnTrain3ActionPerformed

    private void btnTrain4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrain4ActionPerformed
//        Train1Running = false;
//        Train2Running = false;
//        Train3Running = false;
//        Train4Running = true;

        btnTrain1.setBackground(Color.decode("#ffffff"));
        btnTrain2.setBackground(Color.decode("#ffffff"));
        btnTrain3.setBackground(Color.decode("#ffffff"));
        btnTrain4.setBackground(Color.decode("#FF5126"));

        btnTrain1.setForeground(Color.decode("#FF5126"));
        btnTrain2.setForeground(Color.decode("#FF5126"));
        btnTrain3.setForeground(Color.decode("#FF5126"));
        btnTrain4.setForeground(Color.decode("#ffffff"));
    }//GEN-LAST:event_btnTrain4ActionPerformed








 // -----------------end handling button events-------------------------->




 // -----------------Receiving data from the server-------------------------->


    private void return_server_data(){
        ServerThread serverThread = new ServerThread();
        serverThread.start();

        new Thread(()->{
            try {
                // Creating the socket
                DatagramSocket socket = new DatagramSocket(7000);
                byte[] byteMessage = new byte[200];
                String message = new String(byteMessage);
                String comMessage = "";

                DatagramPacket pack = new DatagramPacket(byteMessage, 200);
                DatagramPacket envPacket = new DatagramPacket(byteMessage, 200);
                int port;
                InetAddress address;
                byte[] byteMessage2 = new byte[200];

        
        //-----------> Initializing the cicle who its going to stop the thread who sending the  message        
        
                int running = 0;

                do {
                    Thread.sleep(500);
                    socket.receive(pack);
                    message = new String(byteMessage);
                    txaConsole.append("\n" + message);

                    //checking out if the message comes right
                    System.out.println(message);    

                    port = pack.getPort();
                    address = pack.getAddress();
                    comMessage = "";
                    byteMessage2 = comMessage.getBytes();
                    envPacket = new DatagramPacket(byteMessage2, comMessage.length(), address, port);
                    socket.send(envPacket);
                    running++;
                } while (running <= 10);

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            
        }).start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
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
    // private com.mycompany.sneaksapp.igu.SVGImage svgLogo;
    private javax.swing.JTextArea txaConsole;
    // End of variables declaration//GEN-END:variables
}
