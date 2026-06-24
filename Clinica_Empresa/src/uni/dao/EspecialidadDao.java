package uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uni.database.AccesoDB;
import uni.entity.EspecialidadTo;
import uni.service.ICrudDao;

// S - Responsabilidad única: solo gestiona acceso a datos de especialidades
// D - Depende de la abstracción ICrudDao, no de implementaciones concretas
public class EspecialidadDao implements ICrudDao<EspecialidadTo> {

    // variables
    Connection cn = null;
    Statement stm = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    @Override
    public void create(EspecialidadTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "INSERT INTO especialidades (idespecialidad, nombre, descripcion, area) "
                + "VALUES (?, ?, ?, ?)";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getIdespecialidad());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getDescripcion());
            ps.setString(4, o.getArea());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void update(EspecialidadTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "UPDATE especialidades SET nombre=?, descripcion=?, area=? "
                + "WHERE idespecialidad=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getDescripcion());
            ps.setString(3, o.getArea());
            ps.setString(4, o.getIdespecialidad());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void delete(EspecialidadTo o) throws Exception {
        try {
            cn  = AccesoDB.getConnection();
            sql = "DELETE FROM especialidades WHERE idespecialidad=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, o.getIdespecialidad());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public EspecialidadTo find(Object o) throws Exception {
        EspecialidadTo especialidad = null;
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM especialidades WHERE idespecialidad=?";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, (String) o);
            rs  = ps.executeQuery();
            if (rs.next()) {
                especialidad = mapearFila(rs);
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            cn.close();
        }
        return especialidad;
    }

    @Override
    public List<EspecialidadTo> readAll() throws Exception {
        List<EspecialidadTo> lista = new ArrayList<>();
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM especialidades ORDER BY idespecialidad";
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
            sql = "SELECT idespecialidad FROM especialidades WHERE nombre=?";
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

    // Buscar especialidades por area
    public List<EspecialidadTo> readByArea(String area) throws Exception {
        List<EspecialidadTo> lista = new ArrayList<>();
        try {
            cn  = AccesoDB.getConnection();
            sql = "SELECT * FROM especialidades WHERE area=? ORDER BY nombre";
            ps  = cn.prepareStatement(sql);
            ps.setString(1, area);
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
    private EspecialidadTo mapearFila(ResultSet rs) throws SQLException {
        EspecialidadTo e = new EspecialidadTo();
        e.setIdespecialidad(rs.getString(1));
        e.setNombre(rs.getString(2));
        e.setDescripcion(rs.getString(3));
        e.setArea(rs.getString(4));
        return e;
    }

    private List<EspecialidadTo> cargaLista(ResultSet rs) throws SQLException {
        List<EspecialidadTo> aux = new ArrayList<>();
        while (rs.next()) {
            aux.add(mapearFila(rs)); // reutiliza mapearFila — no duplica lógica
        }
        return aux;
    }
}
