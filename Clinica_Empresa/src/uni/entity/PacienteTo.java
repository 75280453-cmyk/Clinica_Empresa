package uni.entity;

public class PacienteTo {

    // atributos
    private String idpaciente;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String fechaNacimiento;
    private String tipoPaciente; // PARTICULAR, ASEGURADO, CONVENIO

    // constructor vacío
    public PacienteTo() {
    }

    // constructor completo
    public PacienteTo(String idpaciente, String nombre, String direccion,
                      String telefono, String email,
                      String fechaNacimiento, String tipoPaciente) {
        this.idpaciente      = idpaciente;
        this.nombre          = nombre;
        this.direccion       = direccion;
        this.telefono        = telefono;
        this.email           = email;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoPaciente    = tipoPaciente;
    }

    // getters y setters
    public String getIdpaciente() { return idpaciente; }
    public void setIdpaciente(String idpaciente) { this.idpaciente = idpaciente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getTipoPaciente() { return tipoPaciente; }
    public void setTipoPaciente(String tipoPaciente) { this.tipoPaciente = tipoPaciente; }
}
