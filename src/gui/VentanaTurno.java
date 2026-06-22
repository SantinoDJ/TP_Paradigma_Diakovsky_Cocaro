package gui;

import modelo.Odontologo;
import modelo.Paciente;
import modelo.Turno;
import servicio.ServicioOdontologo;
import servicio.ServicioPaciente;
import servicio.ServicioTurno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VentanaTurno extends JFrame {

    public VentanaTurno(ServicioTurno sTurno, ServicioPaciente sPaciente, ServicioOdontologo sOdonto) {

        setTitle("Gestión de Turnos");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formulario.add(new JLabel("ID:"));
        JTextField txtId = new JTextField();
        formulario.add(txtId);

        formulario.add(new JLabel("Paciente:"));
        JComboBox<Paciente> comboPaciente = new JComboBox<>();
        sPaciente.listarTodos().forEach(comboPaciente::addItem);
        formulario.add(comboPaciente);

        formulario.add(new JLabel("Odontólogo:"));
        JComboBox<Odontologo> comboOdonto = new JComboBox<>();
        sOdonto.listarOdontologos().forEach(comboOdonto::addItem);
        formulario.add(comboOdonto);

        formulario.add(new JLabel("Fecha:"));
        JTextField txtFecha = new JTextField("2026-06-22");
        formulario.add(txtFecha);

        formulario.add(new JLabel("Hora:"));
        JTextField txtHora = new JTextField("10:00");
        formulario.add(txtHora);

        add(formulario, BorderLayout.NORTH);

        String[] columnas = {"ID", "Paciente", "Odontólogo", "Fecha", "Hora"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

        JTable tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAgregar = new JButton("Agregar Turno");
        JButton btnModificar = new JButton("Modificar Turno");
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnLimpiar);

        add(panelBotones, BorderLayout.SOUTH);

        cargarTabla(modeloTabla, sTurno);

        btnAgregar.addActionListener(e -> {
            try {
                if (txtId.getText().trim().isEmpty()
                        || txtFecha.getText().trim().isEmpty()
                        || txtHora.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Complete todos los campos");
                    return;
                }

                Integer id = Integer.parseInt(txtId.getText().trim());
                Paciente paciente = (Paciente) comboPaciente.getSelectedItem();
                Odontologo odonto = (Odontologo) comboOdonto.getSelectedItem();
                LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
                LocalTime hora = LocalTime.parse(txtHora.getText().trim());

                Turno nuevo = new Turno(id, fecha, hora, paciente, odonto);

                sTurno.agendarTurno(nuevo);

                cargarTabla(modeloTabla, sTurno);
                limpiar(txtId, txtFecha, txtHora);

                JOptionPane.showMessageDialog(this, "Turno agregado correctamente");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            try {
                int fila = tabla.getSelectedRow();

                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione un turno de la tabla");
                    return;
                }

                Integer id = Integer.parseInt(txtId.getText().trim());
                Paciente paciente = (Paciente) comboPaciente.getSelectedItem();
                Odontologo odonto = (Odontologo) comboOdonto.getSelectedItem();
                LocalDate fecha = LocalDate.parse(txtFecha.getText().trim());
                LocalTime hora = LocalTime.parse(txtHora.getText().trim());

                Turno turno = sTurno.listarTodosLosTurnos()
                        .stream()
                        .filter(t -> t.getId().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new Exception("No existe un turno con ese ID"));

                turno.setPaciente(paciente);
                turno.setOdontologo(odonto);
                turno.setFecha(fecha);
                turno.setHora(hora);

                cargarTabla(modeloTabla, sTurno);
                limpiar(txtId, txtFecha, txtHora);

                JOptionPane.showMessageDialog(this, "Turno modificado correctamente");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpiar.addActionListener(e -> limpiar(txtId, txtFecha, txtHora));

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = tabla.getSelectedRow();

                txtId.setText(tabla.getValueAt(fila, 0).toString());
                txtFecha.setText(tabla.getValueAt(fila, 3).toString());
                txtHora.setText(tabla.getValueAt(fila, 4).toString());
            }
        });

        setVisible(true);
    }

    private void cargarTabla(DefaultTableModel modeloTabla, ServicioTurno sTurno) {
        modeloTabla.setRowCount(0);

        sTurno.listarTodosLosTurnos().forEach(t -> {
            modeloTabla.addRow(new Object[]{
                    t.getId(),
                    t.getPaciente().getNombre() + " " + t.getPaciente().getApellido(),
                    t.getOdontologo().getNombre() + " " + t.getOdontologo().getApellido(),
                    t.getFecha(),
                    t.getHora()
            });
        });
    }

    private void limpiar(JTextField txtId, JTextField txtFecha, JTextField txtHora) {
        txtId.setText("");
        txtFecha.setText("");
        txtHora.setText("");
    }
}