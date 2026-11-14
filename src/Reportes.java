import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Reportes {

    private Catalogo catalogo;
    private Alquileres alquileres;

    public Reportes(Catalogo catalogo, Alquileres alquileres) {
        this.catalogo = catalogo;
        this.alquileres = alquileres;
    }

    // Reporte 1: todo el catalogo ordenado por titulo.
    // El TreeMap ordena solo por su llave, asi que no hay que ordenar a mano.
    public void catalogoAlfabetico() {

        System.out.println("\n--- CATALOGO EN ORDEN ALFABETICO ---");

        if (catalogo.getPeliculas().isEmpty()) {
            System.out.println("El catalogo esta vacio.");
            return;
        }

        TreeMap<String, Pelicula> porTitulo = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        for (Pelicula pelicula : catalogo.getPeliculas().values()) {
            // Se agrega el codigo a la llave para que dos peliculas con el mismo titulo
            // no se pisen entre si, porque en un mapa la llave es unica.
            porTitulo.put(pelicula.getTitulo() + " #" + pelicula.getCodigo(), pelicula);
        }

        for (Map.Entry<String, Pelicula> entrada : porTitulo.entrySet()) {
            Pelicula pelicula = entrada.getValue();
            System.out.println(pelicula.getTitulo()
                    + " (" + pelicula.getGenero() + ")"
                    + " | codigo: " + pelicula.getCodigo()
                    + " | disponibles: " + pelicula.getCopiasDisponibles()
                    + " de " + pelicula.getCopiasTotales());
        }
    }

    // Reporte 2: ranking de las peliculas mas alquiladas.
    // La llave del TreeMap es la cantidad y el valor es la lista de titulos con esa cantidad,
    // de modo que los empates no se pierden. Collections.reverseOrder() lo ordena de mayor a menor.
    public void masAlquiladas() {

        System.out.println("\n--- PELICULAS MAS ALQUILADAS ---");

        Map<Integer, Integer> veces = alquileres.getVecesAlquilada();

        if (veces.isEmpty()) {
            System.out.println("Todavia no se registraron alquileres.");
            return;
        }

        TreeMap<Integer, List<String>> ranking = new TreeMap<>(Collections.reverseOrder());

        for (Map.Entry<Integer, Integer> entrada : veces.entrySet()) {
            int codigo = entrada.getKey();
            int cantidad = entrada.getValue();

            Pelicula pelicula = catalogo.buscarPorCodigo(codigo);
            String titulo;

            if (pelicula == null) {
                titulo = "Pelicula dada de baja (codigo " + codigo + ")";
            } else {
                titulo = pelicula.getTitulo();
            }

            if (ranking.containsKey(cantidad) == false) {
                ranking.put(cantidad, new ArrayList<String>());
            }

            ranking.get(cantidad).add(titulo);
        }

        int puesto = 1;

        for (Map.Entry<Integer, List<String>> entrada : ranking.entrySet()) {
            for (String titulo : entrada.getValue()) {
                System.out.println(puesto + ") " + titulo + ": " + entrada.getKey() + " alquiler(es)");
                puesto++;
            }
        }
    }

    // Reporte 3: cantidad de alquileres acumulados por genero, en orden alfabetico.
    public void alquileresPorGenero() {

        System.out.println("\n--- ALQUILERES POR GENERO ---");

        Map<Integer, Integer> veces = alquileres.getVecesAlquilada();

        if (veces.isEmpty()) {
            System.out.println("Todavia no se registraron alquileres.");
            return;
        }

        TreeMap<String, Integer> porGenero = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        for (Map.Entry<Integer, Integer> entrada : veces.entrySet()) {

            Pelicula pelicula = catalogo.buscarPorCodigo(entrada.getKey());

            if (pelicula != null) {
                String genero = pelicula.getGenero();
                int acumulado = porGenero.getOrDefault(genero, 0);
                porGenero.put(genero, acumulado + entrada.getValue());
            }
        }

        if (porGenero.isEmpty()) {
            System.out.println("No hay generos para informar.");
            return;
        }

        for (Map.Entry<String, Integer> entrada : porGenero.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue() + " alquiler(es)");
        }
    }

    // Muestra los tres reportes, uno abajo del otro.
    public void mostrarTodos() {
        catalogoAlfabetico();
        masAlquiladas();
        alquileresPorGenero();
    }
}
