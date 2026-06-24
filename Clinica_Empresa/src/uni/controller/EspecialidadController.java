package uni.controller;

import java.util.List;
import uni.dao.EspecialidadDao;
import uni.entity.EspecialidadTo;

// S - Responsabilidad única: orquesta la lógica de negocio de especialidades
// O - Abierto para extender con nuevos métodos sin modificar los existentes
public class EspecialidadController {

    // variable
    EspecialidadDao dao;

    // constructor
    public EspecialidadController() {
        dao = new EspecialidadDao();
    }

    // Listar todas las especialidades
    public List<EspecialidadTo> especialidadListar() throws Exception {
        return dao.readAll();
    }

    // Obtener código por nombre
    public String codigoDeEspecialidad(String nombre) throws Exception {
        return dao.readAll1(nombre);
    }

    // Registrar nueva especialidad
    public void especialidadRegistrar(EspecialidadTo especialidad) throws Exception {
        dao.create(especialidad);
    }

    // Actualizar datos de especialidad
    public void especialidadActualizar(EspecialidadTo especialidad) throws Exception {
        dao.update(especialidad);
    }

    // Eliminar especialidad
    public void especialidadEliminar(EspecialidadTo especialidad) throws Exception {
        dao.delete(especialidad);
    }

    // Buscar especialidad por ID
    public EspecialidadTo especialidadBuscar(String idespecialidad) throws Exception {
        return dao.find(idespecialidad);
    }

    // Listar especialidades por area
    public List<EspecialidadTo> especialidadListarPorArea(String area) throws Exception {
        return dao.readByArea(area);
    }
}
