package ec.edu.uce.dominio;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.util.ExcepcionMifo;
//import Datos.SerializacionPresupuesto;
import java.io.Serializable;
import java.util.Date;
public class Presupuesto {
    private double presupuesto;
    private Date fecha;
    private double gastoTotal;
    private double ingresoTotal;
    private Movimiento[] movimientos;
    private int numMovimientos;
    private ENUM tipo;
    public enum ENUM {
        ANUAL, SEMANAL, MENSUAL
    }
    public Presupuesto(double presupuesto, Date fecha, double gastoTotal, double ingresoTotal, int numMovimientos, ENUM tipo) {
        this(presupuesto, fecha, gastoTotal, ingresoTotal, 0.0, numMovimientos, tipo);
    }

    public Presupuesto(double presupuesto, Date fecha, double gastoTotal, double ingresoTotal, double par3, int numMovimientos, ENUM tipo) {
        this.presupuesto = presupuesto;
        this.fecha = fecha;
        this.gastoTotal = gastoTotal;
        this.ingresoTotal = ingresoTotal;
        this.movimientos = new Movimiento[10]; // Inicializar con tamaño 10 o el tamaño que desees
        this.numMovimientos = numMovimientos;
        this.tipo = tipo;
    }

    public Presupuesto(Double presupuesto, Date fecha) {
        this(presupuesto, fecha, 0, 0, 0, null);
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(double gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    public double getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    public Movimiento[] getMovimientos() {
        Movimiento[] result = new Movimiento[numMovimientos];
        System.arraycopy(movimientos, 0, result, 0, numMovimientos);
        return result;
    }

    public int getNumMovimientos() {
        return numMovimientos;
    }

    public void setNumMovimientos(int numMovimientos) {
        this.numMovimientos = numMovimientos;
    }

    public ENUM getTipo() {
        return tipo;
    }

    public void setTipo(ENUM tipo) {
        this.tipo = tipo;
    }

    public void agregarMovimiento(Movimiento movimiento) {
        if (numMovimientos == movimientos.length) {
            Movimiento[] nuevoArray = new Movimiento[movimientos.length * 2];
            System.arraycopy(movimientos, 0, nuevoArray, 0, movimientos.length);
            movimientos = nuevoArray;
        }
        movimientos[numMovimientos] = movimiento;
        numMovimientos++;

        if (movimiento instanceof Ingreso) {
            presupuesto += movimiento.getMonto();
            ingresoTotal += movimiento.getMonto();
        } else if (movimiento instanceof Gasto) {
            presupuesto -= movimiento.getMonto();
            gastoTotal += movimiento.getMonto();
        }
    }

    public String agregarMovimiento(String descripcion, double monto, Date fecha, boolean esIngreso) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Movimiento nuevoMovimiento = esIngreso ? new Ingreso(descripcion, monto, fecha, TipoIngreso.SALARIO) : new Gasto(descripcion, monto, fecha, TipoGasto.TRANSPORTE);

        // Verificar duplicados
        for (int i = 0; i < numMovimientos; i++) {
            if (movimientos[i] != null && movimientos[i].equals(nuevoMovimiento)) {
                throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe un movimiento con la misma descripción, monto y fecha.");
            }
        }

        agregarMovimiento(nuevoMovimiento);
        return "Descripción: " + descripcion + "\nMonto: " + monto + "\nFecha: " + fecha;
    }

    public void editarMovimiento(int indice, String nuevaDescripcion, double nuevoMonto, Date nuevaFecha, boolean esIngreso) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numMovimientos) {
            Movimiento movimientoOriginal = movimientos[indice];
            double montoAnterior = movimientoOriginal.getMonto();

            // Actualizar el movimiento
            movimientoOriginal.setDescripcion(nuevaDescripcion);
            movimientoOriginal.setMonto(nuevoMonto);
            movimientoOriginal.setFecha(nuevaFecha);

            // Ajustar el presupuesto
            if (movimientoOriginal instanceof Ingreso) {
                if (esIngreso) {
                    // Actualizar ingreso
                    presupuesto = presupuesto - montoAnterior + nuevoMonto;
                } else {
                    // Cambiar de ingreso a gasto
                    presupuesto = presupuesto - montoAnterior - nuevoMonto;
                }
            } else if (movimientoOriginal instanceof Gasto) {
                if (esIngreso) {
                    // Cambiar de gasto a ingreso
                    presupuesto = presupuesto + montoAnterior + nuevoMonto;
                } else {
                    // Actualizar gasto
                    presupuesto = presupuesto + montoAnterior - nuevoMonto;
                }
            }

            // Actualizar los totales
            if (esIngreso) {
                ingresoTotal += nuevoMonto - montoAnterior;
            } else {
                gastoTotal += nuevoMonto - montoAnterior;
            }
        } else {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice de movimiento inválido.");
        }
    }

    public void eliminarMovimiento(int indice) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numMovimientos) {
            Movimiento movimiento = movimientos[indice];
            if (movimiento instanceof Ingreso) {
                presupuesto -= movimiento.getMonto();
                ingresoTotal -= movimiento.getMonto();
            } else if (movimiento instanceof Gasto) {
                presupuesto += movimiento.getMonto();
                gastoTotal -= movimiento.getMonto();
            }

            // Reordenar el array usando System.arraycopy
            Movimiento[] nuevosMovimientos = new Movimiento[movimientos.length - 1];
            System.arraycopy(movimientos, 0, nuevosMovimientos, 0, indice);
            System.arraycopy(movimientos, indice + 1, nuevosMovimientos, indice, numMovimientos - indice - 1);
            movimientos = nuevosMovimientos;
            numMovimientos--;
        } else {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice de movimiento inválido.");
        }
    }

    public Movimiento buscarMovimiento(int indice) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numMovimientos) {
            return movimientos[indice];
        } else {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice de movimiento inválido.");
        }
    }

    public String consultarMovimientos() {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < numMovimientos; i++) {
            texto.append(movimientos[i]).append("\n");
        }
        return texto.toString();
    }

    public String inicializarMovimientos() {
        StringBuilder resultado = new StringBuilder();
        try {
            resultado.append(agregarMovimiento("Ingreso", 1000.0, new Date(), true)).append("\n");
            resultado.append(agregarMovimiento("Gasto", 200.0, new Date(), false)).append("\n");
            resultado.append(agregarMovimiento("Ingreso", 500.0, new Date(), true)).append("\n");
        } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }

    public boolean validarDuplicado(double monto, Date fecha) {
        for (int i = 0; i < numMovimientos; i++) {
            if (movimientos[i] != null && movimientos[i].getMonto() == monto && movimientos[i].getFecha().equals(fecha)) {
                return true;
            }
        }
        return false;
    }
    public String toString() {
        return "Presupuesto:\n"
                + "Presupuesto: " + presupuesto + "\n"
                + "Fecha: " + fecha + "\n"
                + "Gasto Total: " + gastoTotal + "\n"
                + "Ingreso Total: " + ingresoTotal + "\n"
                + "Movimientos:\n" + consultarMovimientos();
    }
}
