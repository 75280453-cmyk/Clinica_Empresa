package uni.entity;

public class SedeTo {

    // atributos
    private String idsede;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String distrito;
    private String ciudad;

    // constructor vacío
    public SedeTo() {
    }

    // constructor completo
    public SedeTo(String idsede, String nombre, String direccion,
                  String telefono, String email,
                  String distrito, String ciudad) {
        this.idsede    = idsede;
        this.nombre    = nombre;
        this.direccion = direccion;
        this.telefono  = telefono;
        this.email     = email;
        this.distrito  = distrito;
        this.ciudad    = ciudad;
    }

    // getters y setters
    public String getIdsede() { return idsede; }
    public void setIdsede(String idsede) { this.idsede = idsede; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
}
