package servicio;

import excepciones.DatoInvalidoException;
import excepciones.OdontologoNoEncontradoException;
import modelo.Odontologo;
import repositorio.IRepositorio;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioOdontologo {

    private IRepositorio<Odontologo> odontoRepo;
    // Repositorio donde se guardan los odontólogos.

    public ServicioOdontologo(IRepositorio<Odontologo> repo) {
        // Constructor.
        this.odontoRepo = repo;
        // Inicializa el repositorio.
    }

    public void registrarOdontologo(Odontologo o) throws DatoInvalidoException {
        // Registra un odontólogo.

        if (o == null) {
            // Verifica que el objeto no sea nulo.
            throw new DatoInvalidoException("El odontólogo no puede ser nulo.");
        }

        if (o.getNombre() == null || o.getNombre().isBlank()) {
            // Verifica que tenga nombre.
            throw new DatoInvalidoException("El nombre del odontólogo es obligatorio.");
        }

        if (o.getMatricula() == null || o.getMatricula().isBlank()) {
            // Verifica que tenga matrícula.
            throw new DatoInvalidoException("La matrícula es obligatoria para el odontólogo.");
        }

        odontoRepo.guardar(o);
        // Guarda el odontólogo.

        System.out.println("✅ Odontólogo registrado: " + o.getNombre()
                + " (Matrícula: " + o.getMatricula() + ")");
        // Muestra un mensaje de confirmación.
    }

    public List<Odontologo> listarOdontologos() {
        // Devuelve todos los odontólogos.
        return odontoRepo.listarTodos();
    }

    public List<Odontologo> listarOrdenadosPorNombre() {
        // Devuelve los odontólogos ordenados por nombre.
        return odontoRepo.listarTodos()
                .stream() // Recorre la lista para trabajar con la lista
                .sorted(Comparator.comparing(Odontologo::getNombre))
                // Ordena los odontólogos por el atributo nombre
                .collect(Collectors.toList());  // Guarda el resultado ordenado en una nueva lista

    }

    public void eliminarOdontologo(long id) throws OdontologoNoEncontradoException {
        // Elimina un odontólogo.

        Odontologo o = buscarPorId(id);
        // Verifica que exista.

        odontoRepo.eliminar(o.getId());
        // Lo elimina.
    }

    public Odontologo buscarPorId(long id) throws OdontologoNoEncontradoException {
        // Busca un odontólogo por ID.

        Odontologo odontologo = odontoRepo.buscarPorId(id);
        // Busca en el repositorio.

        if (odontologo == null) {
            // Verifica si existe.
            throw new OdontologoNoEncontradoException("No existe un odontólogo con ID: " + id);
        }

        return odontologo;
        // Devuelve el odontólogo encontrado.
    }
}