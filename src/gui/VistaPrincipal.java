package gui;


// Importa los servicios que manejan la lógica del sistema
import servicio.ServicioPaciente;
import servicio.ServicioOdontologo;
import servicio.ServicioTurno;


// Librerías para crear interfaz gráfica
import javax.swing.*;

import java.awt.*;


// Permite manejar eventos de cierre de ventana
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



// JFrame permite crear la ventana principal
public class VistaPrincipal extends JFrame {



    // Servicios que se envían a las demás ventanas
    private ServicioPaciente sPaciente;
    private ServicioOdontologo sOdonto;
    private ServicioTurno sTurno;




    // Constructor de la ventana principal
    public VistaPrincipal(ServicioPaciente sPaciente,
                          ServicioOdontologo sOdonto,
                          ServicioTurno sTurno) {



        // Guarda los servicios recibidos
        this.sPaciente = sPaciente;

        this.sOdonto = sOdonto;

        this.sTurno = sTurno;




        // Configuración de la ventana
        setTitle("Sistema Odontológico"); // Título

        setSize(900,600); // Tamaño

        setLocationRelativeTo(null); // Centra la ventana




        // IMPORTANTE:
        // Evita cerrar la ventana automáticamente
        // Permite ejecutar código antes de salir
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);





        // Detecta cuando el usuario intenta cerrar la ventana
        addWindowListener(new WindowAdapter() {



            @Override

            public void windowClosing(WindowEvent e) {



                // Muestra mensaje antes de cerrar
                JOptionPane.showMessageDialog(
                        null,
                        "Cerrando sistema. Datos guardados."
                );



                // Cierra la ventana actual
                dispose();



                // Finaliza el programa
                System.exit(0);



            }


        });





        // Organiza los componentes de la ventana
        setLayout(new BorderLayout());





        // Título que aparece arriba de la ventana
        JLabel titulo = new JLabel(
                "Sistema de Gestión Odontológica",
                SwingConstants.CENTER
        );



        // Agrega el título arriba
        add(titulo, BorderLayout.NORTH);





        // Panel donde van los botones
        JPanel panelBotones = new JPanel();



        // Organiza los botones en una grilla
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







        // Cuando se toca abre la ventana de pacientes
        btnPacientes.addActionListener(e ->

                new VentanaPaciente(sPaciente)

        );





        // Cuando se toca abre la ventana de odontólogos
        btnOdontologos.addActionListener(e ->

                new VentanaOdontologo(sOdonto)

        );





        // Cuando se toca abre la ventana de turnos
        // Le pasa los servicios necesarios
        btnTurnos.addActionListener(e ->

                new VentanaTurno(
                        sTurno,
                        sPaciente,
                        sOdonto
                )

        );





        // Cuando se toca abre la ventana de búsqueda
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





        // Coloca el panel en el centro de la ventana
        add(panelBotones, BorderLayout.CENTER);





        // Hace visible la ventana
        setVisible(true);



    }

}