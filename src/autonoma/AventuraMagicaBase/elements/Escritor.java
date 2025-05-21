
package autonoma.AventuraMagicaBase.elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz para la escritura de datos en diferentes formatos
 */
interface Escritor {
    /**
     * Escribe una lista de líneas de texto
     * @param lineas Lista de líneas a escribir
     * @throws IOException Si ocurre un error durante la escritura
     */
    void escribir(ArrayList<String> lineas) throws IOException;
}