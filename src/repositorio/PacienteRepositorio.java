package repositorio;

import modelo.Paciente;
import util.Persistencia;

import java.util.*;

public class PacienteRepositorio implements IRepositorio<Paciente> {


    private Map<Long, Paciente> pacientes;


    public PacienteRepositorio(){

        Object datos = Persistencia.cargar("pacientes.dat");


        if(datos != null){

            pacientes = (Map<Long, Paciente>) datos;

        }else{

            pacientes = new HashMap<>();

        }

    }



    @Override
    public void guardar(Paciente p) {

        pacientes.put(p.getCuil(), p);

        Persistencia.guardar(pacientes,"pacientes.dat");

    }



    @Override
    public Paciente buscarPorId(Long id) {

        return pacientes.get(id);

    }



    @Override
    public List<Paciente> listarTodos() {

        return new ArrayList<>(pacientes.values());

    }



    @Override
    public void eliminar(Long id) {

        pacientes.remove(id);

        Persistencia.guardar(pacientes,"pacientes.dat");

    }



    @Override
    public void actualizar(Paciente p) {

        pacientes.put(p.getCuil(),p);

        Persistencia.guardar(pacientes,"pacientes.dat");

    }

}