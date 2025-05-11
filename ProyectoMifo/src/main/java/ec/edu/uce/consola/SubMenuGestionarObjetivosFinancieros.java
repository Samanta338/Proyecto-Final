package ec.edu.uce.consola;
import ec.edu.uce.dominio.Categoria;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import ec.edu.uce.dominio.Empresa;
import ec.edu.uce.dominio.ObjetivoFinanciero;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
public class SubMenuGestionarObjetivosFinancieros {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Scanner entrada = new Scanner(System.in);
    private ObjetivoFinanciero[] objetivosArray = new ObjetivoFinanciero[100];
    private final Empresa empresa = new Empresa();
    private int objetivoCount = 0;
    public void menuGestionarObjetivosFinancieros() throws ExcepcionMifo.MovimientoInvalidoExcepcion{
        int opcion;
        do {
            mostrarMenuGestObjetivosFinancieros();
            opcion = ComprobacionMenu.validarOpcionMenu(entrada, 5);
            procesarOpcionGestObjetivosFinancieros(opcion);
        } while (opcion != 5);
    }
    private void mostrarMenuGestObjetivosFinancieros() {
        System.out.println("");
        System.out.println("------------------------------");
        System.out.println("  Gestionar Objetivos Financieros ");
        System.out.println("------------------------------");
        System.out.println("1. Crear Objetivo Financiero");
        System.out.println("2. Editar Objetivo Financiero");
        System.out.println("3. Consultar Objetivo Financiero");
        System.out.println("4. Eliminar Objetivo Financiero");
        System.out.println("5. Salir");
        System.out.println("------------------------------");
        System.out.print("Ingrese el número: ");
    }
    private void procesarOpcionGestObjetivosFinancieros(int opcion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        switch (opcion) {
            case 1:
                crearObjetivoFinanciero();
                break;
            case 2:
                editarObjetivoFinanciero();
                break;
            case 3:
                consultarObjetivoFinanciero();
                break;
            case 4:
                eliminarObjetivoFinanciero();
                break;
            case 5:
                System.out.println("Saliendo al menú principal");
                break;
            default:
                System.out.println("Opción no válida, por favor intente de nuevo.");
                break;
        }
    }
    private void crearObjetivoFinanciero() throws ExcepcionMifo.MovimientoInvalidoExcepcion{
        System.out.println("****");

        String descripcion;
        while (true) {
            System.out.print("Ingrese la descripción del objetivo financiero: ");
            descripcion = entrada.nextLine();
            if (ComprobacionMenu.validarDescripcion(descripcion)) {
                break;
            }
            System.out.println("La descripción no puede estar vacía.");
        }

        Double monto;
        while (true) {
            System.out.print("Ingrese el monto del objetivo financiero: ");
            String montoStr = entrada.nextLine();
            monto = ComprobacionMenu.validarMonto(montoStr);
            if (monto != null) {
                break;
            }
        }

        Date fecha;
        while (true) {
            System.out.print("Ingrese la fecha del objetivo financiero (dia/mes/año): ");
            String fechaStr = entrada.nextLine();
            fecha = ComprobacionMenu.validarFecha(fechaStr);
            if (fecha != null) {
                break;
            }
        }
        System.out.print("Ingrese la categoría del objetivo financiero: ");
        String nombreCategoria = entrada.nextLine();
        Categoria categoria = empresa.buscarCategoriaPorNombre(nombreCategoria);
        if (categoria == null) {
            categoria = new Categoria(nombreCategoria);
            empresa.crearCategoria(categoria);
        }

        ObjetivoFinanciero objetivo = new ObjetivoFinanciero(descripcion, monto, fecha, categoria);
        objetivosArray[objetivoCount++] = objetivo;
        System.out.println("Objetivo financiero creado con éxito.");
    }
    private void editarObjetivoFinanciero() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        System.out.println("****");
        if (objetivoCount == 0) {
            System.out.println("No hay objetivos financieros registrados.");
            return;
        }

        System.out.print("Ingrese la descripción del objetivo financiero que desea editar: ");
        String descripcion = entrada.nextLine();

