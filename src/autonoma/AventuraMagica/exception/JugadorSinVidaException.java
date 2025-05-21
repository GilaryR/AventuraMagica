
package autonoma.AventuraMagica.exception;

/**
 *
 * @author Luisa Fernanda Henao
 */
public class JugadorSinVidaException extends Exception {
    public JugadorSinVidaException() {
        super("El jugador ha perdido todas sus vidas");
    }
}