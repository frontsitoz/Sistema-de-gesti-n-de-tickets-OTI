package enums;

public enum TipoIncidente {
    APLICACIONES (1,"Error de aplicativo","Baja"),
    LENTITUD_DE_SISTEMA(2,"Lentitud de Sistema Operativo","Baja"),
    HARDWARE_DEFECTUOSO(3, "Hardware defectuoso", "Media"),
    PROBLEMAS_DE_RED(4, "Problemas de conectividad","Alta"),
    SISTEMA_OPERATIVO(5, "Sistema operativo corrupto","Alta");


    private final int codigo;
    private final String descripcion;
    private final String prioridad;

    TipoIncidente(int codigo, String descripcion, String prioridad) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }
}
