package excepciones;

public class PacienteNoEncontradoException extends ClinicaException { // Clase de excepción personalizada para errores del sistema

    public PacienteNoEncontradoException(String mensaje) { // Constructor que recibe el mensaje del error


        super(mensaje); // Envía el mensaje al constructor de Exception
    }
}