package com.mycompany.gestordearchivos;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;


/**
 *
 * @author Slyfer
 */
public class BorrarArchivo extends JFrame {

    private JButton btnBorrarArchivo;
    private JButton btnBorrarDirectorio;
    private JButton btnSalir;

    public BorrarArchivo() {
        super("Gestor de borrado de archivos");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Font font = new Font("Arial", Font.PLAIN, 12);
        setUIFont(font);

        btnBorrarArchivo = createButton("Borrar Archivo");
        btnBorrarDirectorio = createButton("Borrar Directorio");

        btnBorrarArchivo.addActionListener(e -> borrarArchivo());
        btnBorrarDirectorio.addActionListener(e -> borrarDirectorio());

        btnSalir = createButton("Salir");
        btnSalir.addActionListener(e -> salir());

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(btnBorrarArchivo);
        panel.add(btnBorrarDirectorio);
        panel.add(btnSalir);

        add(panel);

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                salir();
            }
        });

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(getUIFont());
        return button;
    }

    private void borrarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (archivo.delete()) {
                mostrarMensaje("Archivo borrado");
            } else {
                mostrarMensaje("No se pudo borrar el archivo");
            }
        }
    }

    private void borrarDirectorio() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File directorio = fileChooser.getSelectedFile();
            if (borrarDirectorioRecursivo(directorio)) {
                mostrarMensaje("Directorio borrado");
            } else {
                mostrarMensaje("No se pudo borrar el directorio");
            }
        }
    }

    private boolean borrarDirectorioRecursivo(File directorio) {
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isDirectory() && !borrarDirectorioRecursivo(archivo)) {
                        return false;
                    } else if (!archivo.delete()) {
                        return false;
                    }
                }
            }
        }
        return directorio.delete();
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private Font getUIFont() {
        return new Font("Arial", Font.PLAIN, 12);
    }

    private void setUIFont(Font font) {
        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Windows".equals(laf.getName())) {
                UIManager.put("Button.font", font);
                UIManager.put("Label.font", font);
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void salir() {
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea salir de la aplicación?", "Salir",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BorrarArchivo::new);
    }
}
