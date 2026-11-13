public class Socio {
    private String dni;
    private String nombre;
    private int deuda;

    // Constructor: Crea un socio sin deuda inicial (deuda = 0)
    public Socio(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.deuda = 0;
    }

    // Métodos Getters
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDeuda() {
        return deuda;
    }

    // Métodos Setter para modificar la deuda si hay recargos
    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }

    public void sumarDeuda(int monto) {
        this.deuda += monto;
    }

    public void limpiarDeuda() {
        this.deuda = 0;
    }

    @Override
    public String toString() {
        return "Socio [DNI=" + dni + ", Nombre=" + nombre + ", Deuda=$" + deuda + "]";
    }
}