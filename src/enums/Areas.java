package enums;

public enum Areas {
    OFICINA_DE_ADMINISTRACION(1, "Oficina de Administración"),
    OFICINA_DE_PLANEAMIENTO_Y_PRESUPUESTO(2, "Oficina de Planeamiento y Presupuesto"),
    OFICINA_DE_ASESORIA_JURIDICA(3, "Oficina de asesoría jurídica");

    private final int codigo;
    private final String descripcion;

    Areas(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}