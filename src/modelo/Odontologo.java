package modelo;

public class Odontologo extends Persona {
    private String matricula;

    public Odontologo(long id, String nombre, String apellido, String matricula) {
        super(id, nombre, apellido);
        this.matricula = matricula;
    }


    public String getMatricula() {return matricula;}

    public void setMatricula(String matricula) {this.matricula = matricula;}

    @Override
    public String toString() {

        return "Odontologo{" + "id=" + getId() + ", nombre='" + getNombre() + '\'' + ", apellido='" + getApellido() + '\'' + ", matricula='" + matricula + '\'' + '}';
    }
}