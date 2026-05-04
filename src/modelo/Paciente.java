package modelo;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class Paciente extends Persona {

    private Integer dni;
    private long cuil;
    private int edad;
    private String email;
    private Domicilio domicilio;
    private LocalDate fechaAlta;
    private ObraSocial obraSocial;
    private List<Turno> listaTurnos;

    public Paciente(Integer id, String nombre, String apellido, Integer dni, long cuil, int edad, String email, LocalDate fechaAlta, Domicilio domicilio) {
        super(id, nombre, apellido);
        this.dni = dni;
        this.cuil = cuil;
        this.edad = edad;
        this.email = email;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
        this.listaTurnos = new ArrayList<>();
    }

    public Paciente(Integer id, String nombre, String apellido, Integer dni, long cuil, int edad, String email, LocalDate fechaAlta, Domicilio domicilio, ObraSocial obraSocial) {
        super(id, nombre, apellido);
        this.dni = dni;
        this.cuil = cuil;
        this.edad = edad;
        this.email = email;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
        this.obraSocial = obraSocial;
        this.listaTurnos = new ArrayList<>();
    }



    public void agregarTurno(Turno t){
        this.listaTurnos.add(t);
    }

    public List<Turno> getListaTurnos() {
        return listaTurnos;
    }

    public long getCuil() {return cuil;}
    public Integer getDni() {return dni;}
    public int getEdad() {return edad;}
    public Domicilio getDomicilio() {return domicilio;}
    public String getEmail() {return email;}
    public LocalDate getFechaAlta() {return fechaAlta;}

    public void setCuil(long cuil) {this.cuil = cuil;}
    public void setDni(Integer dni) {this.dni = dni;}
    public void setEdad(int edad) {this.edad = edad; }
    public void setEmail(String email) {this.email = email;}
    public void setDomicilio(Domicilio domicilio) {this.domicilio = domicilio;}
    public void setFechaAlta(LocalDate fechaAlta) {this.fechaAlta = fechaAlta;}

    public void mostrarDatosPaciente(Boolean estado){
        if (estado == true) {
            // Usamos los métodos que heredamos de Persona
            System.out.println("Datos del paciente cargado: " + getId());
            System.out.println("Nombre: " + getNombre());
            System.out.println("Apellido: " + getApellido());
            System.out.println("Cedula: " + getDni());
            System.out.println("Email: " + getEmail());
            System.out.println("El Cuil es: " + getCuil());
            System.out.println("FechaDeAlta: " + getFechaAlta());
            System.out.println("Modelo.Domicilio: " + getDomicilio());
        } else {
            System.out.println("Modelo.Paciente con datos incompletos o inactivo.");
        }
    }

    public String mostrarDatosString(){
        return "Nombre: " + getNombre() + " -Apellido: " + getApellido() + " -Cedula: " + this.dni + "-Cuil: " + getCuil();
    }

    public Boolean compararNombrePaciente(Paciente otroPaciente){
        return getNombre().equals(otroPaciente.getNombre());
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + getId() + ", nombre='" + getNombre() + '\'' + ", apellido='" + getApellido() + '\'' + ", dni=" + dni + ", cuil=" + cuil + ", edad=" + edad + ", email='" + email + '\'' + ", domicilio=" + domicilio + ", fechaAlta=" + fechaAlta + '}';
    }
}