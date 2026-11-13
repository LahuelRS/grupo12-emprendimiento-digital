import java.util.List;

public class PruebaCatalogo {

    public static void main(String[] args) {

        Catalogo catalogo = new Catalogo();

        Pelicula p1 = new Pelicula(101, "Matrix", "Ciencia ficcion", 1999, 3);
        Pelicula p2 = new Pelicula(102, "Titanic", "Drama", 1997, 2);

        // Registrar peliculas
        System.out.println("=== REGISTRO ===");
        System.out.println(catalogo.agregarPelicula(p1));
        System.out.println(catalogo.agregarPelicula(p2));
        System.out.println("Codigo repetido: " + catalogo.agregarPelicula(p1));

        // Buscar por codigo y titulo
        System.out.println("\n=== BUSQUEDAS ===");
        System.out.println(catalogo.buscarPorCodigo(101).getTitulo());

        List<Pelicula> resultados = catalogo.buscarPorTitulo("mat");

        for (Pelicula pelicula : resultados) {
            System.out.println(pelicula.getTitulo());
        }

        // Retirar y devolver copias
        System.out.println("\n=== COPIAS ===");
        System.out.println(catalogo.retirarCopia(101));
        System.out.println("Disponibles: " + p1.getCopiasDisponibles());

        // Intentar eliminar con una copia alquilada
        System.out.println("Eliminar alquilada: " + catalogo.eliminarPelicula(101));

        System.out.println(catalogo.devolverCopia(101));

        // Eliminar pelicula
        System.out.println("\n=== ELIMINACION ===");
        System.out.println(catalogo.eliminarPelicula(101));
        System.out.println("Peliculas restantes: " + catalogo.getPeliculas().size());
    }
}
