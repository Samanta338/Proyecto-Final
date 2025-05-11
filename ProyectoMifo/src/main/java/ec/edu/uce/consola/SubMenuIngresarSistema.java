package ec.edu.uce.consola;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Scanner;
public class SubMenuIngresarSistema {
    private Scanner entrada = new Scanner(System.in);
    private Usuario usuario;
    public SubMenuIngresarSistema(Usuario usuario) {
        this.usuario = usuario;
    }
    public void menuIngresarSistema() {
        int opcion;
        do {
            mostrarMenuIngresarSistema();
            opcion = entrada.nextInt();
            entrada.nextLine();
            procesarOpcionIngresarSistema(opcion);
        } while (opcion != 4);
    }
    private void mostrarMenuIngresarSistema() {
        System.out.println("");
        System.out.println("  ---------------------------------------  ");
        System.out.println(" |            Ingresar al sistema         |");
        System.out.println("  --------------------------------------- |");
        System.out.println(" |  1. Editar Credenciales                |");
        System.out.println(" |  2. Eliminar Credenciales              |");
        System.out.println(" |  3. Consultar Credenciales             |");
        System.out.println(" |  4. Salir                              |");
        System.out.println("  ---------------------------------------");
        System.out.println("");
        System.out.print("Por favor, introduce el número correspondiente a la acción que deseas realizar:");
        System.out.println("");
    }
    private void procesarOpcionIngresarSistema(int seleccion) {

        switch (seleccion) {
            case 1:
                try {
                    if (validarCredenciales()) {
                        editarCredenciales();
                    } else {
                        System.out.println("Contraseña incorrecta. No se pueden editar las credenciales.");
                    }
                } catch (ExcepcionMifo.CredencialesInvalidasExcepcion e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    if (validarCredenciales()) {
                        eliminarCredenciales();
                        System.exit(0);
                    } else {
                        System.out.println("Contraseña incorrecta. No se pueden eliminar las credenciales.");
                    }
                } catch (ExcepcionMifo.CredencialesInvalidasExcepcion e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                try {
                    if (validarCredenciales()) {
                        consultarCredenciales();
                    } else {
                        System.out.println("Contraseña incorrecta. No se pueden consultar las credenciales.");
                    }
                } catch (ExcepcionMifo.CredencialesInvalidasExcepcion e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                System.out.println("Saliendo al menu principal...");
                break;
            default:
                System.out.println("Opcion no valida.");
                break;
        }
    }
    private void ingresarCredenciales() {
        String nombre;
        String contrasena;

        do {
            System.out.print("Ingrese el nombre: ");
            nombre =entrada.nextLine();
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacio.");
            }
        } while (nombre.isEmpty());

        do {
            System.out.print("Ingrese la contraseña: ");
            contrasena = entrada.nextLine();
            if (!ComprobacionMenu.validarContrasena(contrasena)) {
                System.out.println("Error: La contraseña no cumple con los requisitos mínimos.");
            }
        } while (!ComprobacionMenu.validarContrasena(contrasena));

        usuario.setNombre(nombre);
        usuario.setContrasena(contrasena);
        System.out.println("Credenciales ingresadas correctamente.");
    }

    private boolean validarCredenciales() throws ExcepcionMifo.CredencialesInvalidasExcepcion {
        System.out.print("Ingrese su contraseña actual: ");
        String contrasena = entrada.nextLine();
        if (usuario.getContrasena().equals(contrasena)) {
            return true;
        } else {
            throw new ExcepcionMifo.CredencialesInvalidasExcepcion("Contraseña incorrecta. No se pueden validar las credenciales.");
        }
    }

    private void editarCredenciales() {
        String nuevoNombre;
        String nuevaContrasena;

        do {
            System.out.print("Ingrese nuevo nombre (solo letras): ");
            nuevoNombre = entrada.nextLine();
            if (!nuevoNombre.matches("[a-zA-Z]+")) {
                System.out.println("Error: El nombre debe contener solo letras.");
            }
        } while (!nuevoNombre.matches("[a-zA-Z]+"));

        do {
            System.out.print("Ingrese nueva contraseña (minimo 8 caracteres con al menos una letra): ");
            nuevaContrasena = entrada.nextLine();
            if (nuevaContrasena.length() < 8 || !nuevaContrasena.matches(".*[a-zA-Z].*")) {
                System.out.println("Error: La contraseña debe tener al menos 8 caracteres con al menos una letra.");
            }
        } while (nuevaContrasena.length() < 8 || !nuevaContrasena.matches(".*[a-zA-Z].*"));

        usuario.setNombre(nuevoNombre);
        usuario.setContrasena(nuevaContrasena);
        System.out.println("Credenciales editadas correctamente.");
    }

    private void eliminarCredenciales() {
        usuario.setNombre("");
        usuario.setContrasena("");
        System.out.println("Credenciales eliminadas correctamente.");
        System.out.println("Para volver a utilizar Mifo, deberá crear una nueva cuenta.");
    }
    private void consultarCredenciales() {
        System.out.println("Código: " + usuario.getCodigo());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Contraseña: " + usuario.getContrasena());
    }
}
