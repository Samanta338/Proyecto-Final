package ec.edu.uce.consola;
import java.util.Scanner;
public class SolicitudCurso {
        private Scanner entrada = new Scanner(System.in);
        private int verCursosDisponibles(int maxOpciones) {
            int opcion = -1;
            while (opcion < 1 || opcion > maxOpciones) {
                try {
                    opcion = Integer.parseInt(entrada.nextLine());
                    if (opcion < 1 || opcion > maxOpciones) {
                        System.out.print("Opción inválida. Ingrese un número entre 1 y " + maxOpciones + ": ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Entrada inválida. Ingrese un número válido: ");
                }
            }
            return opcion;
        }
        private void verCursosDisponibles() {
            System.out.println(" Cursos disponibles:");
            System.out.println("1. Finanzas Personales");
            System.out.println("2. Gasto Hormiga");
            System.out.println("3. Ahorro para el Futuro");
        }
        public void inscribirseCurso() {
            verCursosDisponibles();
            System.out.print("Ingrese el número del curso al que desea inscribirse: ");
            String seleccion = entrada.nextLine();
            switch (seleccion) {
                case "1":
                    System.out.println("Inscripción exitosa al curso: Finanzas Personales.");
                    mostrarContenidoCurso("Finanzas Personales");
                    break;
                case "2":
                    System.out.println("Inscripción exitosa al curso: Inversiones Básicas.");
                    mostrarContenidoCurso("Gasto Hormiga");
                    break;
                case "3":
                    System.out.println("Inscripción exitosa al curso: Ahorro para el Futuro.");
                    mostrarContenidoCurso("Ahorro para el Futuro");
                    break;
                default:
                    System.out.println("Curso no válido.");
            }
        }
        private void mostrarContenidoCurso(String curso) {
            switch (curso) {
                case "Gastos Hormiga":
                    System.out.println("Los gastos hormiga son pequeñas compras o consumos diarios que parecen insignificantes individualmente,");
                    System.out.println("pero que al acumularse representan una gran fuga de dinero mensual. Ejemplos comunes incluyen cafés, snacks,");
                    System.out.println("apps de delivery o transporte innecesario. Para estudiantes foráneos, estos gastos pueden desbalancear el");
                    System.out.println("presupuesto y dificultar el ahorro, especialmente cuando se vive lejos de casa con ingresos limitados.");
                    break;
                case "Gasto Hormiga":
                    System.out.println("Los gastos hormiga son pequeños desembolsos diarios que, aunque parecen insignificantes,");
                    System.out.println("al acumularse a lo largo del tiempo, pueden representar una parte considerable de tu presupuesto.");
                    System.out.println("Para un estudiante foráneo, estos gastos pueden incluir el café diario, snacks, transporte innecesario,");
                    System.out.println("suscripciones que no se usan o compras impulsivas. Identificarlos y reducirlos es clave para mantener");
                    System.out.println("un control financiero efectivo mientras estudias lejos de casa.");
                    break;
                case "Ahorro para el Futuro":
                    System.out.println("El ahorro para el futuro consiste en reservar una parte de tus ingresos actuales para cubrir");
                    System.out.println("necesidades futuras, emergencias o alcanzar metas personales. Como estudiante foráneo, ahorrar");
                    System.out.println("puede ayudarte a enfrentar imprevistos, como gastos médicos o viajes inesperados, sin depender");
                    System.out.println("de tus familiares o endeudarte. Es un hábito esencial para lograr estabilidad y autonomía financiera.");
                    break;
                default:
                    System.out.println("Contenido no disponible.");
            }
        }
}




