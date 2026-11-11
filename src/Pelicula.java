
public class Pelicula {

	private int codigo;
	private String titulo;
	private String genero;
	private int anio;
	private int copiasTotales;
	private int copiasDisponibles;
	
	public Pelicula(int codigo, String titulo, String genero, int anio, int copiasTotales) {
		this.codigo=codigo;
		this.titulo=titulo;
		this.genero=genero;
		this.anio=anio;
		this.copiasTotales=copiasTotales;
		copiasDisponibles = copiasTotales;
	}

	public int getCodigo() {
		return codigo; 
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public int getCopiasDisponibles() {
		return copiasDisponibles;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public boolean descontarCopia() {
		
		if(copiasDisponibles > 0) {
			copiasDisponibles--;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean devolverCopia() {
		
		if(copiasDisponibles < copiasTotales) {
			copiasDisponibles++;
			return true;
		}else {
			return false;
		}
		
	}
