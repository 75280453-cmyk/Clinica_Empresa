package uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uni.database.AccesoDB;
import uni.entity.MedicoTo;
import uni.service.ICrudDao;

// S - Responsabilidad única: solo gestiona acceso a datos de médicos
// D - Depende de la abstracción ICrudDao, no de implementaciones concretas
public class MedicoDao implements ICrudDao<MedicoTo> {

    // variables
    Connection cn = null;
    Statement stm = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    @Override
    public void create(MedicoTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "INSERT INTO medicos (idmedico, nombre, direccion, telefono, email, cmp, idespecialidad) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getIdmedico());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getDireccion());
            ps.setString(4, o.getTelefono());
            ps.setString(5, o.getEmail());
            ps.setString(6, o.getCmp());
            ps.setString(7, o.getIdespecialidad());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void update(MedicoTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "UPDATE medicos SET nombre=?, direccion=?, telefono=?, email=?, "
                + "cmp=?, idespecialidad=? WHERE idmedico=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getDireccion());
            ps.setString(3, o.getTelefono());
            ps.setString(4, o.getEmail());
            ps.setString(5, o.getCmp());
            ps.setString(6, o.getIdespecialidad());
            ps.setString(7, o.getIdmedico());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void delete(MedicoTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "DELETE FROM medicos WHERE idmedico=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getIdmedico());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public MedicoTo find(Object o) throws Exception {
        MedicoTo medico = null;
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM medicos WHERE idmedico=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, (String) o);
            rs  = ps.executeQuery();
            if (rs.next()) {
                medico = mapearFila(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
        return medico;
    }

    @Override
    public List<MedicoTo> readAll() throws Exception {
        List<MedicoTo> lista = new ArrayList<>();
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM medicos ORDER BY idmedico";
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
            sql = "SELECT idmedico FROM medicos WHERE nombre=?";
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

    // Buscar médicos por especialidad
    public List<MedicoTo> readByEspecialidad(String idespecialidad) throws Exception {
        List<MedicoTo> lista = new ArrayList<>();
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM medicos WHERE idespecialidad=? ORDER BY nombre";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, idespecialidad);
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

    // S - Responsabilidad única: mapear una sola fila del ResultSet
    private MedicoTo mapearFila(ResultSet rs) throws SQLException {
        MedicoTo m = new MedicoTo();
        m.setIdmedico(rs.getString(1));
        m.setNombre(rs.getString(2));
        m.setDireccion(rs.getString(3));
        m.setTelefono(rs.getString(4));
        m.setEmail(rs.getString(5));
        m.setCmp(rs.getString(6));
        m.setIdespecialidad(rs.getString(7));
        return m;
    }

    private List<MedicoTo> cargaLista(ResultSet rs) throws SQLException {
        List<MedicoTo> aux = new ArrayList<>();
        while (rs.next()) {
            aux.add(mapearFila(rs)); // reutiliza mapearFila — no duplica lógica
        }
        return aux;
    }
}
