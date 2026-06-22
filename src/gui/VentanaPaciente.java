package gui;

import modelo.Paciente;
import servicio.ServicioPaciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class VentanaPaciente extends JFrame {

    private ServicioPaciente sPaciente;

    public VentanaPaciente(ServicioPaciente sPaciente) {

        this.sPaciente = sPaciente;

        setTitle("Gestión de Pacientes");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ==========================
        // FORMULARIO
        // ==========================

        JPanel formulario = new JPanel();

        formulario.setLayout(new GridLayout(4, 2));

        formulario.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        formulario.add(txtNombre);

        formulario.add(new JLabel("Apellido:"));
        JTextField txtApellido = new JTextField();
        formulario.add(txtApellido);

        formulario.add(new JLabel("CUIL:"));
        JTextField txtCuil = new JTextField();
        formulario.add(txtCuil);

        JButton btnAgregar = new JButton("Agregar Paciente");
        formulario.add(btnAgregar);

        add(formulario, BorderLayout.NORTH);

        // ==========================
        // TABLA DE PACIENTES
        // ==========================

        String[] columnas = {
                "Nombre",
                "Apellido",
                "CUIL"
        };

        final DefaultTableModel modeloTabla =
                new DefaultTableModel(columnas, 0);

        sPaciente.listarTodos().forEach(p -> {

            modeloTabla.addRow(new Object[]{
                    p.getNombre(),
                    p.getApellido(),
                    p.getCuil()
            });

        });

        JTable tabla = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        // ==========================
        // BOTÓN AGREGAR
        // ==========================

        btnAgregar.addActionListener(e -> {

            try {

                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String cuilTexto = txtCuil.getText().trim();

                if (nombre.isEmpty() ||
                        apellido.isEmpty() ||
                        cuilTexto.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Complete todos los campos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                long cuil = Long.parseLong(cuilTexto);

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

                sPaciente.registrarPaciente(nuevo);

                modeloTabla.addRow(new Object[]{
                        nuevo.getNombre(),
                        nuevo.getApellido(),
                        nuevo.getCuil()
                });

                txtNombre.setText("");
                txtApellido.setText("");
                txtCuil.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "Paciente agregado correctamente"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        setVisible(true);
    }
}