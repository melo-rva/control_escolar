package model;

public class Producto {
    private Double NumeroControl;
    private String nombre;
    private String materia;
    private Double calificacion;
    private String especialidad;

    public Producto(double NumeroControl, String nombre, String materia, Double calificacion, String especialidad) {
        this.NumeroControl = NumeroControl;
        this.nombre = nombre;
        this.materia = materia;
        this.calificacion = calificacion;
        this.especialidad = especialidad;
    }

    public Double getNumeroControl() { return NumeroControl; }
    public String getNombre() { return nombre; }
    public String getMateria() { return materia; }
    public double getCalificacion() { return calificacion; }
    public String getEspecialidad() { return especialidad; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMateria(String materia) { this.materia = materia; }
    public void setCalificacion(Double calificacion) { this.calificacion = calificacion; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}