package gui;

import modelo.Paciente;
import modelo.Odontologo;
import modelo.Turno;

import servicio.ServicioPaciente;
import servicio.ServicioOdontologo;
import servicio.ServicioTurno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;


public class VentanaBuscar extends JFrame {
    // extends JFrame significa que esta clase hereda de una ventana de Java Swing


    // Servicios que permiten acceder a la lógica del sistema
    private ServicioPaciente sPaciente;
    private ServicioOdontologo sOdonto;
    private ServicioTurno sTurno;



    // JTable es una tabla visual para mostrar información
    private JTable tabla;


    // Modelo que controla los datos que aparecen en la tabla
    private DefaultTableModel modeloTabla;



    // Caja de texto donde el usuario escribe la búsqueda
    private JTextField txtBuscar;



    // Botones de búsqueda
    private JButton btnBuscarPaciente;
    private JButton btnBuscarOdontologo;
    private JButton btnBuscarTurno;





    // Constructor de la ventana
    public VentanaBuscar(ServicioPaciente sPaciente,
                         ServicioOdontologo sOdonto,
                         ServicioTurno sTurno) {


        // Guarda los servicios recibidos
        this.sPaciente = sPaciente;
        this.sOdonto = sOdonto;
        this.sTurno = sTurno;



        // Título de la ventana
        setTitle("Buscar");


        // Tamaño de la ventana
        setSize(900,400);


        // Centra la ventana en la pantalla
        setLocationRelativeTo(null);


        // BorderLayout organiza los componentes por zonas
        setLayout(new BorderLayout());





        // Panel donde se colocan botones y caja de búsqueda
        JPanel panel = new JPanel();



        // Campo de texto donde se ingresa lo que se quiere buscar
        txtBuscar = new JTextField(15);




        // Creación de botones
        btnBuscarPaciente = new JButton("Paciente");

        btnBuscarOdontologo = new JButton("Odontólogo");

        btnBuscarTurno = new JButton("Turno");




        // Agrega componentes al panel
        panel.add(txtBuscar);
        panel.add(btnBuscarPaciente);
        panel.add(btnBuscarOdontologo);
        panel.add(btnBuscarTurno);




        // Crea el modelo vacío de la tabla
        modeloTabla = new DefaultTableModel();



        // Crea la tabla usando ese modelo
        tabla = new JTable(modeloTabla);




        // Agrega el panel arriba de la ventana
        add(panel, BorderLayout.NORTH);


        // JScrollPane permite hacer scroll en la tabla
        add(new JScrollPane(tabla), BorderLayout.CENTER);





        // Cuando se hace click en el botón llama al método buscarPaciente
        btnBuscarPaciente.addActionListener(e -> buscarPaciente());


        // Cuando se hace click llama al método buscarOdontologo
        btnBuscarOdontologo.addActionListener(e -> buscarOdontologo());


        // Cuando se hace click llama al método buscarTurno
        btnBuscarTurno.addActionListener(e -> buscarTurno());




        // Hace visible la ventana
        setVisible(true);

    }





    // Busca pacientes según el texto ingresado
    private void buscarPaciente(){


        // Limpia las filas anteriores de la tabla
        modeloTabla.setRowCount(0);



        // Define las columnas que tendrá la tabla
        modeloTabla.setColumnIdentifiers(
                new Object[]{
                        "CUIL",
                        "Nombre",
                        "Apellido"
                });




        // Obtiene lo escrito por el usuario
        String texto = txtBuscar.getText();




        // Recorre todos los pacientes
        for(Paciente p : sPaciente.listarTodos()){



            // Verifica si coincide con la búsqueda
            if(texto.isEmpty()

                    // Busca por CUIL
                    || String.valueOf(p.getCuil()).contains(texto)

                    // Busca por nombre ignorando mayúsculas/minúsculas
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






    // Busca odontólogos
    private void buscarOdontologo(){


        // Limpia la tabla
        modeloTabla.setRowCount(0);



        // Define columnas
        modeloTabla.setColumnIdentifiers(
                new Object[]{
                        "ID",
                        "Nombre",
                        "Apellido",
                        "Matrícula"
                });




        String texto = txtBuscar.getText();




        // Recorre todos los odontólogos
        for(Odontologo o : sOdonto.listarOdontologos()){



            if(texto.isEmpty()

                    // Busca por nombre
                    || o.getNombre().toLowerCase().contains(texto.toLowerCase())

                    // Busca por matrícula
                    || o.getMatricula().contains(texto)){



                // Agrega odontólogo encontrado
                modeloTabla.addRow(new Object[]{


                        o.getId(),
                        o.getNombre(),
                        o.getApellido(),
                        o.getMatricula()

                });

            }

        }

    }







    // Busca turnos
    private void buscarTurno(){



        // Limpia la tabla
        modeloTabla.setRowCount(0);



        // Define columnas de turnos
        modeloTabla.setColumnIdentifiers(
                new Object[]{
                        "ID",
                        "Fecha",
                        "Hora",
                        "Paciente",
                        "Odontólogo"
                });




        String texto = txtBuscar.getText();




        // Recorre todos los turnos
        for(Turno t : sTurno.listarTodosLosTurnos()){



            if(texto.isEmpty()

                    // Busca por ID del turno
                    || String.valueOf(t.getId()).contains(texto)

                    // Busca por fecha
                    || t.getFecha().toString().contains(texto)

                    // Busca por nombre del paciente
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