package uni.entity;

public class MedicoTo {

    // atributos
    private String idmedico;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String cmp;            // Código del Colegio Médico del Perú
    private String idespecialidad;

    // constructor vacío
    public MedicoTo() {
    }

    // constructor completo
    public MedicoTo(String idmedico, String nombre, String direccion,
                    String telefono, String email,
                    String cmp, String idespecialidad) {
        this.idmedico       = idmedico;
        this.nombre         = nombre;
        this.direccion      = direccion;
        this.telefono       = telefono;
        this.email          = email;
        this.cmp            = cmp;
        this.idespecialidad = idespecialidad;
    }

    // getters y setters
    public String getIdmedico() { return idmedico; }
    public void setIdmedico(String idmedico) { this.idmedico = idmedico; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCmp() { return cmp; }
    public void setCmp(String cmp) { this.cmp = cmp; }

    public String getIdespecialidad() { return idespecialidad; }
    public void setIdespecialidad(String idespecialidad) { this.idespecialidad = idespecialidad; }
}
