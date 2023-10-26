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
import java.util.ArrayList;
import java.util.List;

public class ListaDirectorio extends JFrame {

    private JList<String> fileList;
    private DefaultListModel<String> listModel;

    public ListaDirectorio() {
        // Configurar la ventana principal
        setTitle("Listar y Mostrar Ficheros y Directorios");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes Swing
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fileList);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnListar = new JButton("Listar Ficheros y Directorios");
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarFicherosYDirectorios();
            }
        });

        JButton btnMostrar = new JButton("Mostrar Contenido");
        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarContenido();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnListar);
        //buttonPanel.add(btnMostrar);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Mostrar la ventana
        setVisible(true);
    }

    private void listarFicherosYDirectorios() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File directory = fileChooser.getSelectedFile();
            File[] filesAndDirs = directory.listFiles();

            listModel.clear();

            if (filesAndDirs != null) {
                for (File fileOrDir : filesAndDirs) {
                    listModel.addElement(fileOrDir.getName());
                }
            }
        }
    }

    private void mostrarContenido() {
        String selectedFile = fileList.getSelectedValue();
        if (selectedFile != null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = new File(fileChooser.getSelectedFile().getParent(), selectedFile);

                try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, "Contenido de " + selectedFile + ":\n" + content.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al abrir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
                new ListaDirectorio();
            }
        });
    }
}
