package com.mycompany.traintrack.server.app;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class TrainServer {

    private DatagramSocket socket;
    private InetAddress clientAddress;
    private int clientPort;

    public TrainServer(int port) throws IOException {
        socket = new DatagramSocket(port);
        System.out.println("Servidor iniciado en el puerto " + port);
    }

    public void start() {
        while (true) {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String request = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received request: " + request);

                String response = handleRequest(request);
                byte[] responseBuffer = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);

            } catch (Exception e) {
                System.err.println("Server error: " + e.getMessage());
            }
        }
    }

    private String handleRequest(String request) {
        // Handle different types of requests
        switch (request) {
            case "START_TRAINS":
                return "Trains started";
            case "RESET_TRAINS":
                return "Trains reset";
            default:
                return "Unknown command";
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            TrainServer server = new TrainServer(9999); // Replace with actual port
            server.start();
        } catch (SocketException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}