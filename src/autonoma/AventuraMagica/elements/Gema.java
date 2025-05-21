
package autonoma.AventuraMagica.elements;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Luisa Fernanda Henao
 */

public class Gema extends Objeto {
    private int valor;
    
    public Gema(int x, int y, int height, int width) {
        super(x, y, height, width);
        valor = 100;
        setColor(Color.YELLOW);
    }
    
    @Override
    public void efecto(Jugador jugador) {
        // Aumentar puntuación del jugador
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        g.fillOval(x, y, width, height);
    }
}