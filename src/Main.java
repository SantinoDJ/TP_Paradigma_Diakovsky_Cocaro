import excepciones.ClinicaException;
import modelo.Odontologo;
import modelo.Paciente;
import repositorio.OdontologoRepositorio;
import repositorio.PacienteRepositorio;
import repositorio.TurnoRepositorio;
import servicio.ServicioOdontologo;
import servicio.ServicioPaciente;
import servicio.ServicioTurno;
import gui.VistaPrincipal;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        PacienteRepositorio repoPaciente = new PacienteRepositorio();
        OdontologoRepositorio repoOdonto = new OdontologoRepositorio();
        TurnoRepositorio repoTurno = new TurnoRepositorio();

        ServicioPaciente sPaciente = new ServicioPaciente(repoPaciente);
        ServicioOdontologo sOdonto = new ServicioOdontologo(repoOdonto);
        ServicioTurno sTurno =
                new ServicioTurno(repoTurno, sPaciente, sOdonto);

        try {
            sOdonto.registrarOdontologo(new Odontologo(123, "Joaquin", "Rodriguez", "MT1345")
            );

            sPaciente.registrarPaciente(new Paciente(1, "Juan", "Gonzalez", 111111, 2011111118L, 40, "juan@email.com", LocalDate.now(), null)
            );

        } catch (ClinicaException e) {
            System.out.println(e.getMessage());
        }

        new VistaPrincipal(
                sPaciente,
                sOdonto,
                sTurno
        );
    }
}