package excepciones;

public class ClinicaException extends Exception { // Clase de excepción personalizada para errores del sistema

    public ClinicaException(String mensaje) { // Constructor que recibe el mensaje del error

        super(mensaje); // Envía el mensaje al constructor de Exception
    }
}