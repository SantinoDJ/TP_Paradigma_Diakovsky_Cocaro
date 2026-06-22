package gui;

import modelo.Odontologo;
import servicio.ServicioOdontologo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaOdontologo extends JFrame {

    private ServicioOdontologo sOdonto;

    public VentanaOdontologo(ServicioOdontologo sOdonto) {

        this.sOdonto = sOdonto;

        setTitle("Gestión de Odontólogos");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        JPanel formulario = new JPanel(new GridLayout(6, 2));


        formulario.add(new JLabel("ID:"));
        JTextField txtId = new JTextField();
        formulario.add(txtId);


        formulario.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        formulario.add(txtNombre);


        formulario.add(new JLabel("Apellido:"));
        JTextField txtApellido = new JTextField();
        formulario.add(txtApellido);


        formulario.add(new JLabel("Matrícula:"));
        JTextField txtMatricula = new JTextField();
        formulario.add(txtMatricula);



        JButton btnAgregar = new JButton("Agregar Odontólogo");
        JButton btnModificar = new JButton("Modificar Odontólogo");
        JButton btnEliminar = new JButton("Eliminar Odontólogo");
        JButton btnLimpiar = new JButton("Limpiar");


        formulario.add(btnAgregar);
        formulario.add(btnModificar);
        formulario.add(btnEliminar);
        formulario.add(btnLimpiar);



        add(formulario, BorderLayout.NORTH);



        String[] columnas = {
                "ID",
                "Nombre",
                "Apellido",
                "Matrícula"
        };


        DefaultTableModel modeloTabla =
                new DefaultTableModel(columnas,0);



        JTable tabla = new JTable(modeloTabla);


        add(new JScrollPane(tabla), BorderLayout.CENTER);



        cargarTabla(modeloTabla);



        tabla.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent e) {


                int fila = tabla.getSelectedRow();


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




        btnAgregar.addActionListener(e -> {

            try {


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



                long id =
                        Long.parseLong(txtId.getText());


                Odontologo nuevo =
                        new Odontologo(
                                id,
                                txtNombre.getText(),
                                txtApellido.getText(),
                                txtMatricula.getText()
                        );


                sOdonto.registrarOdontologo(nuevo);



                cargarTabla(modeloTabla);

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

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );

            }

        });





        btnModificar.addActionListener(e -> {


            try {


                long id =
                        Long.parseLong(txtId.getText());


                Odontologo o =
                        sOdonto.buscarPorId(id);



                o.setNombre(txtNombre.getText());
                o.setApellido(txtApellido.getText());
                o.setMatricula(txtMatricula.getText());



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






        btnEliminar.addActionListener(e -> {


            try {


                long id =
                        Long.parseLong(txtId.getText());



                int opcion =
                        JOptionPane.showConfirmDialog(
                                this,
                                "¿Eliminar odontólogo?",
                                "Confirmar",
                                JOptionPane.YES_NO_OPTION
                        );



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





        btnLimpiar.addActionListener(e -> {

            limpiar(
                    txtId,
                    txtNombre,
                    txtApellido,
                    txtMatricula
            );

        });



        setVisible(true);

    }




    private void cargarTabla(DefaultTableModel modeloTabla){


        modeloTabla.setRowCount(0);



        sOdonto.listarOdontologos()
                .forEach(o -> {


                    modeloTabla.addRow(new Object[]{

                            o.getId(),
                            o.getNombre(),
                            o.getApellido(),
                            o.getMatricula()

                    });


                });

    }





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