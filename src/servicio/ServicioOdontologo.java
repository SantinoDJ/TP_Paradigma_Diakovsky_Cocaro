package servicio;

import excepciones.DatoInvalidoException;
import excepciones.OdontologoNoEncontradoException;
import modelo.Odontologo;
import repositorio.IRepositorio;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioOdontologo {

    // Repositorio que almacena los odontólogos
    private IRepositorio<Odontologo> odontoRepo;

    // Constructor: recibe el repositorio y lo guarda en el atributo
    public ServicioOdontologo(IRepositorio<Odontologo> repo) {
        this.odontoRepo = repo;
    }

    // Registra un odontólogo validando los datos antes de guardarlo
    public void registrarOdontologo(Odontologo o) throws DatoInvalidoException {
        // throws indica que este método puede lanzar una excepción

        // Verifica que el odontólogo exista
        if (o == null) {
            // throw lanza una excepción cuando hay un error
            throw new DatoInvalidoException("El odontólogo no puede ser nulo.");
        }

        // Verifica que tenga nombre
        if (o.getNombre() == null || o.getNombre().isBlank()) {
            throw new DatoInvalidoException("El nombre del odontólogo es obligatorio.");
        }

        // Verifica que tenga matrícula
        if (o.getMatricula() == null || o.getMatricula().isBlank()) {
            throw new DatoInvalidoException("La matrícula es obligatoria para el odontólogo.");
        }

        // Guarda el odontólogo en el repositorio
        odontoRepo.guardar(o);

        System.out.println("✅ Odontólogo registrado: " + o.getNombre()
                + " (Matrícula: " + o.getMatricula() + ")");
    }

    // Devuelve la lista completa de odontólogos
    public List<Odontologo> listarOdontologos() {
        return odontoRepo.listarTodos();
    }

    // Devuelve los odontólogos ordenados por nombre
    public List<Odontologo> listarOrdenadosPorNombre() {

        return odontoRepo.listarTodos()

                .stream()
                // stream() convierte la lista en un flujo de datos
                // para poder aplicar operaciones como ordenar o filtrar

                .sorted(Comparator.comparing(Odontologo::getNombre))
                // sorted() ordena los elementos
                // Comparator.comparing() indica que el criterio de orden
                // será el nombre del odontólogo

                .collect(Collectors.toList());
        // collect() toma el resultado del Stream
        // y lo convierte nuevamente en una List
    }

    // Elimina un odontólogo por ID
    public void eliminarOdontologo(long id) throws OdontologoNoEncontradoException {

        // Busca el odontólogo para verificar que exista
        Odontologo o = buscarPorId(id);

        // Elimina el odontólogo usando su ID
        odontoRepo.eliminar(o.getId());
    }

    // Busca un odontólogo por ID
    public Odontologo buscarPorId(long id) throws OdontologoNoEncontradoException {

        // Busca en el repositorio
        Odontologo odontologo = odontoRepo.buscarPorId(id);

        // Si no existe, lanza una excepción
        if (odontologo == null) {
            throw new OdontologoNoEncontradoException(
                    "No existe un odontólogo con ID: " + id);
        }

        // Devuelve el odontólogo encontrado
        return odontologo;
    }
}