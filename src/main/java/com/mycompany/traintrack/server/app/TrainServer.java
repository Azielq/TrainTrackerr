package com.mycompany.traintrack.server.app;


import com.mycompany.traintrack.server.logica.ServerTrainRunnable;
import com.mycompany.traintrack.server.logica.Station;
import com.mycompany.traintrack.server.logica.Train;
import com.mycompany.traintrack.server.logica.trains.Train1;
import com.mycompany.traintrack.server.logica.trains.Train2;
import com.mycompany.traintrack.server.logica.trains.Train3;
import com.mycompany.traintrack.server.logica.trains.Train4;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.ArrayList;
import java.util.List;


public class TrainServer {

    private DatagramSocket socket;
    private List<ServerTrainRunnable> trainRunnables;

    public TrainServer(int port) throws IOException {
        socket = new DatagramSocket(port);
        trainRunnables = new ArrayList<>();
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

                String response = handleRequest(request, packet.getAddress(), packet.getPort());
                byte[] responseBuffer = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, packet.getAddress(), packet.getPort());
                socket.send(responsePacket);

            } catch (Exception e) {
                System.err.println("Server error: " + e.getMessage());
            }
        }
    }

    private String handleRequest(String request, InetAddress clientAddress, int clientPort) {
        switch (request) {
            case "RESET_TRAINS":
                resetTrains();
                return "TRAINS_RESET";
            case "START_TRAINS":
                startTrains(clientAddress, clientPort);
                return "TRAINS_STARTED";
            case "PAUSE_TRAINS":
                pauseTrains();
                return "TRAINS_PAUSED";
            case "RESUME_TRAINS":
                resumeTrains();
                return "TRAINS_RESUMED";
            default:
                return "UNKNOWN_COMMAND";
        }
    }   

    private void startTrains(InetAddress clientAddress, int clientPort) {
        List<Station> route1 = List.of(Station.Belén, Station.Pedregal, Station.Metrópolis, Station.Demasa, Station.Pecosa, Station.Pavas_Centro, Station.Jacks, Station.AyA, Station.La_Salle, Station.Contraloría, Station.Barrio_Cuba, Station.Estación_Pacífico, Station.Plaza_Víquez, Station.La_Corte, Station.Estación_Atlántico, Station.UCR, Station.U_Latina, Station.Freses, Station.UACA, Station.Tres_Ríos, Station.Cartago, Station.Los_Ángeles, Station.Oreamuno, Station.Paraíso);
        List<Station> route2 = List.of(Station.Paraíso, Station.Oreamuno, Station.Los_Ángeles, Station.Cartago, Station.Tres_Ríos, Station.UACA, Station.Freses, Station.U_Latina, Station.UCR, Station.Estación_Atlántico, Station.La_Corte, Station.Plaza_Víquez, Station.Estación_Pacífico, Station.Barrio_Cuba, Station.Contraloría, Station.La_Salle, Station.AyA, Station.Jacks, Station.Pavas_Centro, Station.Pecosa, Station.Demasa, Station.Metrópolis, Station.Pedregal, Station.Belén);
        List<Station> route3 = List.of(Station.Estación_Atlántico, Station.Calle_Blancos, Station.Colima, Station.Santa_Rosa, Station.Miraflores, Station.Heredia, Station.San_Francisco, Station.San_Joaquín, Station.Río_Segundo, Station.Bulevar_Aeropuerto, Station.Alajuela);
        List<Station> route4 = List.of(Station.Estación_Atlántico, Station.Calle_Blancos, Station.Colima, Station.Santa_Rosa, Station.Miraflores, Station.Heredia, Station.San_Francisco, Station.San_Joaquín, Station.Río_Segundo, Station.Bulevar_Aeropuerto, Station.Alajuela);

        Train train1 = new Train1(Station.Belén, "Train1", 0);
        Train train2 = new Train2(Station.Paraíso, "Train2", 1);
        Train train3 = new Train3(Station.Estación_Atlántico, "Train3", 2);
        Train train4 = new Train4(Station.Estación_Atlántico, "Train4", 3);

        ServerTrainRunnable train1Runnable = new ServerTrainRunnable(train1, route1, Station.Estación_Atlántico, train2, socket, clientAddress, clientPort);
        ServerTrainRunnable train2Runnable = new ServerTrainRunnable(train2, route2, Station.Estación_Atlántico, train1, socket, clientAddress, clientPort);
        ServerTrainRunnable train3Runnable = new ServerTrainRunnable(train3, route3, Station.Heredia, train4, socket, clientAddress, clientPort);
        ServerTrainRunnable train4Runnable = new ServerTrainRunnable(train4, route4, Station.Heredia, train3, socket, clientAddress, clientPort);

        trainRunnables.add(train1Runnable);
        trainRunnables.add(train2Runnable);
        trainRunnables.add(train3Runnable);
        trainRunnables.add(train4Runnable);

        new Thread(train1Runnable).start();
        new Thread(train2Runnable).start();
        new Thread(train3Runnable).start();
        new Thread(train4Runnable).start();
    }

    private void resetTrains() {
        for (ServerTrainRunnable runnable : trainRunnables) {
            runnable.reset();  // Resetea cada tren
        }
    }

    private void pauseTrains() {
        for (ServerTrainRunnable runnable : trainRunnables) {
            runnable.pause();  // Pausa cada tren
        }
    }

    private void resumeTrains() {
        for (ServerTrainRunnable runnable : trainRunnables) {
            runnable.resume();  // Reanuda cada tren
        }
    }

    public static void main(String[] args) {
        try {
            TrainServer server = new TrainServer(9999); // Puerto del servidor
            server.start();
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}