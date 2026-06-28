package excepciones;

public class TurnoYaReservadoException extends ClinicaException { // Clase de excepción personalizada para errores del sistema

    public TurnoYaReservadoException(String mensaje) { // Constructor que recibe el mensaje del error

        super(mensaje); // Envía el mensaje al constructor de Exception
    }
}