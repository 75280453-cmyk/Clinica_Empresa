package uni.controller;

import java.util.List;
import uni.dao.MedicoDao;
import uni.entity.MedicoTo;

// S - Responsabilidad única: orquesta la lógica de negocio de médicos
// O - Abierto para extender con nuevos métodos sin modificar los existentes
public class MedicoController {

    // variable
    MedicoDao dao;

    // constructor
    public MedicoController() {
        dao = new MedicoDao();
    }

    // Listar todos los médicos
    public List<MedicoTo> medicoListar() throws Exception {
        return dao.readAll();
    }

    // Obtener código por nombre
    public String codigoDeMedico(String nombre) throws Exception {
        return dao.readAll1(nombre);
    }

    // Registrar nuevo médico
    public void medicoRegistrar(MedicoTo medico) throws Exception {
        dao.create(medico);
    }

    // Actualizar datos de médico
    public void medicoActualizar(MedicoTo medico) throws Exception {
        dao.update(medico);
    }

    // Eliminar médico
    public void medicoEliminar(MedicoTo medico) throws Exception {
        dao.delete(medico);
    }

    // Buscar médico por ID
    public MedicoTo medicoBuscar(String idmedico) throws Exception {
        return dao.find(idmedico);
    }

    // Listar médicos por especialidad
    public List<MedicoTo> medicoListarPorEspecialidad(String idespecialidad) throws Exception {
        return dao.readByEspecialidad(idespecialidad);
    }
}