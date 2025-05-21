
package autonoma.AventuraMagica.exception;

/**
 * Excepción lanzada cuando se intenta acceder a un nivel que no existe
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
public class NivelNoEncontradoException extends Exception {
    private final int nivelSolicitado;
    
    /**
     * Constructor con el nivel que no se encontró
     * @param nivelSolicitado Número del nivel que no existe
     */
    public NivelNoEncontradoException(int nivelSolicitado) {
        super("El nivel " + nivelSolicitado + " no existe en la lista de niveles disponibles");
        this.nivelSolicitado = nivelSolicitado;
    }
    
    /**
     * Constructor con mensaje personalizado
     * @param nivelSolicitado Número del nivel que no existe
     * @param mensaje Mensaje personalizado de error
     */
    public NivelNoEncontradoException(int nivelSolicitado, String mensaje) {
        super(mensaje);
        this.nivelSolicitado = nivelSolicitado;
    }
    
    /**
     * Obtiene el nivel que se intentó acceder
     * @return Número del nivel no encontrado
     */
    public int getNivelSolicitado() {
        return nivelSolicitado;
    }
}