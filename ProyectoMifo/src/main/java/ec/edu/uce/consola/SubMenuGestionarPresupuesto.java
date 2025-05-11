package ec.edu.uce.consola;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Presupuesto;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class SubMenuGestionarPresupuesto {
    private Scanner entrada;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Usuario usuario;
    public SubMenuGestionarPresupuesto(Scanner entrada) {
        this.entrada = entrada;
    }
    public void menuGestionarPresupuesto(Usuario usuario) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        this.usuario = usuario;
        boolean salir = false;
        while (!salir) {
            System.out.println("+--------------------------------+");
            System.out.println("|        Gestionar Presupuesto   |");
            System.out.println("+--------------------------------+");
            System.out.println("| 1) Ingresar Presupuesto       |");
            System.out.println("| 2) Editar Presupuesto         |");
            System.out.println("| 3) Eliminar Presupuesto       |");
            System.out.println("| 4) Consultar Presupuesto      |");
            System.out.println("| 5) Salir                      |");
            System.out.println("+--------------------------------+");
            System.out.print("Seleccione una opción: ");
            int opcion = ComprobacionMenu.validarOpcionMenu(entrada, 5); // Validación de opción de menú
            switch (opcion) {
                case 1:
                    ingresarPresupuesto();
                    break;
                case 2:
                    editarPresupuesto();
                    break;
                case 3:
                    eliminarPresupuesto();
                    break;
                case 4:
                    consultarPresupuesto();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    public void ingresarPresupuesto() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        System.out.println("****************************");
        boolean inputValido = false;
        while (!inputValido) {
            try {
                System.out.print("Ingrese el presupuesto: ");
                double presupuesto = ComprobacionMenu.validarMonto(entrada.nextLine()); // Validación de monto
                if (presupuesto == 0) {
                    continue;
                }

                System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
                String fechaStr = entrada.nextLine();
                Date fecha = dateFormat.parse(fechaStr);
                Presupuesto nuevoPresupuesto = new Presupuesto(presupuesto, fecha);
                if (usuario.validarDuplicado(nuevoPresupuesto)) {
                    System.out.println("Ya existe un presupuesto con el mismo monto y fecha.");
                    return;
                }

                usuario.crearPresupuesto(nuevoPresupuesto);
                System.out.println("Presupuesto guardado con éxito.");
                inputValido = true;

            } catch (ParseException e) {
                System.out.println("Error al ingresar presupuesto: Formato de fecha incorrecto.");
            }
        }
    }

    public void editarPresupuesto() {
        System.out.println("****************************");
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        if (presupuestos.length == 0) {
            System.out.println("No hay presupuestos guardados para editar.");
            return;
        }

        consultarPresupuesto();
        System.out.print("Seleccione el índice del presupuesto a editar: ");
        int indice;
        try {
            indice = Integer.parseInt(entrada.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Índice no válido.");
            return;
        }

        if (indice < 0 || indice >= presupuestos.length) {
            System.out.println("Índice no válido.");
            return;
        }

        boolean inputValido = false;
        while (!inputValido) {
            try {
                System.out.print("Ingrese el nuevo presupuesto: ");
                double presupuesto = ComprobacionMenu.validarMonto(entrada.nextLine()); // Validación de monto
                if (presupuesto == 0) {
                    continue;
                }

                System.out.print("Ingrese la nueva fecha (dd/MM/yyyy): ");
                String fechaStr = entrada.nextLine();
                Date fecha = dateFormat.parse(fechaStr); // Parseo de la fecha

                Presupuesto p = presupuestos[indice];
                p.setPresupuesto(presupuesto);
                p.setFecha(fecha);
                System.out.println("Presupuesto editado con éxito.");
                inputValido = true;
            } catch (ParseException e) {
                System.out.println("Error al editar presupuesto: Formato de fecha incorrecto.");
            }
        }
    }

    public void eliminarPresupuesto() {
        System.out.println("****************************");
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        if (presupuestos.length == 0) {
            System.out.println("No hay presupuestos guardados para eliminar.");
            return;
        }

        consultarPresupuesto();
        System.out.print("¿Está seguro de que desea eliminar un presupuesto? (s/n): ");
        String confirmacion = entrada.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            System.out.print("Seleccione el índice del presupuesto a eliminar: ");
            int indice = entrada.nextInt();
            entrada.nextLine(); // Consumir el salto de línea

            if (indice < 0 || indice >= presupuestos.length) {
                System.out.println("Índice no válido.");
                return;
            }

            usuario.eliminarPresupuesto(indice);
            System.out.println("Presupuesto eliminado correctamente.");
        } else {
            System.out.println("Operación cancelada por el usuario.");
        }
    }

    public void consultarPresupuesto() {
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        if (presupuestos.length == 0) {
            System.out.println("No hay presupuestos guardados.");
        } else {
            for (int i = 0; i < presupuestos.length; i++) {
                Presupuesto p = presupuestos[i];
                System.out.println(i + ") Presupuesto: " + p.getPresupuesto() + ", Fecha: " + dateFormat.format(p.getFecha()));
            }
        }
    }
}
