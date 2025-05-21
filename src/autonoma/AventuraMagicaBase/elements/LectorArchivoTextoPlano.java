
package autonoma.AventuraMagicaBase.elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
/**
 * Implementación de la interfaz Lector para archivos de texto plano
 */
public class LectorArchivoTextoPlano implements Lector {
    
    /**
     * Lee un archivo de configuración y lo parsea (ejemplo para futuras implementaciones)
     * @param ruta Ruta del archivo
     * @return Objeto Vehiculo creado con la configuración leída
     * @throws IOException Si ocurre un error durante la lectura
     */
    public Object leerConfiguracion(String ruta) throws IOException {
        ArrayList<String> lineas = leer(ruta);
        // Implementar la lógica de parseo específica aquí
        return null; // Retornar el objeto creado
    }
    
    /**
     * Lee un archivo y devuelve su contenido como lista de líneas
     * @param ruta Ruta del archivo a leer
     * @return Lista de líneas leídas
     * @throws IOException Si ocurre un error durante la lectura
     */
    @Override
    public ArrayList<String> leer(String ruta) throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        File file = new File(ruta);
        
        if (!file.exists()) {
            return lineas; // Retorna una lista vacía si el archivo no existe
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo: " + ruta, e);
        }
        
        return lineas;
    }
}
