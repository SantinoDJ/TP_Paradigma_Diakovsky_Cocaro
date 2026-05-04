package servicio;

import modelo.Odontologo;
import repositorio.IRepositorio;
import java.util.List;

public class ServicioOdontologo {
    private IRepositorio<Odontologo> odontoRepo;

    public ServicioOdontologo(IRepositorio<Odontologo> repo) {
        this.odontoRepo = repo;
    }

    public void registrarOdontologo(Odontologo o) {
        // Validación: El odontólogo DEBE tener matrícula
        if (o.getMatricula() != null && !o.getMatricula().isEmpty()) {
            odontoRepo.guardar(o);
            System.out.println("✅ Odontólogo registrado: " + o.getNombre() + " (Matrícula: " + o.getMatricula() + ")");
        } else {
            System.out.println("❌ Error: La matrícula es obligatoria para el odontólogo.");
        }
    }

    public List<Odontologo> listarOdontologos() {
        return odontoRepo.buscarTodos();
    }

    public Odontologo buscarPorId(Integer id) {
        return odontoRepo.buscarPorId(id);
    }
}