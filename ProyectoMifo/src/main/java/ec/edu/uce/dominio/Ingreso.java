package ec.edu.uce.dominio;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.util.ExcepcionMifo.MovimientoInvalidoExcepcion;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Ingreso extends Movimiento {
    private TipoIngreso tipo;
    public Ingreso() {
        super();
        this.tipo = TipoIngreso.BONO;
    }
    public Ingreso(String descripcion, double monto, Date fecha, TipoIngreso tipo) {
        super(descripcion, monto, fecha);
        this.tipo = tipo;
    }
    public Ingreso(String descripcion, double monto, TipoIngreso tipo) {
        super(descripcion, monto);
        this.tipo = tipo;
    }
    public Ingreso(String descripcion, TipoIngreso tipo) {
        super(descripcion);
        this.tipo = tipo;
    }

    public Ingreso(String descripcion, double monto, Date fecha) {
        this(descripcion, monto, fecha, TipoIngreso.BONO);
    }

    public Ingreso(String descripcion, double monto) {
        this(descripcion, monto, new Date(), TipoIngreso.BONO);
    }

    public Ingreso(String descripcion) {
        this(descripcion, 0, new Date(), TipoIngreso.BONO);
    }

    public TipoIngreso getTipo() {
        return tipo;
    }

    public void setTipo(TipoIngreso tipo) {
        this.tipo = tipo;
    }
    public boolean validarDuplicado(Movimiento otroMovimiento) {
        if (otroMovimiento instanceof Ingreso) {
            return this.equals(otroMovimiento);
        }
        return false;
    }
    public boolean registrar() {
        if (getMonto() <= 0) {
            try {
                throw new MovimientoInvalidoExcepcion("El monto del ingreso debe ser mayor que cero.");
            } catch (MovimientoInvalidoExcepcion ex) {
                Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        System.out.println("Ingreso registrado: \nDescripción: " + getDescripcion() + "\nMonto: " + getMonto() + "\nFecha: " + getFecha());
        return true;
    }


    public String toString() {
        return "Ingreso:\n" + super.toString() + "\nTipo: " + tipo;
    }


    public void realizar() throws ExcepcionMifo.SaldoInsuficienteExcepcion, MovimientoInvalidoExcepcion {
        if (getMonto() <= 0) {
            throw new MovimientoInvalidoExcepcion("El monto del ingreso debe ser mayor que cero.");
        }
        System.out.println("Realizando ingreso: \nDescripción: " + getDescripcion() + "\nMonto: " + getMonto() + "\nFecha: " + getFecha() + "\nTipo: " + tipo);
    }
}

