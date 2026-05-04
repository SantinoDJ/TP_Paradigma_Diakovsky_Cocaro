package repositorio;

import modelo.Odontologo;
import modelo.Turno;
import java.util.ArrayList;
import java.util.List;

public class TurnoRepositorio implements IRepositorio<Turno> {
    private List<Turno> turnos = new ArrayList<>();

    @Override
    public void guardar(Turno t) {
        turnos.add(t);
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        for (Turno o : turnos) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        turnos.removeIf(t -> t.getId().equals(id));
    }
}
