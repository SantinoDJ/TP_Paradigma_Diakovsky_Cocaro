package gui;

// Importa las clases del modelo que se van a mostrar
import modelo.Paciente;
import modelo.Odontologo;
import modelo.Turno;

// Importa los servicios que manejan la información
import servicio.ServicioPaciente;
import servicio.ServicioOdontologo;
import servicio.ServicioTurno;


// Librerías para crear la interfaz gráfica
import javax.swing.*;

// Permite manejar el modelo de datos de la tabla
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;


// JFrame permite crear una ventana
public class VentanaBuscar extends JFrame {


    // Servicios que permiten acceder a pacientes, odontólogos y turnos
    private ServicioPaciente sPaciente;
    private ServicioOdontologo sOdonto;
    private ServicioTurno sTurno;


    // JTable representa la tabla donde se muestran los resultados
    private JTable tabla;

    // Controla las filas y columnas de la tabla
    private DefaultTableModel modeloTabla;


    // Caja de texto donde el usuario escribe la búsqueda
    private JTextField txtBuscar;


    // Botones para elegir qué buscar
    private JButton btnBuscarPaciente;
    private JButton btnBuscarOdontologo;
    private JButton btnBuscarTurno;



    // Constructor: se ejecuta cuando se crea la ventana
    public VentanaBuscar(ServicioPaciente sPaciente, ServicioOdontologo sOdonto, ServicioTurno sTurno) {


        // Guarda los servicios recibidos para usarlos en la ventana
        this.sPaciente = sPaciente;
        this.sOdonto = sOdonto;
        this.sTurno = sTurno;


        // Configuración de la ventana
        setTitle("Buscar"); // Nombre que aparece arriba
        setSize(900,400); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(new BorderLayout()); // Organiza los componentes



        // Panel donde se agregan botones y buscador
        JPanel panel = new JPanel();


        // Campo donde se ingresa el texto a buscar
        txtBuscar = new JTextField(15);



        // Creación de los botones
        btnBuscarPaciente = new JButton("Paciente");
        btnBuscarOdontologo = new JButton("Odontólogo");
        btnBuscarTurno = new JButton("Turno");



        // Agrega los componentes al panel
        panel.add(txtBuscar);
        panel.add(btnBuscarPaciente);
        panel.add(btnBuscarOdontologo);
        panel.add(btnBuscarTurno);



        // Crea un modelo vacío para manejar la tabla
        modeloTabla = new DefaultTableModel();


        // Crea una tabla usando el modelo anterior
        tabla = new JTable(modeloTabla);



        // Agrega el panel arriba de la ventana
        add(panel, BorderLayout.NORTH);

        // Agrega la tabla en el centro con barra de desplazamiento
        add(new JScrollPane(tabla), BorderLayout.CENTER);



        // Lambda: cuando se toca el botón ejecuta el método correspondiente
        btnBuscarPaciente.addActionListener(e -> buscarPaciente());

        btnBuscarOdontologo.addActionListener(e -> buscarOdontologo());

        btnBuscarTurno.addActionListener(e -> buscarTurno());



        // Hace visible la ventana
        setVisible(true);

    }





    // Método que busca pacientes
    private void buscarPaciente(){


        // Borra las filas anteriores de la tabla
        modeloTabla.setRowCount(0);


        // Define las columnas que tendrá la tabla
        modeloTabla.setColumnIdentifiers(
                new Object[]{
                        "CUIL",
                        "Nombre",
                        "Apellido"
                });


        // Obtiene el texto escrito por el usuario
        String texto = txtBuscar.getText();



        // Recorre la lista de pacientes
        for(Paciente p : sPaciente.listarTodos()){


            // Comprueba si el texto coincide con CUIL o nombre
            if(texto.isEmpty()
                    || String.valueOf(p.getCuil()).contains(texto)
                    || p.getNombre().toLowerCase().contains(texto.toLowerCase())){


                // Agrega el paciente encontrado a la tabla
                modeloTabla.addRow(new Object[]{

                        p.getCuil(),
                        p.getNombre(),
                        p.getApellido()

                });

            }

        }

    }





    // Método que busca odontólogos
    private void buscarOdontologo(){


        // Limpia resultados anteriores
        modeloTabla.setRowCount(0);


        // Define las columnas de la tabla
        modeloTabla.setColumnIdentifiers(
                new Object[]{
                        "ID",
                        "Nombre",
                        "Apellido",
                        "Matrícula"
                });



        // Guarda el texto buscado
        String texto = txtBuscar.getText();



        // Recorre todos los odontólogos
        for(Odontologo o : sOdonto.listarOdontologos()){


            // Busca coincidencias por nombre o matrícula
            if(texto.isEmpty()
                    || o.getNombre().toLowerCase().contains(texto.toLowerCase())
                    || o.getMatricula().contains(texto)){


                // Agrega el odontólogo encontrado
                modeloTabla.addRow(new Object[]{

                        o.getId(),
                        o.getNombre(),
                        o.getApellido(),
                        o.getMatricula()

                });

            }

        }

    }





    // Método que busca turnos
    private void buscarTurno(){


        // Limpia la tabla
        modeloTabla.setRowCount(0);



        // Define las columnas que muestra la tabla
        modeloTabla.setColumnIdentifiers(
                new Object[]{
                        "ID",
                        "Fecha",
                        "Hora",
                        "Paciente",
                        "Odontólogo"
                });



        // Obtiene el texto buscado
        String texto = txtBuscar.getText();



        // Recorre todos los turnos registrados
        for(Turno t : sTurno.listarTodosLosTurnos()){


            // Busca por ID, fecha o nombre del paciente
            if(texto.isEmpty()
                    || String.valueOf(t.getId()).contains(texto)
                    || t.getFecha().toString().contains(texto)
                    || t.getPaciente().getNombre().toLowerCase().contains(texto.toLowerCase())){


                // Agrega el turno encontrado a la tabla
                modeloTabla.addRow(new Object[]{


                        t.getId(),
                        t.getFecha(),
                        t.getHora(),

                        // Muestra nombre completo del paciente
                        t.getPaciente().getNombre()
                                + " "
                                + t.getPaciente().getApellido(),


                        // Muestra nombre completo del odontólogo
                        t.getOdontologo().getNombre()
                                + " "
                                + t.getOdontologo().getApellido()

                });

            }

        }

    }

}