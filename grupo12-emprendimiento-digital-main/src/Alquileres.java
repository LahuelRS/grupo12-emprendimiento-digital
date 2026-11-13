import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Alquileres {
    private Catalogo catalogo;
    private Map<String, Socio> socios;
    private Map<String, List<Integer>> alquileresPorSocio;

    public Alquileres(Catalogo catalogo) {
        this.catalogo = catalogo;
        this.socios = new HashMap<>();
        this.alquileresPorSocio = new HashMap<>();
    }

    // Registrar un socio (devuelve false si el DNI ya existe)
    public boolean agregarSocio(Socio socio) {
        if (socios.containsKey(socio.getDni())) {
            return false;
        }
        socios.put(socio.getDni(), socio);
        alquileresPorSocio.put(socio.getDni(), new ArrayList<>());
        return true;
    }

    // Buscar socio por DNI
    public Socio buscarSocio(String dni) {
        return socios.get(dni);
    }

    // Registrar alquiler validando reglas de negocio
    public String alquilar(String dni, int codigoPeli, int dia) {
        Socio socio = socios.get(dni);
        if (socio == null) {
            return "El socio no existe.";
        }
        if (socio.getDeuda() > 0) {
            return "El socio tiene deuda.";
        }
        
        Pelicula peli = catalogo.buscarPorCodigo(codigoPeli);
        if (peli == null) {
            return "La película no existe.";
        }
        if (peli.getCopiasDisponibles() <= 0) {
            return "No hay copias disponibles.";
        }

        List<Integer> pelisDelSocio = alquileresPorSocio.get(dni);
        if (pelisDelSocio.size() >= 3) {
            return "El socio ya tiene el máximo de películas.";
        }

        // Descontar copia del catálogo
        boolean retirada = peli.descontarCopia();
        if (!retirada) {
            return "No se pudo retirar la copia.";
        }

        pelisDelSocio.add(codigoPeli);
        return "OK";
    }

    // Registrar devolución y calcular recargo (1500 por día si pasa los 3 días de plazo)
    public int devolver(String dni, int codigoPeli, int diaDevolucion) {
        Socio socio = socios.get(dni);
        if (socio == null) return -1;

        List<Integer> pelisDelSocio = alquileresPorSocio.get(dni);
        if (pelisDelSocio == null || !pelisDelSocio.contains(codigoPeli)) {
            return -1; // El socio no tenía esa película alquilada
        }

        pelisDelSocio.remove(Integer.valueOf(codigoPeli));
        
        Pelicula peli = catalogo.buscarPorCodigo(codigoPeli);
        if (peli != null) {
            peli.devolverCopia();
        }

        int recargo = 0;
        int plazoMaximo = 3;
        if (diaDevolucion > plazoMaximo) {
            int diasDeAtraso = diaDevolucion - plazoMaximo;
            recargo = diasDeAtraso * 1500;
            socio.sumarDeuda(recargo);
        }

        return recargo;
    }

    // Pagar deuda del socio
    public boolean pagarDeuda(String dni) {
        Socio socio = socios.get(dni);
        if (socio == null) return false;
        socio.limpiarDeuda();
        return true;
    }

    // Consultar películas alquiladas por un socio
    public List<Integer> alquileresDe(String dni) {
        return alquileresPorSocio.getOrDefault(dni, new ArrayList<>());
    }
}