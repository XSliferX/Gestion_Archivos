package com.mycompany.gestordearchivos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa un Gestor de Archivos con una interfaz gráfica.
 *
 * @author Slyfer/Yván Morales
 */
public class GestorDeArchivos extends JFrame {

    private final JButton[] buttons;
    private final JLabel resultLabel;
    private final JButton backButton;

    /**
     * Constructor de la clase GestorDeArchivos.
     */
    public GestorDeArchivos() {
        try {
            // Configurar el aspecto del sistema Windows para la interfaz de usuario.
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Configurar la ventana principal.
        setTitle("Gestor De Archivos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Establecer un icono para la ventana.
        setIconImage(new ImageIcon("Gar.jpg").getImage());

        // Crear un botón "Volver" en la parte superior.
        backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar el texto de la etiqueta de resultado cuando se presiona el botón "Volver".
                resultLabel.setText("Menú principal");
            }
        });

        add(backButton, BorderLayout.NORTH);

        // Configurar acciones al cerrar y minimizar la ventana.
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
                // Minimizar la ventana al hacer clic en el ícono de minimizar.
                setState(JFrame.ICONIFIED);
            }
        });

        setLayout(new BorderLayout());

        // Crear un panel de botones con una cuadrícula de 3x3.
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[9];
        resultLabel = new JLabel("Yván Morales", SwingConstants.CENTER);
        resultLabel.setBorder(BorderFactory.createEtchedBorder());

        // Etiquetas y rutas de las imágenes para los botones.
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

        // Crear y configurar botones personalizados.
        for (int i = 0; i < 9; i++) {
            buttons[i] = createCustomButton(buttonLabels[i], imagePaths[i]);
            buttons[i].addActionListener(new FileManagerActionListener(buttonLabels[i]));
            buttonPanel.add(buttons[i]);
        }

        // Agregar componentes a la ventana.
        add(buttonPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    /**
     * Crea un botón personalizado con una etiqueta de imagen y texto.
     *
     * @param buttonText Texto del botón.
     * @param imagePath  Ruta de la imagen para el botón.
     * @return El botón personalizado creado.
     */
    private JButton createCustomButton(String buttonText, String imagePath) {
        JButton button = new JButton();

        // Crear un panel personalizado que contiene una etiqueta de imagen y una etiqueta de texto.
        JPanel customPanel = new JPanel();
        customPanel.setLayout(new BorderLayout());

        // Agregar una etiqueta de imagen al botón.
        ImageIcon originalImageIcon = new ImageIcon(imagePath);
        Image originalImage = originalImageIcon.getImage();
        int width = 50; // Ancho deseado
        int height = 50; // Alto deseado
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        customPanel.add(imageLabel, BorderLayout.CENTER);

        // Agregar una etiqueta de texto al botón.
        JLabel textLabel = new JLabel(buttonText, SwingConstants.CENTER);
        customPanel.add(textLabel, BorderLayout.SOUTH);

        // Configurar el panel personalizado como contenido del botón.
        button.add(customPanel);

        return button;
    }

    /**
     * Clase interna para gestionar las acciones de los botones del FileManager.
     */
    private class FileManagerActionListener implements ActionListener {

        private final String action;

        public FileManagerActionListener(String action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Realizar la acción asociada al botón.
            Accion(action);
        }
    }

    /**
     * Realiza una acción específica según el botón presionado.
     *
     * @param action Acción a realizar.
     */
    private void Accion(String action) {
        switch (action) {
            case "Permisos" -> Permisos.main(new String[0]);
            case "Copiar" -> CopiarArchivo.main(new String[0]);
            case "Crear Fichero" -> CrearFichero.main(new String[0]);
            case "Borrar Fichero" -> BorrarArchivo.main(new String[0]);
            case "Extensiones" -> Extensiones.main(new String[0]);
            case "Leer Fichero" -> LeerFichero.main(new String[0]);
            case "Escribir Fichero" -> EscribirFichero.main(new String[0]);
            case "Ruta Fichero" -> RutaFichero.main(new String[0]);
            case "Listado Directorio" -> ListaDirectorio.main(new String[0]);
        }
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear y mostrar la ventana principal del Gestor de Archivos.
            GestorDeArchivos fileManager = new GestorDeArchivos();
            fileManager.setVisible(true);
        });
    }
}
