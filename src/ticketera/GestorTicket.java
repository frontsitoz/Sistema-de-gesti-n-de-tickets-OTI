package ticketera;
import enums.Areas;
import enums.TipoIncidente;

import java.util.*;

public class GestorTicket {
    private LinkedList<Ticket> tickets;
    private GestorUsuario listaUsuarios;

    public GestorTicket(GestorUsuario listaUsuarios) {
        this.tickets = new LinkedList<>();
        this.listaUsuarios = listaUsuarios;
        //Tickets de prueba
        tickets.add(new Ticket("Office","office","baja","user1","Oficina de Administración"));
        tickets.add(new Ticket("Office","office","baja","user1","Oficina de asesoría jurídica"));
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

        String area = ((UsuarioComun) usuarioLogeado).getAreaTrabajo();
        String descripcion = tempIncidente.getDescripcion();
        String prioridad = tempIncidente.getPrioridad();

        String solicitante = usuarioLogeado.getUsername();
        this.tickets.add(new Ticket(resumen, descripcion, prioridad, solicitante, area));
        System.out.println("Se ha generado el Ticket #" + tickets.peekLast().getId());
    }

    public TipoIncidente asignarTipoIncidencia(int codigo){
        for(TipoIncidente tipoIncidente : TipoIncidente.values()){
            if(tipoIncidente.getCodigo()== codigo){
                return tipoIncidente;
            }
        }
        return null;
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
        System.out.println("Este ticket no se encuentra registrado");
        return null;
    }

    public void buscarTicketPorPrioridad (){
        //List<Ticket> ticketsPrioridad = new ArrayList<>();
        boolean ticketsPendientes = false;
        Scanner scanner = new Scanner(System.in);
        boolean prioridadValida = false;
        String prioridad = "";

        while(!prioridadValida) {
            System.out.println("Ingrese la prioridad (1: Baja, 2: Media, 3: Alta):");
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
                //ticketsPrioridad.add(ticket);
                if(!ticketsPendientes) {
                    ticketsPendientes = true;
                    Ticket.printTableHeader();
                }
                System.out.println(ticket);
            }
        }

        if(!ticketsPendientes){
            System.out.println("No hay tickets pendientes con este nivel de prioridad");
        }

        /*if(!ticketsPrioridad.isEmpty()){
            Ticket.printTableHeader();
        System.out.println(ticketsPrioridad);
        } else {
            System.out.println("No hay tickets pendientes con este nivel de prioridad");
        }*/
    }

    public void cambiarEstadoTicket (){
        Ticket ticket = buscarTicketPorID();
        Scanner scanner = new Scanner(System.in);
        boolean estadoValido = false;
        String nuevoEstado = "";
        while(!estadoValido && ticket !=null) {
            System.out.println("Ingrese el nuevo estado del ticket (1: Asignado, 2: En proceso, 3: Pendiente, 4: Resuelto): ");
            try{
            int codEstado = Integer.parseInt(scanner.nextLine());
            switch (codEstado){
                case 1: nuevoEstado = "Asignado";
                estadoValido = true;
                break;
                case 2: nuevoEstado = "En proceso";
                    estadoValido = true;
                    break;
                case 3: nuevoEstado = "Pendiente";
                    estadoValido = true;
                    break;
                case 4: nuevoEstado = "Resuelto";
                    estadoValido = true;
                    break;
                default:
                    System.out.println("Por favor ingrese un código de estado válido");
            }
            }catch (NumberFormatException e){
                System.out.println("Valor ingresado no válido");
            }
        }
        if (ticket != null) {
            ticket.setEstado(nuevoEstado);
            System.out.println("Estado de ticket "+ticket.getId()+" actualizado");
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
                        ticket.setAsignadoA(usuarioAsignado.getUsername());
                        ticket.setEstado("asignado");
                        System.out.println("El ticket " + ticket.getId() + " ha sido asignado al usuario: " + username);
                    }
                }
        }
    }


}
