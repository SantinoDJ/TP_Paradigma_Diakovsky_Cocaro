package repositorio;
import modelo.Paciente;
import java.util.*;

public class PacienteRepositorio implements IRepositorio<Paciente> {
    // Usamos Long para la llave (el CUIL) y Paciente para el valor
    private Map<Long, Paciente> pacientes = new HashMap<>();

    @Override
    public void guardar(Paciente p) {
        pacientes.put(p.getCuil(), p); // El CUIL actúa como nuestro identificador único
    }

    @Override
    public Paciente buscarPorId(Long id) {
        return pacientes.get(id); // Buscamos directamente en el HashMap usando el CUIL
    }

    @Override
    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes.values());
    }

    @Override
    public void eliminar(Long id) {
        pacientes.remove(id);
    }

    @Override
    public void actualizar(Paciente p) {
        pacientes.put(p.getCuil(), p);
    }
}