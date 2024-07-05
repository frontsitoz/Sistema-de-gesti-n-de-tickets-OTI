package ticketera;

import enums.Areas;
import enums.Estados;
import enums.TipoIncidente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private static int contador = 1;
    private  int id;
    private String resumen;
    private TipoIncidente descripcion;
    private TipoIncidente prioridad;
    private String solicitante;
    private Estados estado;
    private String responsable;
    private Areas areaIncidente;
    private LocalDateTime fechaCreacion;

    public Ticket(String resumen, TipoIncidente descripcion, TipoIncidente prioridad, String solicitante, Areas areaIncidente) {
        this.id = contador++;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.solicitante = solicitante;
        this.areaIncidente = areaIncidente;
        this.estado = Estados.SIN_ASIGNAR;
        this.responsable = "———————————";
        this.fechaCreacion = LocalDateTime.now();
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getId() {
        return id;
    }

    public String getPrioridad() {
        return prioridad.getPrioridad();
    }

    public String getEstado() {
        return estado.getDescripcion();
    }

    public static void imprimirEncabezadoTabla() {
        System.out.printf("%-12s %-33s %-25s %-12s %-14s %-40s %-15s %-15s %-20s\n",
                "TICKET ID",
                "DESCRIPCION",
                "RESUMEN",
                "PRIORIDAD",
                "SOLICITANTE",
                "AREA",
                "ESTADO",
                "RESPONSABLE",
                "FECHA / HORA");
    }


    @Override
    public String toString() {
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%-12s %-33s %-25s %-12s %-14s %-40s %-15s %-15s %-20s",
                this.id,
                this.descripcion.getDescripcion(),
                this.resumen,
                this.prioridad.getPrioridad(),
                this.solicitante,
                this.areaIncidente.getDescripcion(),
                this.estado.getDescripcion(),
                this.responsable,
                this.fechaCreacion.format(formatoHora));
    }
}
