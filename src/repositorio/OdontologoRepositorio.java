package repositorio;

import modelo.Odontologo;
import util.Persistencia;

import java.util.*;

// Clase para manejar los odontólogos y guardarlos en un archivo
public class OdontologoRepositorio implements IRepositorio<Odontologo> {

    private Map<Long, Odontologo> odontologos;

    public OdontologoRepositorio() {
        // Al arrancar, carga los odontólogos desde el archivo
        Object datos = Persistencia.cargar("odontologos.dat");

        if(datos != null){
            odontologos = (Map<Long, Odontologo>) datos;
        } else {
            odontologos = new HashMap<>();
        }
    }

    @Override
    public void guardar(Odontologo o) {
        // Agrega el odontólogo y actualiza el archivo
        odontologos.put(o.getId(), o);
        Persistencia.guardar(odontologos, "odontologos.dat");
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        // Busca un odontólogo por su ID
        return odontologos.get(id);
    }

    @Override
    public List<Odontologo> listarTodos() {
        // Devuelve todos los odontólogos en una lista
        return new ArrayList<>(odontologos.values());
    }

    @Override
    public void eliminar(Long id) {
        // Borra al odontólogo y actualiza el archivo
        odontologos.remove(id);
        Persistencia.guardar(odontologos, "odontologos.dat");
    }

    @Override
    public void actualizar(Odontologo o) {
        // Cambia los datos de un odontólogo y guarda los cambios
        odontologos.put(o.getId(), o);
        Persistencia.guardar(odontologos, "odontologos.dat");
    }
}