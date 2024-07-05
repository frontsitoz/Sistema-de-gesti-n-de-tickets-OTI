package ticketera;
import enums.Areas;
import enums.Estados;
import enums.TipoIncidente;

import java.util.*;

public class GestorTicket implements TicketEstado {
    private LinkedList<Ticket> tickets;
    private GestorUsuario listaUsuarios;

    public GestorTicket(GestorUsuario listaUsuarios) {
        this.tickets = new LinkedList<>();
        this.listaUsuarios = listaUsuarios;
        //Tickets de prueba
        tickets.add(new Ticket("Office",TipoIncidente.PROBLEMAS_DE_RED,TipoIncidente.PROBLEMAS_DE_RED,"user1",Areas.OFICINA_DE_ADMINISTRACION));
        tickets.add(new Ticket("Office",TipoIncidente.APLICACIONES,TipoIncidente.APLICACIONES,"user1",Areas.OFICINA_DE_ASESORIA_JURIDICA));
    }

    public TipoIncidente asignarTipoIncidencia(int codigo){
        for(TipoIncidente tipoIncidente : TipoIncidente.values()){
            if(tipoIncidente.getCodigo()== codigo){
                return tipoIncidente;
            }
        }
        return null;
    }

    public void crearTicket (Usuario usuarioLogeado){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Resumen de incidente:");
        String resumen = scanner.nextLine();

        //ASIGNAR PRIORIDAD
        TipoIncidente tempIncidente = null;
        do{
            System.out.println("Ingrese el tipo de incidencia: ");
            int num = 1;
            for(TipoIncidente tipoIncidente : TipoIncidente.values()){
                System.out.println(num+". "+tipoIncidente.getDescripcion());
                num++;
            }
            int codigo =0;
            try{
                boolean codigoValido= false;
                while (!codigoValido) {
                    codigo = Integer.parseInt(scanner.nextLine());
                    if (codigo > 0 && codigo <= Areas.values().length) {
                        codigoValido = true;
                    } else {
                        System.out.println("Por favor, ingrese un código de incidencia válido");
                    }
                }
            } catch (NumberFormatException e){
                System.out.println("Por favor, ingrese un valor válido");
            }

            if(codigo != 0) {
                tempIncidente = asignarTipoIncidencia(codigo);
            }
        } while (tempIncidente == null);

        Areas area = ((UsuarioComun) usuarioLogeado).getAreaTrabajo();
        //String descripcion = tempIncidente.getDescripcion();
        //String prioridad = tempIncidente.getPrioridad();
        String solicitante = usuarioLogeado.getUsername();

        this.tickets.add(new Ticket(resumen, tempIncidente, tempIncidente, solicitante, area));
        System.out.println("Se ha generado el Ticket #" + tickets.getLast().getId());
    }

    public void consultarTickets(){
        boolean ticketsPendientes = false;
        for(Ticket tickets : tickets){
            if(!tickets.getEstado().equalsIgnoreCase("Resuelto")){
                if(!ticketsPendientes){
                    Ticket.imprimirEncabezadoTabla();
                    ticketsPendientes = true;
                }
                System.out.println(tickets);
            }
        }

    }

    public Ticket buscarTicketPorID (){
        Scanner scanner = new Scanner(System.in);
        boolean idValido = false;
        int id=0;

        while(!idValido){
            System.out.println("Ingrese el ID del ticket: ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                idValido = true;
            } catch (NumberFormatException e){
                System.out.println("Entrada no válida, por favor ingrese un ID de Ticket válido");
            }
        }
        for (Ticket ticket : tickets){
            if (ticket.getId() == id){
                return ticket;
            }
        }
        return null;
    }

    public void imprimirTicketsPorID (){
        Ticket ticket = buscarTicketPorID();
        if(ticket != null){
            Ticket.imprimirEncabezadoTabla();
            System.out.println(ticket);
        }
        else {
            System.out.println("Este ticket no se encuentra registrado");
        }
    }

    public void buscarTicketPorPrioridad (){
        boolean ticketsPendientes = false;
        Scanner scanner = new Scanner(System.in);
        boolean prioridadValida = false;
        String prioridad = "";

        while(!prioridadValida) {

            System.out.println("""
                    Ingrese la prioridad:
                    1. Baja
                    2. Media
                    3. Alta""");
            try {
                int codPrioridad = Integer.parseInt(scanner.nextLine());
                switch (codPrioridad){
                    case 1: prioridad = "baja";
                    prioridadValida = true;
                    break;
                    case 2: prioridad = "media";
                        prioridadValida = true;
                        break;
                    case 3: prioridad = "alta";
                        prioridadValida = true;
                        break;
                    default:
                        System.out.println("Ingrese un código válido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un código de prioridad válido");
            }
        }

        for (Ticket ticket : tickets){
            if(ticket.getPrioridad().equalsIgnoreCase(prioridad) && !ticket.getEstado().equalsIgnoreCase("resuelto")){
                if(!ticketsPendientes) {
                    ticketsPendientes = true;
                    Ticket.imprimirEncabezadoTabla();
                }
                System.out.println(ticket);
            }
        }
        if(!ticketsPendientes){
            System.out.println("No hay tickets pendientes con este nivel de prioridad");
        }
    }

    public void cambiarEstadoTicket (){
        Ticket ticket = buscarTicketPorID();
        Scanner scanner = new Scanner(System.in);
        Estados nuevoEstado;

        int codigo = 0;
        if(ticket != null){
            System.out.println("Ingrese el nuevo estado del ticket:");
            listarEstados();

            boolean codigoValido = false;
            while (!codigoValido) {
                try {
                    codigo = Integer.parseInt(scanner.nextLine());
                    if(codigo>0 && codigo<=Estados.values().length){
                        codigoValido = true;
                    } else {
                        System.out.println("Por favor ingrese un codigo válido");
                        listarEstados();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Valor ingresado no válido");
                }
            }
            nuevoEstado = obtenerEstado(codigo+1);
            ticket.setEstado(nuevoEstado);
            System.out.println("Estado de Ticket #"+ticket.getId()+" actualizado");
        }else {
            System.out.println("Este Ticket no está registrado");
        }
    }

    public void asignarTicket(){
        Scanner scanner = new Scanner(System.in);
        Ticket ticket = buscarTicketPorID();
        if (ticket != null){
            boolean usuarioValidado = false;
            while(!usuarioValidado) {
                System.out.println("Ingrese el usuario que se asignará el ticket: ");
                String username = scanner.nextLine();

                Usuario usuarioAsignado = listaUsuarios.buscarUsuarioXUsername(username);
                if (usuarioAsignado != null) {
                    usuarioValidado = true;
                    ticket.setResponsable(usuarioAsignado.getUsername());
                    ticket.setEstado(Estados.ASIGNADO);
                    System.out.println("El ticket " + ticket.getId() + " ha sido asignado al usuario: " + username);
                }
            }
        } else {
            System.out.println("Este ticket no está registrado");
        }
    }

    @Override
    public Estados obtenerEstado(int codigo) {
        for(Estados estado : Estados.values()){
            if (estado.getCodigo()==codigo){
                return estado;
            }
        }
        return null;
    }

    @Override
    public void listarEstados() {
        int num = 1;
        for(Estados estado : Estados.values()){
            if(estado.getCodigo()!=1) {
                System.out.println(num + ". " + estado.getDescripcion());
                num++;
            }
        }
    }
}
