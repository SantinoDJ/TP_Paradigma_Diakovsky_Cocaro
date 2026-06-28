package repositorio;

import modelo.Paciente;
import util.Persistencia;

import java.util.*;

public class PacienteRepositorio implements IRepositorio<Paciente> {
    // Repositorio para pacientes.

    private Map<Long, Paciente> pacientes;
    // Guarda los pacientes usando el CUIL como clave.

    public PacienteRepositorio(){
        // Constructor.

        Object datos = Persistencia.cargar("pacientes.dat");
        // Carga los pacientes desde el archivo.

        if(datos != null){
            pacientes = (Map<Long, Paciente>) datos;
            // Recupera los datos guardados.
        }else{
            pacientes = new HashMap<>();
            // Crea un mapa vacío si no hay datos.
        }
    }

    @Override
    public void guardar(Paciente p) {
        // Guarda un paciente.

        pacientes.put(p.getCuil(), p);
        // Lo agrega usando su CUIL.

        Persistencia.guardar(pacientes,"pacientes.dat");
        // Guarda los cambios.
    }

    @Override
    public Paciente buscarPorId(Long id) {
        // Busca un paciente por ID.

        return pacientes.get(id);
        // Devuelve el paciente.
    }

    @Override
    public List<Paciente> listarTodos() {
        // Devuelve todos los pacientes.

        return new ArrayList<>(pacientes.values());
        // Convierte el Map en una lista.
    }

    @Override
    public void eliminar(Long id) {
        // Elimina un paciente.

        pacientes.remove(id);
        // Lo elimina del Map.

        Persistencia.guardar(pacientes,"pacientes.dat");
        // Guarda los cambios.
    }

    @Override
    public void actualizar(Paciente p) {
        // Actualiza un paciente.

        pacientes.put(p.getCuil(),p);
        // Reemplaza el paciente con el mismo CUIL.

        Persistencia.guardar(pacientes,"pacientes.dat");
        // Guarda los cambios.
    }
}