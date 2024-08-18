package com.mycompany.traintrack.server.controllers;

import java.awt.Dimension;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mycompany.traintrack.server.logica.Server_LogicThread;

public class ServerController{
    // ------Still in process
    private JButton button, button2,  button3;
    private JPanel panel;
    private JFrame frame_window;


    public ServerController(){
        // frame_window = new JFrame();
        // frame_window.setSize(300, 200);
        // frame_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // panel = new JPanel();
        // panel.setPreferredSize(new Dimension(200, 50));
        
        // button = new JButton("Test");
        // button.setBounds(
        //     10,
        //     20, 
        //     100, 
        //     200
        // );  
        // panel.add(button);

        // frame_window.add(panel);
        // frame_window.setVisible(true);

        System.out.println("Server ready: ");
        Scanner read = new Scanner(
            System.in
        );
        String userRead = read.nextLine();

        while (true) {
            
            if(userRead == "run"){
                new Thread(()->{
                    try {
                        DatagramSocket socket = new DatagramSocket(7000);
                        byte[] byte_message = new byte[200];
                        String message = new String(byte_message);
                        String comMessage = "";
                        DatagramPacket pack = new DatagramPacket(byte_message, 200);
                        DatagramPacket envpack = new DatagramPacket(byte_message, 200);
    
                        int port;
                        InetAddress addres;
                        byte[] byte_message2 = new byte[200];
                        do {
                            Thread.sleep(500);
                            socket.receive(pack);
                            message = new String();
    
                            System.out.println(message);
                            port  = pack.getPort();
                            addres = pack.getAddress();
                            comMessage = "";
                            if (message.startsWith("run")) {
                                Server_LogicThread server_LogicThread = new Server_LogicThread();
                                server_LogicThread.start();
    
                                System.out.println("its working");
                                try {
                                    Thread.sleep(200);
                                } catch (Exception e) {
                                    // TODO: handle exception
                                }
                            }
                            byte_message2  = comMessage.getBytes();
    
                            envpack = new DatagramPacket(byte_message2, comMessage.length(), addres, port);
                            socket.send(envpack);
                        } while (true);
    
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }).start();
            }
        }
        
    }

    public static void main(String[] args) {
        new ServerController();
    }

}
