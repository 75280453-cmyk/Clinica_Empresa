package uni.formularios;

import javax.swing.JOptionPane;
import uni.entity.SedeTo;
import uni.dao.SedeDao;

public class SedeManView extends javax.swing.JFrame {

    private SedeDao sedeDao = new SedeDao();

    private javax.swing.JLabel lblIdSede, lblNombre, lblDireccion, lblTelefono, lblEmail, lblDistrito, lblCiudad;
    private javax.swing.JTextField txtIdSede, txtNombre, txtDireccion, txtTelefono, txtEmail, txtDistrito, txtCiudad;
    private javax.swing.JButton btnAdicionar, btnModificar, btnEliminar, btnBuscar, btnCerrar;

    public SedeManView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("MANTENIMIENTO DE SEDES");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(480, 320);

        lblIdSede    = new javax.swing.JLabel("Codigo");
        txtIdSede    = new javax.swing.JTextField();
        lblNombre    = new javax.swing.JLabel("Nombre");
        txtNombre    = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel("Direccion");
        txtDireccion = new javax.swing.JTextField();
        lblTelefono  = new javax.swing.JLabel("Telefono");
        txtTelefono  = new javax.swing.JTextField();
        lblEmail     = new javax.swing.JLabel("Email");
        txtEmail     = new javax.swing.JTextField();
        lblDistrito  = new javax.swing.JLabel("Distrito");
        txtDistrito  = new javax.swing.JTextField();
        lblCiudad    = new javax.swing.JLabel("Ciudad");
        txtCiudad    = new javax.swing.JTextField();

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
        panelForm.add(lblIdSede);    panelForm.add(txtIdSede);
        panelForm.add(lblNombre);    panelForm.add(txtNombre);
        panelForm.add(lblDireccion); panelForm.add(txtDireccion);
        panelForm.add(lblTelefono);  panelForm.add(txtTelefono);
        panelForm.add(lblEmail);     panelForm.add(txtEmail);
        panelForm.add(lblDistrito);  panelForm.add(txtDistrito);
        panelForm.add(lblCiudad);    panelForm.add(txtCiudad);

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

    private SedeTo recogerDatos() {
        SedeTo s = new SedeTo();
        s.setIdsede(txtIdSede.getText().trim());
        s.setNombre(txtNombre.getText().trim());
        s.setDireccion(txtDireccion.getText().trim());
        s.setTelefono(txtTelefono.getText().trim());
        s.setEmail(txtEmail.getText().trim());
        s.setDistrito(txtDistrito.getText().trim());
        s.setCiudad(txtCiudad.getText().trim());
        return s;
    }

    private void limpiarCampos() {
        txtIdSede.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDistrito.setText("");
        txtCiudad.setText("");
    }

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdSede.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Codigo y Nombre son obligatorios");
                return;
            }
            sedeDao.create(recogerDatos());
            JOptionPane.showMessageDialog(this, "Sede registrada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al adicionar: " + ex.getMessage());
        }
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdSede.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el codigo de la sede a modificar");
                return;
            }
            sedeDao.update(recogerDatos());
            JOptionPane.showMessageDialog(this, "Sede modificada correctamente");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (txtIdSede.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el codigo de la sede a eliminar");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar esta sede?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                sedeDao.delete(recogerDatos());
                JOptionPane.showMessageDialog(this, "Sede eliminada correctamente");
                limpiarCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String codigo = txtIdSede.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un codigo para buscar");
                return;
            }
            SedeTo s = sedeDao.find(codigo);
            if (s == null) {
                JOptionPane.showMessageDialog(this, "No se encontro la sede con codigo " + codigo);
                return;
            }
            txtNombre.setText(s.getNombre());
            txtDireccion.setText(s.getDireccion());
            txtTelefono.setText(s.getTelefono());
            txtEmail.setText(s.getEmail());
            txtDistrito.setText(s.getDistrito());
            txtCiudad.setText(s.getCiudad());
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
            java.util.logging.Logger.getLogger(SedeManView.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new SedeManView().setVisible(true);
        });
    }
}
