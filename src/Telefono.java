public class Telefono {
    private String numero;
    private String tipo;

    public Telefono(String numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo + ": " + numero;
    }

}
