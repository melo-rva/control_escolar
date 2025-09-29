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
            Double NumeroControl = Double.parseDouble(view.txtNumeroControl.getText().trim());
            String nombre = view.txtNombre.getText().trim();
            String materia = view.txtMateria.getText().trim();
            Double calificacion = Double.parseDouble(view.txtCalificacion.getText().trim());
            String especialidad = view.txtEspecialidad.getText().trim();

            validar(NumeroControl, nombre, materia, calificacion);

            Producto existente = repo.findByClave(NumeroControl);
            if (existente == null) {
                // crear
                repo.create(new Producto(NumeroControl, nombre, materia, calificacion, especialidad));
                JOptionPane.showMessageDialog(view, "Alumno agregado.");
            } else {
                // actualizar
                repo.update(new Producto(NumeroControl, nombre, materia, calificacion, especialidad));
                JOptionPane.showMessageDialog(view, "Alumno actualizado.");
            }
            refrescarTabla();
            view.limpiarFormulario();
        } catch (NumberFormatException nfe) {
            mostrarError("Calificacion inválido. Ejemplo: 199.99");
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
                "¿Eliminar producto con clave " + p.getNumeroControl() + "?", "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            try {
                repo.deleteByClave(p.getNumeroControl());
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
        view.txtNumeroControl.setText(String.valueOf(Double.valueOf(p.getNumeroControl())));
        view.txtNombre.setText(p.getNombre());
        view.txtMateria.setText(p.getMateria());
        view.txtCalificacion.setText(String.valueOf(Double.valueOf(p.getCalificacion())));
        view.txtEspecialidad.setText(String.valueOf(p.getEspecialidad()));
    }

    private void refrescarTabla() {
        view.tableModel.setData(repo.findAll());
    }

    private void validar(Double numeroControl, String nombre, String materia, Double calificacion) {
        if (numeroControl == null)
            throw new IllegalArgumentException("El numero de control es requerido.");
        if (nombre.isBlank()) throw new IllegalArgumentException("El nombre es requerido.");
        if (materia.isBlank()) throw new IllegalArgumentException("La materia es requerida.");
        if (calificacion < 0) throw new IllegalArgumentException("La calificacion no puede ser negativa.");
    }

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(view, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}