        ObjetivoFinanciero objetivoEditar = null;
        for (int i = 0; i < objetivoCount; i++) {
            if (objetivosArray[i].getDescripcion().equals(descripcion)) {
                objetivoEditar = objetivosArray[i];
                break;
            }
        }

        if (objetivoEditar == null) {
            System.out.println("No se encontró ningún objetivo financiero con la descripción especificada.");
            return;
        }

        String nuevaDescripcion;
        while (true) {
            System.out.print("Ingrese la nueva descripción del objetivo financiero: ");
            nuevaDescripcion = entrada.nextLine();
            if (ComprobacionMenu.validarDescripcion(nuevaDescripcion)) {
                break;
            }
            System.out.println("La descripción no puede estar vacía.");
        }

        Double nuevoMonto;
        while (true) {
            System.out.print("Ingrese el nuevo monto del objetivo financiero: ");
            String montoStr = entrada.nextLine();
            nuevoMonto = ComprobacionMenu.validarMonto(montoStr);
            if (nuevoMonto != null) {
                break;
            }
        }

        Date nuevaFecha;
        while (true) {
            System.out.print("Ingrese la nueva fecha del objetivo financiero (dia/mes/año): ");
            String fechaStr = entrada.nextLine();
            nuevaFecha = ComprobacionMenu.validarFecha(fechaStr);
            if (nuevaFecha != null) {
                break;
            }
        }

        Categoria nuevaCategoria;
        while (true) {
            System.out.print("Ingrese la nueva categoría del objetivo financiero: ");
            String nombreCategoria = entrada.nextLine();
            nuevaCategoria = empresa.buscarCategoriaPorNombre(nombreCategoria);
            if (nuevaCategoria == null) {
                System.out.print("Categoría no encontrada ¿Desea crear una nueva categoría con este nombre? (s/n): ");
                String respuesta = entrada.nextLine();
                if (respuesta.equalsIgnoreCase("s")) {
                    nuevaCategoria = new Categoria(nombreCategoria);
                    empresa.crearCategoria(nuevaCategoria);
                    break;
                }
            } else {
                break;
            }
        }

        objetivoEditar.setDescripcion(nuevaDescripcion);
        objetivoEditar.setMonto(nuevoMonto);
        objetivoEditar.setFecha(nuevaFecha);
        objetivoEditar.setCategoria(nuevaCategoria);

        System.out.println("Objetivo financiero editado con éxito.");
    }
    private void consultarObjetivoFinanciero() {
        System.out.println("****");
        if (objetivoCount == 0) {
            System.out.println("No hay objetivos financieros registrados.");
            return;
        }

        System.out.print("Ingrese la descripción del objetivo financiero que desea consultar: ");
        String descripcion = entrada.nextLine();

        ObjetivoFinanciero objetivoConsultar = null;
        for (int i = 0; i < objetivoCount; i++) {
            if (objetivosArray[i].getDescripcion().equals(descripcion)) {
                objetivoConsultar = objetivosArray[i];
                break;
            }
        }

        if (objetivoConsultar == null) {
            System.out.println("No se encontró ningún objetivo financiero con la descripción especificada.");
        } else {
            System.out.println("Información del Objetivo Financiero:");
            System.out.println(objetivoConsultar);
        }
    }
    private void eliminarObjetivoFinanciero() {
        System.out.println("****");
        if (objetivoCount == 0) {
            System.out.println("No hay objetivos financieros registrados.");
            return;
        }

        System.out.print("Ingrese la descripción del objetivo financiero que desea eliminar: ");
        String descripcion = entrada.nextLine();

        ObjetivoFinanciero objetivoEliminar = null;
        int indexEliminar = -1;
        for (int i = 0; i < objetivoCount; i++) {
            if (objetivosArray[i].getDescripcion().equals(descripcion)) {
                objetivoEliminar = objetivosArray[i];
                indexEliminar = i;
                break;
            }
        }

        if (objetivoEliminar == null) {
            System.out.println("No se encontró ningún objetivo financiero con la descripción especificada.");
        } else {
            for (int i = indexEliminar; i < objetivoCount - 1; i++) {
                objetivosArray[i] = objetivosArray[i + 1];
            }
            objetivosArray[--objetivoCount] = null;
            System.out.println("Objetivo financiero eliminado con éxito.");
        }
    }
}
