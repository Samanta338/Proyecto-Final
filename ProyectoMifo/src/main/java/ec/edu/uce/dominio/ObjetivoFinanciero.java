package ec.edu.uce.dominio;
import java.io.Serializable;
import java.util.Date;
public class ObjetivoFinanciero implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descripcion;
    private double monto;
    private Date fecha;
    private Categoria categoria;
    public ObjetivoFinanciero() {
        this("Sin descripcion", 0, new Date(), new Categoria());
    }
    public ObjetivoFinanciero(String descripcion, double monto, Date fecha, Categoria categoria) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    // Getters y Setters
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public boolean equals(Object object) {
        ObjetivoFinanciero otroObjetivo = null;
        boolean resp = false;

        if (object != null && object instanceof ObjetivoFinanciero) {
            otroObjetivo = (ObjetivoFinanciero) object;

            if (Double.compare(this.monto, otroObjetivo.monto) == 0
                    && this.descripcion.equals(otroObjetivo.descripcion)
                    && (this.fecha != null ? this.fecha.equals(otroObjetivo.fecha) : otroObjetivo.fecha == null)
                    && this.categoria.equals(otroObjetivo.categoria)) {
                resp = true;
            }
        }
        return resp;
    }
    public String toString() {
        return "Objetivo Financiero:\n"
                + "Descripción: " + descripcion + "\n"
                + "Monto: " + monto + "\n"
                + "Fecha: " + fecha + "\n"
                + "Categoría: " + categoria + "\n";
    }
}



