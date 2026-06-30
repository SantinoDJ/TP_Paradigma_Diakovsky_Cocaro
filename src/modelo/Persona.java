package modelo;


// Permite que los objetos de esta clase puedan guardarse en archivos
import java.io.Serializable;



// Clase abstracta porque sirve como base para otras clases (Paciente, Odontologo)
// Implementa Serializable porque te permite guardar y recuperar objetos en archivos
public abstract class Persona implements Serializable {



    // Sirve para identificar la versión de una clase serializable.

    private static final long serialVersionUID = 1L;



    // Atributos comunes que tienen todas las personas
    protected long id;

    protected String nombre;

    protected String apellido;





    // Constructor para crear una Persona con sus datos básicos
    public Persona(long id, String nombre, String apellido) {


        this.id = id;

        this.nombre = nombre;

        this.apellido = apellido;

    }





    // Devuelve el ID de la persona
    public long getId() {

        return id;

    }





    // Modifica el ID de la persona
    public void setId(Integer id) {

        this.id = id;

    }





    // Devuelve el nombre
    public String getNombre() {

        return nombre;

    }





    // Modifica el nombre
    public void setNombre(String nombre) {

        this.nombre = nombre;

    }





    // Devuelve el apellido
    public String getApellido() {

        return apellido;

    }





    // Modifica el apellido
    public void setApellido(String apellido) {

        this.apellido = apellido;

    }


}