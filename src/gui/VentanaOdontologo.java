package gui;

import modelo.Odontologo;
import servicio.ServicioOdontologo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class VentanaOdontologo extends JFrame {
    // extends JFrame: esta clase hereda de una ventana de Java Swing


    // Servicio que permite usar la lógica de odontólogos
    private ServicioOdontologo sOdonto;



    // Constructor de la ventana
    public VentanaOdontologo(ServicioOdontologo sOdonto) {


        // Guarda el servicio recibido
        this.sOdonto = sOdonto;



        // Título de la ventana
        setTitle("Gestión de Odontólogos");


        // Tamaño de la ventana
        setSize(850, 600);


        // Centra la ventana
        setLocationRelativeTo(null);


        // Organiza los componentes de la ventana
        setLayout(new BorderLayout());




        // Panel donde van los campos del formulario
        // GridLayout organiza los componentes en filas y columnas
        JPanel formulario = new JPanel(new GridLayout(6, 2));



        // Campo ID
        formulario.add(new JLabel("ID:"));
        JTextField txtId = new JTextField();
        formulario.add(txtId);



        // Campo Nombre
        formulario.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        formulario.add(txtNombre);



        // Campo Apellido
        formulario.add(new JLabel("Apellido:"));
        JTextField txtApellido = new JTextField();
        formulario.add(txtApellido);



        // Campo Matrícula
        formulario.add(new JLabel("Matrícula:"));
        JTextField txtMatricula = new JTextField();
        formulario.add(txtMatricula);




        // Creación de botones
        JButton btnAgregar = new JButton("Agregar Odontólogo");

        JButton btnModificar = new JButton("Modificar Odontólogo");

        JButton btnEliminar = new JButton("Eliminar Odontólogo");

        JButton btnLimpiar = new JButton("Limpiar");




        // Agrega los botones al formulario
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



        // Crea la tabla con el modelo
        JTable tabla = new JTable(modeloTabla);



        // JScrollPane permite desplazarse por la tabla
        add(new JScrollPane(tabla), BorderLayout.CENTER);




        // Carga los odontólogos existentes en la tabla
        cargarTabla(modeloTabla);






        // Detecta cuando se hace click en una fila de la tabla
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {


            public void mouseClicked(java.awt.event.MouseEvent e) {


                // Obtiene la fila seleccionada
                int fila = tabla.getSelectedRow();



                // Carga los datos de la fila en los campos
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


                // Verifica que los campos no estén vacíos
                if(txtId.getText().isEmpty()
                        || txtNombre.getText().isEmpty()
                        || txtApellido.getText().isEmpty()
                        || txtMatricula.getText().isEmpty()){



                    JOptionPane.showMessageDialog(
                            this,
                            "Complete todos los campos"
                    );


                    return;
                }




                // Convierte el texto del ID a número
                long id =
                        Long.parseLong(txtId.getText());



                // Crea un nuevo odontólogo
                Odontologo nuevo =
                        new Odontologo(
                                id,
                                txtNombre.getText(),
                                txtApellido.getText(),
                                txtMatricula.getText()
                        );



                // Lo registra usando el servicio
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



                JOptionPane.showMessageDialog(
                        this,
                        "Odontólogo agregado correctamente"
                );



            }catch(Exception ex){

                // Muestra el error
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );

            }

        });






        // Evento del botón modificar
        btnModificar.addActionListener(e -> {


            try {


                long id =
                        Long.parseLong(txtId.getText());



                // Busca el odontólogo existente
                Odontologo o =
                        sOdonto.buscarPorId(id);



                // Modifica sus datos
                o.setNombre(txtNombre.getText());
                o.setApellido(txtApellido.getText());
                o.setMatricula(txtMatricula.getText());



                // Actualiza la tabla
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


                long id =
                        Long.parseLong(txtId.getText());



                // Pregunta confirmación antes de eliminar
                int opcion =
                        JOptionPane.showConfirmDialog(
                                this,
                                "¿Eliminar odontólogo?",
                                "Confirmar",
                                JOptionPane.YES_NO_OPTION
                        );



                // Si acepta elimina
                if(opcion == JOptionPane.YES_OPTION){


                    sOdonto.eliminarOdontologo(id);



                    cargarTabla(modeloTabla);



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






        // Botón limpiar campos
        btnLimpiar.addActionListener(e -> {


            limpiar(
                    txtId,
                    txtNombre,
                    txtApellido,
                    txtMatricula
            );

        });




        // Muestra la ventana
        setVisible(true);

    }







    // Carga los odontólogos en la tabla
    private void cargarTabla(DefaultTableModel modeloTabla){


        // Borra filas anteriores
        modeloTabla.setRowCount(0);



        // Recorre la lista de odontólogos
        sOdonto.listarOdontologos()

                .forEach(o -> {
                    // forEach recorre cada elemento de la lista


                    // Agrega una fila por cada odontólogo
                    modeloTabla.addRow(new Object[]{

                            o.getId(),
                            o.getNombre(),
                            o.getApellido(),
                            o.getMatricula()

                    });


                });

    }







    // Limpia todos los campos de texto
    private void limpiar(
            JTextField id,
            JTextField nombre,
            JTextField apellido,
            JTextField matricula){


        id.setText("");

        nombre.setText("");

        apellido.setText("");

        matricula.setText("");

    }

}