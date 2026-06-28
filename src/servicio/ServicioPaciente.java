package servicio;

import excepciones.DatoInvalidoException;
import excepciones.PacienteNoEncontradoException;
import modelo.Paciente;
import repositorio.IRepositorio;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


// Clase que contiene la lógica de los pacientes
public class ServicioPaciente {

    private IRepositorio<Paciente> repositorio; // Repositorio donde se guardan los pacientes


    // Constructor que recibe el repositorio
    public ServicioPaciente(IRepositorio<Paciente> repositorio) {
        this.repositorio = repositorio; // Guarda el repositorio recibido
    }


    // Registra un paciente nuevo
    public void registrarPaciente(Paciente p) throws DatoInvalidoException {

        if (p == null) { // Verifica que el paciente no sea vacío
            throw new DatoInvalidoException("El paciente no puede ser nulo.");
        }

        if (p.getNombre() == null || p.getNombre().isBlank()) { // Controla que tenga nombre
            throw new DatoInvalidoException("El nombre del paciente es obligatorio.");
        }

        if (p.getCuil() == 0){ // Controla que tenga CUIL
            throw new DatoInvalidoException("El CUIL del paciente es obligatorio.");
        }

        repositorio.guardar(p); // Guarda el paciente en el repositorio

        System.out.println("✅ Paciente registrado con éxito: " + p.getNombre());
    }


    // Devuelve todos los pacientes registrados
    public List<Paciente> listarTodos() {
        return repositorio.listarTodos();
    }


    // Devuelve los pacientes ordenados por nombre
    public List<Paciente> listarOrdenadosPorNombre() {

        return repositorio.listarTodos()
                .stream() // Convierte la lista en un flujo para procesarla
                .sorted(Comparator.comparing(Paciente::getNombre)) // Ordena usando el nombre del paciente
                .collect(Collectors.toList()); // Convierte nuevamente a lista
    }


    // Busca un paciente usando su CUIL
    public Paciente buscarPorCuil(Long cuil) throws PacienteNoEncontradoException {

        Paciente paciente = repositorio.buscarPorId(cuil); // Busca el paciente en el repositorio


        if (paciente == null) { // Si no existe lanza excepción
            throw new PacienteNoEncontradoException("No existe un paciente con CUIL: " + cuil);
        }


        return paciente; // Devuelve el paciente encontrado
    }


    // Elimina un paciente por CUIL
    public void eliminarPaciente(Long cuil) throws PacienteNoEncontradoException {

        Paciente paciente = buscarPorCuil(cuil); // Busca primero si existe

        repositorio.eliminar(paciente.getCuil()); // Elimina el paciente del repositorio
    }
}