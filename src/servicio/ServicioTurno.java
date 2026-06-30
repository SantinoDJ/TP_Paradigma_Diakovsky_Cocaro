package servicio;

import excepciones.DatoInvalidoException;
import excepciones.TurnoYaReservadoException;
import modelo.Turno;
import repositorio.IRepositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


// Clase que maneja la lógica de los turnos
public class ServicioTurno {


    private IRepositorio<Turno> turnoRepo; // Repositorio donde se guardan los turnos

    private ServicioPaciente servicioPaciente; // Servicio para validar pacientes

    private ServicioOdontologo servicioOdonto; // Servicio para validar odontólogos



    // Constructor que recibe repositorio y servicios necesarios
    public ServicioTurno(IRepositorio<Turno> repo,
                         ServicioPaciente sPaciente,
                         ServicioOdontologo sOdonto) {


        this.turnoRepo = repo; // Guarda el repositorio de turnos

        this.servicioPaciente = sPaciente; // Guarda servicio de pacientes

        this.servicioOdonto = sOdonto; // Guarda servicio de odontólogos
    }



    // Crea y guarda un nuevo turno
    public void agendarTurno(Turno t) throws Exception {


        if (t == null) { // Verifica que el turno no sea vacío

            throw new DatoInvalidoException("El turno no puede ser nulo.");

        }


        // Verifica que el paciente exista
        servicioPaciente.buscarPorCuil(t.getPaciente().getCuil());


        // Verifica que el odontólogo exista
        servicioOdonto.buscarPorId(t.getOdontologo().getId());



        // Busca si ya existe un turno en ese horario
        boolean ocupado = turnoRepo.listarTodos()

                .stream() // Convierte la lista de turnos en un stream para poder filtrarla

                .anyMatch(turno -> // Devuelve true si encuentra coincidencia

                        turno.getFecha().equals(t.getFecha()) // Compara si la fecha del turno existente es igual a la nueva fecha

                                && turno.getHora().equals(t.getHora())  // Compara si la hora del turno existente es igual a la nueva hora

                                && turno.getOdontologo().getId() == t.getOdontologo().getId()  // Compara si el odontólogo es el mismo usando su ID


                );


        if (ocupado) { // Si el horario ya está ocupado lanza error

            throw new TurnoYaReservadoException(
                    "El odontólogo ya tiene un turno asignado en ese horario."
            );

        }



        turnoRepo.guardar(t); // Guarda el turno en el repositorio


        System.out.println("✅ Turno agendado con éxito.");

    }




    // Devuelve todos los turnos registrados
    public List<Turno> listarTodosLosTurnos() {

        return turnoRepo.listarTodos();

    }




    // Busca turnos por una fecha específica
    public List<Turno> buscarTurnosPorFecha(LocalDate fecha) {


        return turnoRepo.listarTodos()

                .stream() // Convierte la lista en stream

                .filter(t -> t.getFecha().equals(fecha)) // Filtra por fecha

                .collect(Collectors.toList()); // Convierte nuevamente el stream a lista

    }




    // Busca turnos de un paciente usando su CUIL
    public List<Turno> buscarTurnosPorPaciente(Long cuilPaciente) {


        return turnoRepo.listarTodos()

                .stream() // Permite recorrer y filtrar la lista

                .filter(t -> t.getPaciente().getCuil() == cuilPaciente) // Filtra por CUIL

                .collect(Collectors.toList()); // Devuelve una lista con resultados

    }




    // Busca turnos de un odontólogo usando su ID
    public List<Turno> buscarTurnosPorOdontologo(Long idOdontologo) {


        return turnoRepo.listarTodos()

                .stream() // Convierte la lista en stream para procesarla

                .filter(t -> t.getOdontologo().getId() == idOdontologo) // Filtra por ID

                .collect(Collectors.toList()); // Convierte resultado a lista

    }

}