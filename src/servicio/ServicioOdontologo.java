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

    public ServicioOdontologo(IRepositorio<Odontologo> repo) {
        this.odontoRepo = repo;
    }

    public void registrarOdontologo(Odontologo o) throws DatoInvalidoException {

        if (o == null) {
            throw new DatoInvalidoException("El odontólogo no puede ser nulo.");
        }

        if (o.getNombre() == null || o.getNombre().isBlank()) {
            throw new DatoInvalidoException("El nombre del odontólogo es obligatorio.");
        }

        if (o.getMatricula() == null || o.getMatricula().isBlank()) {
            throw new DatoInvalidoException("La matrícula es obligatoria para el odontólogo.");
        }

        odontoRepo.guardar(o);
        System.out.println("✅ Odontólogo registrado: " + o.getNombre()
                + " (Matrícula: " + o.getMatricula() + ")");
    }

    public List<Odontologo> listarOdontologos() {
        return odontoRepo.listarTodos();
    }

    public List<Odontologo> listarOrdenadosPorNombre() {
        return odontoRepo.listarTodos()
                .stream()
                .sorted(Comparator.comparing(Odontologo::getNombre))
                .collect(Collectors.toList());
    }

    public void eliminarOdontologo(long id) throws OdontologoNoEncontradoException {

        Odontologo o = buscarPorId(id);

        odontoRepo.eliminar(o.getId());

    }



    public Odontologo buscarPorId(long id) throws OdontologoNoEncontradoException {
        Odontologo odontologo = odontoRepo.buscarPorId(id);

        if (odontologo == null) {
            throw new OdontologoNoEncontradoException("No existe un odontólogo con ID: " + id);
        }

        return odontologo;
    }
}