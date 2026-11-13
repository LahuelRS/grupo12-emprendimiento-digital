import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alquileres {

    private static final int PLAZO_DIAS = 3;
    private static final int RECARGO_POR_DIA = 1500;
    private static final int MAXIMO_POR_SOCIO = 3;

    private Catalogo catalogo;
    private Map<String, Socio> socios;                        // DNI -> socio
    private Map<String, Map<Integer, Integer>> alquilados;    // DNI -> (código -> día de retiro)
    private Map<Integer, Integer> vecesAlquilada;             // código -> cantidad

    public Alquileres(Catalogo catalogo) {
        this.catalogo = catalogo;
        socios = new HashMap<>();
        alquilados = new HashMap<>();
        vecesAlquilada = new HashMap<>();
    }

    public boolean agregarSocio(Socio socio) {

        String dni = socio.getDni();

        if (socios.containsKey(dni)) {
            return false;
        } else {
            socios.put(dni, socio);
            alquilados.put(dni, new HashMap<Integer, Integer>());
            return true;
        }
    }

    public Socio buscarSocio(String dni) {
        return socios.get(dni);
    }

    public String alquilar(String dni, int codigo, int dia) {

        Socio socio = buscarSocio(dni);

        if (socio == null) {
            return "El socio no esta registrado";
        }

        if (socio.getDeuda() > 0) {
            return "El socio tiene una deuda de " + socio.getDeuda() + " pesos";
        }

        Map<Integer, Integer> susPeliculas = alquilados.get(dni);

        if (susPeliculas.size() >= MAXIMO_POR_SOCIO) {
            return "El socio ya tiene " + MAXIMO_POR_SOCIO + " peliculas alquiladas";
        }

        if (susPeliculas.containsKey(codigo)) {
            return "El socio ya tiene alquilada esa pelicula";
        }

        Pelicula pelicula = catalogo.buscarPorCodigo(codigo);

        if (pelicula == null) {
            return "La pelicula no existe en el catalogo";
        }

        if (catalogo.retirarCopia(codigo) == false) {
            return "No hay copias disponibles de " + pelicula.getTitulo();
        }

        susPeliculas.put(codigo, dia);
        vecesAlquilada.put(codigo, vecesAlquilada.getOrDefault(codigo, 0) + 1);

        return "OK";
    }

    public int devolver(String dni, int codigo, int dia) {

        Socio socio = buscarSocio(dni);

        if (socio == null) {
            return -1;
        }

        Map<Integer, Integer> susPeliculas = alquilados.get(dni);

        if (susPeliculas.containsKey(codigo) == false) {
            return -1;
        }

        int diaDeRetiro = susPeliculas.get(codigo);
        int diasDeAtraso = dia - diaDeRetiro - PLAZO_DIAS;
        int recargo = 0;

        if (diasDeAtraso > 0) {
            recargo = diasDeAtraso * RECARGO_POR_DIA;
            socio.sumarDeuda(recargo);
        }

        susPeliculas.remove(codigo);
        catalogo.devolverCopia(codigo);

        return recargo;
    }

    // Pendiente: a implementar en el día 5
    public boolean pagarDeuda(String dni) {
        return false;
    }

    // Pendiente: a implementar en el día 5
    public List<Integer> alquileresDe(String dni) {
        return new ArrayList<Integer>();
    }

    public Map<Integer, Integer> getVecesAlquilada() {
        return vecesAlquilada;
    }
}
