package servicio;

import excepciones.DatoInvalidoException;
import excepciones.TurnoYaReservadoException;
import modelo.Turno;
import repositorio.IRepositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioTurno {

    // Repositorio donde se guardan los turnos
    private IRepositorio<Turno> turnoRepo;

    // Servicio que maneja las operaciones de pacientes
    private ServicioPaciente servicioPaciente;

    // Servicio que maneja las operaciones de odontólogos
    private ServicioOdontologo servicioOdonto;


    // Constructor: recibe los repositorios y servicios necesarios
    public ServicioTurno(IRepositorio<Turno> repo,
                         ServicioPaciente sPaciente,
                         ServicioOdontologo sOdonto) {

        // Guarda el repositorio recibido
        this.turnoRepo = repo;

        // Guarda el servicio de paciente
        this.servicioPaciente = sPaciente;

        // Guarda el servicio de odontólogo
        this.servicioOdonto = sOdonto;
    }


    // Permite registrar/agendar un turno
    public void agendarTurno(Turno t) throws Exception {
        // throws indica que este método puede lanzar excepciones

        // Verifica que el turno no sea nulo
        if (t == null) {

            // throw lanza un error si el dato no es válido
            throw new DatoInvalidoException("El turno no puede ser nulo.");
        }


        // Verifica que el paciente exista
        servicioPaciente.buscarPorCuil(t.getPaciente().getCuil());

        // Verifica que el odontólogo exista
        servicioOdonto.buscarPorId(t.getOdontologo().getId());


        // Busca si el horario ya está ocupado
        boolean ocupado = turnoRepo.listarTodos()

                .stream()
                // stream() convierte la lista en un flujo de datos
                // permite recorrer y procesar los elementos

                .anyMatch(turno ->
                        turno.getFecha().equals(t.getFecha())
                                && turno.getHora().equals(t.getHora())
                                && turno.getOdontologo().getId() == t.getOdontologo().getId()
                );
        // anyMatch() verifica si existe algún elemento que cumpla la condición
        // devuelve true si encuentra un turno igual


        // Si el odontólogo ya tiene un turno en ese horario
        if (ocupado) {

            // Lanza una excepción personalizada
            throw new TurnoYaReservadoException(
                    "El odontólogo ya tiene un turno asignado en ese horario."
            );
        }


        // Guarda el turno si todo está correcto
        turnoRepo.guardar(t);

        System.out.println("✅ Turno agendado con éxito.");
    }



    // Devuelve todos los turnos registrados
    public List<Turno> listarTodosLosTurnos() {
        return turnoRepo.listarTodos();
    }



    // Busca turnos según una fecha
    public List<Turno> buscarTurnosPorFecha(LocalDate fecha) {

        return turnoRepo.listarTodos()

                .stream()
                // Convierte la lista en Stream para poder filtrarla

                .filter(t -> t.getFecha().equals(fecha))
                // filter() deja solamente los elementos que cumplen la condición
                // en este caso, los turnos de la fecha buscada

                .collect(Collectors.toList());
        // Convierte el resultado del Stream nuevamente en una lista
    }



    // Busca turnos de un paciente específico
    public List<Turno> buscarTurnosPorPaciente(Long cuilPaciente) {

        return turnoRepo.listarTodos()

                .stream()
                // Convierte la lista en Stream

                .filter(t -> t.getPaciente().getCuil() == cuilPaciente)
                // Filtra los turnos comparando el CUIL del paciente

                .collect(Collectors.toList());
        // Devuelve una lista con los resultados
    }



    // Busca turnos de un odontólogo específico
    public List<Turno> buscarTurnosPorOdontologo(Long idOdontologo) {

        return turnoRepo.listarTodos()

                .stream()
                // Convierte la lista en Stream

                .filter(t -> t.getOdontologo().getId() == idOdontologo)
                // Filtra comparando el ID del odontólogo

                .collect(Collectors.toList());
        // Convierte el resultado nuevamente a una lista
    }
}