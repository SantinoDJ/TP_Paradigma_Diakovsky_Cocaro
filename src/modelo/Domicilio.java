package modelo;

import java.io.Serializable;

public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;


    private String calle;
    private int numero;
    private String localidad;
    private String provincia;



    public Domicilio(String calle, int numero, String localidad, String provincia) {

        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;

    }



    public String getCalle() {

        return calle;

    }


    public int getNumero() {

        return numero;

    }


    public String getLocalidad() {

        return localidad;

    }


    public String getProvincia() {

        return provincia;

    }




    public void setCalle(String calle) {

        this.calle = calle;

    }


    public void setNumero(int numero) {

        this.numero = numero;

    }


    public void setLocalidad(String localidad) {

        this.localidad = localidad;

    }


    public void setProvincia(String provincia) {

        this.provincia = provincia;

    }




    @Override
    public String toString() {

        return calle + " " + numero +
                ", " + localidad +
                " (" + provincia + ")";

    }

}