package ticketera;

public class UsuarioAdministrador extends Usuario {
    public UsuarioAdministrador(String username, String contrasena) {
        super(username, contrasena, "administrador");
    }

    @Override
    protected void mostrarMenu() {
        System.out.println("\n----------MENÚ DE OPCIONES----------");
        System.out.println("1. Consultar tickets pendientes por prioridad");
        System.out.println("2. Consultar tickets por ID");
        System.out.println("3. Actualizar estado de ticket");
        System.out.println("4. Asignar ticket a un usuario");
        System.out.println("5. Crear nuevo usuario");
        System.out.println("0. Cerrar sesión");
    }


}