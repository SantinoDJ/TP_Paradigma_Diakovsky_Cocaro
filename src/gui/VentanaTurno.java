package gui;

import modelo.Odontologo;
import modelo.Paciente;
import modelo.Turno;

import servicio.ServicioOdontologo;
import servicio.ServicioPaciente;
import servicio.ServicioTurno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;


public class VentanaTurno extends JFrame {
    // JFrame: permite crear una ventana gráfica en Java Swing



    // Constructor de la ventana de turnos
    public VentanaTurno(
            ServicioTurno sTurno,
            ServicioPaciente sPaciente,
            ServicioOdontologo sOdonto) {



        // Configuración de la ventana
        setTitle("Gestión de Turnos");

        setSize(850, 600);

        setLocationRelativeTo(null);

        // Organiza los componentes por zonas
        setLayout(new BorderLayout());




        // Panel del formulario
        JPanel formulario =
                new JPanel(new GridLayout(5, 2, 10, 10));

        // Agrega espacio interno al panel
        formulario.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10)
        );




        // Campo ID
        formulario.add(new JLabel("ID:"));

        JTextField txtId = new JTextField();

        formulario.add(txtId);




        // Combo donde se muestran los pacientes
        formulario.add(new JLabel("Paciente:"));

        JComboBox<Paciente> comboPaciente = new JComboBox<>();


        // Agrega todos los pacientes al combo
        sPaciente.listarTodos()
                .forEach(comboPaciente::addItem);
        // forEach recorre la lista
        // addItem agrega un elemento al JComboBox


        formulario.add(comboPaciente);




        // Combo de odontólogos
        formulario.add(new JLabel("Odontólogo:"));

        JComboBox<Odontologo> comboOdonto = new JComboBox<>();


        // Carga los odontólogos disponibles
        sOdonto.listarOdontologos()
                .forEach(comboOdonto::addItem);


        formulario.add(comboOdonto);




        // Campo fecha
        formulario.add(new JLabel("Fecha:"));

        JTextField txtFecha =
                new JTextField("2026-06-22");

        formulario.add(txtFecha);




        // Campo hora
        formulario.add(new JLabel("Hora:"));

        JTextField txtHora =
                new JTextField("10:00");

        formulario.add(txtHora);




        // Agrega formulario arriba de la ventana
        add(formulario, BorderLayout.NORTH);






        // Columnas de la tabla
        String[] columnas = {
                "ID",
                "Paciente",
                "Odontólogo",
                "Fecha",
                "Hora"
        };



        // Modelo que controla la tabla
        DefaultTableModel modeloTabla =
                new DefaultTableModel(columnas,0);



        // Crea la tabla
        JTable tabla = new JTable(modeloTabla);



        // Permite desplazarse por la tabla
        JScrollPane scroll =
                new JScrollPane(tabla);



        add(scroll, BorderLayout.CENTER);






        // Panel de botones
        JPanel panelBotones =
                new JPanel(new GridLayout(1,3,10,10));



        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10)
        );



        JButton btnAgregar =
                new JButton("Agregar Turno");


        JButton btnModificar =
                new JButton("Modificar Turno");


        JButton btnLimpiar =
                new JButton("Limpiar");



        panelBotones.add(btnAgregar);

        panelBotones.add(btnModificar);

        panelBotones.add(btnLimpiar);



        add(panelBotones, BorderLayout.SOUTH);




        // Carga los turnos existentes en la tabla
        cargarTabla(modeloTabla, sTurno);






        // Evento botón agregar
        btnAgregar.addActionListener(e -> {


            try {


                // Verifica que los campos tengan datos
                if(txtId.getText().trim().isEmpty()
                        || txtFecha.getText().trim().isEmpty()
                        || txtHora.getText().trim().isEmpty()){


                    JOptionPane.showMessageDialog(
                            this,
                            "Complete todos los campos"
                    );


                    return;
                }





                // Convierte ID de texto a número
                Integer id =
                        Integer.parseInt(
                                txtId.getText().trim()
                        );



                // Obtiene paciente seleccionado
                Paciente paciente =
                        (Paciente) comboPaciente.getSelectedItem();



                // Obtiene odontólogo seleccionado
                Odontologo odonto =
                        (Odontologo) comboOdonto.getSelectedItem();




                // Convierte texto a fecha
                LocalDate fecha =
                        LocalDate.parse(
                                txtFecha.getText().trim()
                        );



                // Convierte texto a hora
                LocalTime hora =
                        LocalTime.parse(
                                txtHora.getText().trim()
                        );




                // Crea un nuevo turno
                Turno nuevo =
                        new Turno(
                                id,
                                fecha,
                                hora,
                                paciente,
                                odonto
                        );



                // Envía el turno al servicio
                sTurno.agendarTurno(nuevo);




                cargarTabla(modeloTabla, sTurno);


                limpiar(txtId, txtFecha, txtHora);



                JOptionPane.showMessageDialog(
                        this,
                        "Turno agregado correctamente"
                );



            }catch(Exception ex){


                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            }

        });







        // Evento modificar turno
        btnModificar.addActionListener(e -> {


            try {


                // Obtiene fila seleccionada
                int fila =
                        tabla.getSelectedRow();



                if(fila == -1){

                    JOptionPane.showMessageDialog(
                            this,
                            "Seleccione un turno de la tabla"
                    );

                    return;
                }




                Integer id =
                        Integer.parseInt(
                                txtId.getText().trim()
                        );



                Paciente paciente =
                        (Paciente) comboPaciente.getSelectedItem();



                Odontologo odonto =
                        (Odontologo) comboOdonto.getSelectedItem();




                LocalDate fecha =
                        LocalDate.parse(
                                txtFecha.getText().trim()
                        );



                LocalTime hora =
                        LocalTime.parse(
                                txtHora.getText().trim()
                        );





                // Busca el turno por ID usando Stream
                Turno turno =
                        sTurno.listarTodosLosTurnos()

                                .stream()
                                // Convierte la lista en Stream


                                .filter(t -> t.getId().equals(id))
                                // filter busca solamente los turnos que cumplen la condición


                                .findFirst()
                                // Devuelve el primer elemento encontrado


                                .orElseThrow(
                                        () -> new Exception(
                                                "No existe un turno con ese ID")
                                );
                // Si no encuentra nada lanza un error





                // Modifica los datos del turno
                turno.setPaciente(paciente);

                turno.setOdontologo(odonto);

                turno.setFecha(fecha);

                turno.setHora(hora);




                cargarTabla(modeloTabla, sTurno);

                limpiar(txtId, txtFecha, txtHora);



            }catch(Exception ex){


                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            }

        });






        // Limpia campos
        btnLimpiar.addActionListener(e ->
                limpiar(txtId, txtFecha, txtHora)
        );






        // Detecta click en la tabla
        tabla.addMouseListener(
                new java.awt.event.MouseAdapter() {


                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {


                        int fila =
                                tabla.getSelectedRow();



                        txtId.setText(
                                tabla.getValueAt(fila,0).toString()
                        );


                        txtFecha.setText(
                                tabla.getValueAt(fila,3).toString()
                        );


                        txtHora.setText(
                                tabla.getValueAt(fila,4).toString()
                        );

                    }

                });




        // Muestra la ventana
        setVisible(true);

    }







    // Carga los turnos en la tabla
    private void cargarTabla(
            DefaultTableModel modeloTabla,
            ServicioTurno sTurno){


        // Borra filas anteriores
        modeloTabla.setRowCount(0);



        // Recorre todos los turnos
        sTurno.listarTodosLosTurnos()

                .forEach(t -> {


                    // Agrega una fila
                    modeloTabla.addRow(new Object[]{


                            t.getId(),

                            t.getPaciente().getNombre()
                                    + " "
                                    + t.getPaciente().getApellido(),


                            t.getOdontologo().getNombre()
                                    + " "
                                    + t.getOdontologo().getApellido(),


                            t.getFecha(),

                            t.getHora()

                    });


                });

    }






    // Limpia los campos de texto
    private void limpiar(
            JTextField txtId,
            JTextField txtFecha,
            JTextField txtHora){


        txtId.setText("");

        txtFecha.setText("");

        txtHora.setText("");

    }

}