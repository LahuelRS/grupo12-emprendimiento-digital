import java.util.List;

public class PruebaAlquileres {

    public static void main(String[] args) {

        Catalogo catalogo = new Catalogo();
        Alquileres alquileres = new Alquileres(catalogo);

        // Peliculas de prueba: Matrix tiene una sola copia
        catalogo.agregarPelicula(new Pelicula(101, "Matrix", "Ciencia ficcion", 1999, 1));
        catalogo.agregarPelicula(new Pelicula(102, "Titanic", "Drama", 1997, 2));
        catalogo.agregarPelicula(new Pelicula(103, "Toy Story", "Infantil", 1995, 2));
        catalogo.agregarPelicula(new Pelicula(104, "Alien", "Terror", 1979, 2));

        Socio ana = new Socio("30111222", "Ana Perez");
        Socio beto = new Socio("27333444", "Beto Gomez");

        System.out.println("=== REGISTRO DE SOCIOS ===");
        System.out.println("1. Alta de Ana: " + alquileres.agregarSocio(ana));
        System.out.println("2. Alta de Beto: " + alquileres.agregarSocio(beto));
        System.out.println("3. Alta con DNI repetido: " + alquileres.agregarSocio(new Socio("30111222", "Otro")));
        System.out.println("4. Buscar un DNI inexistente: " + alquileres.buscarSocio("11111111"));

        System.out.println("\n=== ALQUILERES RECHAZADOS ===");
        System.out.println("5. Socio no registrado: " + alquileres.alquilar("11111111", 101, 1));
        System.out.println("6. Pelicula inexistente: " + alquileres.alquilar("30111222", 999, 1));

        System.out.println("\n=== ALQUILER VALIDO ===");
        System.out.println("7. Ana alquila Matrix el dia 1: " + alquileres.alquilar("30111222", 101, 1));
        System.out.println("   Copias disponibles de Matrix: " + catalogo.buscarPorCodigo(101).getCopiasDisponibles());
        System.out.println("8. Ana pide Matrix otra vez: " + alquileres.alquilar("30111222", 101, 1));
        System.out.println("9. Beto pide la unica copia de Matrix: " + alquileres.alquilar("27333444", 101, 1));

        System.out.println("\n=== LIMITE POR SOCIO ===");
        System.out.println("10. Ana alquila Titanic: " + alquileres.alquilar("30111222", 102, 1));
        System.out.println("11. Ana alquila Toy Story: " + alquileres.alquilar("30111222", 103, 1));
        System.out.println("12. Ana intenta una cuarta pelicula: " + alquileres.alquilar("30111222", 104, 1));
        System.out.println("    Peliculas en poder de Ana: " + alquileres.alquileresDe("30111222"));

        System.out.println("\n=== DEVOLUCIONES ===");
        System.out.println("13. Ana devuelve Matrix el dia 4 (en plazo): " + alquileres.devolver("30111222", 101, 4));
        System.out.println("    Copias disponibles de Matrix: " + catalogo.buscarPorCodigo(101).getCopiasDisponibles());
        System.out.println("14. Ana devuelve Matrix otra vez: " + alquileres.devolver("30111222", 101, 5));
        System.out.println("15. Beto devuelve algo que no tiene: " + alquileres.devolver("27333444", 102, 5));

        System.out.println("\n=== RECARGO POR ATRASO ===");
        alquileres.alquilar("27333444", 104, 10);
        System.out.println("16. Beto alquila Alien el dia 10 y la devuelve el dia 16: " + alquileres.devolver("27333444", 104, 16));
        System.out.println("    Deuda de Beto: " + beto.getDeuda());
        System.out.println("17. Beto intenta alquilar con deuda: " + alquileres.alquilar("27333444", 102, 17));

        System.out.println("\n=== PAGO DE DEUDA ===");
        System.out.println("18. Beto paga su deuda: " + alquileres.pagarDeuda("27333444"));
        System.out.println("    Deuda de Beto: " + beto.getDeuda());
        System.out.println("19. Pagar la deuda de un DNI inexistente: " + alquileres.pagarDeuda("11111111"));
        System.out.println("20. Beto vuelve a alquilar: " + alquileres.alquilar("27333444", 102, 17));

        System.out.println("\n=== CONSULTAS ===");
        List<Integer> deBeto = alquileres.alquileresDe("27333444");
        System.out.println("21. Peliculas de Beto: " + deBeto);
        System.out.println("22. Peliculas de un socio inexistente: " + alquileres.alquileresDe("11111111"));
        deBeto.clear();
        System.out.println("23. La lista devuelta es una copia: " + alquileres.alquileresDe("27333444"));
        System.out.println("24. Veces que se alquilo cada pelicula: " + alquileres.getVecesAlquilada());
    }
}
