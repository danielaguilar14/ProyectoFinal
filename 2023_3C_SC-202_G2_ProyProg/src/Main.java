
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author mtorr
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
   String contraseñaCorrecta = "1234";
        String password;

        do {
            password = JOptionPane.showInputDialog("Bienvenido al sistema de recepcionista. Ingrese la contraseña:");

            if (contraseñaCorrecta.equals(password)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. ¡Bienvenido!");

                // Crear empleados
                Empleado[] empleados = new Empleado[5];
                empleados[0] = new Empleado("Empleado1", 1);
                empleados[1] = new Empleado("Empleado2", 2);
                empleados[2] = new Empleado("Empleado3", 3);
                empleados[3] = new Empleado("Empleado4", 4);
                empleados[4] = new Empleado("Empleado5", 5);

                // Crear instancias de las clases Cine y Gym
                Cine cine = new Cine(empleados);
                Gym gym = new Gym(empleados);
                Yoga yoga = new Yoga();

                // Menú principal
                while (true) {
                    String[] opciones = {"Cine", "Gym", "Yoga & Baile", "Salir"};
                    int seleccion = JOptionPane.showOptionDialog(
                            null,
                            "Seleccione una opción:",
                            "Menú Principal",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            opciones,
                            opciones[0]
                    );

                    switch (seleccion) {
                        case 0:
                            // Mostrar menú del cine
                            cine.mostrarMenu();
                            break;
                        case 1:
                            // Mostrar menú del gym
                            gym.mostrarMenu();
                            break;
                        case 2:
                            yoga.mostrarMenu();
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "¡Hasta luego! Gracias por usar el sistema.");
                            System.exit(0);
                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione nuevamente.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Por favor, inténtelo nuevamente.");
            }
        } while (!contraseñaCorrecta.equals(password));
    }
}