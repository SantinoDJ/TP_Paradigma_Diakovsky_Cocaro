package gui;

import modelo.Paciente;
import servicio.ServicioPaciente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class VentanaPaciente extends JFrame {

    private ServicioPaciente sPaciente;

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCuil;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public VentanaPaciente(ServicioPaciente sPaciente) {

        this.sPaciente = sPaciente;

        setTitle("Gestión de Pacientes");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));

        formulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);

        formulario.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        formulario.add(txtApellido);

        formulario.add(new JLabel("CUIL:"));
        txtCuil = new JTextField();
        formulario.add(txtCuil);

        JButton btnAgregar = new JButton("Agregar Paciente");
        JButton btnModificar = new JButton("Modificar Paciente");
        JButton btnEliminar = new JButton("Eliminar Paciente");
        JButton btnLimpiar = new JButton("Limpiar");

        formulario.add(btnAgregar);
        formulario.add(btnModificar);
        formulario.add(btnEliminar);
        formulario.add(btnLimpiar);

        add(formulario, BorderLayout.NORTH);

        String[] columnas = {"Nombre", "Apellido", "CUIL"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        cargarTabla();

        btnAgregar.addActionListener(e -> agregarPaciente());
        btnModificar.addActionListener(e -> modificarPaciente());
        btnEliminar.addActionListener(e -> eliminarPaciente());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = tabla.getSelectedRow();

                txtNombre.setText(tabla.getValueAt(fila, 0).toString());
                txtApellido.setText(tabla.getValueAt(fila, 1).toString());
                txtCuil.setText(tabla.getValueAt(fila, 2).toString());
            }
        });

        setVisible(true);
    }

    private void agregarPaciente() {
        try {
            validarCampos();

            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            long cuil = Long.parseLong(txtCuil.getText().trim());

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

            cargarTabla();
            limpiarCampos();

            JOptionPane.showMessageDialog(this, "Paciente agregado correctamente");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarPaciente() {
        try {
            validarCampos();

            long cuil = Long.parseLong(txtCuil.getText().trim());

            Paciente paciente = sPaciente.buscarPorCuil(cuil);

            paciente.setNombre(txtNombre.getText().trim());
            paciente.setApellido(txtApellido.getText().trim());

            cargarTabla();
            limpiarCampos();

            JOptionPane.showMessageDialog(this, "Paciente modificado correctamente");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPaciente() {
        try {
            if (txtCuil.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Seleccione o ingrese un CUIL", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            long cuil = Long.parseLong(txtCuil.getText().trim());

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que querés eliminar este paciente?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                sPaciente.eliminarPaciente(cuil);

                cargarTabla();
                limpiarCampos();

                JOptionPane.showMessageDialog(this, "Paciente eliminado correctamente");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);

        sPaciente.listarTodos().forEach(p -> {
            modeloTabla.addRow(new Object[]{
                    p.getNombre(),
                    p.getApellido(),
                    p.getCuil()
            });
        });
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCuil.setText("");
    }

    private void validarCampos() throws Exception {
        if (txtNombre.getText().trim().isEmpty()
                || txtApellido.getText().trim().isEmpty()
                || txtCuil.getText().trim().isEmpty()) {
            throw new Exception("Complete todos los campos");
        }

        Long.parseLong(txtCuil.getText().trim());
    }
}