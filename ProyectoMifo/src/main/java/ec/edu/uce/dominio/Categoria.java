package ec.edu.uce.dominio;
import java.io.Serializable;
public class Categoria implements Serializable {
    private static Categoria[]categorias  = new Categoria[10];
    private static int contadorId;
    static{
        contadorId=0;
    }
    private String nombreCategoria;
    public Categoria() {
        this("Sin categoria");
    }
    public Categoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    public String getNombreCategoria() {
        return nombreCategoria;
    }
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    public String toString() {
        return "Categoria: " + nombreCategoria;
    }
    public boolean equals(Object object) {
        Categoria otraCategoria = null;
        boolean resp = false;

        if (object != null && object instanceof Categoria) {
            otraCategoria = (Categoria) object;

            if (this == object || nombreCategoria.equals(otraCategoria.nombreCategoria)) {
                resp = true;
            }
        }
        return resp;
    }
}
