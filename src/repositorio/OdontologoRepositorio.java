package repositorio;

import modelo.Odontologo;
import util.Persistencia;

import java.util.*;

public class OdontologoRepositorio implements IRepositorio<Odontologo> {


    private Map<Long, Odontologo> odontologos;



    public OdontologoRepositorio() {


        Object datos = Persistencia.cargar("odontologos.dat");


        if(datos != null){

            odontologos = (Map<Long, Odontologo>) datos;

        } else {

            odontologos = new HashMap<>();

        }

    }




    @Override
    public void guardar(Odontologo o) {


        odontologos.put(o.getId(), o);


        Persistencia.guardar(
                odontologos,
                "odontologos.dat"
        );

    }





    @Override
    public Odontologo buscarPorId(Long id) {

        return odontologos.get(id);

    }





    @Override
    public List<Odontologo> listarTodos() {


        return new ArrayList<>(odontologos.values());

    }





    @Override
    public void eliminar(Long id) {


        odontologos.remove(id);


        Persistencia.guardar(
                odontologos,
                "odontologos.dat"
        );

    }





    @Override
    public void actualizar(Odontologo o) {


        odontologos.put(
                o.getId(),
                o
        );


        Persistencia.guardar(
                odontologos,
                "odontologos.dat"
        );

    }

}