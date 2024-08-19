package com.mycompany.traintrack.client.igu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;

public class ClockManager {
    private static Calendar calendar;
    private static SimpleDateFormat timeFormat;
    private static JLabel clockLabel;
    private static int accelerationFactor = 80; // Ajusta este valor para acelerar el reloj (1 segundo real = 1 minuto simulación)
    private static boolean running = false; // Variable para controlar el ciclo del reloj
    private static Thread clockThread; // Referencia al hilo del reloj

    static {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 5);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        timeFormat = new SimpleDateFormat("HH:mm"); // Solo muestra horas y minutos
    }

    public static void initialize(JLabel lblReloj) {
        clockLabel = lblReloj;
    }

    public static void startClock() {
        stopClock(); // Se detiene cualquier ciclo anterior para asegurarte de que no haya duplicación
        running = true; // Se asegura de que el reloj esté en estado de ejecución
        clockThread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(1000 / accelerationFactor); // Avanza más rápido en función del accelerationFactor

                    synchronized (ClockManager.class) {
                        // Avanza un minuto en la simulación cada iteración
                        calendar.add(Calendar.MINUTE, 1);
                        updateClockLabel();
                    }

                    // Comprueba si el reloj ha llegado a las 00:00
                    if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0) {
                        running = false; // Detiene el ciclo
                        System.out.println("El reloj ha llegado a las 00:00. Se detiene la simulación.");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        clockThread.start(); // Inicia el hilo del reloj
    }

    public static String getCurrentTime() {
        return timeFormat.format(calendar.getTime());
    }

    private static void updateClockLabel() {
        if (clockLabel != null) {
            clockLabel.setText(getCurrentTime());
        }
    }

    // Método para ajustar la velocidad del reloj
    public static void setAccelerationFactor(int factor) {
        accelerationFactor = factor;
    }

    // Método para detener manualmente el reloj
    public static void stopClock() {
        running = false;
        if (clockThread != null) {
            try {
                clockThread.join(); // Espera a que el hilo termine completamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Método para resetear el reloj a las 05:00
    public static void resetClock() {
        stopClock(); // Asegúrate de que el reloj se detenga antes de resetear
        synchronized (ClockManager.class) { // Sincroniza para asegurarte de que no se ejecute otro hilo mientras se resetea
            calendar.set(Calendar.HOUR_OF_DAY, 5);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            updateClockLabel(); // Actualiza la etiqueta del reloj con la hora reseteada
        }
    }
}