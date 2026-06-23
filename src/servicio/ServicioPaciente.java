package servicio;

import excepciones.DatoInvalidoException;
import excepciones.PacienteNoEncontradoException;
import modelo.Paciente;
import repositorio.IRepositorio;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioPaciente {

    // Repositorio donde se almacenan los pacientes
    private IRepositorio<Paciente> repositorio;

    // Constructor: recibe el repositorio y lo guarda
    public ServicioPaciente(IRepositorio<Paciente> repositorio) {
        this.repositorio = repositorio;
    }

    // Registra un paciente realizando validaciones
    public void registrarPaciente(Paciente p) throws DatoInvalidoException {
        // throws indica que este método puede lanzar una excepción

        // Verifica que el paciente exista
        if (p == null) {
            // throw lanza una excepción cuando hay un error
            throw new DatoInvalidoException("El paciente no puede ser nulo.");
        }

        // Verifica que tenga nombre
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new DatoInvalidoException("El nombre del paciente es obligatorio.");
        }

        // Verifica que tenga CUIL
        if (p.getCuil() == 0){
            throw new DatoInvalidoException("El CUIL del paciente es obligatorio.");
        }

        // Guarda el paciente en el repositorio
        repositorio.guardar(p);

        System.out.println("✅ Paciente registrado con éxito: " + p.getNombre());
    }

    // Devuelve todos los pacientes registrados
    public List<Paciente> listarTodos() {
        return repositorio.listarTodos();
    }

    // Devuelve los pacientes ordenados alfabéticamente por nombre
    public List<Paciente> listarOrdenadosPorNombre() {
        return repositorio.listarTodos()

                .stream()
                // stream() convierte la lista en un flujo de datos
                // para poder aplicar operaciones como ordenar o filtrar

                .sorted(Comparator.comparing(Paciente::getNombre))
                // sorted() ordena los elementos
                // Comparator.comparing() indica que el criterio de orden
                // será el nombre del paciente

                .collect(Collectors.toList());
        // collect() toma el resultado del Stream
        // y lo convierte nuevamente en una List
    }

    // Busca un paciente utilizando su CUIL
    public Paciente buscarPorCuil(Long cuil) throws PacienteNoEncontradoException {

        // Busca el paciente en el repositorio
        Paciente paciente = repositorio.buscarPorId(cuil);

        // Si no existe, lanza una excepción
        if (paciente == null) {
            throw new PacienteNoEncontradoException(
                    "No existe un paciente con CUIL: " + cuil);
        }

        // Devuelve el paciente encontrado
        return paciente;
    }

    // Elimina un paciente por su CUIL
    public void eliminarPaciente(Long cuil) throws PacienteNoEncontradoException {

        // Busca primero el paciente para verificar que exista
        Paciente paciente = buscarPorCuil(cuil);

        // Elimina el paciente usando su CUIL
        repositorio.eliminar(paciente.getCuil());
    }
}