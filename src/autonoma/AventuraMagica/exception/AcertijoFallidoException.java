package AventuraMagica.exception;
/**
 * Excepción lanzada cuando el jugador falla un acertijo
 */
/**
 *  * 
 * @author Alejandra Ortega 
 * @since 13-05-2025
 * @version 1.0
 */
public class AcertijoFallidoException extends Exception {
    private int intentosRestantes;

    public AcertijoFallidoException(int intentosRestantes) {
        super("Respuesta incorrecta. Intentos restantes: " + intentosRestantes);
        this.intentosRestantes = intentosRestantes;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }
}