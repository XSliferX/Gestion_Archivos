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

public class LeerFichero extends JFrame {

    private JTextArea contenidoTextArea;

    public LeerFichero() {
        // Configurar la ventana principal
        setTitle("Leer Archivo con JFileChooser");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes Swing
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        contenidoTextArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(contenidoTextArea);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnAbrir = new JButton("Abrir Archivo");
        btnAbrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirArchivo();
            }
        });

        panel.add(btnAbrir, BorderLayout.SOUTH);

        add(panel);

        // Mostrar la ventana
        setVisible(true);
    }

    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileReader fileReader = new FileReader(selectedFile)) {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder contenido = new StringBuilder();
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                contenidoTextArea.setText(contenido.toString());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
                new LeerFichero();
            }
        });
    }
}
