package uni.formularios;

import javax.swing.JOptionPane;
import uni.entity.PacienteTo;
import uni.dao.PacienteDao;

public class PacienteManView extends javax.swing.JFrame {

    private PacienteDao pacienteDao = new PacienteDao();

    private javax.swing.JLabel lblIdPaciente, lblNombre, lblDireccion, lblTelefono, lblEmail, lblFechaNacimiento, lblTipoPaciente;
    private javax.swing.JTextField txtIdPaciente, txtNombre, txtDireccion, txtTelefono, txtEmail, txtFechaNacimiento;
    private javax.swing.JComboBox<String> cboTipoPaciente;
    private javax.swing.JButton btnAdicionar, btnModificar, btnEliminar, btnBuscar, btnCerrar;

    public PacienteManView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("MANTENIMIENTO DE PACIENTES");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(480, 320);

        lblIdPaciente      = new javax.swing.JLabel("Codigo");
        txtIdPaciente      = new javax.swing.JTextField();
        lblNombre          = new javax.swing.JLabel("Nombre");
        txtNombre          = new javax.swing.JTextField();
        lblDireccion       = new javax.swing.JLabel("Direccion");
        txtDireccion       = new javax.swing.JTextField();
        lblTelefono        = new javax.swing.JLabel("Telefono");
        txtTelefono        = new javax.swing.JTextField();
        lblEmail           = new javax.swing.JLabel("Email");
        txtEmail           = new javax.swing.JTextField();
        lblFechaNacimiento = new javax.swing.JLabel("Fecha Nac. (yyyy-MM-dd)");
        txtFechaNacimiento = new javax.swing.JTextField();
        lblTipoPaciente    = new javax.swing.JLabel("Tipo Paciente");
        cboTipoPaciente    = new javax.swing.JComboBox<>(new String[]{"PARTICULAR", "ASEGURADO", "CONVENIO"});

        btnAdicionar = new javax.swing.JButton("Adicionar");
        btnModificar = new javax.swing.JButton("Modificar");
        btnEliminar  = new javax.swing.JButton("Eliminar");
        btnBuscar    = new javax.swing.JButton("Buscar");
        btnCerrar    = new javax.swing.JButton("Cerrar");

        btnAdicionar.addActionListener(evt -> btnAdicionarActionPerformed(evt));
        btnModificar.addActionListener(evt -> btnModificarActionPerformed(evt));
        btnEliminar.addActionListener(evt -> btnEliminarActionPerformed(evt));
        btnBuscar.addActionListener(evt -> btnBuscarActionPerformed(evt));
        btnCerrar.addActionListener(evt -> btnCerrarActionPerformed(evt));

        javax.swing.JPanel panelForm = new javax.swing.JPanel(new java.awt.GridLayout(7, 2, 8, 8));
        panelForm.add(lblIdPaciente);      panelForm.add(txtIdPaciente);
        panelForm.add(lblNombre);          panelForm.add(txtNombre);
        panelForm.add(lblDireccion);       panelForm.add(txtDireccion);
        panelForm.add(lblTelefono);        panelForm.add(txtTelefono);
        panelForm.add(lblEmail);           panelForm.add(txtEmail);
        panelForm.add(lblFechaNacimiento); panelForm.add(txtFechaNacimiento);
        panelForm.add(lblTipoPaciente);    panelForm.add(cboTipoPaciente);

        javax.swing.JPanel panelBotones = new javax.swing.JPanel(new java.awt.FlowLayout());
        panelBotones.add(btnAdicionar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnCerrar);

        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));
        getContentPane().add(panelForm, java.awt.BorderLayout.CENTER);
        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);

        pack();
    }

    private PacienteTo recogerDatos() {
        PacienteTo p = new PacienteTo();
        p.setIdpaciente(txtIdPaciente.getText().trim());
        p.setNombre(txtNombre.getText().trim());
        p.setDireccion(txtDireccion.getText().trim());
        p.setTelefono(txtTelefono.getText().trim());
        p.setEmail(txtEmail.getText().trim());
        p.setFechaNacimiento(txtFechaNacimiento.getText().trim());
        p.setTipoPaciente((String) cboTipoPaciente.getSelectedItem());
        return p;
    }

    private void limpiarCampos() {
        txtIdPaciente.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtFechaNacimiento.setText("");
        cboTipoPaciente.setSelectedIndex(0);
    }

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdPaciente.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Codigo y Nombre son obligatorios");
                return;
            }
            pacienteDao.create(recogerDatos());
            JOptionPane.showMessageDialog(this, "Paciente registrado correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al adicionar: " + ex.getMessage());
        }
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdPaciente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el codigo del paciente a modificar");
                return;
            }
            pacienteDao.update(recogerDatos());
            JOptionPane.showMessageDialog(this, "Paciente modificado correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdPaciente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el codigo del paciente a eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar este paciente?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                pacienteDao.delete(recogerDatos());
                JOptionPane.showMessageDialog(this, "Paciente eliminado correctamente");
                limpiarCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String codigo = txtIdPaciente.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un codigo para buscar");
                return;
            }
            PacienteTo p = pacienteDao.find(codigo);
            if (p == null) {
                JOptionPane.showMessageDialog(this, "No se encontro el paciente con codigo " + codigo);
                return;
            }
            txtNombre.setText(p.getNombre());
            txtDireccion.setText(p.getDireccion());
            txtTelefono.setText(p.getTelefono());
            txtEmail.setText(p.getEmail());
            txtFechaNacimiento.setText(p.getFechaNacimiento());
            cboTipoPaciente.setSelectedItem(p.getTipoPaciente());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage());
        }
    }

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PacienteManView.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new PacienteManView().setVisible(true);
        });
    }
}
