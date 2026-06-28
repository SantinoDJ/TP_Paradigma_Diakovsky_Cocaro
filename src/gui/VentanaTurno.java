package gui;

// Importa las clases del modelo
import modelo.Odontologo;
import modelo.Paciente;
import modelo.Turno;


// Importa los servicios que manejan los datos
import servicio.ServicioOdontologo;
import servicio.ServicioPaciente;
import servicio.ServicioTurno;


// Librerías para crear interfaz gráfica
import javax.swing.*;

// Maneja los datos de la tabla
import javax.swing.table.DefaultTableModel;

import java.awt.*;

// Permite trabajar con fechas
import java.time.LocalDate;

// Permite trabajar con horas
import java.time.LocalTime;



// JFrame permite crear una ventana gráfica
public class VentanaTurno extends JFrame {



    // Constructor de la ventana
    public VentanaTurno(ServicioTurno sTurno, ServicioPaciente sPaciente, ServicioOdontologo sOdonto) {



        // Configuración de la ventana
        setTitle("Gestión de Turnos"); // Título
        setSize(850, 600); // Tamaño
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(new BorderLayout()); // Organiza componentes



        // Panel donde están los datos del turno
        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));


        // Agrega margen interno al formulario
        formulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        // Campo para ingresar ID del turno
        formulario.add(new JLabel("ID:"));
        JTextField txtId = new JTextField();
        formulario.add(txtId);



        // ComboBox para seleccionar paciente
        formulario.add(new JLabel("Paciente:"));

        JComboBox<Paciente> comboPaciente = new JComboBox<>();


        // Carga todos los pacientes en el combo
        sPaciente.listarTodos().forEach(comboPaciente::addItem);


        formulario.add(comboPaciente);




        // ComboBox para seleccionar odontólogo
        formulario.add(new JLabel("Odontólogo:"));

        JComboBox<Odontologo> comboOdonto = new JComboBox<>();


        // Carga todos los odontólogos en el combo
        sOdonto.listarOdontologos().forEach(comboOdonto::addItem);


        formulario.add(comboOdonto);




        // Campo para ingresar fecha
        formulario.add(new JLabel("Fecha:"));

        JTextField txtFecha = new JTextField("2026-06-22");

        formulario.add(txtFecha);




        // Campo para ingresar hora
        formulario.add(new JLabel("Hora:"));

        JTextField txtHora = new JTextField("10:00");

        formulario.add(txtHora);



        // Agrega formulario arriba de la ventana
        add(formulario, BorderLayout.NORTH);




        // Columnas que tendrá la tabla
        String[] columnas = {"ID", "Paciente", "Odontólogo", "Fecha", "Hora"};



        // Modelo que controla las filas de la tabla
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);



        // Crea la tabla
        JTable tabla = new JTable(modeloTabla);


        // Agrega scroll a la tabla
        JScrollPane scroll = new JScrollPane(tabla);



        // Coloca tabla en el centro
        add(scroll, BorderLayout.CENTER);




        // Panel para los botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));


        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );



        // Creación de botones
        JButton btnAgregar = new JButton("Agregar Turno");

        JButton btnModificar = new JButton("Modificar Turno");

        JButton btnLimpiar = new JButton("Limpiar");



        // Agrega botones al panel
        panelBotones.add(btnAgregar);

        panelBotones.add(btnModificar);

        panelBotones.add(btnLimpiar);



        // Coloca botones abajo
        add(panelBotones, BorderLayout.SOUTH);




        // Carga los turnos existentes en la tabla
        cargarTabla(modeloTabla, sTurno);




        // Evento del botón agregar
        btnAgregar.addActionListener(e -> {


            try {


                // Verifica que los campos obligatorios tengan datos
                if (txtId.getText().trim().isEmpty()
                        || txtFecha.getText().trim().isEmpty()
                        || txtHora.getText().trim().isEmpty()) {



                    JOptionPane.showMessageDialog(this, "Complete todos los campos");

                    return;

                }




                // Convierte los datos ingresados
                Integer id = Integer.parseInt(txtId.getText().trim());


                // Obtiene el paciente seleccionado
                Paciente paciente = (Paciente) comboPaciente.getSelectedItem();


                // Obtiene el odontólogo seleccionado
                Odontologo odonto = (Odontologo) comboOdonto.getSelectedItem();


                // Convierte texto a fecha
                LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());


                // Convierte texto a hora
                LocalTime hora = LocalTime.parse(txtHora.getText().trim());




                // Crea un nuevo turno
                Turno nuevo = new Turno(id, fecha, hora, paciente, odonto);



                // Envía el turno al servicio para guardarlo
                sTurno.agendarTurno(nuevo);



                // Actualiza tabla
                cargarTabla(modeloTabla, sTurno);



                // Limpia campos
                limpiar(txtId, txtFecha, txtHora);



                JOptionPane.showMessageDialog(this, "Turno agregado correctamente");



            } catch (Exception ex) {


                // Muestra errores
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }

        });






        // Evento del botón modificar
        btnModificar.addActionListener(e -> {


            try {


                // Obtiene la fila seleccionada
                int fila = tabla.getSelectedRow();



                // Verifica que haya una fila seleccionada
                if (fila == -1) {

                    JOptionPane.showMessageDialog(this, "Seleccione un turno de la tabla");

                    return;

                }




                // Obtiene nuevos datos
                Integer id = Integer.parseInt(txtId.getText().trim());


                Paciente paciente = (Paciente) comboPaciente.getSelectedItem();


                Odontologo odonto = (Odontologo) comboOdonto.getSelectedItem();


                LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());


                LocalTime hora = LocalTime.parse(txtHora.getText().trim());




                // Busca el turno dentro de la lista usando stream
                Turno turno = sTurno.listarTodosLosTurnos()

                        .stream()

                        // Filtra buscando el ID igual al ingresado
                        .filter(t -> t.getId().equals(id))


                        // Obtiene el primero encontrado
                        .findFirst()


                        // Si no existe lanza error
                        .orElseThrow(() -> new Exception("No existe un turno con ese ID"));




                // Modifica los datos del turno
                turno.setPaciente(paciente);

                turno.setOdontologo(odonto);

                turno.setFecha(fecha);

                turno.setHora(hora);




                // Actualiza tabla
                cargarTabla(modeloTabla, sTurno);


                limpiar(txtId, txtFecha, txtHora);



                JOptionPane.showMessageDialog(this, "Turno modificado correctamente");



            } catch (Exception ex) {


                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            }

        });





        // Botón que limpia los campos
        btnLimpiar.addActionListener(e -> limpiar(txtId, txtFecha, txtHora));





        // Evento cuando se selecciona una fila de la tabla
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {


            public void mouseClicked(java.awt.event.MouseEvent e) {



                // Obtiene fila seleccionada
                int fila = tabla.getSelectedRow();



                // Carga datos de la tabla al formulario
                txtId.setText(tabla.getValueAt(fila, 0).toString());


                txtFecha.setText(tabla.getValueAt(fila, 3).toString());


                txtHora.setText(tabla.getValueAt(fila, 4).toString());

            }

        });



        // Muestra la ventana
        setVisible(true);

    }





    // Método que carga los turnos en la tabla
    private void cargarTabla(DefaultTableModel modeloTabla, ServicioTurno sTurno) {



        // Borra filas anteriores
        modeloTabla.setRowCount(0);




        // Recorre todos los turnos y los agrega
        sTurno.listarTodosLosTurnos().forEach(t -> {


            modeloTabla.addRow(new Object[]{


                    t.getId(),

                    // Nombre completo del paciente
                    t.getPaciente().getNombre() + " " + t.getPaciente().getApellido(),


                    // Nombre completo del odontólogo
                    t.getOdontologo().getNombre() + " " + t.getOdontologo().getApellido(),


                    t.getFecha(),

                    t.getHora()

            });


        });

    }





    // Limpia los campos del formulario
    private void limpiar(JTextField txtId, JTextField txtFecha, JTextField txtHora) {


        txtId.setText("");

        txtFecha.setText("");

        txtHora.setText("");

    }

}