import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Estructuras y datos de ejemplo simulando la integración de los módulos
        // Catálogo principal (Código -> Película)
        Map<Integer, String> catalogo = new TreeMap<>();
        // Estructuras de reportes (Tema: Mapas - TreeMap y entrySet)
        TreeMap<String, String> catalogoAlfabetico = new TreeMap<>();
        TreeMap<String, Integer> alquileresPorGenero = new TreeMap<>();

        // Carga de datos de prueba iniciales
        catalogo.put(1, "Matrix (Ciencia Ficción)");
        catalogo.put(2, "Toy Story (Infantil)");
        catalogo.put(3, "El Padrino (Drama)");

        catalogoAlfabetico.put("El Padrino", "El Padrino (Drama)");
        catalogoAlfabetico.put("Matrix", "Matrix (Ciencia Ficción)");
        catalogoAlfabetico.put("Toy Story", "Toy Story (Infantil)");

        alquileresPorGenero.put("Ciencia Ficción", 12);
        alquileresPorGenero.put("Drama", 8);
        alquileresPorGenero.put("Infantil", 15);

        do {
            System.out.println("\n=== VIDEOCLUB BLOCKBUSTER - MENÚ PRINCIPAL ===");
            System.out.println("1. Agregar película");
            System.out.println("2. Buscar película");
            System.out.println("3. Eliminar película");
            System.out.println("4. Listar catálogo");
            System.out.println("5. Registrar socio");
            System.out.println("6. Buscar socio");
            System.out.println("7. Alquilar película");
            System.out.println("8. Devolver película (Recargo: $1500/día)");
            System.out.println("9. Reportes (Alfabético y por Género)");
            System.out.println("10. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        System.out.println("--> Módulo Catálogo: Agregando película...");
                        break;
                    case 2:
                        System.out.println("--> Módulo Catálogo: Buscando película...");
                        break;
                    case 3:
                        System.out.println("--> Módulo Catálogo: Eliminando película...");
                        break;
                    case 4:
                        System.out.println("--> Listado general del catálogo:");
                        for (Map.Entry<Integer, String> entry : catalogo.entrySet()) {
                            System.out.println("Código: " + entry.getKey() + " | Título: " + entry.getValue());
                        }
                        break;
                    case 5:
                        System.out.println("--> Módulo Socios: Registrando nuevo socio...");
                        break;
                    case 6:
                        System.out.println("--> Módulo Socios: Buscando socio...");
                        break;
                    case 7:
                        System.out.println("--> Módulo Alquileres: Realizando préstamo...");
                        break;
                    case 8:
                        System.out.println("--> Módulo Alquileres: Procesando devolución (Calculando multas de $1500 si hay atraso)...");
                        break;
                    case 9:
                        System.out.println("\n--- REPORTES ORDENADOS (Usando TreeMap y entrySet) ---");
                        System.out.println("Catalogo ordenado alfabéticamente por título:");
                        for (Map.Entry<String, String> entry : catalogoAlfabetico.entrySet()) {
                            System.out.println("- " + entry.getKey() + " -> " + entry.getValue());
                        }
                        
                        System.out.println("\nAlquileres acumulados por género:");
                        for (Map.Entry<String, Integer> entry : alquileresPorGenero.entrySet()) {
                            System.out.println("Género: " + entry.getKey() + " | Alquileres: " + entry.getValue());
                        }
                        break;
                    case 10:
                        System.out.println("Saliendo del sistema... ¡Gracias por usar el programa!");
                        break;
                    default:
                        System.out.println("[Error] Opción inválida. Ingrese un número entre 1 y 10.");
                }

            } catch (InputMismatchException e) {
                System.out.println("[Error] Debe ingresar un número entero válido. Intente de nuevo.");
                scanner.nextLine(); // Limpiar la entrada incorrecta del scanner
                opcion = 0; // Para evitar que salga del ciclo
            }

        } while (opcion != 10);

        scanner.close();
    }
}
