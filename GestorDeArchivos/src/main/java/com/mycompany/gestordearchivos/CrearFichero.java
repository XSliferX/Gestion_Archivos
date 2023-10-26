/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestordearchivos;

/**
 *
 * @author Slyfer
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CrearFichero extends JFrame {

    private JTextField nombreArchivo;
    private JTextArea contenidoTexto;

    public CrearFichero() {
        // Configurar la ventana principal
        setTitle("Crear Fichero");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes Swing
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 10, 5, 10);

        JLabel lblNombre = new JLabel("Guardar archivo como:");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(lblNombre, c);

        nombreArchivo = new JTextField(20);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(nombreArchivo, c);

        JLabel lblContenido = new JLabel("Contenido del archivo:");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        panel.add(lblContenido, c);

        contenidoTexto = new JTextArea(15, 40);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        panel.add(contenidoTexto, c);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarArchivo();
            }
        });
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        panel.add(btnGuardar, c);

        JButton btnSeleccionar = new JButton("Seleccionar Ruta");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionarRuta();
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        panel.add(btnSeleccionar, c);

        add(panel);

        // Mostrar la ventana
        setVisible(true);
    }

    private void guardarArchivo() {
        String nombre = nombreArchivo.getText();
        String contenido = contenidoTexto.getText();

        if (nombre.isEmpty() || contenido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre y contenido para el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (FileWriter fr = new FileWriter(nombre)) {
            fr.write(contenido);
            JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void seleccionarRuta() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            nombreArchivo.setText(selectedFile.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CrearFichero();
            }
        });
    }
}
