
package autonoma.AventuraMagica.exception;

import java.io.IOException;

/**
 * Excepción lanzada cuando hay problemas al guardar o cargar partidas
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
public class ArchivoGuardadoException extends IOException {
    private String archivo;

    public ArchivoGuardadoException(String archivo, String mensaje) {
        super("Error con el archivo '" + archivo + "': " + mensaje);
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }
}
