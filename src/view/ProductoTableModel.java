package view;


import model.Producto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductoTableModel extends AbstractTableModel {
    private final String[] cols = {"Numero_Control","Nombre","Materia","Calificacion","Especialidad"};
    private List<Producto> rows;

    public ProductoTableModel(List<Producto> data) {
        this.rows = data;
    }

    public void setData(List<Producto> data) {
        this.rows = data;
        fireTableDataChanged();
    }

    public Producto getAt(int row) { return rows.get(row); }

    @Override public int getRowCount() { return rows == null ? 0 : rows.size(); }
    @Override public int getColumnCount() { return cols.length; }
    @Override public String getColumnName(int column) { return cols[column]; }

    @Override public Object getValueAt(int rowIndex, int columnIndex) {
        Producto p = rows.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getNumeroControl();
            case 1 -> p.getNombre();
            case 2 -> p.getMateria();
            case 3 -> p.getCalificacion();
            case 4 -> p.getEspecialidad();
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;   // Numero_Control
            case 1 -> String.class;   // Nombre
            case 2 -> String.class;   // Materia
            case 3 -> Double.class;   // Calificacion
            case 4 -> String.class;   // Especialidad
            default -> Object.class;
        };
    }




    //   @Override public boolean isCellEditable(int r, int c) { return false; }
}

