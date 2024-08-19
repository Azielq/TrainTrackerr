package com.mycompany.traintrack.client.app;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Connection {
    
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;

    public Connection(String serverIP, int serverPort) throws IOException {
        this.serverAddress = InetAddress.getByName(serverIP);
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
    }

    // Method to send a request to the server
    public void sendRequest(String message) throws IOException {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
        socket.send(packet);
    }

    // Method to receive a response from the server
    public String receiveResponse() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    // Method to close the connection
    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}