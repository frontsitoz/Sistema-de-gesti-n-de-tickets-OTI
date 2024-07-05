package ticketera;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorUsuario gestorUsuario = new GestorUsuario();
        GestorTicket gestorTicket = new GestorTicket(gestorUsuario);

        boolean primerIntento = true;
        boolean cerrarPrograma = false;

        while (!cerrarPrograma) {
            if (primerIntento) {
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

            Usuario usuario = gestorUsuario.validarUsuarioInicioSesion(username, contrasena);
            if (usuario != null) {
                System.out.println("----------BIENVENIDO \"" + usuario.getUsername() + "\"" + "----------");
                boolean salir = false;
                while (!salir) {
                    usuario.mostrarMenu();
                    int opcion = 0;
                    boolean opcionValida = false;
                    while (!opcionValida) {
                        try {
                            opcion = Integer.parseInt(scanner.nextLine());
                            opcionValida = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Valor ingresado no válido, intente nuevamente");
                            usuario.mostrarMenu();
                        }
                    }

                    if (usuario instanceof UsuarioAdministrador) {
                        //opciones de administrador
                        switch (opcion) {
                            case 1:
                                gestorTicket.consultarTickets();
                                break;
                            case 2:
                                gestorTicket.buscarTicketPorPrioridad();
                                break;
                            case 3:
                                gestorTicket.imprimirTicketsPorID();
                                break;
                            case 4:
                                gestorTicket.cambiarEstadoTicket();
                                break;
                            case 5:
                                gestorTicket.asignarTicket();
                                break;
                            case 6:
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
                        switch (opcion) {
                            case 1:
                                gestorTicket.imprimirTicketsPorID();
                                break;
                            case 2:
                                gestorTicket.crearTicket(usuario);
                                break;
                            case 0:
                                salir = true;
                                primerIntento = true;
                                break;
                            default:
                                System.out.println("Opción no válida");
                        }
                    }
                    if(salir) {
                        boolean respuestaValida = false;
                        while (!respuestaValida) {
                            System.out.println("¿Desea salir del sistema? (s/n)");
                            String respuesta = scanner.nextLine();
                            if (respuesta.equalsIgnoreCase("s")) {
                                cerrarPrograma = true;
                                respuestaValida = true;
                                System.out.println("Gracias por usar el sistema de gestión de incidentes de SUTRAN");
                            } else if (respuesta.equalsIgnoreCase("n")) {
                                respuestaValida = true;
                            } else {
                                System.out.println("Por favor ingrese 's' para salir o 'n' para volver a iniciar sesión");
                            }
                        }
                    }
                }
            } else {
                System.out.println("Usuario o contraseña incorrecto");
            }
        }
    }
}

