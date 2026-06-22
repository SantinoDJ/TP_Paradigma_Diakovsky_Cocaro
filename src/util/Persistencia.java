package util;

import java.io.*;
import java.util.List;

public class Persistencia {


    public static void guardar(Object datos, String archivo) {


        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(archivo))) {


            out.writeObject(datos);


        } catch (IOException e) {

            System.out.println("Error guardando: " + e.getMessage());

        }

    }



    public static Object cargar(String archivo) {


        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(archivo))) {


            return in.readObject();


        } catch (Exception e) {


            return null;

        }

    }

}