package view;

import javax.swing.*;
import java.awt.*;

public class  ProductoView extends JFrame {

    // Titulo
    public JPanel panel_titulos = new JPanel();

    public JLabel titulo1 = new JLabel("Control escolar");
    public JLabel titulo2 = new JLabel("Escuela X");



    // Campos
    public JTextField txtClave = new JTextField(10);
    public JTextField txtNombre = new JTextField(20);
    public JTextField txtMarca = new JTextField(20);
    public JTextField txtPrecio = new JTextField(10);
    public JTextArea  txtDescripcion = new JTextArea(3, 20);

    // Botones requeridos
    public JButton btnAgregar = new JButton("Agregar");
    public JButton btnLimpiar = new JButton("Limpiar");
    public JButton btnEliminar = new JButton("Eliminar"); // en este ejemplo: elimina seleccionado

    // Tabla
    public JTable tabla = new JTable();
    public ProductoTableModel tableModel;

    public ProductoView() {
        super("Catalogo de Productos - MVC Guardar en Arreglo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);

        // === Panel Formulario (GridBagLayout) ===
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4,4,4,4);
        g.anchor = GridBagConstraints.WEST;
        g.fill = GridBagConstraints.HORIZONTAL;
        int y = 0;

        g.gridx=0; g.gridy=y; form.add(new JLabel("Clave:"), g);
        g.gridx=1; form.add(txtClave, g);

        y++; g.gridx=0; g.gridy=y; form.add(new JLabel("Nombre:"), g);
        g.gridx=1; form.add(txtNombre, g);

        y++; g.gridx=0; g.gridy=y; form.add(new JLabel("Marca:"), g);
        g.gridx=1; form.add(txtMarca, g);

        y++; g.gridx=0; g.gridy=y; form.add(new JLabel("Precio:"), g);
        g.gridx=1; form.add(txtPrecio, g);

        y++; g.gridx=0; g.gridy=y; g.anchor = GridBagConstraints.NORTHWEST;
        form.add(new JLabel("Descripción:"), g);
        g.gridx=1; JScrollPane sp = new JScrollPane(txtDescripcion);
        form.add(sp, g);
        g.anchor = GridBagConstraints.WEST;

        // === Panel Botones (FlowLayout) ===
        //JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //botones.add(btnAgregar);
        //botones.add(btnLimpiar);
        //botones.add(btnEliminar);

        // === Tabla (BorderLayout CENTER) ===
        JScrollPane tablaScroll = new JScrollPane(tabla);

        // === Layout principal ===
        setLayout(new BorderLayout(8,8));

        //add(form, BorderLayout.NORTH);
        // add(botones, BorderLayout.CENTER);

        add(tablaScroll, BorderLayout.SOUTH);

        panel_titulos.setLayout(new GridLayout(2,1));
        panel_titulos.add(titulo1);
        panel_titulos.add(titulo2);

        add(panel_titulos, BorderLayout.NORTH);

        titulo1.setHorizontalAlignment(SwingConstants.CENTER);
        titulo2.setHorizontalAlignment(SwingConstants.CENTER);

    }

    public void limpiarFormulario() {
        txtClave.setText("");
        txtNombre.setText("");
        txtMarca.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtClave.requestFocus();
    }
}
