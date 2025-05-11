package ec.edu.uce.test;
import ec.edu.uce.consola.MenuPrincipal;
import ec.edu.uce.util.ExcepcionMifo;
public class TestMenuPrincipal {
    public static void main(String[] args) throws ExcepcionMifo {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.mostrarMenuPrincipal();
    }
}
