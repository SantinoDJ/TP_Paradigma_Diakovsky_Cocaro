package repositorio;

import modelo.Odontologo;
import util.Persistencia;

import java.util.*;

public class OdontologoRepositorio implements IRepositorio<Odontologo> {
    // Implementa los métodos del repositorio para Odontologo.

    private Map<Long, Odontologo> odontologos; // Almacena los odontólogos usando el ID como clave.
                                                //map<long: guarda datos en pares: una clave y un valor


    public OdontologoRepositorio() { // Constructor


        Object datos = Persistencia.cargar("odontologos.dat"); // Carga los odontólogos desde el archivo.


        if(datos != null){
            odontologos = (Map<Long, Odontologo>) datos; // Si hay datos, los recupera.


        } else {
            odontologos = new HashMap<>(); // Si no hay datos, crea un mapa vacío.
                                            //hashmap: guarda datos por ID (clave) para encontrarlos rápido

        }
    }

    @Override
    public void guardar(Odontologo o) {  // Guarda un odontólogo.


        odontologos.put(o.getId(), o); // Lo agrega al mapa usando su ID.


        Persistencia.guardar( // Guarda los cambios en el archivo.
                odontologos,
                "odontologos.dat"
        );

    }

    @Override
    public Odontologo buscarPorId(Long id) {
        // Busca un odontólogo por ID.

        return odontologos.get(id);
        // Devuelve el odontólogo encontrado.
    }

    @Override
    public List<Odontologo> listarTodos() {
        // Devuelve todos los odontólogos.

        return new ArrayList<>(odontologos.values());
        // Convierte los valores del mapa en una lista.
    }

    @Override
    public void eliminar(Long id) {
        // Elimina un odontólogo.

        odontologos.remove(id); // Lo elimina del mapa.


        Persistencia.guardar( // Guarda los cambios.
                odontologos,
                "odontologos.dat"
        );

    }

    @Override
    public void actualizar(Odontologo o) { // Actualiza un odontólogo.


        odontologos.put(
                o.getId(),
                o
        ); // Reemplaza el odontólogo con el mismo ID.



        Persistencia.guardar( // Guarda los cambios.
                odontologos,
                "odontologos.dat"
        );

    }
}