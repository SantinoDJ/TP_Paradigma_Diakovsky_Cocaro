package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Paciente paciente;
    private Odontologo odontologo;


    public Turno(Integer id, LocalDate fecha, LocalTime hora, Paciente paciente, Odontologo odontologo) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.odontologo = odontologo;
    }

    public Integer getId() {return id;}

    public LocalDate getFecha() {return fecha;}

    public LocalTime getHora() {return hora;}

    public void setId(Integer id) {this.id = id;}

    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public void setHora(LocalTime hora) {this.hora = hora;}

    public Paciente getPaciente() {return paciente;}

    public void setPaciente(Paciente paciente) {this.paciente = paciente;}

    public Odontologo getOdontologo() {return odontologo;}

    public void setOdontologo(Odontologo odontologo) {this.odontologo = odontologo;}

    @Override
    public String toString() {
        return "Modelo.Turno #" + id + " | Fecha: " + fecha + " | Hora: " + hora + " | Modelo.Paciente: " + paciente.getNombre() + " " + paciente.getApellido() + " | Odontólogo: " + odontologo.getNombre()  + " " + odontologo.getApellido();
    }
}
