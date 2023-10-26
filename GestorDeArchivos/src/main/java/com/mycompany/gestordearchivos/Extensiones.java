package com.mycompany.gestordearchivos;
/**
 *
 * @author Slyfer
 */
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Extensiones extends JFrame {
    private JTextField rutaArchivoTextField;

    public Extensiones() {
        // Configurar la ventana principal
        setTitle("Seleccionar Archivos por Extensión");
        setSize(500, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes Swing
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        rutaArchivoTextField = new JTextField();
        rutaArchivoTextField.setEditable(false);
        panel.add(rutaArchivoTextField, BorderLayout.CENTER);

        JButton btnSeleccionar = new JButton("Seleccionar Archivo");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarArchivoPorExtension();
            }
        });
        panel.add(btnSeleccionar, BorderLayout.EAST);

        add(panel);

        // Mostrar la ventana
        setVisible(true);
    }

    private void seleccionarArchivoPorExtension() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            rutaArchivoTextField.setText(selectedFile.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Extensiones();
            }
        });
    }
}