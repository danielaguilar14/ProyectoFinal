/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mtorr
 */
import javax.swing.*;

public class EmpleadoGUI extends JFrame {
    private Empleado empleado;

    private JLabel nombreLabel;
    private JLabel idLabel;

    public EmpleadoGUI(Empleado empleado) {
        this.empleado = empleado;

        nombreLabel = new JLabel("Nombre: " + empleado.getNombre());
        idLabel = new JLabel("ID: " + empleado.getId());

        // Configurar el diseño de la interfaz
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(nombreLabel);
        add(idLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    public static void main(String[] args) {
        Empleado empleado = new Empleado("Juan", 101);

        SwingUtilities.invokeLater(() -> new EmpleadoGUI(empleado));
    }
}

class Empleado {
    private String nombre;
    private int id;

    public Empleado(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
