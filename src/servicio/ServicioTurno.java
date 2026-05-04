package servicio;

import modelo.Turno;
import repositorio.IRepositorio;
import java.util.List;

public class ServicioTurno {
    private IRepositorio<Turno> turnoRepo;
    private ServicioPaciente servicioPaciente;
    private ServicioOdontologo servicioOdonto;


    public ServicioTurno(IRepositorio<Turno> repo, ServicioPaciente sPaciente, ServicioOdontologo sOdonto) {
        this.turnoRepo = repo;
        this.servicioPaciente = sPaciente;
        this.servicioOdonto = sOdonto;
    }


    public void agendarTurno(Turno t) {

        Object p = servicioPaciente.buscarPorId(t.getPaciente().getId());

        Object o = servicioOdonto.buscarPorId(t.getOdontologo().getId());

        if (p != null && o != null) {
            turnoRepo.guardar(t);
            System.out.println("✅ Turno agendado con éxito.");
        } else {

            System.out.println("❌ Error: No se puede dar el turno porque el paciente o el médico no existen.");
        }
    }

    public List<Turno> listarTodosLosTurnos() {
        return turnoRepo.buscarTodos();
    }
}
