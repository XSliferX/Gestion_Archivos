package com.mycompany.gestordearchivos;

/**
 *
 * @author Slyfer
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RutaFichero extends JFrame {

    private JList<String> rutaList;
    private DefaultListModel<String> listModel;

    public RutaFichero() {
        // Configurar la ventana principal
        setTitle("Mostrar Rutas de Ficheros y Directorios");
        setSize(500, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes Swing
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        rutaList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(rutaList);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnListar = new JButton("Listar Rutas");
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarRutas();
            }
        });

        panel.add(btnListar, BorderLayout.NORTH);

        add(panel);

        // Mostrar la ventana
        setVisible(true);
    }

    private void listarRutas() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();

            listModel.clear();

            for (File selectedFile : selectedFiles) {
                listModel.addElement(selectedFile.getAbsolutePath());
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
                new RutaFichero();
            }
        });
    }
}
