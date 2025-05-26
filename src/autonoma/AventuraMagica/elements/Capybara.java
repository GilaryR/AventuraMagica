package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagica.elements.Enemigo;

/**
 * Representa un enemigo tipo Capybara en el juego.
 * Es un tipo específico de enemigo con dimensiones y velocidad predefinidas.
 * Extiende de la clase Enemigo heredando su comportamiento básico.
 * 
 * @author Luisa Fernanda Henao Posada
 * @version 2.0
 * @since 26-05-2025
 */
public class Capybara extends Enemigo {
    /** Ancho predeterminado del Capybara en píxeles */
    private static final int ANCHO = 80;
    
    /** Alto predeterminado del Capybara en píxeles */
    private static final int ALTO = 50;
    
    /** Ruta de la imagen que representa al Capybara */
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagica/images/capybara.png";

    /**
     * Crea un nuevo Capybara en la posición especificada.
     * Configura automáticamente su velocidad a 4 unidades.
     * 
     * @param x Posición horizontal inicial (coordenada x)
     * @param y Posición vertical inicial (coordenada y)
     */
    public Capybara(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO, ALTO);
        setVelocidad(4);
    }
}