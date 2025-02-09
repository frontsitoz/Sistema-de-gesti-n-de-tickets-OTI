package ticketera;

import enums.Areas;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorUsuario implements UsuarioArea {
    private ArrayList<Usuario> usuarios;

    public GestorUsuario() {
        usuarios = new ArrayList<>();
        usuarios.add(new UsuarioAdministrador("admin", "admin123"));
        usuarios.add(new UsuarioComun("user1", "user123",Areas.OFICINA_DE_ADMINISTRACION));
    }

    public Usuario validarUsuarioInicioSesion(String username, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }

    public void crearUsario() {
        Scanner scanner = new Scanner(System.in);
        boolean usuarioExistente= false;
        String nuevoUsername=null;
        while (!usuarioExistente) {
            System.out.println("Ingrese el username del nuevo usuario:");
            nuevoUsername = scanner.nextLine();
            usuarioExistente = validarUsuarioExistente(nuevoUsername);
        }
        if(nuevoUsername !=null) {
            System.out.println("Ingrese la contraseña del nuevo usuario:");
            String nuevoPassword = scanner.nextLine();

            //Perfil, excepecion para valores int
            boolean perfilValido = false;
            String perfil = null;
            while (!perfilValido) {
                System.out.println("Ingrese el perfil (1: Administrador, 2: User):");
                try {
                    int codPerfil = Integer.parseInt(scanner.nextLine());
                    switch (codPerfil) {
                        case 1:
                            perfil = "administrador";
                            perfilValido = true;
                            break;
                        case 2:
                            perfil = "user";
                            perfilValido = true;
                            break;
                        default:
                            System.out.println("Ingrese un código válido");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Valor ingresado no válido, por favor escoja entre perfil 1 o 2");
                }
            }
            //Area
            Areas area;
            do {
                System.out.println("Ingrese el área de trabajo del usuario: ");
                listarAreas();

                int codigo = 0;
                boolean codigoValido = false;
                while (!codigoValido) {
                    try {
                        codigo = Integer.parseInt(scanner.nextLine());
                        if (codigo > 0 && codigo <= Areas.values().length) {
                            codigoValido = true;
                        } else {
                            System.out.println("Ingrese por favor un codigo de área valida");
                            listarAreas();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor ingrese un valor válido");
                    }
                }
                area = obtenerArea(codigo);
            } while (area == null);

            if (perfil.equalsIgnoreCase("administrador")) {
                usuarios.add(new UsuarioAdministrador(nuevoUsername, nuevoPassword));
                System.out.println("Usuario " + '"' + nuevoUsername + '"' + " registrado");
            }
            else {
                usuarios.add(new UsuarioComun(nuevoUsername, nuevoPassword, area));
                System.out.println("Usuario " + '"' + nuevoUsername + '"' + " registrado");
            }
        }
    }

    public Usuario buscarUsuarioXUsername(String username){
        for (Usuario usuario : usuarios){
            if(usuario.getUsername().equals(username)){
                return usuario;
            }
        }
        System.out.println("Este usuario no existe, por favor vuelva a indicar un usuario");
        return null;
    }

    public boolean validarUsuarioExistente (String username){
        for(Usuario usuario : usuarios){
            if(usuario.getUsername().equals(username)){
                System.out.println("Este usuario ya existe, por favor inserte otro Username");
                return false;
            }
        }
        return true;
    }

    @Override
    public Areas obtenerArea(int codigo) {
        for (Areas area : Areas.values()) {
            if (area.getCodigo() == codigo) {
                return area;
            }
        }
        return null;
    }

    @Override
    public void listarAreas() {
        int num = 1;
        for(Areas area : Areas.values()){
            System.out.println(num+". "+area.getDescripcion());
            num++;
        }
    }
}
