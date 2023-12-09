
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mtorr
 */
import javax.swing.*;

public class Yoga {
    private static final int NUM_CLASES = 2;
    private static final int LIMITE_ESPACIOS = 30;
    private String[][] reservas; // Matriz para almacenar las reservas de clases [Clase][Espacio]

    public Yoga() {
        reservas = new String[NUM_CLASES][LIMITE_ESPACIOS]; // [Clase][Espacio]
    }

    public void mostrarMenu() {
        while (true) {
            String[] opciones = {"Reservar Clase", "Ver Reservas", "Volver al Menú Principal"};
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú Yoga",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0:
                    // Reservar clase
                    reservarClase();
                    break;
                case 1:
                    // Ver reservas
                    verReservas();
                    break;
                case 2:
                    // Volver al menú principal
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione nuevamente.");
            }
        }
    }

    private void reservarClase() {
        int clase = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la clase (1. Yoga / 2. Baile):")) - 1;

        if (clase < 0 || clase >= NUM_CLASES) {
            JOptionPane.showMessageDialog(null, "Número de clase no válido. Inténtelo nuevamente.");
            return;
        }

        // Mostrar espacios disponibles
        StringBuilder opcionesEspacios = new StringBuilder("Seleccione un espacio para la clase " + (clase + 1) + ":\n");
        for (int i = 0; i < LIMITE_ESPACIOS; i++) {
            if (reservas[clase][i] == null) {
                opcionesEspacios.append(i + 1).append(". ").append("Espacio ").append(i + 1).append("\n");
            }
        }

        int seleccionEspacio = Integer.parseInt(JOptionPane.showInputDialog(opcionesEspacios.toString())) - 1;

        if (seleccionEspacio < 0 || seleccionEspacio >= LIMITE_ESPACIOS || reservas[clase][seleccionEspacio] != null) {
            JOptionPane.showMessageDialog(null, "Selección de espacio no válida o no disponible. Inténtelo nuevamente.");
            return;
        }

        // Reservar espacio
        reservas[clase][seleccionEspacio] = "Reservado";
        JOptionPane.showMessageDialog(null, "Reserva exitosa. Clase " + (clase + 1)
                + " en el Espacio " + (seleccionEspacio + 1) + " reservada.");
    }

    private void verReservas() {
        StringBuilder mensaje = new StringBuilder("Reservas de Yoga y Baile:\n");

        for (int clase = 0; clase < NUM_CLASES; clase++) {
            mensaje.append("Clase ").append(clase + 1).append(":\n");

            for (int espacio = 0; espacio < LIMITE_ESPACIOS; espacio++) {
                if (reservas[clase][espacio] != null) {
                    mensaje.append("Espacio ").append(espacio + 1).append(": Reservado\n");
                } else {
                    mensaje.append("Espacio ").append(espacio + 1).append(": Disponible\n");
                }
            }

            mensaje.append("\n");
        }

        JOptionPane.showMessageDialog(null, mensaje.toString());
    }
}
