package ticketera;

public abstract class Usuario {
    private String username;
    private String contrasena;
    private String perfil;

    public Usuario(String username, String contrasena, String perfil) {
        this.username = username;
        this.contrasena = contrasena;
        this.perfil = perfil;}

    public String getUsername() {
        return username;
    }

    public String getContrasena() {
        return contrasena;
    }

    protected abstract void mostrarMenu();
}
