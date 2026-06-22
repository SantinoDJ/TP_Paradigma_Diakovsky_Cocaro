package gui;

import servicio.ServicioPaciente;

import javax.swing.*;
import java.awt.*;

public class VentanaPaciente extends JFrame {

    private ServicioPaciente sPaciente;

    public VentanaPaciente(ServicioPaciente sPaciente) {

        this.sPaciente = sPaciente;

        setTitle("Gestión de Pacientes");
        setSize(700, 500);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel formulario = new JPanel();

        formulario.setLayout(new GridLayout(4,2));

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

        setVisible(true);
    }
}
