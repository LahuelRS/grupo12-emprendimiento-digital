public class Socio {

    private String dni;
    private String nombre;
    private int deuda;

    public Socio(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        deuda = 0;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDeuda() {
        return deuda;
    }

    public void sumarDeuda(int monto) {
        if (monto > 0) {
            deuda = deuda + monto;
        }
    }

    public void pagarDeuda() {
        deuda = 0;
    }
}
