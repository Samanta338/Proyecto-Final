package ec.edu.uce.util;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Scanner;
public class ComprobacionMenu {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    @Contract(pure = true)
    public static boolean validarDescripcion(@NotNull String descripcion) {
        return !descripcion.isEmpty();
    }
    @Nullable
    public static Double validarMonto(String montoStr) {
        Double monto = null;
        try {
            monto = Double.parseDouble(montoStr);
            if (monto < 0) {
                System.out.println("El monto ingresado es menor que cero.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("El monto debe ser un número y no puede estar vacío.");
        }
        return monto;
    }
    @Nullable
    public static Date validarFecha(@NotNull String fechaStr) {
        Date fecha = null;
        try {
            if (!fechaStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.out.println("La fecha ingresada no tiene el formato correcto (DIA/MES/AÑO).");
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            fecha = sdf.parse(fechaStr);
            if (fecha.before(new Date())) {
                System.out.println("La fecha ingresada está en el pasado.");
                return null;
            }
        } catch (ParseException e) {
            System.out.println("La fecha ingresada no es válida.");
        }
        return fecha;
    }
    public static int validarOpcionMenu(@NotNull Scanner teclado, int maxOpcion) {
        int seleccion;
        while (true) {
            try {
                seleccion = Integer.parseInt(teclado.nextLine());
                if (seleccion >= 1 && seleccion <= maxOpcion) {
                    break;
                } else {
                    System.out.println("¡Error! Opción no válida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("¡Error! Opción no válida. Inténtalo de nuevo.");
            }
        }
        return seleccion;
    }
    @Contract(pure = true)
    public static boolean validarNombreUsuario(@NotNull String nombre) {
        return nombre.matches("[a-zA-Z]+");
    }
    @Contract(pure = true)
    public static boolean validarContrasena(@NotNull String contrasena) {
        return contrasena.matches("(?=.*[a-zA-Z])(?=.*\\d).{8,}");

    }
    @Contract(pure = true)
    public static boolean validarCorreo(@NotNull String correo) {
        return correo.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$");
    }
    @Contract(pure = true)
    public static boolean validarTituloEducacionFinanciera(@NotNull String titulo) {
        return !titulo.isEmpty();
    }
    public static boolean validarContenidoPDF(@NotNull String contenido) {
        return !contenido.isEmpty() && contenido.endsWith(".pdf");
    }
    @Contract(pure = true)
    public static boolean validarNombreMenu(@NotNull String nombre) {
        return !nombre.isEmpty();
    }
    public static boolean validarOpcionSubMenu(int opcion) {
        return opcion >= 1 && opcion <= 5;
    }
    public static int validarOpcionMenu1(@NotNull Scanner scanner, int maxOpcion) {
        int opcion;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.print("Debe ingresar un número válido: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion < 1 || opcion > maxOpcion) {
                System.out.print("Opción fuera de rango, ingrese nuevamente: ");
            } else {
                break;
            }
        }
        return opcion;
    }
    public static double validarMonto(@NotNull Scanner scanner) {
        double monto;
        while (true) {
            while (!scanner.hasNextDouble()) {
                System.out.print("Debe ingresar un número válido para el monto: ");
                scanner.next();
            }
            monto = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer de entrada

            if (monto <= 0) {
                System.out.print("El monto debe ser mayor a cero. Ingrese nuevamente: ");
            } else {
                break;
            }
        }
        return monto;
    }
    public static Date validarFecha(@NotNull Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date fecha;
        while (true) {
            String fechaStr = scanner.nextLine();
            try {
                fecha = dateFormat.parse(fechaStr);
                break;
            } catch (java.text.ParseException e) {
                System.out.print("Formato de fecha inválido. Use el formato dia/mes/año. Ejemplo: 31/12/2023: ");
            }
        }
        return fecha;
    }
    public static boolean verificarContrasena(String contrasenaIngresada, String contrasenaHasheada) {
        String contrasenaHasheadaIngresada = hashearContrasena(contrasenaIngresada);
        return contrasenaHasheadaIngresada.equals(contrasenaHasheada);
    }
    public static String hashearContrasena(@NotNull String contrasena) {
        String hashedContrasena = null;
        try {
            KeySpec spec = new PBEKeySpec(contrasena.toCharArray(), "salt".getBytes(), 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            hashedContrasena = hexString.toString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("Error al hashear la contraseña: " + e.getMessage());
        }
        return hashedContrasena;
    }
    @Nullable
    public static Double validarPresupuesto(String presupuestoStr) {
        Double presupuesto = null;
        try {
            presupuesto = Double.parseDouble(presupuestoStr);
            if (presupuesto <= 0) {
                System.out.println("El presupuesto debe ser mayor que cero.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("El presupuesto debe ser un número y no puede estar vacío.");
        }
        return presupuesto;
    }
}
