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
        JButton btnLimpiar = new JButton("Limpiar");

        formulario.add(btnAgregar);
        formulario.add(btnModificar);
        formulario.add(btnLimpiar);

        add(formulario, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Apellido", "Matrícula"};

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

        JTable tabla = new JTable(modeloTabla);

        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        cargarTabla(modeloTabla);

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {

                int fila = tabla.getSelectedRow();

                txtId.setText(tabla.getValueAt(fila, 0).toString());
                txtNombre.setText(tabla.getValueAt(fila, 1).toString());
                txtApellido.setText(tabla.getValueAt(fila, 2).toString());
                txtMatricula.setText(tabla.getValueAt(fila, 3).toString());
            }
        });

        btnAgregar.addActionListener(e -> {

            try {
                String idTexto = txtId.getText().trim();
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String matricula = txtMatricula.getText().trim();

                if (idTexto.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || matricula.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Complete todos los campos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                long id = Long.parseLong(idTexto);

                Odontologo nuevo = new Odontologo(id, nombre, apellido, matricula);

                sOdonto.registrarOdontologo(nuevo);

                cargarTabla(modeloTabla);

                txtId.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtMatricula.setText("");

                JOptionPane.showMessageDialog(this, "Odontólogo agregado correctamente");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnModificar.addActionListener(e -> {

            try {
                String idTexto = txtId.getText().trim();
                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String matricula = txtMatricula.getText().trim();

                if (idTexto.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || matricula.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Seleccione o complete todos los campos");
                    return;
                }

                long id = Long.parseLong(idTexto);

                Odontologo odontologo = sOdonto.buscarPorId(id);

                odontologo.setNombre(nombre);
                odontologo.setApellido(apellido);
                odontologo.setMatricula(matricula);

                cargarTabla(modeloTabla);

                txtId.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtMatricula.setText("");

                JOptionPane.showMessageDialog(this, "Odontólogo modificado correctamente");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnLimpiar.addActionListener(e -> {
            txtId.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtMatricula.setText("");
        });

        setVisible(true);
    }

    private void cargarTabla(DefaultTableModel modeloTabla) {

        modeloTabla.setRowCount(0);

        sOdonto.listarOdontologos().forEach(o -> {

            modeloTabla.addRow(new Object[]{
                    o.getId(),
                    o.getNombre(),
                    o.getApellido(),
                    o.getMatricula()
            });
        });
    }
}