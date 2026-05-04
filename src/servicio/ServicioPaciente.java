package servicio;

import modelo.Paciente;
import repositorio.IRepositorio;
import java.util.List;

public class ServicioPaciente {
    // Esta es la conexión con el archivero (repositorio)
    private IRepositorio<Paciente> pacienteRepo;


    public ServicioPaciente(IRepositorio<Paciente> repo) {
        this.pacienteRepo = repo;
    }


    public void registrarPaciente(Paciente p) {

        if (p.getNombre() != null && !p.getNombre().isEmpty() && p.getDni() != null) {
            pacienteRepo.guardar(p);
            System.out.println("✅ Paciente registrado: " + p.getNombre() + " " + p.getApellido());
        } else {
            System.out.println("❌ Error: No se puede registrar un paciente sin nombre o DNI.");
        }
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepo.buscarTodos();
    }

    public Paciente buscarPorId(Integer id) {
        return pacienteRepo.buscarPorId(id);
    }
}
