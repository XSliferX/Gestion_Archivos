
package com.mycompany.gestordearchivos;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * 
 * @author Slyfer
 */

public class Permisos {
    public static void main(String[] args) {
          try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        while (true) {
            // Menú principal
            String[] opciones = { "Permisos sobre archivo", "Permisos sobre directorio", "Salir" };
            int seleccion = JOptionPane.showOptionDialog(null, "Selecciona una opción:", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == 0) { // Permisos sobre archivo
                String rutaArchivo = JOptionPane.showInputDialog("Introduce la ruta completa del archivo:");
                File archivo = new File(rutaArchivo);

                if (archivo.isFile()) {
                    boolean[] tienePermisos = new boolean[3];

                    for (int i = 0; i < 3; i++) {
                        tienePermisos[i] = archivo.canRead(); // Para lectura
                        if (i == 1)
                            tienePermisos[i] = archivo.canWrite(); // Para escritura
                        if (i == 2)
                            tienePermisos[i] = archivo.canExecute(); // Para ejecución
                    }

                    String mensaje = "Permisos para el archivo:\n" +
                            "Lectura: " + tienePermisos[0] + "\n" +
                            "Escritura: " + tienePermisos[1] + "\n" +
                            "Ejecución: " + tienePermisos[2];

                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "No has introducido correctamente un archivo.");
                }
            } else if (seleccion == 1) { // Permisos sobre directorio
                String rutaDirectorio = JOptionPane.showInputDialog("Introduce la ruta del directorio:");
                File directorio = new File(rutaDirectorio);

                if (directorio.isDirectory()) {
                    boolean[] tienePermisos = new boolean[3];

                    for (int i = 0; i < 3; i++) {
                        tienePermisos[i] = directorio.canRead();
                        if (i == 1)
                            tienePermisos[i] = directorio.canWrite();
                        if (i == 2)
                            tienePermisos[i] = directorio.canExecute();
                    }

                    String mensaje = "Permisos para el directorio:\n" +
                            "Lectura: " + tienePermisos[0] + "\n" +
                            "Escritura: " + tienePermisos[1] + "\n" +
                            "Ejecución: " + tienePermisos[2];

                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "No has introducido correctamente un directorio.");
                }
            } else if (seleccion == 2) { // Salir
                System.exit(0); // Salir del programa
            }
        }
    }
}
