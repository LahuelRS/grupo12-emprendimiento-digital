import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner teclado = new Scanner(System.in);
    private static Catalogo catalogo = new Catalogo();
    private static Alquileres alquileres = new Alquileres(catalogo);
    private static Reportes reportes = new Reportes(catalogo, alquileres);

    public static void main(String[] args) {

        cargarDatosDePrueba();

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Elija una opcion: ");

            switch (opcion) {
                case 1:
                    agregarPelicula();
                    break;
                case 2:
                    buscarPelicula();
                    break;
                case 3:
                    eliminarPelicula();
                    break;
                case 4:
                    listarCatalogo();
                    break;
                case 5:
                    registrarSocio();
                    break;
                case 6:
                    buscarSocio();
                    break;
                case 7:
                    alquilarPelicula();
                    break;
                case 8:
                    devolverPelicula();
                    break;
                case 9:
                    pagarDeuda();
                    break;
                case 10:
                    reportes.mostrarTodos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema... Gracias por usar el programa!");
                    break;
                default:
                    System.out.println("[Error] Opcion invalida. Ingrese un numero entre 0 y 10.");
            }

        } while (opcion != 0);

        teclado.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== VIDEOTECA - MENU PRINCIPAL ===");
        System.out.println("1. Agregar pelicula");
        System.out.println("2. Buscar pelicula");
        System.out.println("3. Eliminar pelicula");
        System.out.println("4. Listar catalogo");
        System.out.println("5. Registrar socio");
        System.out.println("6. Buscar socio");
        System.out.println("7. Alquilar pelicula");
        System.out.println("8. Devolver pelicula");
        System.out.println("9. Pagar deuda");
        System.out.println("10. Reportes");
        System.out.println("0. Salir");
    }

    // ----- Catalogo -----

    private static void agregarPelicula() {
        int codigo = leerEntero("Codigo: ");
        String titulo = leerTexto("Titulo: ");
        String genero = leerTexto("Genero: ");
        int anio = leerEntero("Anio: ");
        int copias = leerEntero("Cantidad de copias: ");

        if (copias <= 0) {
            System.out.println("[Error] La cantidad de copias debe ser mayor a cero.");
            return;
        }

        if (catalogo.agregarPelicula(new Pelicula(codigo, titulo, genero, anio, copias))) {
            System.out.println("Pelicula agregada correctamente.");
        } else {
            System.out.println("[Error] Ya existe una pelicula con el codigo " + codigo + ".");
        }
    }

    private static void buscarPelicula() {
        String texto = leerTexto("Ingrese un codigo o parte del titulo: ");

        Pelicula porCodigo = null;

        try {
            porCodigo = catalogo.buscarPorCodigo(Integer.parseInt(texto));
        } catch (NumberFormatException e) {
            // No era un numero, se busca por titulo mas abajo.
        }

        if (porCodigo != null) {
            mostrarPelicula(porCodigo);
            return;
        }

        List<Pelicula> encontradas = catalogo.buscarPorTitulo(texto);

        if (encontradas.isEmpty()) {
            System.out.println("No se encontraron peliculas.");
        } else {
            for (Pelicula pelicula : encontradas) {
                mostrarPelicula(pelicula);
            }
        }
    }

    private static void eliminarPelicula() {
        int codigo = leerEntero("Codigo de la pelicula a eliminar: ");

        if (catalogo.eliminarPelicula(codigo)) {
            System.out.println("Pelicula eliminada.");
        } else {
            System.out.println("[Error] No existe esa pelicula o tiene copias alquiladas.");
        }
    }

    private static void listarCatalogo() {
        System.out.println("\n--- CATALOGO ---");

        if (catalogo.getPeliculas().isEmpty()) {
            System.out.println("El catalogo esta vacio.");
            return;
        }

        for (Map.Entry<Integer, Pelicula> entrada : catalogo.getPeliculas().entrySet()) {
            System.out.print("Codigo: " + entrada.getKey() + " | ");
            mostrarPelicula(entrada.getValue());
        }
    }

    private static void mostrarPelicula(Pelicula pelicula) {
        System.out.println(pelicula.getTitulo()
                + " (" + pelicula.getGenero() + ")"
                + " | codigo: " + pelicula.getCodigo()
                + " | disponibles: " + pelicula.getCopiasDisponibles()
                + " de " + pelicula.getCopiasTotales());
    }

    // ----- Socios -----

    private static void registrarSocio() {
        String dni = leerTexto("DNI: ");
        String nombre = leerTexto("Nombre y apellido: ");

        if (alquileres.agregarSocio(new Socio(dni, nombre))) {
            System.out.println("Socio registrado correctamente.");
        } else {
            System.out.println("[Error] Ya existe un socio con el DNI " + dni + ".");
        }
    }

    private static void buscarSocio() {
        String dni = leerTexto("DNI: ");
        Socio socio = alquileres.buscarSocio(dni);

        if (socio == null) {
            System.out.println("[Error] No existe un socio con ese DNI.");
            return;
        }

        System.out.println("Socio: " + socio.getNombre() + " | DNI: " + socio.getDni()
                + " | deuda: $" + socio.getDeuda());

        List<Integer> suyas = alquileres.alquileresDe(dni);

        if (suyas.isEmpty()) {
            System.out.println("No tiene peliculas alquiladas.");
            return;
        }

        System.out.println("Peliculas en su poder:");

        for (Integer codigo : suyas) {
            Pelicula pelicula = catalogo.buscarPorCodigo(codigo);

            if (pelicula != null) {
                System.out.println("  - " + pelicula.getTitulo() + " (codigo " + codigo + ")");
            }
        }
    }

    // ----- Alquileres -----

    private static void alquilarPelicula() {
        String dni = leerTexto("DNI del socio: ");
        int codigo = leerEntero("Codigo de la pelicula: ");
        int dia = leerEntero("Dia de la operacion: ");

        String resultado = alquileres.alquilar(dni, codigo, dia);

        if (resultado.equals("OK")) {
            System.out.println("Alquiler registrado. Vence el dia " + (dia + 3) + ".");
        } else {
            System.out.println("[Rechazado] " + resultado);
        }
    }

    private static void devolverPelicula() {
        String dni = leerTexto("DNI del socio: ");
        int codigo = leerEntero("Codigo de la pelicula: ");
        int dia = leerEntero("Dia de la operacion: ");

        int recargo = alquileres.devolver(dni, codigo, dia);

        if (recargo == -1) {
            System.out.println("[Error] Ese socio no tiene alquilada esa pelicula.");
        } else if (recargo == 0) {
            System.out.println("Devolucion registrada dentro del plazo. Sin recargo.");
        } else {
            System.out.println("Devolucion registrada con atraso. Recargo: $" + recargo + ".");
            System.out.println("Deuda total del socio: $" + alquileres.buscarSocio(dni).getDeuda() + ".");
        }
    }

    private static void pagarDeuda() {
        String dni = leerTexto("DNI del socio: ");
        Socio socio = alquileres.buscarSocio(dni);

        if (socio == null) {
            System.out.println("[Error] No existe un socio con ese DNI.");
            return;
        }

        if (socio.getDeuda() == 0) {
            System.out.println("El socio no tiene deuda.");
            return;
        }

        System.out.println("Deuda de $" + socio.getDeuda() + " abonada.");
        alquileres.pagarDeuda(dni);
    }

    // ----- Lectura por teclado -----
    // Se lee siempre la linea completa y se convierte a numero, para evitar que
    // un nextInt() deje el Enter en el buffer y la lectura siguiente se saltee.

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("[Error] Debe ingresar un numero entero. Intente de nuevo.");
            }
        }
    }

    // ----- Datos de prueba -----

    private static void cargarDatosDePrueba() {
        catalogo.agregarPelicula(new Pelicula(101, "Matrix", "Ciencia ficcion", 1999, 2));
        catalogo.agregarPelicula(new Pelicula(102, "Titanic", "Drama", 1997, 3));
        catalogo.agregarPelicula(new Pelicula(103, "Toy Story", "Infantil", 1995, 2));
        catalogo.agregarPelicula(new Pelicula(104, "Alien", "Terror", 1979, 1));
        catalogo.agregarPelicula(new Pelicula(105, "El Padrino", "Drama", 1972, 2));

        alquileres.agregarSocio(new Socio("30111222", "Ana Perez"));
        alquileres.agregarSocio(new Socio("27333444", "Beto Gomez"));
        alquileres.agregarSocio(new Socio("35666777", "Carla Diaz"));

        System.out.println("Datos de prueba cargados: 5 peliculas y 3 socios.");
    }
}
