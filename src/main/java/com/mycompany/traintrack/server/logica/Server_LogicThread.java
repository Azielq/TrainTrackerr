package com.mycompany.traintrack.server.logica;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server_LogicThread  extends Thread{

    public void run(){
        int running = 0;
        // int running2 = 0;
        int[] listcoordinates = {1, 2, 3};

        while (running <= 5) {
            try {
                Thread.sleep(200);

                //openning the datagram socket and configuring it

                DatagramSocket socket = new DatagramSocket();
                // establishing the comunication networkd
                InetAddress address = InetAddress.getByName("localhost");
                //with datagramPacket [packet] we are gonna establish message or whatever thing that we are gonna send
                DatagramPacket packet;
                //we converte the message in bytes for pass it
                byte[] byteMessage = new byte[200];

                //package - response of the server
                String stringmessage =  "";
                DatagramPacket servPacket;
                byte[] pickupbytes_server;

                String message = "Test for train one";
                int num;
                // for(int numbers: listcoordinates){
                //     num = numbers;
                // }
                // String messagenum = String.valueOf(num);
                do {
                    Thread.sleep(200);
                    //we converted the message to a bytes for the transfer
                    byteMessage = message.getBytes();
                    //we pass the message through the package
                    packet = new DatagramPacket(byteMessage, message.length(), address, 7000);
                    //and then we send that package
                    socket.send(packet);
                    pickupbytes_server = new byte[200];
                    servPacket = new DatagramPacket(pickupbytes_server, 200);
                    socket.receive(servPacket);
                    stringmessage = new String(pickupbytes_server).trim();
                    System.out.println(stringmessage);
                    running++;
                } while (running <= 10);
                socket.close();
            } catch (Exception e) {
                
            }
        }
    }
    
}
