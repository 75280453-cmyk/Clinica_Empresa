package uni.controller;

import java.util.List;
import uni.dao.SedeDao;
import uni.entity.SedeTo;

// S - Responsabilidad única: orquesta la lógica de negocio de sedes
// O - Abierto para extender con nuevos métodos sin modificar los existentes
public class SedeController {

    // variable
    SedeDao dao;

    // constructor
    public SedeController() {
        dao = new SedeDao();
    }

    // Listar todas las sedes
    public List<SedeTo> sedeListar() throws Exception {
        return dao.readAll();
    }

    // Obtener código por nombre
    public String codigoDeSede(String nombre) throws Exception {
        return dao.readAll1(nombre);
    }

    // Registrar nueva sede
    public void sedeRegistrar(SedeTo sede) throws Exception {
        dao.create(sede);
    }

    // Actualizar datos de sede
    public void sedeActualizar(SedeTo sede) throws Exception {
        dao.update(sede);
    }

    // Eliminar sede
    public void sedeEliminar(SedeTo sede) throws Exception {
        dao.delete(sede);
    }

    // Buscar sede por ID
    public SedeTo sedeBuscar(String idsede) throws Exception {
        return dao.find(idsede);
    }

    // Listar sedes por ciudad
    public List<SedeTo> sedeListarPorCiudad(String ciudad) throws Exception {
        return dao.readByCiudad(ciudad);
    }
}