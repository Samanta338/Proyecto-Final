package ec.edu.uce.test;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.consola.SubMenuGestionarObjetivosFinancieros;
public class TestSubMenuObjetivosFinancieros {
    public static void main(String[] args) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        SubMenuGestionarObjetivosFinancieros subMenu = new SubMenuGestionarObjetivosFinancieros();
        subMenu.menuGestionarObjetivosFinancieros();
    }
}
