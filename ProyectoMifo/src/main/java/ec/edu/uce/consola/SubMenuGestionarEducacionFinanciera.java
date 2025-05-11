package ec.edu.uce.consola;
import ec.edu.uce.util.ComprobacionMenu;
import java.util.Scanner;
import ec.edu.uce.dominio.EducacionFinanciera;
import ec.edu.uce.dominio.Empresa;
import ec.edu.uce.consola.SolicitudCurso;
import ec.edu.uce.util.ExcepcionMifo;
public class SubMenuGestionarEducacionFinanciera {
        private Scanner entrada = new Scanner(System.in);
        public void menuGestionarEducacionFinanciera() {
            int opcion;
            do {
                mostrarMenuGestionarEducacionFinanciera();
                opcion = ComprobacionMenu.validarOpcionMenu(entrada, 5);
                procesarOpcionGestionarEducacionFinanciera(opcion);
            } while (opcion != 5);
        }

        public void mostrarMenuGestionarEducacionFinanciera() {
            System.out.println("Menú de Gestión de Educación Financiera:");
            System.out.println("1. Ver cursos disponibles por categoría");
            System.out.println("2. Buscar curso por categoría");
            System.out.println("3. Ver detalle de un curso");
            System.out.println("4. Solicitar inscripción a un curso");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opción: ");
        }

        public void procesarOpcionGestionarEducacionFinanciera(int opcion) {
            switch (opcion) {
                case 1:
                    verCursosDisponibles();
                    break;
                case 2:
                    buscarCursoPorCategoria();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del curso para ver el detalle: ");
                    String curso = entrada.nextLine();
                    verDetalleCurso(curso);
                    break;
                case 4:
                    SolicitudCurso solicitud = new SolicitudCurso();
                    solicitud.inscribirseCurso();
                    break;
                case 5:
                    System.out.println("Saliendo del menú de educación financiera.");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        }
        private void verCursosDisponibles() {
            System.out.println("Cursos disponibles:");
            System.out.println("1. Finanzas Personales");
            System.out.println("2. Gasto Hormiga");
            System.out.println("3. Ahorro para el Futuro");
        }
        private void buscarCursoPorCategoria() {
            System.out.println("Seleccione la categoría de cursos que desea ver:");
            System.out.println("1. Finanzas Personales");
            System.out.println("2. Gasto Hormiga");
            System.out.println("3. Ahorro");
            System.out.print("Ingrese el número de categoría: ");
            String categoriaSeleccionada = entrada.nextLine();
            mostrarCursosPorCategoria(categoriaSeleccionada);
        }
        private void verDetalleCurso(String curso) {
            switch (curso) {
                case "Finanzas Personales":
                    System.out.println("Bienvenido/a al curso Finanzas Personales");
                    System.out.println("Este curso te brindará herramientas prácticas para mejorar tu economía diaria.");
                    System.out.println("Duración: 4 semanas | Nivel: Básico | Modalidad: Online");
                    break;
                case "Gasto Hormiga":
                    System.out.println("Bienvenido/a al curso de Gasto Hormiga");
                    System.out.println("Este curso te enseñará a identificar y reducir los pequeños gastos diarios que parecen inofensivos");
                    System.out.println("Duración: 5 semanas | Nivel: Intermedio | Modalidad: Online");
                    break;
                case "Ahorro para el Futuro":
                    System.out.println("Bienvenido/a al curso Ahorro para el Futuro");
                    System.out.println("Este curso se enfoca en la planificación a largo plazo.");
                    System.out.println("Duración: 6 semanas | Nivel: Básico | Modalidad: Online");
                    break;
                default:
                    System.out.println("Contenido no disponible.");
            }
        }
    private void mostrarCursosPorCategoria(String categoria) {
        switch (categoria) {
            case "1":
                System.out.println("Cursos en Finanzas Personales:");
                System.out.println("  - Finanzas Personales: Curso básico de gestión financiera.");
                break;
            case "2":
                System.out.println("Cursos de Gasto Hormiga");
                System.out.println("  - Inversiones Básicas: Curso introductorio a las inversiones.");
                break;
            case "3":
                System.out.println("Cursos en Ahorro:");
                System.out.println("  - Ahorro para el Futuro: Curso sobre planificación y ahorro a largo plazo.");
                break;
            default:
                System.out.println("Categoría no válida.");
                break;
        }
    }
        public static void main(String[] args) {
            SubMenuGestionarEducacionFinanciera menu = new SubMenuGestionarEducacionFinanciera();
            menu.menuGestionarEducacionFinanciera();
        }
    }