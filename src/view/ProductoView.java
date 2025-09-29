package view;

import javax.swing.*;
import java.awt.*;

public class  ProductoView extends JFrame {

    // Menu
    JMenuBar menuBar;
    JMenu menu_con, menu_act, menu_config;
    JMenuItem con_buscar;
    public JMenuItem act_agregar, act_eliminar, act_modificar;

    // Titulo
    public JPanel panel_titulos = new JPanel();

    public JLabel titulo1 = new JLabel("Control escolar");
    public JLabel titulo2 = new JLabel("Instituto Tecnológico Nacional de México");



    // Campos
    public JTextField txtNumeroControl = new JTextField(10);
    public JTextField txtNombre = new JTextField(20);
    public JTextField txtMateria = new JTextField(20);
    public JTextField txtCalificacion = new JTextField(10);
    public JTextArea  txtEspecialidad = new JTextArea(3, 20);


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

        // Crear barra de menú
        menuBar = new JMenuBar();
        menu_con= new JMenu("Consultar");
        menu_act= new JMenu("Actualizar");
        menu_config= new JMenu("Configuración");

        //colores de barra menú
        menu_con.setForeground(new Color(0xFFFFFFFF, true));
        menu_act.setForeground(new Color(0xFFFFFFFF, true));
        menu_config.setForeground(new Color(0xFFFFFFFF, true));
        menuBar.setBackground(new Color(0x050548));

        // Submenús
        con_buscar = new JMenuItem("Buscar");

        act_agregar = new JMenuItem("Agregar");
        act_eliminar = new JMenuItem("Eliminar");
        act_modificar = new JMenuItem("Modificar");

        act_agregar.setBackground(new Color(0x7474EA));
        act_eliminar.setBackground(new Color(0x7474EA));
        act_modificar.setBackground(new Color(0x7474EA));

        // Agregar los multiples menús a la barra de Menú
        menuBar.add(menu_con);
        menuBar.add(menu_act);
        menuBar.add(menu_config);

        // Agregar los submenús a los menús
        menu_con.add(con_buscar);
        menu_con.setBackground(new Color(0x2D3762));

        menu_act.add(act_agregar);
        menu_act.add(act_eliminar);
        menu_act.add(act_modificar);

        setJMenuBar(menuBar);


        // === Panel Formulario (GridBagLayout) ===
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4,4,4,4);
        g.anchor = GridBagConstraints.WEST;
        g.fill = GridBagConstraints.HORIZONTAL;
        int y = 0;

        g.gridx=0; g.gridy=y; form.add(new JLabel("Numero de Control:"), g);
        g.gridx=1; form.add(txtNumeroControl, g);

        y++; g.gridx=0; g.gridy=y; form.add(new JLabel("Nombre:"), g);
        g.gridx=1; form.add(txtNombre, g);

        y++; g.gridx=0; g.gridy=y; form.add(new JLabel("Materia:"), g);
        g.gridx=1; form.add(txtMateria, g);

        y++; g.gridx=0; g.gridy=y; form.add(new JLabel("Calificacion:"), g);
        g.gridx=1; form.add(txtCalificacion, g);

        y++; g.gridx=0; g.gridy=y; g.anchor = GridBagConstraints.NORTHWEST;
        form.add(new JLabel("Especialidad:"), g);
        g.gridx=1; JScrollPane sp = new JScrollPane(txtEspecialidad);
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
        txtNumeroControl.setText("");
        txtNombre.setText("");
        txtMateria.setText("");
        txtCalificacion.setText("");
        txtEspecialidad.setText("");
        txtNumeroControl.requestFocus();
    }
}