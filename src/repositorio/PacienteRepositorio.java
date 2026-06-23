package repositorio;

import modelo.Paciente;
import util.Persistencia;

import java.util.*;

// Clase para manejar los pacientes y guardarlos en un archivo
public class PacienteRepositorio implements IRepositorio<Paciente> {

    private Map<Long, Paciente> pacientes;

    public PacienteRepositorio(){
        // Al arrancar, carga los pacientes desde el archivo
        Object datos = Persistencia.cargar("pacientes.dat");

        if(datos != null){ // Verifica si existen datos cargados desde el archivo
            pacientes = (Map<Long, Paciente>) datos; // Convierte el Object recibido a un Map de pacientes

        }else{
            pacientes = new HashMap<>(); // Si no hay datos, crea un Map vacío para empezar a guardar pacientes
        }
    }

    @Override
    public void guardar(Paciente p) {
        // Guarda un paciente nuevo y actualiza el archivo
        pacientes.put(p.getCuil(), p);
        Persistencia.guardar(pacientes,"pacientes.dat");
    }

    @Override
    public Paciente buscarPorId(Long id) {
        // Busca un paciente usando su ID
        return pacientes.get(id);
    }

    @Override
    public List<Paciente> listarTodos() {
        // Devuelve todos los pacientes en una lista
        return new ArrayList<>(pacientes.values());
    }

    @Override
    public void eliminar(Long id) {
        // Borra un paciente y actualiza el archivo
        pacientes.remove(id);
        Persistencia.guardar(pacientes,"pacientes.dat");
    }

    @Override
    public void actualizar(Paciente p) {
        // Cambia los datos de un paciente y guarda los cambios
        pacientes.put(p.getCuil(),p);
        Persistencia.guardar(pacientes,"pacientes.dat");
    }
}