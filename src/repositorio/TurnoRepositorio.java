package repositorio;

import modelo.Turno;
import util.Persistencia;

import java.util.*;

public class TurnoRepositorio implements IRepositorio<Turno> {
    // Repositorio para turnos.

    private List<Turno> turnos;
    // Lista donde se guardan los turnos.

    public TurnoRepositorio(){
        // Constructor.

        Object datos = Persistencia.cargar("turnos.dat");
        // Carga los turnos del archivo.

        if(datos != null){
            turnos = (List<Turno>) datos;
            // Recupera los datos guardados.
        } else {
            turnos = new ArrayList<>();
            // Crea una lista vacía.
        }
    }

    @Override
    public void guardar(Turno t) {
        // Guarda un turno.

        turnos.add(t);
        // Agrega el turno a la lista.

        Persistencia.guardar(
                turnos,
                "turnos.dat"
        );
        // Guarda los cambios.
    }

    @Override
    public Turno buscarPorId(Long id) {
        // Busca un turno por ID.

        return turnos.stream()
                // Recorre la lista.
                .filter(t -> Long.valueOf(t.getId()).equals(id))
                // Filtra el turno con ese ID.
                .findFirst()
                // Toma el primero que encuentra.
                .orElse(null);
        // Si no existe, devuelve null.
    }

    @Override
    public List<Turno> listarTodos() {
        // Devuelve todos los turnos.

        return turnos;
    }

    @Override
    public void eliminar(Long id) {
        // Elimina un turno.

        turnos.removeIf(
                t -> Long.valueOf(t.getId()).equals(id)
        );
        // Elimina el turno con ese ID.

        Persistencia.guardar(
                turnos,
                "turnos.dat"
        );
        // Guarda los cambios.
    }

    @Override
    public void actualizar(Turno t) {
        // Actualiza un turno.

        for(int i = 0; i < turnos.size(); i++){
            // Recorre la lista.

            if(turnos.get(i).getId()
                    .equals(t.getId())){
                // Busca el turno con el mismo ID.

                turnos.set(i,t);
                // Lo reemplaza.

                break;
                // Termina el recorrido.
            }
        }

        Persistencia.guardar(
                turnos,
                "turnos.dat"
        );
        // Guarda los cambios.
    }
}