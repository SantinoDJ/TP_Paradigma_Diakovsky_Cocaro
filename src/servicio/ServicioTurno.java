package servicio; // Paquete de la capa de servicio.

import excepciones.DatoInvalidoException; // Excepción por datos inválidos.
import excepciones.TurnoYaReservadoException; // Excepción si el turno ya está ocupado.
import modelo.Turno; // Importa la clase Turno.
import repositorio.IRepositorio; // Importa la interfaz del repositorio.

import java.time.LocalDate; // Permite usar fechas.
import java.util.List; // Permite usar listas.
import java.util.stream.Collectors; // Convierte un Stream en una lista.

public class ServicioTurno {

    private IRepositorio<Turno> turnoRepo;
    // Repositorio de turnos.

    private ServicioPaciente servicioPaciente;
    // Servicio de pacientes.

    private ServicioOdontologo servicioOdonto;
    // Servicio de odontólogos.

    public ServicioTurno(IRepositorio<Turno> repo,
                         ServicioPaciente sPaciente,
                         ServicioOdontologo sOdonto) {
        // Constructor.

        this.turnoRepo = repo;
        // Inicializa el repositorio.

        this.servicioPaciente = sPaciente;
        // Inicializa el servicio de pacientes.

        this.servicioOdonto = sOdonto;
        // Inicializa el servicio de odontólogos.
    }

    public void agendarTurno(Turno t) throws Exception {
        // Agenda un turno.

        if (t == null) {
            // Verifica que el turno no sea nulo.
            throw new DatoInvalidoException("El turno no puede ser nulo.");
        }

        servicioPaciente.buscarPorCuil(t.getPaciente().getCuil());
        // Verifica que exista el paciente.

        servicioOdonto.buscarPorId(t.getOdontologo().getId());
        // Verifica que exista el odontólogo.

        boolean ocupado = turnoRepo.listarTodos()
                .stream() // Recorre la lista de turnos
                .anyMatch(turno -> // Lambda: revisa cada turno y devuelve true o false.
                        turno.getFecha().equals(t.getFecha()) // Compara la fecha.
                                && turno.getHora().equals(t.getHora())  // Compara la hora.
                                && turno.getOdontologo().getId() == t.getOdontologo().getId() // Compara si es el mismo odontólogo.
                );
        // Verifica si ya existe un turno en ese horario.

        if (ocupado) {
            // Si está ocupado, lanza una excepción.
            throw new TurnoYaReservadoException(
                    "El odontólogo ya tiene un turno asignado en ese horario."
            );
        }

        turnoRepo.guardar(t);
        // Guarda el turno.

        System.out.println("✅ Turno agendado con éxito.");
        // Muestra un mensaje.
    }

    public List<Turno> listarTodosLosTurnos() {
        // Devuelve todos los turnos.
        return turnoRepo.listarTodos();
    }

    public List<Turno> buscarTurnosPorFecha(LocalDate fecha) {
        // Busca turnos por fecha.

        return turnoRepo.listarTodos()
                .stream() // Recorre la lista.
                .filter(t -> t.getFecha().equals(fecha))
                // Filtra por fecha.
                .collect(Collectors.toList()); // Devuelve una lista.

    }

    public List<Turno> buscarTurnosPorPaciente(Long cuilPaciente) {
        // Busca turnos de un paciente.

        return turnoRepo.listarTodos()
                .stream() // Recorre la lista.
                .filter(t -> t.getPaciente().getCuil() == cuilPaciente)
                // Filtra por CUIL.
                .collect(Collectors.toList());  // Devuelve una lista.

    }

    public List<Turno> buscarTurnosPorOdontologo(Long idOdontologo) {
        // Busca turnos de un odontólogo.

        return turnoRepo.listarTodos()
                .stream() // Recorre la lista.
                .filter(t -> t.getOdontologo().getId() == idOdontologo)
                // Filtra por ID.
                .collect(Collectors.toList()); // Devuelve una lista.

    }
}