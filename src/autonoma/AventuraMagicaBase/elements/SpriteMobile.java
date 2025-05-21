
package autonoma.AventuraMagicaBase.elements;

import autonoma.AventuraMagicaBase.elements.Sprite;

/**
 *
 * @author Alejandra Ortega 
 */
/**
 * Clase base para sprites que pueden moverse
 */
public abstract class SpriteMobile extends Sprite {
    protected int step;
    
    /**
     * Constructor de sprite móvil
     * @param x Posición en el eje X
     * @param y Posición en el eje Y
     * @param height Alto del sprite
     * @param width Ancho del sprite
     */
    public SpriteMobile(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.step = 5; // Valor por defecto para el paso de movimiento
    }
    
    // Getters y setters adicionales
    public int getStep() { return step; }
    public void setStep(int step) { this.step = step; }
}