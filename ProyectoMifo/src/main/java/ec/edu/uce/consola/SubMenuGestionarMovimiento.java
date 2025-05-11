package ec.edu.uce.consola;
import ec.edu.uce.dominio.Movimiento;
import ec.edu.uce.dominio.Presupuesto;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Scanner;
import ec.edu.uce.dominio.Gasto;
import ec.edu.uce.dominio.Ingreso;
import org.jetbrains.annotations.NotNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import ec.edu.uce.dominio.TipoGasto;
import ec.edu.uce.dominio.TipoIngreso;
import java.util.Date;
public class SubMenuGestionarMovimiento {
    private final Scanner entrada;
    private final Usuario usuario;
    public SubMenuGestionarMovimiento(Usuario usuario) {
        this.entrada = new Scanner(System.in);
        this.usuario = usuario;
    }
    public void menuGestionarMovimiento() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        int seleccion;
        do {
            mostrarMenuGestionarMovimiento();
            seleccion = ComprobacionMenu.validarOpcionMenu(entrada, 5);
            procesarOpcionGestionarMovimiento(seleccion);
        } while (seleccion != 5);
    }
    private void mostrarMenuGestionarMovimiento() {
        System.out.println("");
        System.out.println("+-------------------------------+");
        System.out.println("|    Gestionar Movimiento       |");
        System.out.println("|-------------------------------|");
        System.out.println("|1. Crear Movimiento            |");
        System.out.println("|2. Editar Movimiento           |");
        System.out.println("|3. Consultar Movimiento        |");
        System.out.println("|4. Eliminar Movimiento         |");
        System.out.println("|5. Salir                       |");
        System.out.println("+-------------------------------+");
        System.out.print("Ingresa el número: ");
    }
    private void procesarOpcionGestionarMovimiento(int seleccion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        switch (seleccion) {
            case 1:
                crearMovimiento();
                break;
            case 2:
                editarMovimiento();
                break;
            case 3:
                consultarMovimiento();
                break;
            case 4:
                eliminarMovimiento();
                break;
            case 5:
                System.out.println("Saliendo al menú principal");
                break;
            default:
                System.out.println("Opción no válida, por favor intente de nuevo.");
                break;
        }
    }

    private void crearMovimiento() {
        //Presupuesto[] presupuestos = usuario.getPresupuesto();
        /*if (presupuestos.length == 0) {
            System.out.println("No hay presupuestos guardados. Por favor, cree un presupuesto primero.");
            return;
        }*/

        System.out.println("Seleccione el presupuesto para este movimiento:");
        /*for (int i = 0; i < presupuestos.length; i++) {
            Presupuesto p = presupuestos[i];
            System.out.println((i + 1) + ") Presupuesto: " + p.getPresupuesto() + ", Fecha: " + p.getFecha());
        }
        int presupuestoIndex = ComprobacionMenu.validarOpcionMenu(entrada, presupuestos.length) - 1;
        Presupuesto presupuestoSeleccionado = presupuestos[presupuestoIndex];*/

        System.out.println("****************************");
        System.out.println("Seleccione el tipo de movimiento:");
        System.out.println("1. Ingreso");
        System.out.println("2. Gasto");
        System.out.print("Ingresa el número correspondiente: ");
        int tipoMovimiento = ComprobacionMenu.validarOpcionMenu(entrada, 2);

        System.out.print("Ingrese la descripción: ");
        String descripcion = entrada.nextLine();
        while (descripcion.isEmpty()) {
            System.out.println("Error: La descripción no puede estar vacía.");
            System.out.print("Ingrese la descripción: ");
            descripcion = entrada.nextLine();
        }

        System.out.print("Ingrese el monto: ");
        double monto = ComprobacionMenu.validarMonto(entrada);
        /*if (monto > presupuestoSeleccionado.getPresupuesto() && tipoMovimiento == 2) { // Solo para gastos
            System.out.println("Error: El monto ingresado supera el presupuesto disponible.");
            return;
        }*/

        System.out.print("Ingrese la fecha (dia/mes/año): ");
        Date fecha = ComprobacionMenu.validarFecha(entrada);

        Movimiento movimiento;
        if (tipoMovimiento == 1) {
            System.out.println("Seleccione el tipo de ingreso:");
            int tipoIngresoOption = 1;
            for (TipoIngreso tipoIngreso : TipoIngreso.values()) {
                System.out.println(tipoIngresoOption + ". " + tipoIngreso);
                tipoIngresoOption++;
            }
            System.out.print("Ingresa el número correspondiente al tipo de ingreso: ");
            tipoIngresoOption = ComprobacionMenu.validarOpcionMenu(entrada, TipoIngreso.values().length);

            TipoIngreso tipoIngresoSeleccionado = TipoIngreso.values()[tipoIngresoOption - 1];
            //movimiento = new Ingreso(descripcion, monto, fecha, tipoIngresoSeleccionado);
        } else {
            System.out.println("Seleccione el tipo de gasto:");
            int tipoGastoOption = 1;
            for (TipoGasto tipoGasto : TipoGasto.values()) {
                System.out.println(tipoGastoOption + ". " + tipoGasto);
                tipoGastoOption++;
            }
            System.out.print("Ingresa el número correspondiente al tipo de gasto: ");
            tipoGastoOption =  ComprobacionMenu.validarOpcionMenu(entrada, TipoGasto.values().length);

            TipoGasto tipoGastoSeleccionado = TipoGasto.values()[tipoGastoOption - 1];
            //movimiento = new Gasto(descripcion, monto, fecha, tipoGastoSeleccionado);
        }
       //presupuestoSeleccionado.agregarMovimiento(movimiento);
        //System.out.println("Movimiento creado con éxito: " + movimiento);

    }
    private void editarMovimiento() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        if (presupuestos.length == 0) {
            System.out.println("No hay presupuestos guardados. Por favor, cree un presupuesto primero.");
            return;
        }

        System.out.println("Seleccione el presupuesto para editar movimientos:");
        for (int i = 0; i < presupuestos.length; i++) {
            Presupuesto p = presupuestos[i];
            System.out.println((i + 1) + ") Presupuesto: " + p.getPresupuesto() + ", Fecha: " + p.getFecha());
        }

        int presupuestoIndex = ComprobacionMenu.validarOpcionMenu(entrada, presupuestos.length) - 1;
        Presupuesto presupuestoSeleccionado = presupuestos[presupuestoIndex];

        Movimiento[] movimientos = presupuestoSeleccionado.getMovimientos();
        if (movimientos == null || movimientos.length == 0) {
            System.out.println("No hay movimientos para editar en el presupuesto seleccionado.");
            return;
        }

        mostrarMovimientos(movimientos);

        System.out.print("Ingrese el número del movimiento que desea editar: ");
        int indiceMovimiento = ComprobacionMenu.validarOpcionMenu(entrada, movimientos.length) - 1;

        if (movimientos[indiceMovimiento] == null) {
            System.out.println("El movimiento seleccionado no existe.");
            return;
        }

        System.out.print("Ingrese la nueva descripción: ");
        String nuevaDescripcion = entrada.nextLine();
        while (nuevaDescripcion.isEmpty()) {
            System.out.println("Error: La descripción no puede estar vacía.");
            System.out.print("Ingrese la nueva descripción: ");
            nuevaDescripcion = entrada.nextLine();
        }

        System.out.print("Ingrese el nuevo monto: ");
        double nuevoMonto = ComprobacionMenu.validarMonto(entrada);

        System.out.print("Ingrese la nueva fecha (dia/mes/año): ");
        Date nuevaFecha = ComprobacionMenu.validarFecha(entrada);

        Movimiento movimiento = movimientos[indiceMovimiento];
        boolean esIngreso = movimiento instanceof Ingreso;

        if (esIngreso) {
            Ingreso ingreso = (Ingreso) movimiento;
            System.out.println("Seleccione el nuevo tipo de ingreso:");
            int tipoIngresoOption = 1;
            for (TipoIngreso tipo : TipoIngreso.values()) {
                System.out.println(tipoIngresoOption + ". " + tipo);
                tipoIngresoOption++;
            }
            System.out.print("Ingresa el número correspondiente al tipo de ingreso: ");
            tipoIngresoOption = ComprobacionMenu.validarOpcionMenu(entrada, TipoIngreso.values().length);

            TipoIngreso tipoIngresoSeleccionado = TipoIngreso.values()[tipoIngresoOption - 1];
           // ingreso.setTipo(tipoIngresoSeleccionado); // Actualizar el tipo de ingreso
        } else {
            Gasto gasto = (Gasto) movimiento;
            System.out.println("Seleccione el nuevo tipo de gasto:");
            int tipoGastoOption = 1;
            for (TipoGasto tipoGasto : TipoGasto.values()) {
                System.out.println(tipoGastoOption + ". " + tipoGasto);
                tipoGastoOption++;
            }
            System.out.print("Ingresa el número correspondiente al tipo de gasto: ");
            tipoGastoOption = ComprobacionMenu.validarOpcionMenu(entrada, TipoGasto.values().length);

            TipoGasto tipoGastoSeleccionado = TipoGasto.values()[tipoGastoOption - 1];
            //gasto.setCategoria(tipoGastoSeleccionado);
        }
        presupuestoSeleccionado.editarMovimiento(indiceMovimiento, nuevaDescripcion, nuevoMonto, nuevaFecha, esIngreso);

        System.out.println("Movimiento editado con éxito.");
    }

    private void eliminarMovimiento() {
        try {
            Presupuesto[] presupuestos = usuario.getPresupuestos();
            if (presupuestos.length == 0) {
                System.out.println("No hay presupuestos guardados. Por favor, cree un presupuesto primero.");
                return;
            }

            System.out.println("Seleccione el presupuesto para eliminar movimientos:");
            for (int i = 0; i < presupuestos.length; i++) {
                Presupuesto p = presupuestos[i];
                System.out.println((i + 1) + ") Presupuesto: " + p.getPresupuesto() + ", Fecha: " + p.getFecha());
            }

            int presupuestoIndex = ComprobacionMenu.validarOpcionMenu(entrada, presupuestos.length) - 1;
            Presupuesto presupuestoSeleccionado = presupuestos[presupuestoIndex];

            Movimiento[] movimientos = presupuestoSeleccionado.getMovimientos();
            if (movimientos == null || movimientos.length == 0) {
                System.out.println("No hay movimientos para eliminar en el presupuesto seleccionado.");
                return;
            }

            mostrarMovimientos(movimientos);

            System.out.print("Ingrese el número del movimiento que desea eliminar: ");
            int indiceMovimiento = ComprobacionMenu.validarOpcionMenu(entrada, movimientos.length) - 1;

            if (movimientos[indiceMovimiento] == null) {
                System.out.println("El movimiento seleccionado no existe.");
                return;
            }

            presupuestoSeleccionado.eliminarMovimiento(indiceMovimiento);
            System.out.println("Movimiento eliminado con éxito.");
        } catch (ExcepcionMifo.MovimientoInvalidoExcepcion ex) {
            Logger.getLogger(SubMenuGestionarMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void consultarMovimiento() {
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        if (presupuestos.length == 0) {
            System.out.println("No hay presupuestos guardados. Por favor, cree un presupuesto primero.");
            return;
        }

        System.out.println("Seleccione el presupuesto para consultar movimientos:");
        for (int i = 0; i < presupuestos.length; i++) {
            Presupuesto p = presupuestos[i];
            System.out.println((i + 1) + ") Presupuesto: " + p.getPresupuesto() + ", Fecha: " + p.getFecha());
        }

        int presupuestoIndex = ComprobacionMenu.validarOpcionMenu(entrada, presupuestos.length) - 1;
        Presupuesto presupuestoSeleccionado = presupuestos[presupuestoIndex];

        Movimiento[] movimientos = presupuestoSeleccionado.getMovimientos();
        if (movimientos == null || movimientos.length == 0) {
            System.out.println("No hay movimientos en el presupuesto seleccionado.");
            return;
        }

        mostrarMovimientos(movimientos);
    }
    private void mostrarMovimientos( @NotNull Movimiento[] movimientos) {
        System.out.println("Movimientos:");
        for (int i = 0; i < movimientos.length; i++) {
            Movimiento movimiento = movimientos[i];
            if (movimiento != null) {
                System.out.println((i + 1) + ". " + movimiento);
            }
        }
    }
}
