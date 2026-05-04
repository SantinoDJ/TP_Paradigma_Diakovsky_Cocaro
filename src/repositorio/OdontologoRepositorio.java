package repositorio;

import modelo.Odontologo;
import java.util.ArrayList;
import java.util.List;

public class OdontologoRepositorio implements IRepositorio<Odontologo> {
    private List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public void guardar(Odontologo o) {
        odontologos.add(o);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologos;
    }


    @Override
    public Odontologo buscarPorId(Integer id) {
        for (Odontologo o : odontologos) {
            if (o.getId() != null && o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }
    @Override
    public void eliminar(Integer id) {
        odontologos.removeIf(o -> o.getId().equals(id));
    }
}
