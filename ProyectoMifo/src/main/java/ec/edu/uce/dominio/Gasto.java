package ec.edu.uce.dominio;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.util.ExcepcionMifo.MovimientoInvalidoExcepcion;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Gasto extends Movimiento  {
    private TipoGasto categoria;
    public Gasto() {
        super();
        this.categoria = TipoGasto.ALIMENTACION;
    }
    public Gasto(String descripcion, double monto, Date fecha, TipoGasto categoria) {
        super(descripcion, monto, fecha);
        this.categoria = categoria;
    }

    public Gasto(String descripcion, double monto, TipoGasto categoria) {
        super(descripcion, monto);
        this.categoria = categoria;
    }

    public Gasto(String descripcion, TipoGasto categoria) {
        super(descripcion);
        this.categoria = categoria;
    }

    public Gasto(String descripcion, double monto, Date fecha) {
        this(descripcion, monto, fecha, TipoGasto.ALIMENTACION);
    }

    public Gasto(String descripcion, double monto) {
        this(descripcion, monto, new Date(), TipoGasto.ALIMENTACION);
    }

    public Gasto(String descripcion) {
        this(descripcion, 0, new Date(), TipoGasto.ALIMENTACION);
    }
    public TipoGasto getCategoria() {
        return categoria;
    }
    public void setCategoria(TipoGasto categoria) {
        this.categoria = categoria;
    }
    public boolean validarDuplicado(Movimiento otroMovimiento) {
        if (otroMovimiento instanceof Gasto) {
            return this.equals(otroMovimiento);
        }
        return false;
    }
    public boolean registrar() {
        if (getMonto() <= 0) {
            try {
                throw new MovimientoInvalidoExcepcion("El monto del gasto debe ser mayor que cero.");
            } catch (MovimientoInvalidoExcepcion ex) {
                Logger.getLogger(Gasto.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        System.out.println("Gasto registrado: \nDescripción: " + getDescripcion() + "\nMonto: " + getMonto() + "\nFecha: " + getFecha());
        return true;
    }
    public String toString() {
        return "Gasto:\n" + super.toString() + "\nCategoría: " + categoria;
    }
    public void realizar() throws ExcepcionMifo.SaldoInsuficienteExcepcion, MovimientoInvalidoExcepcion{
        if (getMonto() <= 0) {
            throw new MovimientoInvalidoExcepcion("El monto del gasto debe ser mayor que cero.");
        }
        System.out.println("Realizando gasto: \nDescripción: " + getDescripcion() + "\nMonto: " + getMonto() + "\nFecha: " + getFecha());
    }
}

