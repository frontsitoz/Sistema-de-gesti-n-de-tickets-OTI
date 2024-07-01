package ticketera;

import java.util.Scanner;

public class Exec {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorUsuario gestorUsuario = new GestorUsuario();
        GestorTicket gestorTicket = new GestorTicket(gestorUsuario);

        boolean primerIntento = true;
        while (true) {
            while (primerIntento) {
                System.out.println("""
                        --------BIENVENIDO AL SISTEMA DE GESTION DE INCIDENTES DE SUTRAN--------
                        ---El presente sistema solo puede ser accedido por personal autorizado---
                        """);
                primerIntento = false;
            }
            System.out.println("Ingrese su usuario:");
            String username = scanner.nextLine();
            System.out.println("Ingrese su contraseña:");
            String contrasena = scanner.nextLine();

            Usuario usuario = gestorUsuario.validarUsuario(username, contrasena);
            if (usuario != null) {
                System.out.println("----------BIENVENIDO \"" +usuario.getUsername()+"\""+"----------");
                boolean salir = false;
                while (!salir) {
                    usuario.mostrarMenu();
                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    if(usuario instanceof UsuarioAdministrador){
                        //opciones de administrador
                        switch (opcion){
                            case 1:
                                gestorTicket.buscarTicketPorPrioridad();
                                break;
                            case 2:
                                Ticket ticket = gestorTicket.buscarTicketPorID();
                                if(ticket != null) {
                                    Ticket.printTableHeader();
                                    System.out.println(ticket);
                                }
                                break;
                            case 3:
                                gestorTicket.cambiarEstadoTicket();
                                break;
                            case 4:
                                gestorTicket.asignarTicket();
                                break;
                            case 5:
                                gestorUsuario.crearUsario();
                                break;
                            case 0:
                                salir = true;
                                primerIntento = true;
                                break;
                            default:
                                System.out.println("Opción no válida");
                        }
                    } else if (usuario instanceof UsuarioComun) {
                        //opciones de usuario comun
                        switch (opcion){
                            case 1:
                                Ticket ticket = gestorTicket.buscarTicketPorID();
                                if(ticket != null) {
                                    Ticket.printTableHeader();
                                    System.out.println(ticket);
                                }
                                break;
                            case 2:
                                gestorTicket.crearTicket(usuario);
                                break;
                            case 0:
                                salir=true;
                                primerIntento = true;
                                break;
                            default:
                                System.out.println("Opción no válida");
                        }
                    }
                }
            }
        else {
                System.out.println("Usuario o contraseña incorrecto");
            }
        }
    }
}
