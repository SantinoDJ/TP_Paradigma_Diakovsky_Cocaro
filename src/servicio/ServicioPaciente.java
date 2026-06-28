package servicio; // Paquete de la capa de servicio.

import excepciones.DatoInvalidoException; // Excepción por datos inválidos.
import excepciones.PacienteNoEncontradoException; // Excepción si no existe el paciente.
import modelo.Paciente; // Importa la clase Paciente.
import repositorio.IRepositorio; // Importa la interfaz del repositorio.

import java.util.Comparator; // Permite ordenar.
import java.util.List; // Permite usar listas.
import java.util.stream.Collectors; // Convierte el Stream en una lista.

public class ServicioPaciente {

    private IRepositorio<Paciente> repositorio;
    // Repositorio de pacientes.

    public ServicioPaciente(IRepositorio<Paciente> repositorio) {
        // Constructor.
        this.repositorio = repositorio;
        // Inicializa el repositorio.
    }

    public void registrarPaciente(Paciente p) throws DatoInvalidoException {
        // Registra un paciente.

        if (p == null) {
            // Verifica que no sea nulo.
            throw new DatoInvalidoException("El paciente no puede ser nulo.");
        }

        if (p.getNombre() == null || p.getNombre().isBlank()) {
            // Verifica que tenga nombre.
            throw new DatoInvalidoException("El nombre del paciente es obligatorio.");
        }

        if (p.getCuil() == 0){
            // Verifica que tenga CUIL.
            throw new DatoInvalidoException("El CUIL del paciente es obligatorio.");
        }

        repositorio.guardar(p);
        // Guarda el paciente.

        System.out.println("✅ Paciente registrado con éxito: " + p.getNombre());
        // Muestra un mensaje.
    }

    public List<Paciente> listarTodos() {
        // Devuelve todos los pacientes.
        return repositorio.listarTodos();
    }

    public List<Paciente> listarOrdenadosPorNombre() {
        // Devuelve los pacientes ordenados por nombre.
        return repositorio.listarTodos()
                .stream() // Recorre la lista para trabajar con la lista
                .sorted(Comparator.comparing(Paciente::getNombre))
                // Ordena a los odontologos por el atributo nombre
                .collect(Collectors.toList()); // Guarda el resultado ordenado en una nueva lista

    }

    public Paciente buscarPorCuil(Long cuil) throws PacienteNoEncontradoException {
        // Busca un paciente por CUIL.

        Paciente paciente = repositorio.buscarPorId(cuil);
        // Busca en el repositorio.

        if (paciente == null) {
            // Verifica si existe.
            throw new PacienteNoEncontradoException("No existe un paciente con CUIL: " + cuil);
        }

        return paciente;
        // Devuelve el paciente.
    }

    public void eliminarPaciente(Long cuil) throws PacienteNoEncontradoException {
        // Elimina un paciente.

        Paciente paciente = buscarPorCuil(cuil);
        // Verifica que exista.

        repositorio.eliminar(paciente.getCuil());
        // Lo elimina.
    }
}