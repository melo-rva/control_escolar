package view;

import javax.swing.*;
import java.awt.*;

public class FormularioAgregar extends JDialog{
    public JTextField txtNumeroControl = new JTextField(10);
    public JTextField txtNombre = new JTextField(20);
    public JTextField txtMateria = new JTextField(20);
    public JTextField txtCalificacion = new JTextField(10);
    public JTextArea txtEspecialidad = new JTextArea(3, 20);

    public JButton btnAgregar = new JButton("Agregar");
    public JButton btnCancelar = new JButton("Cancelar");
    public JButton btnLimpiar = new JButton("Limpiar");

    public FormularioAgregar(JFrame parent) {
        super(parent, "Agregar", true); // true = modal
        setSize(400, 350);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(0x4242E8));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4,4,4,4);
        g.fill = GridBagConstraints.HORIZONTAL;
        int y = 0;

        g.gridx=0; g.gridy=y; panel.add(new JLabel("Numero de Control:"), g);
        g.gridx=1; panel.add(txtNumeroControl, g);

        y++; g.gridx=0; g.gridy=y; panel.add(new JLabel("Nombre:"), g);
        g.gridx=1; panel.add(txtNombre, g);

        y++; g.gridx=0; g.gridy=y; panel.add(new JLabel("Materia:"), g);
        g.gridx=1; panel.add(txtMateria, g);

        y++; g.gridx=0; g.gridy=y; panel.add(new JLabel("Calificación:"), g);
        g.gridx=1; panel.add(txtCalificacion, g);

        y++; g.gridx=0; g.gridy=y; g.anchor = GridBagConstraints.NORTHWEST;
        panel.add(new JLabel("Especialidad:"), g);
        g.gridx=1; panel.add(new JScrollPane(txtEspecialidad), g);
        g.anchor = GridBagConstraints.WEST;

        // Botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnAgregar.setBackground(Color.GREEN);
        btnAgregar.setForeground(Color.WHITE);

        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(Color.WHITE);

        btnLimpiar.setBackground(Color.ORANGE);
        btnLimpiar.setForeground(Color.BLACK);

        btnLimpiar.addActionListener(e -> {
            txtNumeroControl.setText("");
            txtNombre.setText("");
            txtMateria.setText("");
            txtCalificacion.setText("");
            txtEspecialidad.setText("");
        });

        botones.add(btnAgregar);
        botones.add(btnCancelar);
        botones.add(btnLimpiar);

        add(panel, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }
}
