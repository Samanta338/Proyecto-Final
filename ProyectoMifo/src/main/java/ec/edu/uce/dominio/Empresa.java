package ec.edu.uce.dominio;
import java.io.Serializable;
import java.util.Arrays;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.util.ExcepcionMifo.UsuarioNoEncontradoExcepcion;
import ec.edu.uce.util.ExcepcionMifo.MovimientoInvalidoExcepcion;
public class Empresa {
    private Usuario[] usuarios;
    private int numUsuarios;
    private static Categoria[] categorias;
    private static int numCategorias;
    private EducacionFinanciera[] educacionesFinancieras;
    private int numEducacionesFinancieras;
    private int usuarioCodigoContador = 0;
    private int categoriaCodigoContador = 0;
    private int educacionCodigoContador = 0;

    public Empresa() {
        this(0, 0, 0);
    }

    public Empresa(int numUsuarios, int numCategorias, int numEducacionesFinancieras) {
        this.usuarios = new Usuario[numUsuarios];
        this.numUsuarios = 0;
        this.categorias = new Categoria[numCategorias];
        this.numCategorias = 0;
        this.educacionesFinancieras = new EducacionFinanciera[numEducacionesFinancieras];
        this.numEducacionesFinancieras = 0;
    }

    public int crearCodigoUsuario() {
        return ++usuarioCodigoContador;
    }

    public String crearUsuarioConCodigo(String nombre, String contrasena, String correo) {
        int codigo = crearCodigoUsuario();
        if (numUsuarios == usuarios.length) {
            Usuario[] aux = new Usuario[usuarios.length + 1];
            System.arraycopy(usuarios, 0, aux, 0, usuarios.length);
            usuarios = aux;
    }
        usuarios[numUsuarios] = new Usuario(nombre, contrasena, correo, codigo);
        numUsuarios++;
        return "Código: " + codigo + "\nNombre: " + nombre + "\nContraseña: " + contrasena + "\nCorreo: " + correo;
    }

    public double crearCodigoCategoria() {
        return ++categoriaCodigoContador;
    }

    public String crearCategoriaConCodigo(String nombreCategoria) {
        double codigo = crearCodigoCategoria();
        if (numCategorias == categorias.length) {
            Categoria[] aux = new Categoria[categorias.length + 1];
            System.arraycopy(categorias, 0, aux, 0, categorias.length);
            categorias = aux;
        }
        categorias[numCategorias] = new Categoria(nombreCategoria);
        numCategorias++;
        return "Código: " + codigo + "\nNombre Categoría: " + nombreCategoria;
    }

    public double crearCodigoEducacionFinanciera() {
        return ++educacionCodigoContador;
    }

    public String crearEducacionFinancieraConCodigo(String titulo, String contenido) {
        double codigo = crearCodigoEducacionFinanciera();
        if (numEducacionesFinancieras == educacionesFinancieras.length) {
            EducacionFinanciera[] aux = new EducacionFinanciera[educacionesFinancieras.length + 1];
            System.arraycopy(educacionesFinancieras, 0, aux, 0, educacionesFinancieras.length);
            educacionesFinancieras = aux;
        }
        educacionesFinancieras[numEducacionesFinancieras] = new EducacionFinanciera(titulo, contenido);
        numEducacionesFinancieras++;
        return "Código: " + codigo + "\nTítulo: " + titulo + "\nContenido: " + contenido;
    }

    public String crearUsuario(String nombre, String contrasena, String correo) {
        int codigo = crearCodigoUsuario();
        if (numUsuarios == usuarios.length) {
            Usuario[] aux = usuarios;
            usuarios = new Usuario[aux.length + 1];
            System.arraycopy(aux, 0, usuarios, 0, aux.length);
        }
        usuarios[numUsuarios] = new Usuario(nombre, contrasena, correo, numUsuarios);
        numUsuarios++;
        return "Código: " + codigo + "\nNombre: " + nombre + "\nContraseña: " + contrasena + "\nCorreo: " + correo;

    }

