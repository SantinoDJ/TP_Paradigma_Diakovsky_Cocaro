public class ObraSocial {
    private String nombre;
    private String plan;
    private String numeroAfiliado;


    public ObraSocial(String nombre, String plan, String numeroAfiliado) {
        this.nombre = nombre;
        this.plan = plan;
        this.numeroAfiliado = numeroAfiliado;
    }

    @Override
    public String toString() {
        return "ObraSocial{" + "nombre='" + nombre + '\'' + ", plan='" + plan + '\'' + ", numeroAfiliado='" + numeroAfiliado + '\'' + '}';
    }

}
