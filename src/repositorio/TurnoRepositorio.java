package repositorio;

import modelo.Turno;
import util.Persistencia;

import java.util.*;

public class TurnoRepositorio implements IRepositorio<Turno> {


    private List<Turno> turnos;



    public TurnoRepositorio(){


        Object datos = Persistencia.cargar("turnos.dat");


        if(datos != null){

            turnos = (List<Turno>) datos;

        } else {

            turnos = new ArrayList<>();

        }

    }





    @Override
    public void guardar(Turno t) {


        turnos.add(t);


        Persistencia.guardar(
                turnos,
                "turnos.dat"
        );

    }





    @Override
    public Turno buscarPorId(Long id) {


        return turnos.stream()

                .filter(t -> Long.valueOf(t.getId()).equals(id))

                .findFirst()

                .orElse(null);

    }





    @Override
    public List<Turno> listarTodos() {


        return turnos;

    }





    @Override
    public void eliminar(Long id) {


        turnos.removeIf(
                t -> Long.valueOf(t.getId()).equals(id)
        );


        Persistencia.guardar(
                turnos,
                "turnos.dat"
        );

    }





    @Override
    public void actualizar(Turno t) {


        for(int i = 0; i < turnos.size(); i++){


            if(turnos.get(i).getId()
                    .equals(t.getId())){


                turnos.set(i,t);

                break;

            }

        }



        Persistencia.guardar(
                turnos,
                "turnos.dat"
        );

    }

}