    public String crearUsuario(Usuario nuevoUsuario) throws MovimientoInvalidoExcepcion{
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i].equals(nuevoUsuario)) {
                throw new MovimientoInvalidoExcepcion("Ya existe un usuario con este nombre.");
            }
        }

        if (numUsuarios == usuarios.length) {
            Usuario[] aux = usuarios;
            usuarios = new Usuario[aux.length + 1];
            System.arraycopy(aux, 0, usuarios, 0, aux.length);
        }
        usuarios[numUsuarios] = nuevoUsuario;
        numUsuarios++;
        return "Usuario creado con éxito.\nNombre: " + nuevoUsuario.getNombre() + "\nContraseña: " + nuevoUsuario.getContrasena() + "\nCorreo: " + nuevoUsuario.getCorreo();
    }

    public String consultarUsuario() {
        StringBuilder texto = new StringBuilder();
        for (Usuario usuario : usuarios) {
            if (usuario != null) {
                texto.append(usuario).append("\n");
            }
        }
        return texto.toString();
    }

    public String consultarUsuario(Usuario nuevoUsuario) throws UsuarioNoEncontradoExcepcion {
        StringBuilder texto = new StringBuilder();
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getNombre().equalsIgnoreCase(nuevoUsuario.getNombre() + nuevoUsuario.getContrasena() + nuevoUsuario.getCorreo())) {
                texto.append(usuario).append("\n");
            }
        }
        if (texto.length() > 0) {
            return texto.toString();
        } else {
            throw new UsuarioNoEncontradoExcepcion(nuevoUsuario.getNombre());
        }
    }

    public void editarUsuario(int indice, String nombre, String contrasena, String correo) {
        if (indice >= 0 && indice < numUsuarios) {
            usuarios[indice].setNombre(nombre);
            usuarios[indice].setContrasena(contrasena);
            usuarios[indice].setCorreo(correo);
        }
    }

    public void editarUsuario(int indice, Usuario nuevoUsuario) throws UsuarioNoEncontradoExcepcion {
        if (indice >= 0 && indice < numUsuarios) {
            usuarios[indice].setNombre(nuevoUsuario.getNombre());
            usuarios[indice].setContrasena(nuevoUsuario.getContrasena());
            usuarios[indice].setCorreo(nuevoUsuario.getCorreo());
        } else {
            throw new UsuarioNoEncontradoExcepcion("Usuario no encontrado con índice: " + indice);
        }
    }

    public boolean validarCredencia(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getNombre().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public Usuario buscarUsuario(int indice) throws UsuarioNoEncontradoExcepcion {
        if (indice >= 0 && indice < numUsuarios) {
            return usuarios[indice];
        } else {
            throw new UsuarioNoEncontradoExcepcion("Usuario no encontrado con índice: " + indice);
        }
    }

    public Usuario buscarUsuarioPorNombre(Usuario nuevoUsuario) throws UsuarioNoEncontradoExcepcion {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getNombre().equalsIgnoreCase(nuevoUsuario.getNombre() + nuevoUsuario.getContrasena() + nuevoUsuario.getCorreo())) {
                return usuario;
            }
        }
        throw new UsuarioNoEncontradoExcepcion(nuevoUsuario.getNombre());
    }

    public void eliminarUsuario(int indice) throws UsuarioNoEncontradoExcepcion {
        if (indice >= 0 && indice < numUsuarios) {
            Usuario[] aux = usuarios;
            usuarios = new Usuario[aux.length - 1];
            if (indice == 0) {
                System.arraycopy(aux, 1, usuarios, 0, usuarios.length);
            } else if (indice == numUsuarios - 1) {
                System.arraycopy(aux, 0, usuarios, 0, usuarios.length);
            } else {
                System.arraycopy(aux, 0, usuarios, 0, indice);
                System.arraycopy(aux, indice + 1, usuarios, indice, usuarios.length - indice);
            }
            numUsuarios--;
        } else {
            throw new UsuarioNoEncontradoExcepcion("Usuario no encontrado con índice: " + indice);
        }
    }

    public String inicializarUsuarios() {
        StringBuilder resultado = new StringBuilder();
        resultado.append(crearUsuario("Usuario1", "Contraseña1", "usuario1@example.com")).append("\n");
        resultado.append(crearUsuario("Usuario2", "Contraseña2", "usuario2@example.com")).append("\n");
        resultado.append(crearUsuario("Usuario3", "Contraseña3", "usuario3@example.com")).append("\n");
        return resultado.toString();
    }

    public boolean validarDuplicado(String nombre) throws MovimientoInvalidoExcepcion {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getNombre().equals(nombre)) {
                throw new MovimientoInvalidoExcepcion("Ya existe un usuario con el mismo nombre.");
            }
        }
        return false;
    }

    public String crearCategoria(String nombreCategoria) {
        if (numCategorias == categorias.length) {
            Categoria[] aux = categorias;
            categorias = new Categoria[aux.length + 1];
            System.arraycopy(aux, 0, categorias, 0, aux.length);
        }
        categorias[numCategorias] = new Categoria(nombreCategoria);
        numCategorias++;
        return "Nombre Categoria:" + nombreCategoria;
    }

    public String crearCategoria(Categoria nuevaCategoria) {
        if (numCategorias == categorias.length) {
            Categoria[] aux = categorias;
            categorias = new Categoria[aux.length + 1];
            System.arraycopy(aux, 0, categorias, 0, aux.length);
        }
        categorias[numCategorias] = nuevaCategoria;
        numCategorias++;
        return "Nombre Categoria: " + nuevaCategoria.getNombreCategoria();
    }

    public static String consultarCategoria() {
        StringBuilder texto = new StringBuilder();
        for (Categoria categoria : categorias) {
            if (categoria != null) {
                texto.append(categoria).append("\n");
            }
        }
        return texto.toString();
    }

    public String consultarCategoria(Categoria nuevaCategoria) {
        StringBuilder texto = new StringBuilder();
        for (Categoria categoria : categorias) {
            if (categoria != null && categoria.getNombreCategoria().equalsIgnoreCase(nuevaCategoria.getNombreCategoria())) {
                texto.append(categoria).append("\n");
            }
        }
        return texto.length() > 0 ? texto.toString() : "Categoria no encontrada";
    }

    public void editarCategoria(int indice, String nombreCategoria) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numCategorias) {
            categorias[indice].setNombreCategoria(nombreCategoria);
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de categoría inválido.");
        }
    }

    public void editarCategoria(int indice, Categoria nuevaCategoria) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numCategorias) {
            categorias[indice] = nuevaCategoria;
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de categoría inválido.");
        }
    }

    public static Categoria buscarCategoria(int indice) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numCategorias) {
            return categorias[indice];
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de categoría inválido.");
        }
    }

    public static Categoria buscarCategoria(Categoria nuevaCategoria) throws MovimientoInvalidoExcepcion {
        for (Categoria categoria : categorias) {
            if (categoria != null && categoria.getNombreCategoria().equalsIgnoreCase(nuevaCategoria.getNombreCategoria())) {
                return nuevaCategoria;
            }
        }
        throw new MovimientoInvalidoExcepcion("Categoría no encontrada: " + nuevaCategoria.getNombreCategoria());
    }

    public static void eliminarCategoria(int indice) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numCategorias) {
            Categoria[] aux = categorias;
            categorias = new Categoria[aux.length - 1];
            if (indice == 0) {
                System.arraycopy(aux, 1, categorias, 0, categorias.length);
            } else if (indice == numCategorias - 1) {
                System.arraycopy(aux, 0, categorias, 0, categorias.length);
            } else {
                System.arraycopy(aux, 0, categorias, 0, indice);
                System.arraycopy(aux, indice + 1, categorias, indice, categorias.length - indice);
            }
            numCategorias--;
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de categoría inválido.");
        }
    }

    public String inicializarCategorias() {
        StringBuilder resultado = new StringBuilder();
        resultado.append(crearCategoria("Categoria1")).append("\n");
        resultado.append(crearCategoria("Categoria2")).append("\n");
        resultado.append(crearCategoria("Categoria3")).append("\n");
        return resultado.toString();
    }

    public String crearEducacionFinanciera(String titulo, String contenido) throws MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacion : educacionesFinancieras) {
            if (educacion != null && educacion.getTitulo().equals(titulo)) {
                throw new MovimientoInvalidoExcepcion("Ya existe una educación financiera con el mismo título.");
            }
        }

        if (numEducacionesFinancieras == educacionesFinancieras.length) {
            EducacionFinanciera[] aux = educacionesFinancieras;
            educacionesFinancieras = new EducacionFinanciera[aux.length + 1];
            System.arraycopy(aux, 0, educacionesFinancieras, 0, aux.length);
        }
        educacionesFinancieras[numEducacionesFinancieras] = new EducacionFinanciera(titulo, contenido);
        numEducacionesFinancieras++;
        return "Título: " + titulo + "\nContenido: " + contenido;
    }

    public String crearEducacionFinanciera(EducacionFinanciera nuevaEducacion) throws MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacion : educacionesFinancieras) {
            if (educacion != null && educacion.getTitulo().equals(nuevaEducacion.getTitulo())) {
                throw new MovimientoInvalidoExcepcion("Ya existe una educación financiera con el mismo título.");
            }
        }

        if (numEducacionesFinancieras == educacionesFinancieras.length) {
            EducacionFinanciera[] aux = educacionesFinancieras;
            educacionesFinancieras = new EducacionFinanciera[aux.length + 1];
            System.arraycopy(aux, 0, educacionesFinancieras, 0, aux.length);
        }
        educacionesFinancieras[numEducacionesFinancieras] = nuevaEducacion;
        numEducacionesFinancieras++;
        return "Titulo: " + nuevaEducacion.getTitulo() + "\nContenido: " + nuevaEducacion.getContenido();
    }

    public String consultarEducacionFinanciera() {
        StringBuilder texto = new StringBuilder();
        for (EducacionFinanciera educacionFinanciera : educacionesFinancieras) {
            if (educacionFinanciera != null) {
                texto.append(educacionFinanciera).append("\n");
            }
        }
        return texto.toString();
    }

    public String consultarEducacionFinanciera(EducacionFinanciera nuevaEducacionFinanciera) throws MovimientoInvalidoExcepcion {
        StringBuilder texto = new StringBuilder();
        for (EducacionFinanciera educacionFinanciera : educacionesFinancieras) {
            if (educacionFinanciera != null && educacionFinanciera.equals(nuevaEducacionFinanciera.getTitulo() + nuevaEducacionFinanciera.getContenido())) {
                texto.append(educacionFinanciera).append("\n");
            }
        }
        if (texto.length() > 0) {
            return texto.toString();
        } else {
            throw new MovimientoInvalidoExcepcion("Educación financiera no encontrada: " + nuevaEducacionFinanciera.getTitulo());
        }
    }

    public void editarEducacionFinanciera(int indice, String nuevoTitulo, String nuevoContenido) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numEducacionesFinancieras) {
            EducacionFinanciera nuevaEducacion = new EducacionFinanciera(nuevoTitulo, nuevoContenido);
            educacionesFinancieras[indice] = nuevaEducacion;
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de educación financiera inválido.");
        }
    }

    public void editarEducacionFinanciera(int indice, EducacionFinanciera nuevaEducacion) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numEducacionesFinancieras) {
            educacionesFinancieras[indice] = nuevaEducacion;
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de educación financiera inválido.");
        }
    }

    public EducacionFinanciera buscarEducacionFinanciera(int indice) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numEducacionesFinancieras) {
            return educacionesFinancieras[indice];
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de educación financiera inválido.");
        }
    }

    public EducacionFinanciera buscarEducacionFinanciera(String titulo) throws MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacionFinanciera : educacionesFinancieras) {
            if (educacionFinanciera != null && educacionFinanciera.getTitulo().equalsIgnoreCase(titulo)) {
                return educacionFinanciera;
            }
        }
        throw new MovimientoInvalidoExcepcion("Educación financiera no encontrada: " + titulo);
    }

    public EducacionFinanciera buscarEducacionFinanciera(EducacionFinanciera nuevaEducacionFinanciera) throws MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacionFinanciera : educacionesFinancieras) {
            if (educacionFinanciera != null && educacionFinanciera.getTitulo().equalsIgnoreCase(nuevaEducacionFinanciera.getTitulo() + nuevaEducacionFinanciera.getContenido())) {
                return nuevaEducacionFinanciera;
            }
        }
        throw new MovimientoInvalidoExcepcion("Educación financiera no encontrada: " + nuevaEducacionFinanciera.getTitulo());
    }

    public void eliminarEducacionFinanciera(int indice) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numEducacionesFinancieras) {
            EducacionFinanciera[] aux = educacionesFinancieras;
            educacionesFinancieras = new EducacionFinanciera[aux.length - 1];
            if (indice == 0) {
                System.arraycopy(aux, 1, educacionesFinancieras, 0, educacionesFinancieras.length);
            } else if (indice == numEducacionesFinancieras - 1) {
                System.arraycopy(aux, 0, educacionesFinancieras, 0, educacionesFinancieras.length);
            } else {
                System.arraycopy(aux, 0, educacionesFinancieras, 0, indice);
                System.arraycopy(aux, indice + 1, educacionesFinancieras, indice, educacionesFinancieras.length - indice);
            }
            numEducacionesFinancieras--;
        } else {
            throw new MovimientoInvalidoExcepcion ("Índice de educación financiera inválido.");
        }
    }

    public boolean eliminarEducacionFinanciera(String titulo) throws MovimientoInvalidoExcepcion {
        int indexToRemove = -1;
        for (int i = 0; i < educacionesFinancieras.length; i++) {
            if (educacionesFinancieras[i].getTitulo().equals(titulo)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            throw new MovimientoInvalidoExcepcion("Educación financiera no encontrada: " + titulo);
        }

        EducacionFinanciera[] newArray = new EducacionFinanciera[educacionesFinancieras.length - 1];
        System.arraycopy(educacionesFinancieras, 0, newArray, 0, indexToRemove);
        System.arraycopy(educacionesFinancieras, indexToRemove + 1, newArray, indexToRemove, educacionesFinancieras.length - indexToRemove - 1);

        educacionesFinancieras = newArray;
        return true;
    }

    public String inicializarEducacionesFinancieras() {
        StringBuilder resultado = new StringBuilder();
        try {
            resultado.append(crearEducacionFinanciera("El Buen uso del dinero", "buendin.pdf")).append("\n");
            resultado.append(crearEducacionFinanciera("Las malas administracciones", "malad.pdf")).append("\n");
            resultado.append(crearEducacionFinanciera("Como ahorrar", "ahorrar.pdf")).append("\n");
        } catch (MovimientoInvalidoExcepcion e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }

    public boolean validarDuplicado(Object o) throws MovimientoInvalidoExcepcion {
        if (!(o instanceof EducacionFinanciera)) {
            return false;
        }
        EducacionFinanciera otraEducacion = (EducacionFinanciera) o;
        if (this.equals(otraEducacion)) {
            throw new MovimientoInvalidoExcepcion("Ya existe una educación financiera con el mismo título.");
        }
        return false;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public void setNumUsuarios(int numUsuarios) {
        this.numUsuarios = numUsuarios;
    }

    public Categoria[] getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria[] categorias) {
        this.categorias = categorias;
    }

    public int getNumCategorias() {
        return numCategorias;
    }

    public void setNumCategorias(int numCategorias) {
        this.numCategorias = numCategorias;
    }

    public EducacionFinanciera[] getEducacionesFinancieras() {
        return educacionesFinancieras;
    }

    public void setEducacionesFinancieras(EducacionFinanciera[] educacionesFinancieras) {
        this.educacionesFinancieras = educacionesFinancieras;
    }

    public int getNumEducacionesFinancieras() {
        return numEducacionesFinancieras;
    }

    public void setNumEducacionesFinancieras(int numEducacionesFinancieras) {
        this.numEducacionesFinancieras = numEducacionesFinancieras;
    }

    @Override
    public String toString() {
        return "Usuarios=" + Arrays.toString(usuarios)
                + "Num Usuarios=" + numUsuarios
                + "Educaciones Financieras=" + Arrays.toString(educacionesFinancieras)
                + "Num Educaciones Financieras=" + numEducacionesFinancieras
                + "Categorias=" + Arrays.toString(categorias)
                + "Num Categorias " + numCategorias;
    }

    public Categoria buscarCategoriaPorNombre(String nombreCategoria) throws MovimientoInvalidoExcepcion {
        for (int i = 0; i < numCategorias; i++) {
            if (categorias[i].getNombreCategoria().equalsIgnoreCase(nombreCategoria)) {
                return categorias[i];
            }
        }
        throw new MovimientoInvalidoExcepcion("Categoría no encontrada: " + nombreCategoria);
    }
}
