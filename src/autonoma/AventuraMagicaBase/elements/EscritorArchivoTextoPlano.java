
package autonoma.AventuraMagicaBase.elements;

import autonoma.AventuraMagicaBase.elements.Escritor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jgiugtiñut
 */
/**
 * Implementación de la interfaz Escritor para archivos de texto plano
 */
public class EscritorArchivoTextoPlano implements Escritor {
    private String rutaArchivo;
    
    /**
     * Constructor que especifica la ruta del archivo
     * @param rutaArchivo Ruta donde se guardará el archivo
     */
    public EscritorArchivoTextoPlano(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    
    /**
     * Escribe una lista de líneas en un archivo de texto
     * @param lineas Lista de líneas a escribir
     * @throws IOException Si ocurre un error durante la escritura
     */
    @Override
    public void escribir(ArrayList<String> lineas) throws IOException {
        File file = new File(rutaArchivo);
        
        // Crear directorios si no existen
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            throw new IOException("Error al escribir en el archivo: " + rutaArchivo, e);
        }
    }
}