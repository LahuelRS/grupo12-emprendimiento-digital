import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Catalogo {

	private Map<Integer, Pelicula> peliculas;
	
	public Catalogo() {
		peliculas = new HashMap<>();
	}
	
	public boolean agregarPelicula(Pelicula pelicula) {
		
		int codigo = pelicula.getCodigo();
		
		if(peliculas.containsKey(codigo)) {
			return false;
		}else {
			peliculas.put(codigo, pelicula);
			return true;
		}
	}
	
	
	public Pelicula buscarPorCodigo(int codigo) {
		return peliculas.get(codigo);
	}
	
	
	public boolean retirarCopia(int codigo) {
		
		Pelicula peliculaEncontrada = buscarPorCodigo(codigo);
		
		if(peliculaEncontrada == null) {
			return false;
		}else {
			return peliculaEncontrada.descontarCopia();			
		}	
	}
	
	public boolean devolverCopia(int codigo) {

		Pelicula peliculaEncontrada = buscarPorCodigo(codigo);
		
		if(peliculaEncontrada == null) {
			return false;
		}else {
			return peliculaEncontrada.devolverCopia();
		}
	}
	
	public boolean eliminarPelicula(int codigo) {
		Pelicula peliculaEncontrada = buscarPorCodigo(codigo);
		
		if(peliculaEncontrada == null) {
			return false;
		}
		
		if(peliculaEncontrada.getCopiasDisponibles() < peliculaEncontrada.getCopiasTotales()) {
			return false;
		}
			
		peliculas.remove(codigo);
		return true;
		
		
	}
	
	public List<Pelicula> buscarPorTitulo(String titulo){
		List<Pelicula> resultados = new ArrayList<>();
		
		for (Pelicula pelicula : peliculas.values()) {

			if (pelicula.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {

				resultados.add(pelicula);

			}

		}
		
		return resultados;
	}
	
	
	public Map<Integer, Pelicula> getPeliculas() {
	    return Collections.unmodifiableMap(peliculas);
	}
	
}
