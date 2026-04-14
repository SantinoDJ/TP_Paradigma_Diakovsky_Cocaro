import java.time.LocalDate;

public class Paciente {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private int cuil;
    private int edad;
    private String email;
    private Domicilio domicilio;
    private LocalDate fechaAlta;
    private ObraSocial obraSocial;


    public Paciente(Integer id, String nombre, String apellido, Integer dni, int cuil ,int edad, String email, LocalDate fechaAlta, Domicilio domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.email = email;
        this.cuil = cuil;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;


    }

    public Paciente(Integer id, String nombre, String apellido, Integer dni, int cuil ,int edad, String email, LocalDate fechaAlta ,Domicilio domicilio, ObraSocial obraSocial) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.email = email;
        this.cuil = cuil;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
        this.obraSocial = obraSocial;

    }

    public int getCuil() {
        return cuil;
    }

    public void setCuil(int cuil) {
        this.cuil = cuil;
    }


    public String getNombre() {
        return this.nombre;
    }

    public Integer getId() {
        return this.id;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public int getEdad() {return edad;}

    public Domicilio getDomicilio() {return domicilio;}

    public String getEmail() {
        return email;
    }


    public LocalDate getFechaAlta() {
        return fechaAlta;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void setEdad(int edad) {this.edad = edad; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDomicilio(Domicilio domicilio) {this.domicilio = domicilio;}

    public void setFechaAlta(LocalDate fechaAlta) {this.fechaAlta = fechaAlta;}


    public void mostrarDatosPaciente(Boolean estado){
        if (estado == true) {
            System.out.println("Datos del paciente cargado: " + getId());
            System.out.println("Nombre: " + getNombre());
            System.out.println("Apellido: " + getApellido());
            System.out.println("Cedula: " + getDni());
            System.out.println("Email: " + getEmail());
            System.out.println("El Cuil es: " + getCuil());
            System.out.println("FechaDeAlta: " + getFechaAlta());
            System.out.println("Domicilio: " + getDomicilio());

        }
        else {
            System.out.println("Paciente con datos incompletos o inactivo.");
        }
    }

    public String mostrarDatosString(){
        return "Nombre: " + this.nombre + " -Apellido: " + getApellido() + " -Cedula: " + this.dni + "-Cuil: " + getCuil();
    }

    public Boolean compararNombrePaciente(Paciente otroPaciente){
        return this.nombre.equals(otroPaciente.getNombre());
    }


    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + ", nombre='" + nombre + '\'' + ", apellido='" + apellido + '\'' + ", dni=" + dni + ", cuil=" + cuil + ", edad=" + edad + ", email='" + email + '\'' + ", domicilio=" + domicilio + ", fechaAlta=" + fechaAlta + '}';
    }
}
