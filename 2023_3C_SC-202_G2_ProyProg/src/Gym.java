
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mtorr
 */
public class Gym {
    
private static final int NUM_ENTRENADORES = 3;
    private static final int NUM_HORARIOS = 3;
    private static final int NUM_PERSONAS = 5;
    private String[][][] reservas; // Matriz para almacenar las reservas de los entrenadores
    private Empleado[] empleados;

    public Gym(Empleado[] empleados) {
        reservas = new String[NUM_ENTRENADORES][NUM_HORARIOS][NUM_PERSONAS]; // [Entrenador][Horario][ID de Empleado]
        this.empleados = empleados;
    }

    public void mostrarMenu() {
        while (true) {
            String[] opciones = {"Reservar Entrenador", "Editar Reserva", "Ver Reservas", "Volver al Menú Principal"};
            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción:",
                    "Menú Gym",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0:
                    // Reservar entrenador
                    reservarEntrenador();
                    break;
                case 1:
                    // Editar reserva
                    editarReserva();
                    break;
                case 2:
                    // Ver reservas
                    verReservas();
                    break;
                case 3:
                    // Volver al menú principal
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione nuevamente.");
            }
        }
    }

    private void reservarEntrenador() {
        int entrenador = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de entrenador (1-" + NUM_ENTRENADORES + "):")) - 1;

        if (entrenador < 0 || entrenador >= NUM_ENTRENADORES) {
            JOptionPane.showMessageDialog(null, "Número de entrenador no válido. Inténtelo nuevamente.");
            return;
        }

        // Mostrar horarios disponibles
        StringBuilder opcionesHorarios = new StringBuilder("Seleccione un horario para el entrenador " + (entrenador + 1) + ":\n");
        for (int i = 0; i < NUM_HORARIOS; i++) {
            if (reservas[entrenador][i][0] == null) {
                opcionesHorarios.append(i + 1).append(". ").append("Horario ").append(i + 1).append("\n");
            }
        }

        int seleccionHorario = Integer.parseInt(JOptionPane.showInputDialog(opcionesHorarios.toString())) - 1;

        if (seleccionHorario < 0 || seleccionHorario >= NUM_HORARIOS || reservas[entrenador][seleccionHorario][0] != null) {
            JOptionPane.showMessageDialog(null, "Selección de horario no válida o no disponible. Inténtelo nuevamente.");
            return;
        }

        // Solicitar ID del empleado
        int idEmpleado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID de empleado para confirmar la reserva:"));

        if (!validarEmpleado(idEmpleado)) {
            JOptionPane.showMessageDialog(null, "ID de empleado no válido. Inténtelo nuevamente.");
            return;
        }

        // Reservar horario
        reservas[entrenador][seleccionHorario][0] = "Reservado";
        reservas[entrenador][seleccionHorario][1] = String.valueOf(idEmpleado);
        JOptionPane.showMessageDialog(null, "Reserva exitosa. Entrenador " + (entrenador + 1)
                + " a las " + "Horario " + (seleccionHorario + 1) + " reservado para la persona con ID " + idEmpleado + ".");
    }

    private void editarReserva() {
        // Solicitar ID del empleado
        int idEmpleado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID de empleado para editar la reserva:"));

        if (!validarEmpleado(idEmpleado)) {
            JOptionPane.showMessageDialog(null, "ID de empleado no válido. Inténtelo nuevamente.");
            return;
        }

        // Buscar la reserva del empleado
        int entrenador = -1;
        int horario = -1;

        outer:
        for (int i = 0; i < NUM_ENTRENADORES; i++) {
            for (int j = 0; j < NUM_HORARIOS; j++) {
                if (reservas[i][j][0] != null && reservas[i][j][1].equals(String.valueOf(idEmpleado))) {
                    entrenador = i;
                    horario = j;
                    break outer;
                }
            }
        }

        if (entrenador == -1 || horario == -1) {
            JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva para el ID de empleado proporcionado.");
            return;
        }

        // Mostrar opciones de edición
        String[] opcionesEdicion = {"Cancelar Reserva", "Cambiar a Otro Horario", "Cambiar con Otro Entrenador"};
        int seleccionEdicion = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción de edición para su reserva:",
                "Editar Reserva",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesEdicion,
                opcionesEdicion[0]
        );

        switch (seleccionEdicion) {
            case 0:
                // Cancelar Reserva
                cancelarReserva(entrenador, horario);
                break;
            case 1:
                // Cambiar a Otro Horario
                cambiarHorario(entrenador, horario);
                break;
            case 2:
                // Cambiar con Otro Entrenador
                cambiarEntrenador(entrenador, horario);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida. No se realizó ningún cambio en la reserva.");
        }
    }

    private void cancelarReserva(int entrenador, int horario) {
        // Cancelar reserva
        reservas[entrenador][horario][0] = null;
        reservas[entrenador][horario][1] = null;
        JOptionPane.showMessageDialog(null, "Reserva cancelada con éxito.");
    }

    private void cambiarHorario(int entrenador, int horario) {
        // Mostrar horarios disponibles
        StringBuilder opcionesHorarios = new StringBuilder("Seleccione un nuevo horario para su reserva:\n");
        for (int i = 0; i < NUM_HORARIOS; i++) {
            if (i != horario && reservas[entrenador][i][0] == null) {
                opcionesHorarios.append(i + 1).append(". ").append("Horario ").append(i + 1).append("\n");
            }
        }

        int nuevoHorario = Integer.parseInt(JOptionPane.showInputDialog(opcionesHorarios.toString())) - 1;

        if (nuevoHorario < 0 || nuevoHorario >= NUM_HORARIOS || reservas[entrenador][nuevoHorario][0] != null) {
            JOptionPane.showMessageDialog(null, "Selección de nuevo horario no válida o no disponible. No se realizó ningún cambio en la reserva.");
            return;
        }

        // Obtener el ID del empleado de la reserva actual
        String idEmpleadoActual = reservas[entrenador][horario][1];

        // Liberar el horario anterior
        reservas[entrenador][horario][0] = null;
        reservas[entrenador][horario][1] = null;

        // Reservar el nuevo horario con el mismo ID
        reservas[entrenador][nuevoHorario][0] = "Reservado";
        reservas[entrenador][nuevoHorario][1] = idEmpleadoActual;
        JOptionPane.showMessageDialog(null, "Reserva editada con éxito. Entrenador " + (entrenador + 1)
                + " a las " + "Horario " + (nuevoHorario + 1) + " para la persona con ID " + idEmpleadoActual + ".");
    }

    private void cambiarEntrenador(int entrenador, int horario) {
        // Mostrar entrenadores disponibles
        StringBuilder opcionesEntrenadores = new StringBuilder("Seleccione otro entrenador para su reserva:\n");
        for (int i = 0; i < NUM_ENTRENADORES; i++) {
            if (i != entrenador && reservas[i][horario][0] == null) {
                opcionesEntrenadores.append(i + 1).append(". ").append("Entrenador ").append(i + 1).append("\n");
            }
        }

        int nuevoEntrenador = Integer.parseInt(JOptionPane.showInputDialog(opcionesEntrenadores.toString())) - 1;

        if (nuevoEntrenador < 0 || nuevoEntrenador >= NUM_ENTRENADORES || reservas[nuevoEntrenador][horario][0] != null) {
            JOptionPane.showMessageDialog(null, "Selección de nuevo entrenador no válida o no disponible. No se realizó ningún cambio en la reserva.");
            return;
        }

        // Obtener el ID del empleado de la reserva actual
        String idEmpleadoActual = reservas[entrenador][horario][1];

        // Liberar la reserva anterior
        reservas[entrenador][horario][0] = null;
        reservas[entrenador][horario][1] = null;

        // Reservar con el nuevo entrenador y el mismo ID
        reservas[nuevoEntrenador][horario][0] = "Reservado";
        reservas[nuevoEntrenador][horario][1] = idEmpleadoActual;
        JOptionPane.showMessageDialog(null, "Reserva editada con éxito. Entrenador " + (nuevoEntrenador + 1)
                + " a las " + "Horario " + (horario + 1) + " para la persona con ID " + idEmpleadoActual + ".");
    }

    private void verReservas() {
        StringBuilder mensaje = new StringBuilder("Reservas del Gym:\n");

        for (int entrenador = 0; entrenador < NUM_ENTRENADORES; entrenador++) {
            mensaje.append("Entrenador ").append(entrenador + 1).append(":\n");

            for (int horario = 0; horario < NUM_HORARIOS; horario++) {
                if (reservas[entrenador][horario][0] != null) {
                    mensaje.append("Horario ").append(horario + 1).append(": Reservado (ID Empleado: ").append(reservas[entrenador][horario][1]).append(")\n");
                } else {
                    mensaje.append("Horario ").append(horario + 1).append(": Disponible\n");
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