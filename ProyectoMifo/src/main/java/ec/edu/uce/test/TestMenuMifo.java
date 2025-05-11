package ec.edu.uce.test;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.consola.MenuMifo;
public class TestMenuMifo {
    public static void main(String[] args) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Usuario usuario = new Usuario("testUser", "testPass", "test@example.com",0);
        MenuMifo menuFinFit = new MenuMifo(usuario);
        menuFinFit.menuMifo();
    }
}
