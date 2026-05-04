package repositorio;

import modelo.Turno;
import java.util.*;

public class TurnoRepositorio implements IRepositorio<Turno> {
    // Usamos una lista porque el profe dijo que para turnos podía ser ArrayList
    private List<Turno> turnos = new ArrayList<>();

    @Override
    public void guardar(Turno t) {
        turnos.add(t);
    }

    @Override
    public Turno buscarPorId(Long id) {
        // Buscamos el turno que coincida con el ID
        return turnos.stream()
                .filter(t -> Long.valueOf(t.getId()).equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnos;
    }

    @Override
    public void eliminar(Long id) {
        turnos.removeIf(t -> Long.valueOf(t.getId()).equals(id));
    }

    @Override
    public void actualizar(Turno t) {
        // Buscamos si existe y lo reemplazamos
        for (int i = 0; i < turnos.size(); i++) {
            if (turnos.get(i).getId() == t.getId()) {
                turnos.set(i, t);
                break;
            }
        }
    }
}