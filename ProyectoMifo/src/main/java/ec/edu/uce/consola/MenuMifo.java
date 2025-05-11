package ec.edu.uce.consola;
import java.util.Scanner;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.util.ComprobacionMenu;
public class MenuMifo {
        private Scanner entrada = new Scanner(System.in);
        private SubMenuIngresarSistema subMenuIngresarSistema;
        private SubMenuGestionarObjetivosFinancieros subMenuGestionarObjetivosFinancieros;
        private SubMenuGestionarEducacionFinanciera subMenuGestionarEducacionFinanciera;
        private SubMenuGestionarMovimiento subMenuGestionarMovimiento;
        private SubMenuGestionarPresupuesto subMenuGestionarPresupuesto;
        private Usuario usuario;
        public MenuMifo(Usuario usuario) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            this.usuario = usuario;
            this.subMenuIngresarSistema = new SubMenuIngresarSistema(usuario);
            this.subMenuGestionarObjetivosFinancieros = new SubMenuGestionarObjetivosFinancieros();
            this.subMenuGestionarEducacionFinanciera = new SubMenuGestionarEducacionFinanciera();
            this.subMenuGestionarPresupuesto = new SubMenuGestionarPresupuesto(entrada);
            this.subMenuGestionarMovimiento = new SubMenuGestionarMovimiento(usuario);
            menuMifo();
        }
        public void menuMifo() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            int seleccion;
            do {
                mostrarMenuMifo();
                seleccion = ComprobacionMenu.validarOpcionMenu(entrada, 6);
                procesarOpcionMenuMifo(seleccion);
            } while (seleccion != 6);
        }
        private void mostrarMenuMifo() {
            System.out.println("");
            System.out.println("  ---------------------------------------  ");
            System.out.println(" |                MIFO                    |");
            System.out.println(" |          Mis Finanzas Foraneas         |");
            System.out.println(" |          BIENVENIDO AL MENU            |");
            System.out.println("  --------------------------------------- |");
            System.out.println(" |                                        |");
            System.out.println(" |  1. Ingresar al Sistema                |");
            System.out.println(" |  2. Gestionar Presupuesto              |");
            System.out.println(" |  3. Gestionar Educación Financiera     |");
            System.out.println(" |  4. Gestionar Objetivos Financieros    |");
            System.out.println(" |  5. Gestionar Movimiento               |");
            System.out.println(" |  6. Salir                              |");
            System.out.println(" |                                        |");
            System.out.println("  ---------------------------------------");
            System.out.println();
            System.out.print("Por favor, introduce el número correspondiente a la acción que deseas realizar:");
        }

        private void procesarOpcionMenuMifo(int seleccion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            switch (seleccion) {
                case 1:
                    subMenuIngresarSistema.menuIngresarSistema();
                    break;
                case 2:
                    subMenuGestionarPresupuesto.menuGestionarPresupuesto(usuario);
                    break;
                case 3:
                    subMenuGestionarEducacionFinanciera.menuGestionarEducacionFinanciera();
                    break;
                case 4:
                    subMenuGestionarObjetivosFinancieros.menuGestionarObjetivosFinancieros();
                    break;
                case 5:
                    subMenuGestionarMovimiento.menuGestionarMovimiento();
                    break;
                case 6:
                    System.out.println("");
                    System.out.println("Cerrando el sistema.");
                    System.out.println("------------------------------------");
                    System.out.println("Gracias por haber confiado en Mifo. Esperamos que nuestra plataforma te haya sido de gran ayuda.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lo sentimos, la opción que has ingresado no es reconocida. Por favor, introduce un número válido del menú.");
                    break;
            }
        }
        public static void main(String[] args) throws ExcepcionMifo.MovimientoInvalidoExcepcion{
            Usuario usuario = new Usuario("Usuario predeterminado", "Contraseña predeterminado", "Correo predeterminado @example.com", 0);
            new MenuMifo(usuario);
        }
}
