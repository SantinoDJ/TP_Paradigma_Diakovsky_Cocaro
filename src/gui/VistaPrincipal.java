package gui;

import servicio.ServicioPaciente;
import servicio.ServicioOdontologo;
import servicio.ServicioTurno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaPrincipal extends JFrame {


    private ServicioPaciente sPaciente;
    private ServicioOdontologo sOdonto;
    private ServicioTurno sTurno;



    public VistaPrincipal(ServicioPaciente sPaciente,
                          ServicioOdontologo sOdonto,
                          ServicioTurno sTurno) {


        this.sPaciente = sPaciente;
        this.sOdonto = sOdonto;
        this.sTurno = sTurno;



        setTitle("Sistema Odontológico");

        setSize(900,600);

        setLocationRelativeTo(null);


        // IMPORTANTE
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);



        addWindowListener(new WindowAdapter() {


            @Override
            public void windowClosing(WindowEvent e) {


                JOptionPane.showMessageDialog(
                        null,
                        "Cerrando sistema. Datos guardados."
                );


                dispose();

                System.exit(0);

            }


        });




        setLayout(new BorderLayout());



        JLabel titulo = new JLabel(
                "Sistema de Gestión Odontológica",
                SwingConstants.CENTER
        );


        add(titulo, BorderLayout.NORTH);



        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(
                new GridLayout(3,1,10,10)
        );



        JButton btnPacientes =
                new JButton("Pacientes");


        JButton btnOdontologos =
                new JButton("Odontólogos");


        JButton btnTurnos =
                new JButton("Turnos");


        JButton btnBuscar =
                new JButton("Buscar");




        btnPacientes.addActionListener(e ->
                new VentanaPaciente(sPaciente)
        );



        btnOdontologos.addActionListener(e ->
                new VentanaOdontologo(sOdonto)
        );



        btnTurnos.addActionListener(e ->
                new VentanaTurno(
                        sTurno,
                        sPaciente,
                        sOdonto
                )
        );



        btnBuscar.addActionListener(e ->
                new VentanaBuscar(
                        sPaciente,
                        sOdonto,
                        sTurno
                )
        );




        panelBotones.add(btnPacientes);

        panelBotones.add(btnOdontologos);

        panelBotones.add(btnTurnos);

        panelBotones.add(btnBuscar);



        add(panelBotones, BorderLayout.CENTER);



        setVisible(true);

    }

}