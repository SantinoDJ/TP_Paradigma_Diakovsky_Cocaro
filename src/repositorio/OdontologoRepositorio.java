package repositorio;

import modelo.Odontologo;
import java.util.*;

public class OdontologoRepositorio implements IRepositorio<Odontologo> {
    // Usamos Long para que coincida con IRepositorio
    private Map<Long, Odontologo> odontologos = new HashMap<>();

    @Override
    public void guardar(Odontologo o) {
        // Convertimos el id (Integer) a Long para guardarlo en el mapa
        odontologos.put(Long.valueOf(o.getId()), o);
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        return odontologos.get(id);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return new ArrayList<>(odontologos.values());
    }

    @Override
    public void eliminar(Long id) {
        odontologos.remove(id);
    }

    @Override
    public void actualizar(Odontologo o) {
        odontologos.put(Long.valueOf(o.getId()), o);
    }
}