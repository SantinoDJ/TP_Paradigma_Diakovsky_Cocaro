package servicio;

import excepciones.DatoInvalidoException;
import excepciones.OdontologoNoEncontradoException;
import modelo.Odontologo;
import repositorio.IRepositorio;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


// Clase que contiene la lógica de los odontólogos
public class ServicioOdontologo {

    private IRepositorio<Odontologo> odontoRepo; // Repositorio donde se guardan los odontólogos


    // Constructor que recibe el repositorio
    public ServicioOdontologo(IRepositorio<Odontologo> repo) {
        this.odontoRepo = repo; // Guarda el repositorio recibido
    }


    // Registra un odontólogo nuevo
    public void registrarOdontologo(Odontologo o) throws DatoInvalidoException {

        if (o == null) { // Verifica que el odontólogo no sea vacío
            throw new DatoInvalidoException("El odontólogo no puede ser nulo.");
        }

        if (o.getNombre() == null || o.getNombre().isBlank()) { // Valida que tenga nombre
            throw new DatoInvalidoException("El nombre del odontólogo es obligatorio.");
        }

        if (o.getMatricula() == null || o.getMatricula().isBlank()) { // Valida que tenga matrícula
            throw new DatoInvalidoException("La matrícula es obligatoria para el odontólogo.");
        }

        odontoRepo.guardar(o); // Guarda el odontólogo en el repositorio

        System.out.println("✅ Odontólogo registrado: " + o.getNombre()
                + " (Matrícula: " + o.getMatricula() + ")");
    }


    // Devuelve todos los odontólogos registrados
    public List<Odontologo> listarOdontologos() {
        return odontoRepo.listarTodos();
    }


    // Devuelve la lista ordenada por nombre
    public List<Odontologo> listarOrdenadosPorNombre() {

        return odontoRepo.listarTodos()
                .stream() // Convierte la lista en un flujo para procesarla
                .sorted(Comparator.comparing(Odontologo::getNombre)) // Ordena a los odontologos usando el nombre
                .collect(Collectors.toList()); // Vuelve a convertirlo en una lista
    }


    // Elimina un odontólogo por ID
    public void eliminarOdontologo(long id) throws OdontologoNoEncontradoException {

        Odontologo o = buscarPorId(id); // Busca primero si existe

        odontoRepo.eliminar(o.getId()); // Elimina el odontólogo encontrado
    }



    // Busca un odontólogo por su ID
    public Odontologo buscarPorId(long id) throws OdontologoNoEncontradoException {

        Odontologo odontologo = odontoRepo.buscarPorId(id); // Busca en el repositorio


        if (odontologo == null) { // Si no encuentra nada lanza error
            throw new OdontologoNoEncontradoException(
                    "No existe un odontólogo con ID: " + id
            );
        }

        return odontologo; // Devuelve el odontólogo encontrado
    }
}