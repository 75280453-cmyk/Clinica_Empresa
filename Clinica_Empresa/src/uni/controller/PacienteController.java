package uni.controller;

import java.util.List;
import uni.dao.PacienteDao;
import uni.entity.PacienteTo;

// S - Responsabilidad única: orquesta la lógica de negocio de pacientes
// O - Abierto para extender (nuevos métodos de negocio) sin modificar los existentes
public class PacienteController {

    // variable
    PacienteDao dao;

    // constructor
    public PacienteController() {
        dao = new PacienteDao();
    }

    // Listar todos los pacientes
    public List<PacienteTo> pacienteListar() throws Exception {
        return dao.readAll();
    }

    // Obtener código por nombre
    public String codigoDePaciente(String nombre) throws Exception {
        return dao.readAll1(nombre);
    }

    // Registrar nuevo paciente
    public void pacienteRegistrar(PacienteTo paciente) throws Exception {
        dao.create(paciente);
    }

    // Actualizar datos de paciente
    public void pacienteActualizar(PacienteTo paciente) throws Exception {
        dao.update(paciente);
    }

    // Eliminar paciente
    public void pacienteEliminar(PacienteTo paciente) throws Exception {
        dao.delete(paciente);
    }

    // Buscar paciente por ID
    public PacienteTo pacienteBuscar(String idpaciente) throws Exception {
        return dao.find(idpaciente);
    }

    // Listar por tipo: PARTICULAR, ASEGURADO, CONVENIO
    public List<PacienteTo> pacienteListarPorTipo(String tipo) throws Exception {
        return dao.readByTipo(tipo);
    }
}
