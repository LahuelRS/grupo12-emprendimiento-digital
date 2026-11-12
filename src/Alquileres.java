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

    // Pendiente: a implementar en el día 5
    public String alquilar(String dni, int codigo, int dia) {
        return "Pendiente de implementacion";
    }

    // Pendiente: a implementar en el día 5
    public int devolver(String dni, int codigo, int dia) {
        return -1;
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
