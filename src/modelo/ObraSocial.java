package modelo;

import java.io.Serializable;

public class ObraSocial implements Serializable {

    private static final long serialVersionUID = 1L;


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

        return "ObraSocial{" +
                "nombre='" + nombre + '\'' +
                ", plan='" + plan + '\'' +
                ", numeroAfiliado='" + numeroAfiliado + '\'' +
                '}';

    }

}