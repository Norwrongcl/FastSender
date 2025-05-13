package client.gui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Iniciar Sesión - FastSender");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#004aad"));
        setLayout(new GridBagLayout());

        Font poppins = MainFrame.loadFont("resources/fonts/Poppins-Regular.ttf", 16f);
        Font poppinsBold = MainFrame.loadFont("resources/fonts/Poppins-Bold.ttf", 18f);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Ingresa tu RUT");
        label.setForeground(Color.WHITE);
        label.setFont(poppinsBold);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField rutField = new JTextField(20);
        rutField.setMaximumSize(new Dimension(300, 40));
        rutField.setFont(poppins);
        rutField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton ingresarBtn = new JButton("Ingresar");
        ingresarBtn.setBackground(Color.decode("#ff914d"));
        ingresarBtn.setForeground(Color.WHITE);
        ingresarBtn.setFont(poppins);
        ingresarBtn.setFocusPainted(false);
        ingresarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingresarBtn.setMaximumSize(new Dimension(200, 45));

        JLabel noCuentaLabel = new JLabel("No tengo una cuenta");
        noCuentaLabel.setForeground(Color.WHITE);
        noCuentaLabel.setFont(poppins);
        noCuentaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        noCuentaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        noCuentaLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        noCuentaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new RegisterFrame(); // Abre el frame de registro
                dispose(); // Cierra este frame si quieres
            }
        });

        ingresarBtn.addActionListener(e -> {
            String rut = rutField.getText().trim();
            // Aquí deberías consultar al servidor RMI para verificar el RUT
            if (rut.equals("12345678-9")) { // Simulación de éxito
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
            } else {
                JOptionPane.showMessageDialog(this, "RUT no encontrado. Debes registrarte.");
            }
        });

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(rutField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(ingresarBtn);
        panel.add(noCuentaLabel);

        add(panel);
        setVisible(true);
    }
}
