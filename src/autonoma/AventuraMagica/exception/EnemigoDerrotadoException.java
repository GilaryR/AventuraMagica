
package autonoma.AventuraMagica.exception;


/**
 *
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
/**
 * Excepción lanzada cuando se intenta realizar una acción sobre un enemigo ya derrotado
 */
public class EnemigoDerrotadoException extends RuntimeException {
    private final int idEnemigo;
    private final String nombreEnemigo;

    /**
     * Constructor de la excepción
     * @param idEnemigo ID del enemigo derrotado
     * @param nombreEnemigo Nombre del enemigo derrotado
     * @param message Mensaje descriptivo del error
     */
    public EnemigoDerrotadoException(int idEnemigo, String nombreEnemigo, String message) {
        super(message);
        this.idEnemigo = idEnemigo;
        this.nombreEnemigo = nombreEnemigo;
    }

    /**
     * Constructor simplificado
     * @param idEnemigo ID del enemigo derrotado
     * @param nombreEnemigo Nombre del enemigo derrotado
     */
    public EnemigoDerrotadoException(int idEnemigo, String nombreEnemigo) {
        this(idEnemigo, nombreEnemigo, "El enemigo " + nombreEnemigo + " (ID: " + idEnemigo + ") ya ha sido derrotado");
    }

    // Getters
    public int getIdEnemigo() {
        return idEnemigo;
    }

    public String getNombreEnemigo() {
        return nombreEnemigo;
    }
}