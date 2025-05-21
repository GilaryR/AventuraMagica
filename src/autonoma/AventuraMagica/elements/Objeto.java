
package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagicaBase.elements.Sprite;


/**
 * Clase abstracta que representa un objeto dentro del juego.
 * Hereda de la clase {@link Sprite} e incluye comportamiento relacionado con 
 * la recolección de objetos y la aplicación de efectos al jugador.
 * 
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
public abstract class Objeto extends Sprite {
    
    /**
     * Indica si el objeto ha sido recolectado por el jugador.
     */
    private boolean recolectado;
    
    /**
     * Crea un nuevo objeto en una posición y tamaño determinados.
     * 
     * @param x coordenada horizontal del objeto
     * @param y coordenada vertical del objeto
     * @param height altura del objeto
     * @param width anchura del objeto
     */
    public Objeto(int x, int y, int height, int width) {
        super(x, y, height, width);
        recolectado = false;
    }
    
    /**
     * Verifica si el objeto ha sido recolectado.
     * 
     * @return {@code true} si el objeto fue recolectado, {@code false} en caso contrario
     */
    public boolean isRecolectado() {
        return recolectado;
    }
    
    /**
     * Marca el objeto como recolectado.
     */
    public void recolectar() {
        recolectado = true;
    }
    
    /**
     * Aplica el efecto del objeto sobre el jugador.
     * Este método debe ser implementado por las clases concretas.
     * 
     * @param jugador el jugador que recibe el efecto del objeto
     */
    public abstract void efecto(Jugador jugador);
}

