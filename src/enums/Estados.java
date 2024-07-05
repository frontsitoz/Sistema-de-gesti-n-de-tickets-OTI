package enums;

public enum Estados {
    SIN_ASIGNAR (1,"Sin asignar"),
    ASIGNADO (2,"Asignado"),
    EN_PROCESO(3,"En proceso"),
    PENDIENTE(4,"Pendiente"),
    RESUELTO(5, "Resuelto");

    private final int codigo;
    private final String descripcion;

    Estados(int codigo, String descripcion) {
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
