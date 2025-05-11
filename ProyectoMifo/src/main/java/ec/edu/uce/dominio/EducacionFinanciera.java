package ec.edu.uce.dominio;
import java.io.Serializable;
import ec.edu.uce.util.ExcepcionMifo.MovimientoInvalidoExcepcion;
public class EducacionFinanciera implements Serializable {
    private static EducacionFinanciera[] educaciones = new EducacionFinanciera[10];
    private static int contadorId;
    private final int id;
    private final String titulo;
    private String contenido;
    static {
        contadorId=0;
    }
    public EducacionFinanciera() {
        this.id = contadorId++;
        this.titulo = "Sin título";
        this.contenido = "Sin contenido";
    }
    public EducacionFinanciera(String titulo, String contenido) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.contenido = contenido;
    }
    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public boolean equals(Object object) {
        EducacionFinanciera otraEducacion = null;
        boolean resp = false;

        if (object != null && object instanceof EducacionFinanciera) {
            otraEducacion = (EducacionFinanciera) object;

            if (this == object || titulo.equals(otraEducacion.titulo)) {
                resp = true;
            }
        }
        return resp;
    }
    public String toString() {
        return "EducacionFinanciera: "
                + "Contador= " + id
                + " Titulo= " + titulo
                + " Contenido= " + contenido;
    }

    public static void agregarEducacion(EducacionFinanciera nuevaEducacion) throws MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacion : educaciones) {
            if (educacion != null && educacion.equals(nuevaEducacion)) {
                throw new MovimientoInvalidoExcepcion("Ya existe una educación financiera con el mismo título.");
            }
        }
        if (educaciones[educaciones.length - 1] != null) {
            EducacionFinanciera[] aux = new EducacionFinanciera[educaciones.length * 2];
            System.arraycopy(educaciones, 0, aux, 0, educaciones.length);
            educaciones = aux;
        }

        for (int i = 0; i < educaciones.length; i++) {
            if (educaciones[i] == null) {
                educaciones[i] = nuevaEducacion;
                break;
            }
        }
    }
    public static EducacionFinanciera buscarEducacion(String titulo) throws MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacion : educaciones) {
            if (educacion != null && educacion.getTitulo().equalsIgnoreCase(titulo)) {
                return educacion;
            }
        }
        throw new MovimientoInvalidoExcepcion("Educación financiera no encontrada con el título: " + titulo);
    }
    public static void eliminarEducacion(String titulo) throws MovimientoInvalidoExcepcion {
        boolean encontrada = false;
        for (int i = 0; i < educaciones.length; i++) {
            if (educaciones[i] != null && educaciones[i].getTitulo().equalsIgnoreCase(titulo)) {
                educaciones[i] = null;
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            throw new MovimientoInvalidoExcepcion("Educación financiera no encontrada con el título: " + titulo);
        }
        EducacionFinanciera[] aux = new EducacionFinanciera[educaciones.length];
        int index = 0;
        for (EducacionFinanciera educacion : educaciones) {
            if (educacion != null) {
                aux[index++] = educacion;
            }
        }
        educaciones = aux;
    }
}
