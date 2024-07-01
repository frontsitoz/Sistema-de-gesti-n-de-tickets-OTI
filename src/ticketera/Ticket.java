package ticketera;

public class Ticket {
    private static int contador = 1;
    private  int id;
    private String resumen;
    private String descripcion;
    private String prioridad;
    private String solicitante;
    private String estado;
    private String asignadoA;
    private String areaTrabajo;

    public Ticket(String resumen, String descripcion, String prioridad, String solicitante, String areaTrabajo) {
        this.id = contador++;
        this.resumen = resumen;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.solicitante = solicitante;
        this.areaTrabajo = areaTrabajo;
        this.estado = "Sin asignar";
        this.asignadoA = "-";
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setAsignadoA(String asignadoA) {
        this.asignadoA = asignadoA;
    }

    public int getId() {
        return id;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public static void printTableHeader() {
        System.out.printf("%-12s %-25s %-35s %-12s %-14s %-40s %-15s %-15s\n",
                "TICKET ID",
                "RESUMEN",
                "DESCRIPCION",
                "PRIORIDAD",
                "SOLICITANTE",
                "AREA",
                "ESTADO",
                "RESPONSABLE");
    }
/*
    @Override
    public String toString() {
        return
                "TicketId=" + id +
                ", resumen='" + resumen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", prioridad='" + prioridad + '\'' +
                ", solicitante='" + solicitante + '\'' +
                ", área='" + areaTrabajo + '\'' +
                ", estado='" + estado + '\'' +
                ", responsable ='" + asignadoA + '\'' +
                '\n';
    }*/

    @Override
    public String toString() {
        return String.format("%-12s %-25s %-35s %-12s %-14s %-40s %-15s %-15s\n",
                this.id,
                this.resumen,
                this.descripcion,
                this.prioridad,
                this.solicitante,
                this.areaTrabajo,
                this.estado,
                this.asignadoA);
    }
}
