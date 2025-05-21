
package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagicaBase.elements.Sprite;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase que representa un obstáculo dentro del juego.
 * Hereda de {@link Sprite} y se dibuja como un rectángulo gris en pantalla.
 * 
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
public class Obstaculo extends Sprite {

    /**
     * Crea un nuevo obstáculo con la posición y dimensiones especificadas.
     * El color del obstáculo por defecto es gris.
     * 
     * @param x coordenada horizontal del obstáculo
     * @param y coordenada vertical del obstáculo
     * @param height altura del obstáculo
     * @param width anchura del obstáculo
     */
    public Obstaculo(int x, int y, int height, int width) {
        super(x, y, height, width);
        setColor(Color.GRAY);
    }

    /**
     * Dibuja el obstáculo como un rectángulo relleno con el color especificado.
     * 
     * @param g el contexto gráfico donde se dibuja el obstáculo
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillRect(x, y, width, height);
    }
}