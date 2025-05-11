package ec.edu.uce.test;
import java.util.Scanner;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.consola.SubMenuGestionarMovimiento;
import java.util.Date;
import ec.edu.uce.dominio.Presupuesto;
import ec.edu.uce.dominio.Usuario;
public class TestSubMenuMovimiento {
    public static void main(String[] args) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Scanner entrada = new Scanner(System.in);
        Usuario usuario = new Usuario("testUser");
        Presupuesto presupuesto = new Presupuesto(5000.0, new Date());
        usuario.crearPresupuesto(presupuesto);
        SubMenuGestionarMovimiento subMenu = new SubMenuGestionarMovimiento(usuario);
        subMenu.menuGestionarMovimiento();
        entrada.close();
    }
}
