package ec.edu.uce.dominio;
//import Datos.SerializacionUsuario;
import java.io.Serializable;
import java.util.Date;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
public class Usuario {
    private int codigo;
    private String nombre;
    private String contrasena;
    private String correo;
    private Presupuesto[] presupuestos;
    private int numPresupuestos;
    private ObjetivoFinanciero[] objetivosFinancieros;
    private int numObjetivosFinancieros;

    public Usuario(String nombre, String contrasena, String correo, int codigo) {
        this(nombre, contrasena, correo, 0, 0, codigo);
    }
    private static Usuario[] usuarios = new Usuario[10];
    private static int contadorId;

    static {
        contadorId = 0;
    }

    public Usuario(String nombre, String contrasena, String correo, int numPresupuestos, int numObjetivosFinancieros, int codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.presupuestos = new Presupuesto[0];
        this.numPresupuestos = numPresupuestos;
        this.objetivosFinancieros = new ObjetivoFinanciero[0];
        this.numObjetivosFinancieros = numObjetivosFinancieros;
    }
    public Usuario(String testUser) {
        this.nombre = testUser; // Inicialización correcta
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Presupuesto[] getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(Presupuesto[] presupuestos) {
        this.presupuestos = presupuestos;
    }

    public int getNumPresupuestos() {
        return numPresupuestos;
    }

    public void setNumPresupuestos(int numPresupuestos) {
        this.numPresupuestos = numPresupuestos;
    }

    public ObjetivoFinanciero[] getObjetivosFinancieros() {
        return objetivosFinancieros;
    }

    public void setObjetivosFinancieros(ObjetivoFinanciero[] objetivosFinancieros) {
        this.objetivosFinancieros = objetivosFinancieros;
    }

    public int getNumObjetivosFinancieros() {
        return numObjetivosFinancieros;
    }

    public void setNumObjetivosFinancieros(int numObjetivosFinancieros) {
        this.numObjetivosFinancieros = numObjetivosFinancieros;
    }

    public void cambiarContrasena(String contrasenaActual, String nuevaContrasena) throws ExcepcionMifo.ContrasenaInvalidaExcepcion {
        if (this.contrasena.equals(contrasenaActual)) {
            this.contrasena = nuevaContrasena;
            System.out.println("Contraseña cambiada correctamente.");
        } else {
            throw new ExcepcionMifo.ContrasenaInvalidaExcepcion("La contraseña actual no coincide. No se pudo cambiar la contraseña.");
        }
    }

    public void crearPresupuesto(Double presupuesto, Date fecha) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Presupuesto nuevoPresupuesto = new Presupuesto(presupuesto, fecha);

        // Verificar duplicados
        if (validarDuplicado(nuevoPresupuesto)) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe un presupuesto con el mismo valor y fecha.");
        }
        if (numPresupuestos == presupuestos.length) {
            Presupuesto[] aux = presupuestos;
            presupuestos = new Presupuesto[aux.length + 1];
            System.arraycopy(aux, 0, presupuestos, 0, aux.length);
        }
        presupuestos[numPresupuestos] = nuevoPresupuesto;
        numPresupuestos++;
    }
    public String crearPresupuesto(Presupuesto nuevoPresupuesto) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (validarDuplicado(nuevoPresupuesto)) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe un presupuesto con el mismo valor y fecha.");
        }

        if (numPresupuestos == presupuestos.length) {
            Presupuesto[] aux = presupuestos;
            presupuestos = new Presupuesto[aux.length + 1];
            System.arraycopy(aux, 0, presupuestos, 0, aux.length);
        }
        presupuestos[numPresupuestos] = nuevoPresupuesto;
        numPresupuestos++;

        return "Presupuesto: " + nuevoPresupuesto.getPresupuesto() + "\nFecha: " + nuevoPresupuesto.getFecha();
    }

    public String consultarPresupuesto() {
        StringBuilder texto = new StringBuilder();
        for (Presupuesto presupuesto : presupuestos) {
            if (presupuesto != null) {
                texto.append(presupuesto).append("\n");
            }
        }
        return texto.toString();
    }

    public void editarPresupuesto(int indice, double presupuesto, Date fecha, double gastoTotal, double ingresoTotal) {
        if (indice >= 0 && indice < numPresupuestos) {
            Presupuesto p = presupuestos[indice];
            if (p != null) {
                p.setPresupuesto(presupuesto);
                p.setFecha(fecha);
                p.setGastoTotal(gastoTotal);
                p.setIngresoTotal(ingresoTotal);
            } else {
                System.out.println("El presupuesto en el índice " + indice + " es nulo.");
            }
        } else {
            System.out.println("Índice fuera de rango: " + indice);
        }
    }

    public void editarPresupuesto(int indice, Presupuesto nuevoPresupuesto) {
        if (indice >= 0 && indice < numPresupuestos) {
            presupuestos[indice].setPresupuesto(nuevoPresupuesto.getPresupuesto());
            presupuestos[indice].setFecha(nuevoPresupuesto.getFecha());
            presupuestos[indice].setGastoTotal(nuevoPresupuesto.getGastoTotal());
            presupuestos[indice].setIngresoTotal(nuevoPresupuesto.getIngresoTotal());
        }
    }
    public Presupuesto buscarPresupuesto(int indice) {
        if (indice >= 0 && indice < numPresupuestos) {
            return presupuestos[indice];
        } else {
            return null;
        }
    }
    public void eliminarPresupuesto(int indice) {
        if (indice >= 0 && indice < numPresupuestos) {
            Presupuesto[] aux = presupuestos;
            presupuestos = new Presupuesto[aux.length - 1];
            System.arraycopy(aux, 0, presupuestos, 0, indice);
            System.arraycopy(aux, indice + 1, presupuestos, indice, presupuestos.length - indice);
            numPresupuestos--;
        }
    }

    public void inicializarPresupuestos() {
        try {
            this.crearPresupuesto(2000.0, new Date());
            this.crearPresupuesto(3000.0, new Date());
            this.crearPresupuesto(2500.0, new Date());
        } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
            e.printStackTrace();
        }
    }
    public boolean validarDuplicado(Presupuesto nuevoPresupuesto) {
        for (int i = 0; i < numPresupuestos; i++) {
            Presupuesto presupuestoExistente = presupuestos[i];
            if (presupuestoExistente.getFecha().equals(nuevoPresupuesto.getFecha())
                    && Double.compare(presupuestoExistente.getPresupuesto(), nuevoPresupuesto.getPresupuesto()) == 0) {
                return true;
            }
        }
        return false;
    }
    public void crearObjetivoFinanciero(String descripcion, double monto, Date fecha, Categoria categoria) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        ObjetivoFinanciero nuevoObjetivo = new ObjetivoFinanciero(descripcion, monto, fecha, categoria);
        if (validarDuplicado(nuevoObjetivo)) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe un objetivo financiero con la misma descripción y fecha.");
        }
        if (numObjetivosFinancieros == objetivosFinancieros.length) {
            ObjetivoFinanciero[] aux = objetivosFinancieros;
            objetivosFinancieros = new ObjetivoFinanciero[aux.length + 1];
            System.arraycopy(aux, 0, objetivosFinancieros, 0, aux.length);
        }
        objetivosFinancieros[numObjetivosFinancieros] = nuevoObjetivo;
        numObjetivosFinancieros++;
    }

    public String crearObjetivoFinanciero(ObjetivoFinanciero nuevoObjetivo) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (validarDuplicado(nuevoObjetivo)) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe un objetivo financiero con la misma descripción y fecha.");
        }

        if (numObjetivosFinancieros == objetivosFinancieros.length) {
            ObjetivoFinanciero[] aux = objetivosFinancieros;
            objetivosFinancieros = new ObjetivoFinanciero[aux.length + 1];
            System.arraycopy(aux, 0, objetivosFinancieros, 0, aux.length);
        }
        objetivosFinancieros[numObjetivosFinancieros] = nuevoObjetivo;
        numObjetivosFinancieros++;

        return "Objetivo: " + nuevoObjetivo.getDescripcion() + "\nMonto: " + nuevoObjetivo.getMonto() + "\nFecha: " + nuevoObjetivo.getFecha();
    }
    public String consultarObjetivoFinanciero() {
        StringBuilder texto = new StringBuilder();
        for (ObjetivoFinanciero objetivo : objetivosFinancieros) {
            if (objetivo != null) {
                texto.append(objetivo).append("\n");
            }
        }
        return texto.toString();
    }
    public void editarObjetivoFinanciero(int indice, String descripcion, double monto, Date fecha, Categoria categoria) {
        if (indice >= 0 && indice < numObjetivosFinancieros) {
            ObjetivoFinanciero o = objetivosFinancieros[indice];
            if (o != null) {
                o.setDescripcion(descripcion);
                o.setMonto(monto);
                o.setFecha(fecha);
                o.setCategoria(categoria);
            } else {
                System.out.println("El objetivo financiero en el índice " + indice + " es nulo.");
            }
        } else {
            System.out.println("Índice fuera de rango: " + indice);
        }
    }
    public void editarObjetivoFinanciero(int indice, ObjetivoFinanciero nuevoObjetivo) {
        if (indice >= 0 && indice < numObjetivosFinancieros) {
            objetivosFinancieros[indice].setDescripcion(nuevoObjetivo.getDescripcion());
            objetivosFinancieros[indice].setMonto(nuevoObjetivo.getMonto());
            objetivosFinancieros[indice].setFecha(nuevoObjetivo.getFecha());
            objetivosFinancieros[indice].setCategoria(nuevoObjetivo.getCategoria());
        }
    }
    public ObjetivoFinanciero buscarObjetivoFinanciero(int indice) {
        if (indice >= 0 && indice < numObjetivosFinancieros) {
            return objetivosFinancieros[indice];
        } else {
            return null;
        }
    }
    public void eliminarObjetivoFinanciero(int indice) {
        if (indice >= 0 && indice < numObjetivosFinancieros) {
            ObjetivoFinanciero[] aux = objetivosFinancieros;
            objetivosFinancieros = new ObjetivoFinanciero[aux.length - 1];
            System.arraycopy(aux, 0, objetivosFinancieros, 0, indice);
            System.arraycopy(aux, indice + 1, objetivosFinancieros, indice, objetivosFinancieros.length - indice);
            numObjetivosFinancieros--;
        }
    }
    public void inicializarObjetivosFinancieros() {
        try {
            this.crearObjetivoFinanciero("Ahorro para vacaciones", 1000.0, new Date(), new Categoria());
            this.crearObjetivoFinanciero("Fondo de emergencia", 1500.0, new Date(), new Categoria());
            this.crearObjetivoFinanciero("Compra de coche", 5000.0, new Date(), new Categoria());
        } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
            e.printStackTrace();
        }
    }
    private boolean validarDuplicado(ObjetivoFinanciero nuevoObjetivo) {
        for (int i = 0; i < numObjetivosFinancieros; i++) {
            ObjetivoFinanciero objetivoExistente = objetivosFinancieros[i];
            if (objetivoExistente.getDescripcion().equals(nuevoObjetivo.getDescripcion())
                    && objetivoExistente.getFecha().equals(nuevoObjetivo.getFecha())) {
                return true;
            }
        }
        return false;
    }
    public boolean validarContrasenaActual(String currentPassword) {
        String hashedCurrentPassword = ComprobacionMenu.hashearContrasena(currentPassword);
        return hashedCurrentPassword.equals(this.contrasena);
    }
    public void inicializarUsuario(String nombre, String contrasena, String correo, int codigo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.codigo = codigo;
        this.presupuestos = new Presupuesto[0];
        this.numPresupuestos = 0;
        this.objetivosFinancieros = new ObjetivoFinanciero[0];
        this.numObjetivosFinancieros = 0;
    }
    public String toString() {
        return "Usuario{"
                + "codigo=" + codigo
                + ", nombre='" + nombre + '\''
                + ", contrasena='" + contrasena + '\''
                + ", correo='" + correo + '\''
                + '}';
    }
    public boolean equals(Object object) {
        Usuario otroUsuario = null;
        boolean resp = false;

        if (object != null && object instanceof Usuario) {
            otroUsuario = (Usuario) object;

            if (this.codigo == otroUsuario.codigo) {
                resp = true;
            }
        }
        return resp;
    }
    public int hashCode() {
        return codigo;
    }
}
