package ticketera;

public class UsuarioComun extends Usuario {
    private String areaTrabajo;
    public UsuarioComun(String username, String contrasena, String areaTrabajo) {
        super(username, contrasena, "user");
        this.areaTrabajo = areaTrabajo;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    @Override
    protected void mostrarMenu() {
        System.out.println("\n----------MENÚ DE OPCIONES----------");
        System.out.println("1. Consultar ticket por ID");
        System.out.println("2. Crear ticket de incidencia");
        System.out.println("0. Cerrar sesión");
    }
}
