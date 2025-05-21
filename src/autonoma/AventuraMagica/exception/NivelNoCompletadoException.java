package autonoma.AventuraMagica.exception;

/**
 * Excepción lanzada cuando se intenta avanzar sin completar los requisitos del nivel
 */
public class NivelNoCompletadoException extends Exception {
    private int nivel;

    public NivelNoCompletadoException(int nivel) {
        super("No se han cumplido los requisitos para completar el nivel " + nivel);
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}