package com.mycompany.gestordearchivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 *
 * @author Slyfer
 */
public class CopiarArchivo {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        String rutaOrigen = JOptionPane.showInputDialog("Ingrese la ruta del archivo de origen:");
        String rutaDestino = JOptionPane.showInputDialog("Ingrese la ruta del archivo de destino:");

        File archivoOrigen = new File(rutaOrigen);
        File archivoDestino = new File(rutaDestino);

        if (!archivoOrigen.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo de origen no existe.");
            return;
        }

        if (archivoDestino.isDirectory()) {

            String nombreArchivo = archivoOrigen.getName();
            File destino = new File(archivoDestino.getAbsolutePath() + File.separator + nombreArchivo);
            copiarArchivo(archivoOrigen, destino);
        } else {

            if (archivoDestino.exists()) {
                int respuesta = JOptionPane.showConfirmDialog(null,
                        "El archivo de destino ya existe. ¿Desea reemplazarlo?", "Confirmación",
                        JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    copiarArchivo(archivoOrigen, archivoDestino);
                }
            } else {
                copiarArchivo(archivoOrigen, archivoDestino);
            }
        }

        JOptionPane.showMessageDialog(null, "Operación completada.");
    }

    private static void copiarArchivo(File origen, File destino) {
        try {
            Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al copiar el archivo: " + e.getMessage());
        }
    }
}
