package controller;

import model.Producto;
import model.ProductoRepositorioArreglo;
import view.FormularioAgregar;
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

    private void eliminarSeleccionado() {
        int fila = view.tabla.getSelectedRow();
        if (fila >= 0) {
            Producto seleccionado = view.tableModel.getAt(fila);

            int opcion = JOptionPane.showConfirmDialog(view,
                    "¿Eliminar el alumno con nombre: " + seleccionado.getNombre() + "?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                repo.deleteByClave(seleccionado.getNumeroControl());
                refrescarTabla();
                JOptionPane.showMessageDialog(view, "Alumno eliminado correctamente");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Seleccione un alumno primero");
        }
    }

    private void registrarEventos() {

        // Limpiar
        view.btnLimpiar.addActionListener(e -> view.limpiarFormulario());


        // Formulario
        view.act_agregar.addActionListener(e -> {
            // Crea la ventana del formulario para agregar
            FormularioAgregar form = new FormularioAgregar(view);

            // Botón agregar dentro del formulario
            form.btnAgregar.addActionListener(ev -> {
                try {
                    Double numeroControl = Double.parseDouble(form.txtNumeroControl.getText().trim());
                    String nombre = form.txtNombre.getText().trim();
                    String materia = form.txtMateria.getText().trim();
                    Double calificacion = Double.parseDouble(form.txtCalificacion.getText().trim());
                    String especialidad = form.txtEspecialidad.getText().trim();

                    // 1. Validar datos
                    validar(numeroControl, nombre, materia, calificacion);

                    // 2. Crear objeto alumno
                    Producto producto = new Producto(numeroControl, nombre, materia, calificacion, especialidad);

                    // 3. Guardar en el repositorio
                    repo.create(producto);

                    // 4. Refrescar la tabla de la vista principal
                    refrescarTabla();

                    // 5. Confirmar y cerrar el formulario
                    JOptionPane.showMessageDialog(view, "Alumno agregado correctamente");
                    form.dispose();

                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(form, "Datos invalidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Botón cancelar solo cierra el formulario
            form.btnCancelar.addActionListener(ev -> form.dispose());

            // Mostrar el formulario
            form.setVisible(true);
        });
    }

    private void modificarSeleccionado() {
        int fila = view.tabla.getSelectedRow();

        if (fila >= 0) {
            Producto seleccionado = view.tableModel.getAt(fila);

            // Crear un formulario igual que para agregar, pero con los datos cargados
            FormularioAgregar form = new FormularioAgregar(view);

            // Llenar campos con los datos del producto seleccionado
            form.txtNumeroControl.setText(String.valueOf(seleccionado.getNumeroControl()));
            form.txtNumeroControl.setEditable(false); // normalmente no se cambia la clave
            form.txtNombre.setText(seleccionado.getNombre());
            form.txtMateria.setText(seleccionado.getMateria());
            form.txtCalificacion.setText(String.valueOf(seleccionado.getCalificacion()));
            form.txtEspecialidad.setText(seleccionado.getEspecialidad());

            // Botón agregar ahora funciona como "guardar cambios"
            form.btnAgregar.setText("Guardar cambios");
            form.btnAgregar.addActionListener(ev -> {
                try {
                    String nombre = form.txtNombre.getText().trim();
                    String materia = form.txtMateria.getText().trim();
                    Double calificacion = Double.parseDouble(form.txtCalificacion.getText().trim());
                    String especialidad = form.txtEspecialidad.getText().trim();

                    // Validar datos
                    validar(seleccionado.getNumeroControl(), nombre, materia, calificacion);

                    // Actualizar objeto
                    seleccionado.setNombre(nombre);
                    seleccionado.setMateria(materia);
                    seleccionado.setCalificacion(calificacion);
                    seleccionado.setEspecialidad(especialidad);

                    // Refrescar tabla
                    refrescarTabla();

                    JOptionPane.showMessageDialog(view, "Alumno modificado correctamente");
                    form.dispose();

                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(form, "Número inválido en los campos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Botón cancelar
            form.btnCancelar.addActionListener(ev -> form.dispose());

            form.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(view, "Seleccione un alumno primero");
        }
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
