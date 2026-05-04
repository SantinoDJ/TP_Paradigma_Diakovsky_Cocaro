import modelo.*;
import repositorio.*;
import servicio.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        PacienteRepositorio repoPaciente = new PacienteRepositorio();
        OdontologoRepositorio repoOdonto = new OdontologoRepositorio();
        TurnoRepositorio repoTurno = new TurnoRepositorio();


        ServicioPaciente sPaciente = new ServicioPaciente(repoPaciente);
        ServicioOdontologo sOdonto = new ServicioOdontologo(repoOdonto);

        ServicioTurno sTurno = new ServicioTurno(repoTurno, sPaciente, sOdonto);


        Domicilio dom1 = new Domicilio("Calle Vicente Dupuy", 123, "CABA", "Buenos Aires");
        Domicilio dom2 = new Domicilio("Av. Sarmiento", 742, "Rosario", "Santa Fe");

        ObraSocial os1 = new ObraSocial("OSDE", "Plan 230", " 1-456790-4");
        ObraSocial os2 = new ObraSocial(" Swiss Medicla", " SMG40", " 2-990856-1");

        Odontologo odonto1 = new Odontologo(130, "Joaquin", "Rodriguez", "MT1345");
        Odontologo odonto2 = new Odontologo(120, "Maria", "Sanchez", "MT6790");

        Paciente paciente1 = new Paciente(1, "Juan", "Gonzalez", 111111, 2011111118, 40, "juan@email.com", LocalDate.now(), dom1, os1);
        Paciente paciente2 = new Paciente(2, "Armando", "Perez", 2222222, 2022222228, 30, "armando@gmail.com", LocalDate.now(), dom2, os2);

        System.out.println("-------------------- Bienvenido al sistema de pacientes --------------------");


        sOdonto.registrarOdontologo(odonto1);
        sOdonto.registrarOdontologo(odonto2);
        sPaciente.registrarPaciente(paciente1);
        sPaciente.registrarPaciente(paciente2);

        System.out.println("____________________________________________________________________________");


        Boolean estado = false;
        System.out.println("Prueba con estado FALSE:");
        paciente1.mostrarDatosPaciente(estado);

        System.out.println("____________________________________________________________________________");

        paciente2.setFechaAlta(LocalDate.now());
        estado = true;
        System.out.println("Prueba con estado TRUE:");
        paciente2.mostrarDatosPaciente(estado);

        System.out.println("-------------------------- GESTIÓN DE TURNOS ------------------------------");


        Turno turno1 = new Turno(501, LocalDate.now(), LocalTime.of(15, 30), paciente1, odonto1);
        Turno turno2 = new Turno(502, LocalDate.now().plusDays(1), LocalTime.of(9, 0), paciente2, odonto2);


        sTurno.agendarTurno(turno1);
        sTurno.agendarTurno(turno2);


        paciente1.agregarTurno(turno1);
        paciente2.agregarTurno(turno2);

        System.out.println("____________________________________________________________________________");


        System.out.println("Resumen de turnos en el sistema:");
        for (Turno t : sTurno.listarTodosLosTurnos()) {
            System.out.println("ID Turno: " + t.getId() + " - Paciente: " + t.getPaciente().getNombre() + " con Dr/a: " + t.getOdontologo().getNombre());
        }

        System.out.println("Historial de " + paciente1.getNombre() + ": " + paciente1.getListaTurnos().size() + " turno/s.");
        System.out.println("Historial de " + paciente2.getNombre() + ": " + paciente2.getListaTurnos().size() + " turno/s.");
    }
}