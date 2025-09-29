package model;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioArreglo {
    private final List<Producto> data = new ArrayList<>();

    public List<Producto> findAll() {
        return new ArrayList<>(data);
    }

    public Producto findByClave(int NumeroControl) {
        for (Producto p : data) {
            if (p.getNumeroControl() == (NumeroControl)) return p; // usa equals()
        }
        return null;
    }

    // Crea si no existe; si existe, lanza excepción
    public void create(Producto p) {
        if (findByClave(p.getNumeroControl()) != null) {
            throw new IllegalArgumentException("La clave ya existe: " + p.getNumeroControl());
        }
        data.add(p);
    }

    // Actualiza si existe; si no existe, lanza excepción
    public void update(Producto p) {
        Producto existing = findByClave(p.getNumeroControl());
        if (existing == null) throw new IllegalArgumentException("No existe clave: " + p.getNumeroControl());
        existing.setNombre(p.getNombre());
        existing.setMateria(p.getMateria());
        existing.setCalificacion(p.getCalificacion());
        existing.setEspecialidad(p.getEspecialidad());
    }

    public void deleteByClave(int numeroControl) {
        data.removeIf(p -> p.getNumeroControl() == (numeroControl));
    }





}







