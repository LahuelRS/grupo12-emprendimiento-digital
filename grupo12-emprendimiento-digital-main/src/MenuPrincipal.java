import java.util.Scanner;
import java.util.TreeMap;
public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Instanciamos los objetos troncales UNA SOLA VEZ al inicio (como pidió Lahuel)
        Catalogo catalogo = new Catalogo();
        Alquileres alquileres = new Alquileres(catalogo);
        
        int opcion = 0;

        do {
            System.out.println("\n--- VIDEOCLUB: MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar película");
            System.out.println("2. Buscar película por código");
            System.out.println("3. Buscar película por título");
            System.out.println("4. Eliminar película");
            System.out.println("5. Registrar socio");
            System.out.println("6. Buscar socio");
            System.out.println("7. Alquilar película");
            System.out.println("8. Devolver película");
            System.out.println("9. Pagar deuda");
            System.out.println("10. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                case 1: // Agregar película
                    System.out.print("Ingrese el código de la película: ");
                    int codPeli = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    
                    System.out.print("Ingrese el título: ");
                    String tituloPeli = scanner.nextLine();
                    
                    System.out.print("Ingrese el género: ");
                    String generoPeli = scanner.nextLine();
                    
                    System.out.print("Ingrese el año: ");
                    int anioPeli = scanner.nextInt();
                    
                    System.out.print("Ingrese las copias totales: ");
                    int copiasPeli = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    Pelicula nuevaPeli = new Pelicula(codPeli, tituloPeli, generoPeli, anioPeli, copiasPeli);
                    
                    boolean agregado = catalogo.agregarPelicula(nuevaPeli);
                    
                    if (agregado) {
                        System.out.println("\n----------------------------------------");
                        System.out.println("¡ÉXITO: Película agregada correctamente!");
                        System.out.println("----------------------------------------\n");
                    } else {
                        System.out.println("\n[ERROR]: Ya existe una película con ese código en el catálogo.\n");
                    }
                    break;

                    case 2: // Buscar por código
                        System.out.print("Ingrese el código a buscar: ");
                        int busqCod = scanner.nextInt();
                        scanner.hasNextLine();
                        Pelicula peliEncontrada = catalogo.buscarPorCodigo(busqCod);
                        if (peliEncontrada != null) {
                            System.out.println("Encontrada: " + peliEncontrada.toString());
                        } else {
                            System.out.println("No se encontró ninguna película con ese código.");
                        }
                        break;

                    case 3: // Buscar por título (o listar usando TreeMap por el aviso de solo lectura)
                        System.out.println("--- LISTADO DE PELÍCULAS (Catálogo Alfabético) ---");
                        // Recordá el aviso de Lahuel: getPeliculas() es de solo lectura, lo copiamos a un TreeMap
                        TreeMap<Integer, Pelicula> catalogoOrdenado = new TreeMap<Integer, Pelicula>();
                        for (Pelicula p : catalogoOrdenado.values()) {
                            System.out.println(p.toString());
                        }
                        break;

                    case 4: // Eliminar película
                        System.out.print("Ingrese el código de la película a eliminar: ");
                        int elimCod = scanner.nextInt();
                        boolean eliminado = catalogo.eliminarPelicula(elimCod);
                        if (eliminado) {
                            System.out.println("Película eliminada correctamente.");
                        } else {
                            System.out.println("No se pudo eliminar (código inexistente).");
                        }
                        break;

                    case 5: // Registrar socio
                        System.out.print("Ingrese el DNI del socio: ");
                        String dniReg = scanner.nextLine();
                        System.out.print("Ingrese el nombre del socio: ");
                        String nombreReg = scanner.nextLine();
                        
                        Socio nuevoSocio = new Socio(dniReg, nombreReg);
                        boolean socioAgregado = alquileres.agregarSocio(nuevoSocio);
                        if (socioAgregado) {
                            System.out.println("\n[ÉXITO]: Socio registrado correctamente.\n");
                        } else {
                            System.out.println("\n[ERROR]: Ya existe un socio con ese DNI.\n");
                        }
                        break;

                    case 6: // Buscar socio
                        System.out.print("Ingrese el DNI a buscar: ");
                        String dniBusq = scanner.nextLine();
                        Socio socioEncontrado = alquileres.buscarSocio(dniBusq);
                        if (socioEncontrado != null) {
                            System.out.println("\nEncontrado: " + socioEncontrado.toString() + "\n");
                        } else {
                            System.out.println("\n[INFO]: No se encontró ningún socio con ese DNI.\n");
                        }
                        break;

                    case 7: // Alquilar película
                        System.out.print("Ingrese el DNI del socio: ");
                        String dniAlq = scanner.nextLine();
                        System.out.print("Ingrese el código de la película: ");
                        int codAlq = scanner.nextInt();
                        System.out.print("Ingrese el número de día actual: ");
                        int diaAlq = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        String resultadoAlquiler = alquileres.alquilar(dniAlq, codAlq, diaAlq);
                        if (resultadoAlquiler.equals("OK")) {
                            System.out.println("\n[ÉXITO]: ¡Película alquilada con éxito!\n");
                        } else {
                            System.out.println("\n[RECHAZADO]: " + resultadoAlquiler + "\n");
                        }
                        break;

                    case 8: // Devolver película
                        System.out.print("Ingrese el DNI del socio: ");
                        String dniDev = scanner.nextLine();
                        System.out.print("Ingrese el código de la película: ");
                        int codDev = scanner.nextInt();
                        System.out.print("Ingrese el número de día de devolución: ");
                        int diaDev = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        int recargo = alquileres.devolver(dniDev, codDev, diaDev);
                        if (recargo == -1) {
                            System.out.println("\n[ERROR]: No se pudo procesar la devolución (verifique DNI o que el socio tuviera la película).\n");
                        } else if (recargo > 0) {
                            System.out.println("\n[AVISO]: Película devuelta con atraso. Recargo aplicado a la cuenta: $" + recargo + "\n");
                        } else {
                            System.out.println("\n[ÉXITO]: Película devuelta a tiempo y sin recargos.\n");
                        }
                        break;

                    case 9: // Pagar deuda
                        System.out.print("Ingrese el DNI del socio para saldar deuda: ");
                        String dniPago = scanner.nextLine();
                        boolean pagado = alquileres.pagarDeuda(dniPago);
                        if (pagado) {
                            System.out.println("\n[ÉXITO]: Deuda saldada correctamente. El socio ya puede volver a alquilar.\n");
                        } else {
                            System.out.println("\n[ERROR]: No se encontró el socio especificado.\n");
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un valor válido. (" + e.getMessage() + ")");
                scanner.nextLine(); // Limpiar error de lectura
                opcion = 0;
            }

        } while (opcion != 10);
        
        scanner.close();
    }
}