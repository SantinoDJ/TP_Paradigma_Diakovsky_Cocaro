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

    private ServicioPaciente sPaciente;
    private ServicioOdontologo sOdonto;
    private ServicioTurno sTurno;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private JTextField txtBuscar;

    private JButton btnBuscarPaciente;
    private JButton btnBuscarOdontologo;
    private JButton btnBuscarTurno;



    public VentanaBuscar(ServicioPaciente sPaciente,
                         ServicioOdontologo sOdonto,
                         ServicioTurno sTurno) {


        this.sPaciente = sPaciente;
        this.sOdonto = sOdonto;
        this.sTurno = sTurno;


        setTitle("Buscar");
        setSize(900,400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());



        JPanel panel = new JPanel();


        txtBuscar = new JTextField(15);


        btnBuscarPaciente = new JButton("Paciente");
        btnBuscarOdontologo = new JButton("Odontólogo");
        btnBuscarTurno = new JButton("Turno");



        panel.add(txtBuscar);
        panel.add(btnBuscarPaciente);
        panel.add(btnBuscarOdontologo);
        panel.add(btnBuscarTurno);



        modeloTabla = new DefaultTableModel();


        tabla = new JTable(modeloTabla);



        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);



        btnBuscarPaciente.addActionListener(e -> buscarPaciente());

        btnBuscarOdontologo.addActionListener(e -> buscarOdontologo());

        btnBuscarTurno.addActionListener(e -> buscarTurno());



        setVisible(true);

    }




    private void buscarPaciente(){


        modeloTabla.setRowCount(0);


        modeloTabla.setColumnIdentifiers(
                new Object[]{
                        "CUIL",
                        "Nombre",
                        "Apellido"
                });


        String texto = txtBuscar.getText();



        for(Paciente p : sPaciente.listarTodos()){


            if(texto.isEmpty()
                    || String.valueOf(p.getCuil()).contains(texto)
                    || p.getNombre().toLowerCase().contains(texto.toLowerCase())){


                modeloTabla.addRow(new Object[]{

                        p.getCuil(),
                        p.getNombre(),
                        p.getApellido()

                });

            }

        }

    }




    private void buscarOdontologo(){


        modeloTabla.setRowCount(0);


        modeloTabla.setColumnIdentifiers(
                new Object[]{
                        "ID",
                        "Nombre",
                        "Apellido",
                        "Matrícula"
                });



        String texto = txtBuscar.getText();



        for(Odontologo o : sOdonto.listarOdontologos()){


            if(texto.isEmpty()
                    || o.getNombre().toLowerCase().contains(texto.toLowerCase())
                    || o.getMatricula().contains(texto)){


                modeloTabla.addRow(new Object[]{

                        o.getId(),
                        o.getNombre(),
                        o.getApellido(),
                        o.getMatricula()

                });

            }

        }

    }




    private void buscarTurno(){


        modeloTabla.setRowCount(0);



        modeloTabla.setColumnIdentifiers(
                new Object[]{
                        "ID",
                        "Fecha",
                        "Hora",
                        "Paciente",
                        "Odontólogo"
                });



        String texto = txtBuscar.getText();



        for(Turno t : sTurno.listarTodosLosTurnos()){


            if(texto.isEmpty()
                    || String.valueOf(t.getId()).contains(texto)
                    || t.getFecha().toString().contains(texto)
                    || t.getPaciente().getNombre().toLowerCase().contains(texto.toLowerCase())){


                modeloTabla.addRow(new Object[]{

                        t.getId(),
                        t.getFecha(),
                        t.getHora(),
                        t.getPaciente().getNombre()
                                + " "
                                + t.getPaciente().getApellido(),

                        t.getOdontologo().getNombre()
                                + " "
                                + t.getOdontologo().getApellido()

                });

            }

        }

    }

}