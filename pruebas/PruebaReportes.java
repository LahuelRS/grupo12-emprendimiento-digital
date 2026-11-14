public class PruebaReportes {

    public static void main(String[] args) {

        // --- Caso con el sistema vacio ---
        Catalogo vacio = new Catalogo();
        Alquileres sinMovimientos = new Alquileres(vacio);
        Reportes reportesVacios = new Reportes(vacio, sinMovimientos);

        System.out.println("=== 1. REPORTES CON EL SISTEMA VACIO ===");
        reportesVacios.catalogoAlfabetico();
        reportesVacios.masAlquiladas();
        reportesVacios.alquileresPorGenero();

        // --- Sistema cargado ---
        Catalogo catalogo = new Catalogo();
        Alquileres alquileres = new Alquileres(catalogo);
        Reportes reportes = new Reportes(catalogo, alquileres);

        // El orden de carga es a proposito distinto del alfabetico,
        // y hay dos peliculas con el mismo titulo para verificar que no se pisen.
        catalogo.agregarPelicula(new Pelicula(101, "Toy Story", "Infantil", 1995, 3));
        catalogo.agregarPelicula(new Pelicula(102, "alien", "Terror", 1979, 3));
        catalogo.agregarPelicula(new Pelicula(103, "King Kong", "Aventura", 1933, 3));
        catalogo.agregarPelicula(new Pelicula(104, "King Kong", "Aventura", 2005, 3));
        catalogo.agregarPelicula(new Pelicula(105, "Titanic", "Drama", 1997, 3));

        alquileres.agregarSocio(new Socio("30111222", "Ana Perez"));
        alquileres.agregarSocio(new Socio("27333444", "Beto Gomez"));
        alquileres.agregarSocio(new Socio("35666777", "Carla Diaz"));

        System.out.println("\n=== 2. CATALOGO EN ORDEN ALFABETICO ===");
        System.out.println("Se cargaron en este orden: Toy Story, alien, King Kong (1933), King Kong (2005), Titanic");
        reportes.catalogoAlfabetico();

        // Toy Story: 3 alquileres | King Kong (1933): 2 | alien: 1 | Titanic: 1
        alquileres.alquilar("30111222", 101, 1);
        alquileres.alquilar("27333444", 101, 1);
        alquileres.alquilar("35666777", 101, 1);
        alquileres.alquilar("30111222", 103, 1);
        alquileres.alquilar("27333444", 103, 1);
        alquileres.alquilar("30111222", 102, 1);
        alquileres.alquilar("27333444", 105, 1);

        System.out.println("\n=== 3. RANKING DE MAS ALQUILADAS ===");
        System.out.println("Esperado: Toy Story 3, King Kong 2, y alien y Titanic empatadas en 1");
        reportes.masAlquiladas();

        System.out.println("\n=== 4. ALQUILERES POR GENERO ===");
        System.out.println("Esperado: Aventura 2, Drama 1, Infantil 3, Terror 1");
        reportes.alquileresPorGenero();

        System.out.println("\n=== 5. UNA PELICULA DADA DE BAJA SIGUE EN EL HISTORIAL ===");
        alquileres.devolver("27333444", 105, 2);
        System.out.println("Titanic devuelta y eliminada del catalogo: " + catalogo.eliminarPelicula(105));
        reportes.masAlquiladas();

        System.out.println("\n=== 6. LOS TRES REPORTES JUNTOS ===");
        reportes.mostrarTodos();
    }
}
