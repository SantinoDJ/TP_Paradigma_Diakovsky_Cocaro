package repositorio;

import modelo.Paciente;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepositorio implements IRepositorio<Paciente> {
    private List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void guardar(Paciente p) {
        pacientes.add(p);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacientes;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        for (Paciente p : pacientes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        pacientes.removeIf(p -> p.getId().equals(id));
    }
}