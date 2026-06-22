package gui;

import servicio.ServicioPaciente;
import servicio.ServicioOdontologo;
import servicio.ServicioTurno;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {

    private ServicioPaciente sPaciente;
    private ServicioOdontologo sOdonto;
    private ServicioTurno sTurno;

    public VistaPrincipal(ServicioPaciente sPaciente, ServicioOdontologo sOdonto, ServicioTurno sTurno) {

        this.sPaciente = sPaciente;
        this.sOdonto = sOdonto;
        this.sTurno = sTurno;

        setTitle("Sistema Odontológico");
        setSize(900, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel(
                "Sistema de Gestión Odontológica",
                SwingConstants.CENTER);

        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnPacientes = new JButton("Pacientes");

        btnPacientes.addActionListener(e -> {
            new VentanaPaciente(sPaciente);
        });

        JButton btnOdontologos = new JButton("Odontólogos");
        JButton btnTurnos = new JButton("Turnos");

        panelBotones.add(btnPacientes);
        panelBotones.add(btnOdontologos);
        panelBotones.add(btnTurnos);

        add(panelBotones, BorderLayout.CENTER);

        setVisible(true);
    }
}