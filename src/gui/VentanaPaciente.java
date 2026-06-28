package gui;

// Importa la clase Paciente del modelo
import modelo.Paciente;

// Importa el servicio que maneja los pacientes
import servicio.ServicioPaciente;


// Librerías para interfaz gráfica
import javax.swing.*;

// Maneja los datos de la tabla
import javax.swing.table.DefaultTableModel;

import java.awt.*;

// Permite trabajar con fechas
import java.time.LocalDate;


// JFrame permite crear una ventana
public class VentanaPaciente extends JFrame {


    // Servicio encargado de registrar, modificar, eliminar y buscar pacientes
    private ServicioPaciente sPaciente;



    // Campos donde el usuario ingresa los datos del paciente
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCuil;



    // Tabla donde se muestran los pacientes
    private JTable tabla;

    // Controla los datos que aparecen en la tabla
    private DefaultTableModel modeloTabla;



    // Constructor de la ventana
    public VentanaPaciente(ServicioPaciente sPaciente) {


        // Guarda el servicio recibido
        this.sPaciente = sPaciente;



        // Configuración de la ventana
        setTitle("Gestión de Pacientes"); // Título
        setSize(700, 500); // Tamaño
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(new BorderLayout()); // Distribuye componentes



        // Panel que contiene los campos y botones
        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));



        // Campo para nombre
        formulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);



        // Campo para apellido
        formulario.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        formulario.add(txtApellido);



        // Campo para CUIL
        formulario.add(new JLabel("CUIL:"));
        txtCuil = new JTextField();
        formulario.add(txtCuil);



        // Creación de botones de acciones
        JButton btnAgregar = new JButton("Agregar Paciente");
        JButton btnModificar = new JButton("Modificar Paciente");
        JButton btnEliminar = new JButton("Eliminar Paciente");
        JButton btnLimpiar = new JButton("Limpiar");



        // Agrega botones al formulario
        formulario.add(btnAgregar);
        formulario.add(btnModificar);
        formulario.add(btnEliminar);
        formulario.add(btnLimpiar);



        // Coloca el formulario arriba de la ventana
        add(formulario, BorderLayout.NORTH);




        // Define las columnas que tendrá la tabla
        String[] columnas = {"Nombre", "Apellido", "CUIL"};


        // Crea el modelo de la tabla con esas columnas
        modeloTabla = new DefaultTableModel(columnas, 0);



        // Crea la tabla usando el modelo
        tabla = new JTable(modeloTabla);


        // Agrega barra de desplazamiento a la tabla
        JScrollPane scroll = new JScrollPane(tabla);



        // Coloca la tabla en el centro
        add(scroll, BorderLayout.CENTER);




        // Carga los pacientes existentes en la tabla
        cargarTabla();



        // Cada botón ejecuta un método diferente
        btnAgregar.addActionListener(e -> agregarPaciente());

        btnModificar.addActionListener(e -> modificarPaciente());

        btnEliminar.addActionListener(e -> eliminarPaciente());

        btnLimpiar.addActionListener(e -> limpiarCampos());



        // Evento al seleccionar una fila de la tabla
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {


            public void mouseClicked(java.awt.event.MouseEvent e) {


                // Guarda la fila seleccionada
                int fila = tabla.getSelectedRow();



                // Pasa los datos de la fila a los campos
                txtNombre.setText(tabla.getValueAt(fila, 0).toString());

                txtApellido.setText(tabla.getValueAt(fila, 1).toString());

                txtCuil.setText(tabla.getValueAt(fila, 2).toString());

            }
        });



        // Hace visible la ventana
        setVisible(true);
    }





    // Método para agregar un paciente
    private void agregarPaciente() {


        try {


            // Verifica que los campos estén completos
            validarCampos();



            // Obtiene los datos escritos
            String nombre = txtNombre.getText().trim();

            String apellido = txtApellido.getText().trim();


            // Convierte el CUIL de texto a número
            long cuil = Long.parseLong(txtCuil.getText().trim());



            // Crea un nuevo objeto paciente
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



            // Envía el paciente al servicio para registrarlo
            sPaciente.registrarPaciente(nuevo);



            // Actualiza tabla
            cargarTabla();


            // Limpia los campos
            limpiarCampos();



            // Mensaje de confirmación
            JOptionPane.showMessageDialog(this, "Paciente agregado correctamente");



        } catch (Exception ex) {


            // Muestra errores
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

    }





    // Método para modificar un paciente existente
    private void modificarPaciente() {


        try {


            // Valida los campos
            validarCampos();



            // Obtiene el CUIL del paciente
            long cuil = Long.parseLong(txtCuil.getText().trim());



            // Busca el paciente por CUIL
            Paciente paciente = sPaciente.buscarPorCuil(cuil);



            // Modifica sus datos
            paciente.setNombre(txtNombre.getText().trim());

            paciente.setApellido(txtApellido.getText().trim());



            // Actualiza tabla y limpia campos
            cargarTabla();

            limpiarCampos();



            JOptionPane.showMessageDialog(this, "Paciente modificado correctamente");



        } catch (Exception ex) {


            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

    }





    // Método para eliminar un paciente
    private void eliminarPaciente() {


        try {


            // Verifica que haya un CUIL ingresado
            if (txtCuil.getText().trim().isEmpty()) {


                JOptionPane.showMessageDialog(this, "Seleccione o ingrese un CUIL", "Error", JOptionPane.ERROR_MESSAGE);

                return;

            }



            // Convierte CUIL a número
            long cuil = Long.parseLong(txtCuil.getText().trim());



            // Pregunta confirmación antes de eliminar
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que querés eliminar este paciente?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );



            // Si acepta elimina el paciente
            if (opcion == JOptionPane.YES_OPTION) {


                sPaciente.eliminarPaciente(cuil);



                // Actualiza tabla
                cargarTabla();


                // Limpia campos
                limpiarCampos();



                JOptionPane.showMessageDialog(this, "Paciente eliminado correctamente");

            }



        } catch (Exception ex) {


            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

    }





    // Método que carga los pacientes en la tabla
    private void cargarTabla() {


        // Borra filas anteriores
        modeloTabla.setRowCount(0);



        // Recorre pacientes y agrega cada uno a la tabla
        sPaciente.listarTodos().forEach(p -> {


            modeloTabla.addRow(new Object[]{


                    p.getNombre(),

                    p.getApellido(),

                    p.getCuil()

            });


        });

    }





    // Limpia los campos del formulario
    private void limpiarCampos() {


        txtNombre.setText("");

        txtApellido.setText("");

        txtCuil.setText("");

    }





    // Valida que los datos estén completos
    private void validarCampos() throws Exception {


        // Revisa si algún campo está vacío
        if (txtNombre.getText().trim().isEmpty()
                || txtApellido.getText().trim().isEmpty()
                || txtCuil.getText().trim().isEmpty()) {


            // Lanza un error si falta información
            throw new Exception("Complete todos los campos");

        }



        // Verifica que el CUIL sea un número
        Long.parseLong(txtCuil.getText().trim());

    }

}