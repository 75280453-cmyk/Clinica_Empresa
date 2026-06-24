package uni.entity;
public class EspecialidadTo {
    // atributos
    private String idespecialidad;
    private String nombre;
    private String descripcion;
    private String area; // CLINICA, QUIRURGICA, DIAGNOSTICO
    // constructor vacío
    public EspecialidadTo() {
    }
    // constructor completo
    public EspecialidadTo(String idespecialidad, String nombre,
                          String descripcion, String area) {
        this.idespecialidad = idespecialidad;
        this.nombre         = nombre;
        this.descripcion    = descripcion;
        this.area           = area;
    }
    // getters y setters
    public String getIdespecialidad() { return idespecialidad; }
    public void setIdespecialidad(String idespecialidad) { this.idespecialidad = idespecialidad; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    @Override
    public String toString() {
        return nombre;
    }
}