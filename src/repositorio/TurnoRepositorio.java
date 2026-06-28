package repositorio;

import modelo.Turno;
import util.Persistencia;

import java.util.*;


// Clase encargada de manejar los turnos y guardarlos en un archivo
public class TurnoRepositorio implements IRepositorio<Turno> {


    private List<Turno> turnos; // Lista donde se almacenan todos los turnos



    public TurnoRepositorio(){


        // Carga los turnos guardados anteriormente desde el archivo
        Object datos = Persistencia.cargar("turnos.dat");



        if(datos != null){ // Si encontró datos guardados

            turnos = (List<Turno>) datos; // Convierte el Object cargado a una lista de Turnos


        } else {

            turnos = new ArrayList<>(); // Si no hay datos crea una lista vacía

        }

    }







    @Override
    public void guardar(Turno t) {


        turnos.add(t); // Agrega un nuevo turno a la lista


        Persistencia.guardar(turnos, "turnos.dat"); // Guarda la lista actualizada en el archivo

    }







    @Override
    public Turno buscarPorId(Long id) {


        return turnos.stream() // Convierte la lista de turnos en un Stream para procesarla

                .filter(t -> Long.valueOf(t.getId()).equals(id))
                // filter busca solamente el turno que tenga ese ID


                .findFirst()
                // Devuelve el primer turno encontrado


                .orElse(null); // Si no encuentra ninguno devuelve null



    }







    @Override
    public List<Turno> listarTodos() {


        return turnos; // Devuelve la lista completa de turnos

    }







    @Override
    public void eliminar(Long id) {


        turnos.removeIf(t -> Long.valueOf(t.getId()).equals(id));
        // removeIf elimina los turnos que cumplen la condición del ID


        Persistencia.guardar(turnos, "turnos.dat");
        // Guarda los cambios en el archivo

    }








    @Override
    public void actualizar(Turno t) {


        for(int i = 0; i < turnos.size(); i++){
            // Recorre la lista buscando el turno a modificar


            if(turnos.get(i).getId().equals(t.getId())){
                // Compara el ID del turno guardado con el nuevo turno


                turnos.set(i,t);
                // Reemplaza el turno viejo por el nuevo


                break;
                // Termina el recorrido porque ya encontró el turno

            }

        }


        Persistencia.guardar(turnos, "turnos.dat");
        // Guarda la lista actualizada

    }

}