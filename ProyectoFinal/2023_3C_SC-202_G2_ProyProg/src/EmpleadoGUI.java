/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mtorr
 */
import javax.swing.*;

public class EmpleadoGUI {
    private Empleado empleado;

    private JFrame frame;
    private JLabel nombreLabel;
    private JLabel idLabel;

    public EmpleadoGUI(Empleado empleado) {
        this.empleado = empleado;

        frame = new JFrame("Información del Empleado");
        nombreLabel = new JLabel("Nombre: " + empleado.getNombre());
        idLabel = new JLabel("ID: " + empleado.getId());

        // Configurar el diseño de la interfaz
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(nombreLabel);
        frame.add(idLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Empleado empleado = new Empleado("Juan", 101);

        SwingUtilities.invokeLater(() -> new EmpleadoGUI(empleado));
    }
}

