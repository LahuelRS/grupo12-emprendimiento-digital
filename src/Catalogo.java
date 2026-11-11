import java.util.HashMap;
import java.util.Map;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
