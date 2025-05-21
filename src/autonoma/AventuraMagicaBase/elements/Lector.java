
package autonoma.AventuraMagicaBase.elements;

import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
/**
 * Interfaz para la lectura de datos en diferentes formatos
 */
interface Lector {
    /**
     * Lee un archivo y devuelve su contenido como lista de líneas
     * @param ruta Ruta del archivo a leer
     * @return Lista de líneas leídas
     * @throws IOException Si ocurre un error durante la lectura
     */
    ArrayList<String> leer(String ruta) throws IOException;
}
