package uni.formularios;

import java.util.List;
import javax.swing.JOptionPane;
import uni.entity.MedicoTo;
import uni.entity.EspecialidadTo;
import uni.dao.MedicoDao;
import uni.dao.EspecialidadDao;

public class MedicoManView extends javax.swing.JFrame {

    private MedicoDao medicoDao = new MedicoDao();
    private EspecialidadDao especialidadDao = new EspecialidadDao();

    private javax.swing.JLabel lblIdMedico, lblNombre, lblDireccion, lblTelefono, lblEmail, lblCmp, lblEspecialidad;
    private javax.swing.JTextField txtIdMedico, txtNombre, txtDireccion, txtTelefono, txtEmail, txtCmp;
    private javax.swing.JComboBox<EspecialidadTo> cboEspecialidad;
    private javax.swing.JButton btnAdicionar, btnModificar, btnEliminar, btnBuscar, btnCerrar;

    public MedicoManView() {
        initComponents();
        cargarEspecialidades();
    }

    private void initComponents() {
        setTitle("MANTENIMIENTO DE MEDICOS");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(480, 320);

        lblIdMedico     = new javax.swing.JLabel("Codigo");
        txtIdMedico     = new javax.swing.JTextField();
        lblNombre       = new javax.swing.JLabel("Nombre");
        txtNombre       = new javax.swing.JTextField();
        lblDireccion    = new javax.swing.JLabel("Direccion");
        txtDireccion    = new javax.swing.JTextField();
        lblTelefono     = new javax.swing.JLabel("Telefono");
        txtTelefono     = new javax.swing.JTextField();
        lblEmail        = new javax.swing.JLabel("Email");
        txtEmail        = new javax.swing.JTextField();
        lblCmp          = new javax.swing.JLabel("CMP");
        txtCmp          = new javax.swing.JTextField();
        lblEspecialidad = new javax.swing.JLabel("Especialidad");
        cboEspecialidad = new javax.swing.JComboBox<>();

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
        panelForm.add(lblIdMedico);     panelForm.add(txtIdMedico);
        panelForm.add(lblNombre);       panelForm.add(txtNombre);
        panelForm.add(lblDireccion);    panelForm.add(txtDireccion);
        panelForm.add(lblTelefono);     panelForm.add(txtTelefono);
        panelForm.add(lblEmail);        panelForm.add(txtEmail);
        panelForm.add(lblCmp);          panelForm.add(txtCmp);
        panelForm.add(lblEspecialidad); panelForm.add(cboEspecialidad);

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

    private void cargarEspecialidades() {
        try {
            List<EspecialidadTo> lista = especialidadDao.readAll();
            cboEspecialidad.removeAllItems();
            for (EspecialidadTo e : lista) {
                cboEspecialidad.addItem(e);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar especialidades: " + ex.getMessage());
        }
    }

    private MedicoTo recogerDatos() {
        MedicoTo m = new MedicoTo();
        m.setIdmedico(txtIdMedico.getText().trim());
        m.setNombre(txtNombre.getText().trim());
        m.setDireccion(txtDireccion.getText().trim());
        m.setTelefono(txtTelefono.getText().trim());
        m.setEmail(txtEmail.getText().trim());
        m.setCmp(txtCmp.getText().trim());
        EspecialidadTo esp = (EspecialidadTo) cboEspecialidad.getSelectedItem();
        if (esp != null) {
            m.setIdespecialidad(esp.getIdespecialidad());
        }
        return m;
    }

    private void limpiarCampos() {
        txtIdMedico.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtCmp.setText("");
        if (cboEspecialidad.getItemCount() > 0) cboEspecialidad.setSelectedIndex(0);
    }

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdMedico.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Codigo y Nombre son obligatorios");
                return;
            }
            medicoDao.create(recogerDatos());
            JOptionPane.showMessageDialog(this, "Medico registrado correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al adicionar: " + ex.getMessage());
        }
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdMedico.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el codigo del medico a modificar");
                return;
            }
            medicoDao.update(recogerDatos());
            JOptionPane.showMessageDialog(this, "Medico modificado correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdMedico.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el codigo del medico a eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar este medico?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                medicoDao.delete(recogerDatos());
                JOptionPane.showMessageDialog(this, "Medico eliminado correctamente");
                limpiarCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String codigo = txtIdMedico.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un codigo para buscar");
                return;
            }
            MedicoTo m = medicoDao.find(codigo);
            if (m == null) {
                JOptionPane.showMessageDialog(this, "No se encontro el medico con codigo " + codigo);
                return;
            }
            txtNombre.setText(m.getNombre());
            txtDireccion.setText(m.getDireccion());
            txtTelefono.setText(m.getTelefono());
            txtEmail.setText(m.getEmail());
            txtCmp.setText(m.getCmp());
            for (int i = 0; i < cboEspecialidad.getItemCount(); i++) {
                EspecialidadTo e = cboEspecialidad.getItemAt(i);
                if (e.getIdespecialidad().equals(m.getIdespecialidad())) {
                    cboEspecialidad.setSelectedIndex(i);
                    break;
                }
            }
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
            java.util.logging.Logger.getLogger(MedicoManView.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new MedicoManView().setVisible(true);
        });
    }
}