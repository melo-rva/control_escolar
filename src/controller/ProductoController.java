package controller;

import model.Producto;
import model.ProductoRepositorioArreglo;
import view.ProductoTableModel;
import view.ProductoView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductoController {
    private final ProductoRepositorioArreglo repo;
    private final ProductoView view;

    public ProductoController(ProductoRepositorioArreglo repo, ProductoView view) {
        this.repo = repo;
        this.view = view;

        // Inicializar tabla
        view.tableModel = new ProductoTableModel(repo.findAll());
        view.tabla.setModel(view.tableModel);

        registrarEventos();
        refrescarTabla();
    }

    private void registrarEventos() {
        // ACEPTAR: crea o actualiza según exista la clave
        view.btnAgregar.addActionListener(e -> onAceptar());

        // LIMPIAR: limpia formulario
        view.btnLimpiar.addActionListener(e -> view.limpiarFormulario());

        // CANCELAR: elimina registro seleccionado (o cambia a cerrar ventana si prefieres)
        view.btnEliminar.addActionListener(e -> onEliminarSeleccionado());
        // Para que "Cancelar" cierre la ventana, cambia a:
        // view.btnCancelar.addActionListener(e -> view.dispose());

        // Cargar datos al seleccionar fila
        view.tabla.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int row = view.tabla.getSelectedRow();
                if (row >= 0) cargarFormularioDesdeTabla(row);
            }
        });
    }

    private void onAceptar() {
        try {
            String clave = view.txtClave.getText().trim();
            String nombre = view.txtNombre.getText().trim();
            String marca = view.txtMarca.getText().trim();
            String descripcion = view.txtDescripcion.getText().trim();
            double precio = Double.parseDouble(view.txtPrecio.getText().trim());

            validar(clave, nombre, marca, precio);

            Producto existente = repo.findByClave(clave);
            if (existente == null) {
                // crear
                repo.create(new Producto(clave, nombre, marca, descripcion, precio));
                JOptionPane.showMessageDialog(view, "Producto creado.");
            } else {
                // actualizar
                repo.update(new Producto(clave, nombre, marca, descripcion, precio));
                JOptionPane.showMessageDialog(view, "Producto actualizado.");
            }
            refrescarTabla();
            view.limpiarFormulario();
        } catch (NumberFormatException nfe) {
            mostrarError("Precio inválido. Ejemplo: 199.99");
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void onEliminarSeleccionado() {
        int row = view.tabla.getSelectedRow();
        if (row < 0) {
            mostrarError("Selecciona un registro en la tabla para eliminar.");
            return;
        }
        Producto p = view.tableModel.getAt(row);
        int opt = JOptionPane.showConfirmDialog(view,
                "¿Eliminar producto con clave " + p.getClave() + "?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            try {
                repo.deleteByClave(p.getClave());
                refrescarTabla();
                view.limpiarFormulario();
                JOptionPane.showMessageDialog(view, "Producto eliminado.");
            } catch (Exception ex) {
                mostrarError(ex.getMessage());
            }
        }
    }

    private void cargarFormularioDesdeTabla(int row) {
        Producto p = view.tableModel.getAt(row);
        view.txtClave.setText(p.getClave());
        view.txtNombre.setText(p.getNombre());
        view.txtMarca.setText(p.getMarca());
        view.txtDescripcion.setText(p.getDescripcion());
        view.txtPrecio.setText(String.valueOf(p.getPrecio()));
    }

    private void refrescarTabla() {
        view.tableModel.setData(repo.findAll());
    }

    private void validar(String clave, String nombre, String marca, double precio) {
        if (clave.isBlank()) throw new IllegalArgumentException("La clave es requerida.");
        if (nombre.isBlank()) throw new IllegalArgumentException("El nombre es requerido.");
        if (marca.isBlank()) throw new IllegalArgumentException("La marca es requerida.");
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
    }

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(view, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
