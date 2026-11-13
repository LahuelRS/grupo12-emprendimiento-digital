import java.util.List;
public class PruebaCatalogo {

	public void main(String[] args) {
		
		 // 1. Crear el catálogo
        Catalogo catalogo = new Catalogo();

        // 2. Crear películas
        Pelicula p1 = new Pelicula(101, "Matrix", "Ciencia ficcion", 1999, 3);
        Pelicula p2 = new Pelicula(102, "Titanic", "Drama", 1997, 2);
        Pelicula p3 = new Pelicula(103, "Matrix Reloaded", "Accion", 2003, 1);

        // 3. Agregar películas
        System.out.println("=== REGISTRAR PELICULAS ===");
        System.out.println(catalogo.agregarPelicula(p1));
        System.out.println(catalogo.agregarPelicula(p2));
        System.out.println(catalogo.agregarPelicula(p3));

        // Intentar registrar un código repetido
        System.out.println("Codigo repetido: " + catalogo.agregarPelicula(p1));

        // 4. Buscar por código
        System.out.println("\n=== BUSCAR POR CODIGO ===");

        Pelicula encontrada = catalogo.buscarPorCodigo(101);

        if (encontrada != null) {
            System.out.println(encontrada.getTitulo());
        }

        System.out.println("Codigo inexistente: "
                + catalogo.buscarPorCodigo(999));

        // 5. Buscar por título
        System.out.println("\n=== BUSCAR POR TITULO ===");

        List<Pelicula> resultados = catalogo.buscarPorTitulo("matrix");

        for (Pelicula pelicula : resultados) {
            System.out.println(pelicula.getTitulo());
        }

        // 6. Alquilar y devolver copias
        System.out.println("\n=== CONTROL DE COPIAS ===");

        System.out.println("Copias iniciales: " + p1.getCopiasDisponibles());

        System.out.println("Retirar copia: " + catalogo.retirarCopia(101));
        System.out.println("Copias disponibles: " + p1.getCopiasDisponibles());

        // Intentar eliminar una película alquilada
        System.out.println("Eliminar con alquiler activo: "
                + catalogo.eliminarPelicula(101));

        System.out.println("Devolver copia: " + catalogo.devolverCopia(101));
        System.out.println("Copias disponibles: " + p1.getCopiasDisponibles());

        // 7. Eliminar películas
        System.out.println("\n=== ELIMINAR PELICULAS ===");

        System.out.println("Eliminar Matrix: " + catalogo.eliminarPelicula(101));
        System.out.println("Eliminar nuevamente: " + catalogo.eliminarPelicula(101));

        // 8. Consultar el mapa
        System.out.println("\n=== CATALOGO FINAL ===");

        for (Pelicula pelicula : catalogo.getPeliculas().values()) {
            System.out.println(pelicula.getCodigo() + " - " + pelicula.getTitulo());
        }
	}
	
}
