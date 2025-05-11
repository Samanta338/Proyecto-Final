package ec.edu.uce.test;
import ec.edu.uce.consola.SubMenuGestionarPresupuesto;
import java.util.Scanner;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.util.ExcepcionMifo;
public class TestSubMenuPresupuesto {
    public static void main(String[] args) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Scanner entrada = new Scanner(System.in);
        Usuario usuario = new Usuario("Carlos", "1234567p", "example@.com", 0, 0, 0);
        SubMenuGestionarPresupuesto subMenu = new SubMenuGestionarPresupuesto(entrada);
        subMenu.menuGestionarPresupuesto(usuario);
        entrada.close();
    }
}
