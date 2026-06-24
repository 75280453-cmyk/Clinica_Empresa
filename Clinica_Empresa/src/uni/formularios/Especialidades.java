package uni.formularios;

import javax.swing.JOptionPane;
import uni.entity.EspecialidadTo;
import uni.dao.EspecialidadDao;

public class Especialidades extends javax.swing.JFrame {

    private EspecialidadDao especialidadDao = new EspecialidadDao();

    private javax.swing.JLabel lblIdEspecialidad, lblNombre, lblDescripcion, lblArea;
    private javax.swing.JTextField txtIdEspecialidad, txtNombre;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JComboBox<String> cboArea;
    private javax.swing.JButton btnAdicionar, btnModificar, btnEliminar, btnBuscar, btnCerrar;

    public Especialidades() {
        initComponents();
    }

    private void initComponents() {
        setTitle("MANTENIMIENTO DE ESPECIALIDADES");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(450, 300);

        lblIdEspecialidad = new javax.swing.JLabel("Codigo");
        txtIdEspecialidad = new javax.swing.JTextField();
        lblNombre         = new javax.swing.JLabel("Nombre");
        txtNombre         = new javax.swing.JTextField();
        lblDescripcion    = new javax.swing.JLabel("Descripcion");
        txtDescripcion    = new javax.swing.JTextArea(3, 20);
        lblArea           = new javax.swing.JLabel("Area");
        cboArea           = new javax.swing.JComboBox<>(new String[]{"CLINICA", "QUIRURGICA", "DIAGNOSTICO"});

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

        javax.swing.JPanel panelForm = new javax.swing.JPanel(new java.awt.GridLayout(4, 2, 8, 8));
        panelForm.add(lblIdEspecialidad); panelForm.add(txtIdEspecialidad);
        panelForm.add(lblNombre);         panelForm.add(txtNombre);
        panelForm.add(lblDescripcion);    panelForm.add(new javax.swing.JScrollPane(txtDescripcion));
        panelForm.add(lblArea);           panelForm.add(cboArea);

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

    private EspecialidadTo recogerDatos() {
        EspecialidadTo e = new EspecialidadTo();
        e.setIdespecialidad(txtIdEspecialidad.getText().trim());
        e.setNombre(txtNombre.getText().trim());
        e.setDescripcion(txtDescripcion.getText().trim());
        e.setArea((String) cboArea.getSelectedItem());
        return e;
    }

    private void limpiarCampos() {
        txtIdEspecialidad.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        cboArea.setSelectedIndex(0);
    }

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdEspecialidad.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Codigo y Nombre son obligatorios");
                return;
            }
            especialidadDao.create(recogerDatos());
            JOptionPane.showMessageDialog(this, "Especialidad registrada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al adicionar: " + ex.getMessage());
        }
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdEspecialidad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el codigo de la especialidad a modificar");
                return;
            }
            especialidadDao.update(recogerDatos());
            JOptionPane.showMessageDialog(this, "Especialidad modificada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdEspecialidad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el codigo de la especialidad a eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar esta especialidad?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                especialidadDao.delete(recogerDatos());
                JOptionPane.showMessageDialog(this, "Especialidad eliminada correctamente");
                limpiarCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String codigo = txtIdEspecialidad.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un codigo para buscar");
                return;
            }
            EspecialidadTo e = especialidadDao.find(codigo);
            if (e == null) {
                JOptionPane.showMessageDialog(this, "No se encontro la especialidad con codigo " + codigo);
                return;
            }
            txtNombre.setText(e.getNombre());
            txtDescripcion.setText(e.getDescripcion());
            cboArea.setSelectedItem(e.getArea());
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
            java.util.logging.Logger.getLogger(Especialidades.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Especialidades().setVisible(true);
        });
    }
}
