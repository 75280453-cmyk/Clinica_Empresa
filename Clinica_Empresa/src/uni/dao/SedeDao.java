package uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uni.database.AccesoDB;
import uni.entity.SedeTo;
import uni.service.ICrudDao;

// S - Responsabilidad única: solo gestiona acceso a datos de sedes
// D - Depende de la abstracción ICrudDao, no de implementaciones concretas
public class SedeDao implements ICrudDao<SedeTo> {

    // variables
    Connection cn = null;
    Statement stm = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    @Override
    public void create(SedeTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "INSERT INTO sedes (idsede, nombre, direccion, telefono, email, distrito, ciudad) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getIdsede());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getDireccion());
            ps.setString(4, o.getTelefono());
            ps.setString(5, o.getEmail());
            ps.setString(6, o.getDistrito());
            ps.setString(7, o.getCiudad());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void update(SedeTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "UPDATE sedes SET nombre=?, direccion=?, telefono=?, email=?, "
                + "distrito=?, ciudad=? WHERE idsede=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getDireccion());
            ps.setString(3, o.getTelefono());
            ps.setString(4, o.getEmail());
            ps.setString(5, o.getDistrito());
            ps.setString(6, o.getCiudad());
            ps.setString(7, o.getIdsede());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void delete(SedeTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "DELETE FROM sedes WHERE idsede=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getIdsede());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public SedeTo find(Object o) throws Exception {
        SedeTo sede = null;
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM sedes WHERE idsede=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, (String) o);
            rs  = ps.executeQuery();
            if (rs.next()) {
                sede = mapearFila(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
        return sede;
    }

    @Override
    public List<SedeTo> readAll() throws Exception {
        List<SedeTo> lista = new ArrayList<>();
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM sedes ORDER BY idsede";
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
            sql = "SELECT idsede FROM sedes WHERE nombre=?";
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

    // Buscar sedes por ciudad
    public List<SedeTo> readByCiudad(String ciudad) throws Exception {
        List<SedeTo> lista = new ArrayList<>();
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM sedes WHERE ciudad=? ORDER BY nombre";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, ciudad);
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
    private SedeTo mapearFila(ResultSet rs) throws SQLException {
        SedeTo s = new SedeTo();
        s.setIdsede(rs.getString(1));
        s.setNombre(rs.getString(2));
        s.setDireccion(rs.getString(3));
        s.setTelefono(rs.getString(4));
        s.setEmail(rs.getString(5));
        s.setDistrito(rs.getString(6));
        s.setCiudad(rs.getString(7));
        return s;
    }

    private List<SedeTo> cargaLista(ResultSet rs) throws SQLException {
        List<SedeTo> aux = new ArrayList<>();
        while (rs.next()) {
            aux.add(mapearFila(rs)); // reutiliza mapearFila — no duplica lógica
        }
        return aux;
    }
}
