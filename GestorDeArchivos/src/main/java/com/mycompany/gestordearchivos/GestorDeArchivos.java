package com.mycompany.gestordearchivos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

public class GestorDeArchivos extends JFrame {

    private final JButton[] buttons;
    private final JLabel resultLabel;
    private final JButton backButton;

    public GestorDeArchivos() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("Gestor De Archivos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setIconImage(new ImageIcon("Gar.jpg").getImage());
        backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("Menú principal");
            }
        });

        add(backButton, BorderLayout.NORTH);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(GestorDeArchivos.this,
                        "¿Seguro que quieres salir?", "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

            @Override
            public void windowIconified(java.awt.event.WindowEvent windowEvent) {
                setState(JFrame.ICONIFIED);
            }

        });
        // getContentPane().setBackground(new Color(30, 30, 30)); // Cambia el color a tu preferencia
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[9];
        resultLabel = new JLabel("Yván Morales", SwingConstants.CENTER);
        resultLabel.setBorder(BorderFactory.createEtchedBorder());

        String[] buttonLabels = {
            "Permisos", "Copiar", "Crear Fichero",
            "Borrar Fichero", "Extensiones", "Leer Fichero",
            "Escribir Fichero", "Ruta Fichero", "Listado Directorio"
        };

        String[] imagePaths = {
            "Permiso.png", "Copiar.png", "Crear.png",
            "Borrar.png", "Extensiones.png", "Leer.png",
            "Escribir.png", "Ruta.png", "Listado.png"
        };

        for (int i = 0; i < 9; i++) {
            buttons[i] = createCustomButton(buttonLabels[i], imagePaths[i]);
            buttons[i].addActionListener(new FileManagerActionListener(buttonLabels[i]));
            buttonPanel.add(buttons[i]);
        }

        add(buttonPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    private JButton createCustomButton(String buttonText, String imagePath) {
        JButton button = new JButton();

        // Crear un panel personalizado para el botón que contiene una etiqueta de imagen y una etiqueta de texto
        JPanel customPanel = new JPanel();
        customPanel.setLayout(new BorderLayout());

        // Agregar una etiqueta de imagen
        ImageIcon originalImageIcon = new ImageIcon(imagePath);
        Image originalImage = originalImageIcon.getImage();
        int width = 50; // Ancho deseado
        int height = 50; // Alto deseado
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH); // para que la imagen se adapte al botón
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        customPanel.add(imageLabel, BorderLayout.CENTER);

        // Agregar una etiqueta de texto
        JLabel textLabel = new JLabel(buttonText, SwingConstants.CENTER);
        customPanel.add(textLabel, BorderLayout.SOUTH);

        //customPanel.setBackground(new Color(30, 30, 30)); // Cambia el color a tu preferencia
        // Configurar el panel personalizado como el contenido del botón
        button.add(customPanel);

        // button.setBackground(new Color(30, 30, 30)); // Cambia el color a tu preferencia
        return button;
    }

    private class FileManagerActionListener implements ActionListener {

        private final String action;

        public FileManagerActionListener(String action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Accion(action);
        }
    }

    private void Accion(String action) {
        switch (action) {
            case "Permisos" ->
                Permisos.main(new String[0]);
            case "Copiar" ->
                CopiarArchivo.main(new String[0]);
            case "Crear Fichero" ->
                CrearFichero.main(new String[0]);
            case "Borrar Fichero" ->
                BorrarArchivo.main(new String[0]);
            case "Extensiones" ->
                Extensiones.main(new String[0]);
            case "Leer Fichero" ->
                LeerFichero.main(new String[0]);
            case "Escribir Fichero" ->
                EscribirFichero.main(new String[0]);
            case "Ruta Fichero" ->
                RutaFichero.main(new String[0]);
            case "Listado Directorio" ->
                ListaDirectorio.main(new String[0]);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorDeArchivos fileManager = new GestorDeArchivos();
            fileManager.setVisible(true);
        });
    }
}
