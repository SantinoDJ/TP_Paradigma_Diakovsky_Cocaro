import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Domicilio dom1 = new Domicilio("Calle Vicente Dupuy", 123, "CABA", "Buenos Aires");
        Domicilio dom2 = new Domicilio("Av. Sarmiento", 742, "Rosario", "Santa Fe");

        ObraSocial os1= new ObraSocial("OSDE", "Plan 230", " 1-456790-4");
        ObraSocial os2 = new ObraSocial(" Swiss Medicla", " SMG40", " 2-990856-1");

        Odontologo odonto1= new Odontologo(130, "Joaquin", "Rodriguez", "MT1345");
        Odontologo odonto2 = new Odontologo(120, "Maria", "Sanchez", "MT6790");

        Paciente paciente = new Paciente(1, "Juan", "Gonzalez", 111111, 2011111118, 40, "juan@email.com", LocalDate.now() ,dom1, os1);
        Paciente paciente2 = new Paciente(2, "Armando", "Perez", 2222222, 2022222228, 30, "armando@gmail.com", LocalDate.now() ,dom2, os2);



        System.out.println("-------------------- Bienvenido al sistema de pacientes --------------------");


        System.out.println("Odontólogo disponible: " + odonto1);
        System.out.println("Odontólogo disponible: " + odonto2);

        System.out.println(paciente);
        System.out.println(paciente2);

        System.out.println("____________________________________________________________________________");


        Boolean estado = false;
        System.out.println("Prueba con estado FALSE:");
        paciente.mostrarDatosPaciente(estado);

        System.out.println("____________________________________________________________________________");

        paciente2.setFechaAlta(LocalDate.now());
        estado= true;
        System.out.println("Prueba con estado TRUE:");
        paciente2.mostrarDatosPaciente(estado);

        System.out.println("___________________________ Paciente String ________________________________");
        System.out.println(paciente.mostrarDatosString());

        System.out.println("-------------------------- Comparando nombres ------------------------------");
        System.out.println("Funcion Equals Ignore Case:");


        System.out.println(paciente.getNombre().equalsIgnoreCase(paciente2.getNombre()));


        System.out.println("Resultado método compararNombrePaciente: " + paciente.compararNombrePaciente(paciente2));


        System.out.println("========================== GESTIÓN DE TURNOS ==========================");


        Turno turno1 = new Turno(501, LocalDate.now(), LocalTime.of(15, 30), paciente, odonto1);


        Turno turno2 = new Turno(502, LocalDate.now().plusDays(1), LocalTime.of(9, 0), paciente2, odonto2);

        System.out.println("Detalles del Turno 1: " + turno1);
        System.out.println("Detalles del Turno 2: " + turno2);
    }
}