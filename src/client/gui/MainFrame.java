package client.gui;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("FastSender");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#004aad"));
        setLayout(new BorderLayout());

        // Cargar fuentes
        Font poppins = loadFont("resources/fonts/Poppins-Regular.ttf", 18f);
        Font poppinsBold = loadFont("resources/fonts/Poppins-Bold.ttf", 22f);
        Font poppinsExtraBold = loadFont("resources/fonts/Poppins-ExtraBold.ttf", 32f);
        Font poppinsExtraBoldItalic = loadFont("resources/fonts/Poppins-ExtraBoldItalic.ttf", 32f);
        setUIFont(poppins);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.decode("#ff914d"));
        header.setBorder(BorderFactory.createEmptyBorder(10, 1, 10, 20));

        // Panel izquierdo con logo + texto
        JPanel leftHeader = new JPanel();
        leftHeader.setOpaque(false);
        leftHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        ImageIcon icon = new ImageIcon("resources/images/logo.png");
        Image scaled = icon.getImage().getScaledInstance(167, 69, Image.SCALE_SMOOTH); // Ajusta tamaño aquí
        JLabel logo = new JLabel(new ImageIcon(scaled));
        logo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Sin margen

        JLabel logoText = new JLabel("FastSender");
        logoText.setFont(poppinsExtraBoldItalic.deriveFont(26f));
        logoText.setForeground(Color.WHITE);
        logoText.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // Pegado al logo

        leftHeader.add(logo);
        leftHeader.add(logoText);

        // Botón de iniciar sesión (redondeado)
        JButton loginButton = createRoundedButton("Iniciar sesión", poppins);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.decode("#ff914d"));
        loginButton.addActionListener(e -> new LoginFrame());
        loginButton.setMaximumSize(new Dimension(160, 2)); // Ajusta tamaño si lo deseas

        header.add(leftHeader, BorderLayout.WEST);
        header.add(loginButton, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // CENTRO de la ventana (formulario)
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new GridBagLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título
        JLabel title = new JLabel("Seguimiento de Pedido");
        title.setFont(poppinsExtraBold);
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Subtítulo
        JLabel subtitle = new JLabel("Ingresa tu número de pedido");
        subtitle.setFont(poppinsBold);
        subtitle.setForeground(Color.WHITE);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Campo de texto redondeado
        JTextField pedidoField = createRoundedTextField(poppins);

        // Botón de búsqueda
        JButton botonBuscar = createRoundedButton("Buscar", poppins);

        // Agregar al panel
        contentPanel.add(title);
        contentPanel.add(subtitle);
        contentPanel.add(pedidoField);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(botonBuscar);

        centerPanel.add(contentPanel);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }

    // Métodos auxiliares
    public static Font loadFont(String path, float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            return font;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) size);
        }
    }

    private void setUIFont(Font font) {
        UIDefaults defaults = UIManager.getDefaults();
        Enumeration<Object> keys = defaults.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = defaults.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, font);
            }
        }
    }

    private JTextField createRoundedTextField(Font font) {
        JTextField field = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {}

            @Override
            public boolean isOpaque() { return false; }
        };
        field.setMaximumSize(new Dimension(300, 40));
        field.setFont(font);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setCaretColor(Color.BLACK);
        field.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        return field;
    }

    private JButton createRoundedButton(String text, Font font) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {}

            @Override
            public boolean isOpaque() { return false; }
        };
        button.setFont(font);
        button.setBackground(Color.decode("#ff914d"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        button.setMaximumSize(new Dimension(200, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
