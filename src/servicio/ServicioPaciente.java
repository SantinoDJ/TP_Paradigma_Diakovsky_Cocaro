package servicio;

import modelo.Paciente;
import repositorio.IRepositorio;
import java.util.List;

public class ServicioPaciente {
    private IRepositorio<Paciente> repositorio;

    // Constructor que recibe el repositorio (Inyección de dependencias)
    public ServicioPaciente(IRepositorio<Paciente> repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarPaciente(Paciente p) {
        // Validación: Expert y Controller
        if (p.getNombre() != null && p.getCuil() != 0) {
            repositorio.guardar(p);
            System.out.println("✅ Paciente registrado con éxito: " + p.getNombre());
        } else {
            System.out.println("❌ Error: Datos incompletos.");
        }
    }

    public List<Paciente> listarTodos() {
        return repositorio.listarTodos();
    }

    public Paciente buscarPorCuil(Long cuil) {
        return repositorio.buscarPorId(cuil);
    }

    public void eliminarPaciente(Long cuil) {
        repositorio.eliminar(cuil);
    }
}