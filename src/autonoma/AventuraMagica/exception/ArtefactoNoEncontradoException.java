
package AventuraMagica.exception;

/**
 * Excepción lanzada cuando no se encuentra un artefacto en el inventario
 */
/**
 *  * 
 * @author Alejandra Ortega 
 * @since 13-05-2025
 * @version 1.0
 */
public class ArtefactoNoEncontradoException extends Exception {
    private String nombreArtefacto;

    public ArtefactoNoEncontradoException(String nombreArtefacto) {
        super("El artefacto '" + nombreArtefacto + "' no se encuentra en el inventario");
        this.nombreArtefacto = nombreArtefacto;
    }

    public String getNombreArtefacto() {
        return nombreArtefacto;
    }
}