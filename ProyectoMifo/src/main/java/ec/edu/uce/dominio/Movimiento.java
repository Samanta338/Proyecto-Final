package ec.edu.uce.dominio;
import ec.edu.uce.util.ExcepcionMifo.SaldoInsuficienteExcepcion;
import ec.edu.uce.util.ExcepcionMifo.MovimientoInvalidoExcepcion;
import java.util.Date;
public  abstract  class Movimiento {
    protected String descripcion;
    protected double monto;
    protected Date fecha;
    public Movimiento() {
        this("Sin descripcion", 0, new Date());
    }
    public Movimiento(String descripcion, double monto, Date fecha) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
    }
    public Movimiento(String descripcion, double monto) {
        this(descripcion, monto, new Date());
    }
    public Movimiento(String descripcion) {
        this(descripcion, 0.0, new Date());
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    @Override
    public boolean equals(Object object) {
        Movimiento otroMovimiento = null;
        boolean resp = false;

        if (object != null && object instanceof Movimiento) {
            otroMovimiento = (Movimiento) object;

            if (Double.compare(this.monto, otroMovimiento.monto) == 0
                    && this.fecha.equals(otroMovimiento.fecha)) {
                resp = true;
            }
        }
        return resp;
    }
    public String toString() {
        return "\nDescripcion= " + descripcion
                + "\nMonto= " + monto
                + "\nFecha= " + fecha;
    }
    public abstract boolean registrar();
    public abstract boolean validarDuplicado(Movimiento otroMovimiento);
    public abstract void realizar() throws SaldoInsuficienteExcepcion, MovimientoInvalidoExcepcion;
}
