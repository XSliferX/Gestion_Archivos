package com.mycompany.gestordearchivos;
import java.io.*;
import javax.swing.*;
import javax.swing.UIManager;


/**
 *
 * @author Slyfer
 */
public class EscribirFichero {
    public static void main(String[] args) {
        
          try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
          
        // Crear un objeto JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Mostrar el cuadro de diálogo para seleccionar el archivo
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtener la ruta seleccionada por el usuario
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();

            // Pedir al usuario el texto que desea escribir en el fichero
            String texto = JOptionPane.showInputDialog("Ingrese el texto que desea escribir en el fichero:");

            // Llamar al método para escribir el fichero
            escribirFichero(ruta, texto);

            // Llamar al método para mostrar el contenido del fichero
            mostrarFichero(ruta);
        }
    }

    // Método para escribir el fichero
    private static void escribirFichero(String ruta, String texto) {
        try (FileWriter fr = new FileWriter(ruta)) {
            fr.write(texto);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al escribir en el archivo.");
        }
    }

    // Método para mostrar el contenido del fichero
    private static void mostrarFichero(String ruta) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            JOptionPane.showMessageDialog(null, "El contenido del fichero " + ruta + " es:\n" + contenido.toString());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al mostrar el contenido del archivo.");
        }
    }
}
