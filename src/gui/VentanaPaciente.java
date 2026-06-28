package gui;

import modelo.Paciente;
import servicio.ServicioPaciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;


public class VentanaPaciente extends JFrame {
    // extends JFrame: esta clase hereda de una ventana de Java Swing



    // Servicio que maneja la lógica de pacientes
    private ServicioPaciente sPaciente;



    // Campos de texto donde el usuario ingresa datos
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCuil;



    // Tabla donde se muestran los pacientes
    private JTable tabla;


    // Modelo que controla los datos de la tabla
    private DefaultTableModel modeloTabla;




    // Constructor de la ventana
    public VentanaPaciente(ServicioPaciente sPaciente) {


        // Guarda el servicio recibido
        this.sPaciente = sPaciente;



        // Título de la ventana
        setTitle("Gestión de Pacientes");



        // Tamaño de la ventana
        setSize(700, 500);



        // Centra la ventana
        setLocationRelativeTo(null);



        // Organiza los componentes
        setLayout(new BorderLayout());





        // Panel del formulario
        // GridLayout organiza los elementos en filas y columnas
        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));




        // Campo nombre
        formulario.add(new JLabel("Nombre:"));

        txtNombre = new JTextField();

        formulario.add(txtNombre);




        // Campo apellido
        formulario.add(new JLabel("Apellido:"));

        txtApellido = new JTextField();

        formulario.add(txtApellido);




        // Campo CUIL
        formulario.add(new JLabel("CUIL:"));

        txtCuil = new JTextField();

        formulario.add(txtCuil);





        // Botones de acciones
        JButton btnAgregar = new JButton("Agregar Paciente");

        JButton btnModificar = new JButton("Modificar Paciente");

        JButton btnEliminar = new JButton("Eliminar Paciente");

        JButton btnLimpiar = new JButton("Limpiar");




        // Agrega botones al formulario
        formulario.add(btnAgregar);
        formulario.add(btnModificar);
        formulario.add(btnEliminar);
        formulario.add(btnLimpiar);




        // Coloca el formulario arriba
        add(formulario, BorderLayout.NORTH);





        // Columnas que tendrá la tabla
        String[] columnas = {
                "Nombre",
                "Apellido",
                "CUIL"
        };



        // Crea el modelo de la tabla
        modeloTabla = new DefaultTableModel(columnas, 0);



        // Crea la tabla usando el modelo
        tabla = new JTable(modeloTabla);



        // JScrollPane permite desplazarse por la tabla
        JScrollPane scroll = new JScrollPane(tabla);



        // Agrega la tabla al centro
        add(scroll, BorderLayout.CENTER);




        // Carga los pacientes existentes
        cargarTabla();





        // Cuando se hace click llama al método correspondiente
        btnAgregar.addActionListener(e -> agregarPaciente());

        btnModificar.addActionListener(e -> modificarPaciente());

        btnEliminar.addActionListener(e -> eliminarPaciente());

        btnLimpiar.addActionListener(e -> limpiarCampos());





        // Detecta cuando se selecciona una fila de la tabla
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {


            public void mouseClicked(java.awt.event.MouseEvent e) {


                // Obtiene la fila seleccionada
                int fila = tabla.getSelectedRow();



                // Carga los datos seleccionados en los campos
                txtNombre.setText(
                        tabla.getValueAt(fila, 0).toString()
                );


                txtApellido.setText(
                        tabla.getValueAt(fila, 1).toString()
                );


                txtCuil.setText(
                        tabla.getValueAt(fila, 2).toString()
                );

            }

        });




        // Hace visible la ventana
        setVisible(true);

    }






    // Método para agregar pacientes
    private void agregarPaciente() {


        try {


            // Valida que los campos tengan datos
            validarCampos();



            // Obtiene los datos ingresados
            String nombre = txtNombre.getText().trim();

            String apellido = txtApellido.getText().trim();


            // Convierte el texto del CUIL a número
            long cuil = Long.parseLong(txtCuil.getText().trim());



            // Crea un nuevo objeto Paciente
            Paciente nuevo = new Paciente(

                    0,
                    nombre,
                    apellido,
                    0,
                    cuil,
                    0,
                    "",
                    LocalDate.now(),
                    null

            );



            // Envía el paciente al servicio para guardarlo
            sPaciente.registrarPaciente(nuevo);



            // Actualiza la tabla
            cargarTabla();



            // Limpia los campos
            limpiarCampos();



            JOptionPane.showMessageDialog(
                    this,
                    "Paciente agregado correctamente"
            );



        } catch (Exception ex) {

            // Captura errores y muestra mensaje
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }








    // Modifica un paciente existente
    private void modificarPaciente() {

        try {


            validarCampos();



            long cuil =
                    Long.parseLong(txtCuil.getText().trim());



            // Busca el paciente por CUIL
            Paciente paciente =
                    sPaciente.buscarPorCuil(cuil);



            // Cambia sus datos
            paciente.setNombre(
                    txtNombre.getText().trim()
            );


            paciente.setApellido(
                    txtApellido.getText().trim()
            );



            cargarTabla();

            limpiarCampos();



            JOptionPane.showMessageDialog(
                    this,
                    "Paciente modificado correctamente"
            );



        } catch(Exception ex){

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }








    // Elimina un paciente
    private void eliminarPaciente() {

        try {


            // Verifica que haya un CUIL escrito
            if(txtCuil.getText().trim().isEmpty()){


                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione o ingrese un CUIL",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );


                return;
            }



            long cuil =
                    Long.parseLong(txtCuil.getText().trim());



            // Pregunta confirmación antes de eliminar
            int opcion =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Seguro que querés eliminar este paciente?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                    );



            // Si acepta elimina
            if(opcion == JOptionPane.YES_OPTION){


                sPaciente.eliminarPaciente(cuil);



                cargarTabla();


                limpiarCampos();


                JOptionPane.showMessageDialog(
                        this,
                        "Paciente eliminado correctamente"
                );

            }



        } catch(Exception ex){


            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }








    // Carga los pacientes en la tabla
    private void cargarTabla() {


        // Borra las filas anteriores
        modeloTabla.setRowCount(0);



        // Recorre la lista de pacientes
        sPaciente.listarTodos()

                .forEach(p -> {
                    // forEach recorre cada paciente de la lista



                    // Agrega una fila con los datos
                    modeloTabla.addRow(new Object[]{

                            p.getNombre(),
                            p.getApellido(),
                            p.getCuil()

                    });


                });

    }







    // Limpia los campos de texto
    private void limpiarCampos() {


        txtNombre.setText("");

        txtApellido.setText("");

        txtCuil.setText("");

    }







    // Verifica que los datos estén completos
    private void validarCampos() throws Exception {


        // trim() elimina espacios al principio y al final
        if(txtNombre.getText().trim().isEmpty()

                || txtApellido.getText().trim().isEmpty()

                || txtCuil.getText().trim().isEmpty()){


            throw new Exception("Complete todos los campos");

        }



        // Convierte texto a número para validar que sea correcto
        Long.parseLong(txtCuil.getText().trim());

    }

}