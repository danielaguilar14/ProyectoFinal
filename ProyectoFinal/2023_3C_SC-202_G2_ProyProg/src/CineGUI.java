
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

public class CineGUI {
    private static final int NUM_SALAS = 3;
    private static final int NUM_ASIENTOS_POR_SALA = 25;
    private String[][][] reservas;
    private Empleado[] empleados;

    public CineGUI(Empleado[] empleados) {
        reservas = new String[NUM_SALAS][NUM_ASIENTOS_POR_SALA][2];
        this.empleados = empleados;
    }

    public void mostrarMenu() {
        while (true) {
            String[] opciones = {"Reservar Asiento", "Ver Reservas", "Volver al Menú Principal"};
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú Cine",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0:
                    reservarAsiento();
                    break;
                case 1:
                    verReservas();
                    break;
                case 2:
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione nuevamente");
                    break;
            }
        }
    }

    private void reservarAsiento() {
        int sala = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de sala (1-" + NUM_SALAS + "):")) - 1;

        if (sala < 0 || sala >= NUM_SALAS) {
            JOptionPane.showMessageDialog(null, "Número de sala no válido. Inténtelo nuevamente.");
            return;
        }

        StringBuilder opcionesAsientos = new StringBuilder("Seleccione un asiento para la sala " + (sala + 1) + ":\n");
        for (int i = 0; i < NUM_ASIENTOS_POR_SALA; i++) {
            if (reservas[sala][i][0] == null) {
                opcionesAsientos.append(i + 1).append(". ").append("Asiento ").append(i + 1).append("\n");
            }
        }

        int seleccionAsiento = Integer.parseInt(JOptionPane.showInputDialog(opcionesAsientos.toString())) - 1;

        if (seleccionAsiento < 0 || seleccionAsiento >= NUM_ASIENTOS_POR_SALA || reservas[sala][seleccionAsiento][0] != null) {
            JOptionPane.showMessageDialog(null, "Selección de asiento no válida o no disponible. Inténtelo nuevamente.");
            return;
        }

        int idEmpleado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID de empleado para confirmar la reserva:"));

        if (!validarEmpleado(idEmpleado)) {
            JOptionPane.showMessageDialog(null, "ID de empleado no válido. Inténtelo nuevamente.");
            return;
        }

        reservas[sala][seleccionAsiento][0] = "Reservado";
        reservas[sala][seleccionAsiento][1] = String.valueOf(idEmpleado);
        JOptionPane.showMessageDialog(null, "Reserva exitosa. Asiento " + (seleccionAsiento + 1) + " en la sala " + (sala + 1) + " reservado.");
    }

    private void verReservas() {
        StringBuilder mensaje = new StringBuilder("Reservas del Cine:\n");

        for (int sala = 0; sala < NUM_SALAS; sala++) {
            mensaje.append("Sala ").append(sala + 1).append(":\n");

            for (int asiento = 0; asiento < NUM_ASIENTOS_POR_SALA; asiento++) {
                if (reservas[sala][asiento][0] != null) {
                    mensaje.append("Asiento ").append(asiento + 1).append(": Reservado (ID Empleado: ").append(reservas[sala][asiento][1]).append(")\n");
                } else {
                    mensaje.append("Asiento ").append(asiento + 1).append(": Disponible\n");
                }
            }

            mensaje.append("\n");
        }

        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    private boolean validarEmpleado(int idEmpleado) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == idEmpleado) {
                return true;
            }
        }
        return false;
    }
}
