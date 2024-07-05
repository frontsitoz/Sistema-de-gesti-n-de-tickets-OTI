package enums;

public enum Areas {

    SUPER_INTENDENCIA(1, "Super Intedencia"),
    GERENCIA_GENERAL(2, "Gerencia General"),
    GERENCIA_EYN(3, "Gerencia de Estudios y Normas"),
    GERENCIA_PREVENCION(4, "Gerencia de Prevención"),
    GERENCIA_SYF(5, "Gerencia de Supervisión y Fiscalización"),
    GERENCIA_PYS(6, "Gerencia de Procedimientos y Sanciones"),
    GERENCIA_AT(7, "Gerencia de Articulación Territorial"),
    GERENCIA_SYE(8, "Gerencia de Seguimiento y Evaluación"),
    OFICINA_DE_ADMINISTRACION(9, "Oficina de Administración"),
    OFICINA_DE_PLANEAMIENTO_Y_PRESUPUESTO(10, "Oficina de Planeamiento y Presupuesto"),
    OFICINA_DE_ASESORIA_JURIDICA(11, "Oficina de asesoría jurídica");


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