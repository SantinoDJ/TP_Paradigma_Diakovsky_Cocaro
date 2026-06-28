package util;

import java.io.*;



public class Persistencia {


    // Guarda un objeto en un archivo
    public static void guardar(Object datos, String archivo) {


        // Permite escribir objetos en archivos
        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(archivo))) {


            // Guarda el objeto recibido
            out.writeObject(datos);


        } catch (IOException e) {

            // Captura errores al guardar
            System.out.println("Error guardando: " + e.getMessage());

        }

    }



    // Lee un objeto guardado en un archivo
    public static Object cargar(String archivo) {


        // Permite leer objetos desde archivos
        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(archivo))) {


            // Devuelve el objeto leído
            return in.readObject();


        } catch (Exception e) {


            // Si falla devuelve null
            return null;

        }

    }

}