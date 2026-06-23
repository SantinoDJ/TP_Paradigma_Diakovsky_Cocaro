package gui;


import servicio.ServicioPaciente;
import servicio.ServicioOdontologo;
import servicio.ServicioTurno;


import javax.swing.*;

import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class VistaPrincipal extends JFrame {
    // JFrame: permite crear una ventana gráfica



    // Servicios que usa la interfaz
    private ServicioPaciente sPaciente;

    private ServicioOdontologo sOdonto;

    private ServicioTurno sTurno;






    // Constructor de la ventana principal
    public VistaPrincipal(
            ServicioPaciente sPaciente,
            ServicioOdontologo sOdonto,
            ServicioTurno sTurno) {



        // Guarda los servicios recibidos
        this.sPaciente = sPaciente;

        this.sOdonto = sOdonto;

        this.sTurno = sTurno;





        // Título de la ventana
        setTitle("Sistema Odontológico");



        // Tamaño de la ventana
        setSize(900,600);



        // Centra la ventana en pantalla
        setLocationRelativeTo(null);





        // Evita que la ventana se cierre automáticamente
        // Nosotros controlamos qué pasa al cerrar
        setDefaultCloseOperation(
                JFrame.DO_NOTHING_ON_CLOSE
        );






        // Detecta cuando el usuario intenta cerrar la ventana
        addWindowListener(
                new WindowAdapter() {



                    @Override
                    public void windowClosing(WindowEvent e) {



                        // Muestra mensaje antes de cerrar
                        JOptionPane.showMessageDialog(
                                null,
                                "Cerrando sistema. Datos guardados."
                        );



                        // Cierra la ventana correctamente
                        dispose();



                        // Termina completamente el programa
                        System.exit(0);



                    }


                });







        // Organiza los componentes de la ventana
        setLayout(new BorderLayout());







        // Título que aparece arriba
        JLabel titulo = new JLabel(
                "Sistema de Gestión Odontológica",
                SwingConstants.CENTER
        );


        // Lo coloca arriba de la ventana
        add(titulo, BorderLayout.NORTH);







        // Panel donde estarán los botones
        JPanel panelBotones = new JPanel();



        // Organiza botones en filas y columnas
        panelBotones.setLayout(
                new GridLayout(3,1,10,10)
        );







        // Creación de botones
        JButton btnPacientes =
                new JButton("Pacientes");



        JButton btnOdontologos =
                new JButton("Odontólogos");



        JButton btnTurnos =
                new JButton("Turnos");



        JButton btnBuscar =
                new JButton("Buscar");








        // Cuando toca pacientes abre VentanaPaciente
        btnPacientes.addActionListener(e ->

                new VentanaPaciente(sPaciente)

        );





        // Cuando toca odontólogos abre VentanaOdontologo
        btnOdontologos.addActionListener(e ->

                new VentanaOdontologo(sOdonto)

        );







        // Cuando toca turnos abre VentanaTurno
        // Le pasa los servicios necesarios
        btnTurnos.addActionListener(e ->

                new VentanaTurno(
                        sTurno,
                        sPaciente,
                        sOdonto
                )

        );








        // Cuando toca buscar abre VentanaBuscar
        btnBuscar.addActionListener(e ->

                new VentanaBuscar(
                        sPaciente,
                        sOdonto,
                        sTurno
                )

        );







        // Agrega botones al panel
        panelBotones.add(btnPacientes);

        panelBotones.add(btnOdontologos);

        panelBotones.add(btnTurnos);

        panelBotones.add(btnBuscar);







        // Coloca el panel en el centro
        add(panelBotones, BorderLayout.CENTER);







        // Hace visible la ventana
        setVisible(true);

    }

}