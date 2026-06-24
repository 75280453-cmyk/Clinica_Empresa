package uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uni.database.AccesoDB;
import uni.entity.PacienteTo;
import uni.service.ICrudDao;

// S - Responsabilidad única: solo gestiona acceso a datos de pacientes
// D - Depende de la abstracción ICrudDao, no de implementaciones concretas
public class PacienteDao implements ICrudDao<PacienteTo> {

    // variables
    Connection cn = null;
    Statement stm = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    @Override
    public void create(PacienteTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "INSERT INTO pacientes (idpaciente, nombre, direccion, telefono, email, fecha_nacimiento, tipo_paciente) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getIdpaciente());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getDireccion());
            ps.setString(4, o.getTelefono());
            ps.setString(5, o.getEmail());
            ps.setString(6, o.getFechaNacimiento());
            ps.setString(7, o.getTipoPaciente());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void update(PacienteTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "UPDATE pacientes SET nombre=?, direccion=?, telefono=?, email=?, "
                + "fecha_nacimiento=?, tipo_paciente=? WHERE idpaciente=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getDireccion());
            ps.setString(3, o.getTelefono());
            ps.setString(4, o.getEmail());
            ps.setString(5, o.getFechaNacimiento());
            ps.setString(6, o.getTipoPaciente());
            ps.setString(7, o.getIdpaciente());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void delete(PacienteTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "DELETE FROM pacientes WHERE idpaciente=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getIdpaciente());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public PacienteTo find(Object o) throws Exception {
        PacienteTo paciente = null;
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM pacientes WHERE idpaciente=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, (String) o);
            rs  = ps.executeQuery();
            if (rs.next()) {
                paciente = mapearFila(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
        return paciente;
    }

    @Override
    public List<PacienteTo> readAll() throws Exception {
        List<PacienteTo> lista = new ArrayList<>();
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM pacientes ORDER BY idpaciente";
            ps  = cn.prepareStatement(sql);
            rs  = ps.executeQuery();
            lista = cargaLista(rs);
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
        return lista;
    }

    // Buscar ID por nombre
    public String readAll1(String nombre) throws Exception {
        String codigo = null;
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT idpaciente FROM pacientes WHERE nombre=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            rs  = ps.executeQuery();
            if (rs.next()) {
                codigo = rs.getString(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
        return codigo;
    }

    // Buscar pacientes por tipo
    public List<PacienteTo> readByTipo(String tipoPaciente) throws Exception {
        List<PacienteTo> lista = new ArrayList<>();
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM pacientes WHERE tipo_paciente=? ORDER BY nombre";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, tipoPaciente);
            rs  = ps.executeQuery();
            lista = cargaLista(rs);
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
        return lista;
    }

    // S - Método privado con única responsabilidad: mapear una fila del ResultSet
    private PacienteTo mapearFila(ResultSet rs) throws SQLException {
        PacienteTo p = new PacienteTo();
        p.setIdpaciente(rs.getString(1));
        p.setNombre(rs.getString(2));
        p.setDireccion(rs.getString(3));
        p.setTelefono(rs.getString(4));
        p.setEmail(rs.getString(5));
        p.setFechaNacimiento(rs.getString(6));
        p.setTipoPaciente(rs.getString(7));
        return p;
    }

    private List<PacienteTo> cargaLista(ResultSet rs) throws SQLException {
        List<PacienteTo> aux = new ArrayList<>();
        while (rs.next()) {
            aux.add(mapearFila(rs)); // reutiliza mapearFila — no duplica lógica
        }
        return aux;
    }
}