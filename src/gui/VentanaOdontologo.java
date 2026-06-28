package gui;

// Importa la clase del modelo odontólogo
import modelo.Odontologo;

// Importa el servicio que maneja los odontólogos
import servicio.ServicioOdontologo;


// Librerías para crear la interfaz gráfica
import javax.swing.*;

// Permite manejar los datos de la tabla
import javax.swing.table.DefaultTableModel;

import java.awt.*;


// JFrame permite crear una ventana
public class VentanaOdontologo extends JFrame {


    // Servicio que permite agregar, modificar, eliminar y buscar odontólogos
    private ServicioOdontologo sOdonto;



    // Constructor de la ventana
    public VentanaOdontologo(ServicioOdontologo sOdonto) {


        // Guarda el servicio recibido
        this.sOdonto = sOdonto;


        // Configuración de la ventana
        setTitle("Gestión de Odontólogos"); // Título
        setSize(850, 600); // Tamaño
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(new BorderLayout()); // Organiza componentes



        // Panel que contiene los campos del formulario
        JPanel formulario = new JPanel(new GridLayout(6, 2));



        // Campo para ingresar ID
        formulario.add(new JLabel("ID:"));
        JTextField txtId = new JTextField();
        formulario.add(txtId);



        // Campo para ingresar nombre
        formulario.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        formulario.add(txtNombre);



        // Campo para ingresar apellido
        formulario.add(new JLabel("Apellido:"));
        JTextField txtApellido = new JTextField();
        formulario.add(txtApellido);



        // Campo para ingresar matrícula
        formulario.add(new JLabel("Matrícula:"));
        JTextField txtMatricula = new JTextField();
        formulario.add(txtMatricula);




        // Creación de botones de acciones
        JButton btnAgregar = new JButton("Agregar Odontólogo");
        JButton btnModificar = new JButton("Modificar Odontólogo");
        JButton btnEliminar = new JButton("Eliminar Odontólogo");
        JButton btnLimpiar = new JButton("Limpiar");



        // Agrega botones al formulario
        formulario.add(btnAgregar);
        formulario.add(btnModificar);
        formulario.add(btnEliminar);
        formulario.add(btnLimpiar);



        // Coloca el formulario arriba de la ventana
        add(formulario, BorderLayout.NORTH);




        // Columnas que tendrá la tabla
        String[] columnas = {
                "ID",
                "Nombre",
                "Apellido",
                "Matrícula"
        };



        // Modelo que controla los datos de la tabla
        DefaultTableModel modeloTabla =
                new DefaultTableModel(columnas,0);



        // Crea la tabla usando el modelo
        JTable tabla = new JTable(modeloTabla);



        // Agrega la tabla al centro con scroll
        add(new JScrollPane(tabla), BorderLayout.CENTER);




        // Carga los odontólogos existentes en la tabla
        cargarTabla(modeloTabla);




        // Evento cuando se selecciona una fila de la tabla
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {


            public void mouseClicked(java.awt.event.MouseEvent e) {


                // Guarda la fila seleccionada
                int fila = tabla.getSelectedRow();



                // Pasa los datos de la tabla a los campos del formulario
                txtId.setText(
                        tabla.getValueAt(fila,0).toString()
                );


                txtNombre.setText(
                        tabla.getValueAt(fila,1).toString()
                );


                txtApellido.setText(
                        tabla.getValueAt(fila,2).toString()
                );


                txtMatricula.setText(
                        tabla.getValueAt(fila,3).toString()
                );

            }

        });





        // Evento del botón agregar
        btnAgregar.addActionListener(e -> {


            try {


                // Verifica que ningún campo esté vacío
                if(txtId.getText().isEmpty()
                        || txtNombre.getText().isEmpty()
                        || txtApellido.getText().isEmpty()
                        || txtMatricula.getText().isEmpty()){



                    // Muestra mensaje de error
                    JOptionPane.showMessageDialog(
                            this,
                            "Complete todos los campos"
                    );


                    return;
                }




                // Convierte el texto del ID a número
                long id =
                        Long.parseLong(txtId.getText());



                // Crea un nuevo objeto odontólogo
                Odontologo nuevo =
                        new Odontologo(
                                id,
                                txtNombre.getText(),
                                txtApellido.getText(),
                                txtMatricula.getText()
                        );



                // Envía el odontólogo al servicio para registrarlo
                sOdonto.registrarOdontologo(nuevo);



                // Actualiza la tabla
                cargarTabla(modeloTabla);


                // Limpia los campos
                limpiar(
                        txtId,
                        txtNombre,
                        txtApellido,
                        txtMatricula
                );



                // Mensaje de confirmación
                JOptionPane.showMessageDialog(
                        this,
                        "Odontólogo agregado correctamente"
                );



            }catch(Exception ex){

                // Muestra errores
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );

            }

        });






        // Evento del botón modificar
        btnModificar.addActionListener(e -> {


            try {


                // Obtiene el ID del odontólogo
                long id =
                        Long.parseLong(txtId.getText());



                // Busca el odontólogo por ID
                Odontologo o =
                        sOdonto.buscarPorId(id);



                // Modifica sus datos
                o.setNombre(txtNombre.getText());
                o.setApellido(txtApellido.getText());
                o.setMatricula(txtMatricula.getText());



                // Actualiza tabla
                cargarTabla(modeloTabla);



                JOptionPane.showMessageDialog(
                        this,
                        "Odontólogo modificado"
                );


            }catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );

            }


        });







        // Evento del botón eliminar
        btnEliminar.addActionListener(e -> {


            try {


                // Obtiene el ID a eliminar
                long id =
                        Long.parseLong(txtId.getText());



                // Pregunta confirmación antes de borrar
                int opcion =
                        JOptionPane.showConfirmDialog(
                                this,
                                "¿Eliminar odontólogo?",
                                "Confirmar",
                                JOptionPane.YES_NO_OPTION
                        );



                // Si acepta elimina
                if(opcion == JOptionPane.YES_OPTION){



                    // Llama al servicio para eliminar
                    sOdonto.eliminarOdontologo(id);



                    // Actualiza la tabla
                    cargarTabla(modeloTabla);



                    // Limpia formulario
                    limpiar(
                            txtId,
                            txtNombre,
                            txtApellido,
                            txtMatricula
                    );



                    JOptionPane.showMessageDialog(
                            this,
                            "Odontólogo eliminado"
                    );

                }



            }catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );

            }

        });






        // Botón que limpia los campos
        btnLimpiar.addActionListener(e -> {


            limpiar(
                    txtId,
                    txtNombre,
                    txtApellido,
                    txtMatricula
            );

        });



        // Hace visible la ventana
        setVisible(true);

    }






    // Método que carga todos los odontólogos en la tabla
    private void cargarTabla(DefaultTableModel modeloTabla){



        // Borra filas anteriores
        modeloTabla.setRowCount(0);



        // Recorre la lista de odontólogos y los agrega a la tabla
        sOdonto.listarOdontologos()
                .forEach(o -> {



                    // Agrega una fila con los datos del odontólogo
                    modeloTabla.addRow(new Object[]{


                            o.getId(),
                            o.getNombre(),
                            o.getApellido(),
                            o.getMatricula()

                    });


                });

    }






    // Método para dejar vacíos los campos del formulario
    private void limpiar(JTextField id, JTextField nombre, JTextField apellido, JTextField matricula){

        id.setText("");
        nombre.setText("");
        apellido.setText("");
        matricula.setText("");

    }

}