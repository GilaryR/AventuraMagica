
package autonoma.AventuraMagicaBase.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
/**
 * Clase base para todos los elementos visuales del juego
 */
public abstract class Sprite {
    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected Color color;
    protected ImageIcon image;
    protected GraphicContainer gameContainer;
    
    /**
     * Constructor de la clase Sprite
     * @param x Posición en el eje X
     * @param y Posición en el eje Y
     * @param height Alto del sprite
     * @param width Ancho del sprite
     */
    public Sprite(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = Color.BLACK;
    }
    
    /**
     * Verifica si el sprite está fuera del contenedor gráfico
     * @return true si está fuera, false si está dentro
     */
    public boolean isOutOfGraphicContainer() {
        if (gameContainer == null) return false;
        Rectangle bounds = gameContainer.getBoundaries();
        return x < 0 || y < 0 || x + width > bounds.width || y + height > bounds.height;
    }
    
    /**
     * Verifica si unas coordenadas específicas están fuera del contenedor gráfico
     * @param x Posición X a verificar
     * @param y Posición Y a verificar
     * @param width Ancho del área a verificar
     * @param height Alto del área a verificar
     * @return true si está fuera, false si está dentro
     */
    public boolean isOutOfGraphicContainer(int x, int y, int width, int height) {
        if (gameContainer == null) return false;
        Rectangle bounds = gameContainer.getBoundaries();
        return x < 0 || y < 0 || x + width > bounds.width || y + height > bounds.height;
    }
    
    /**
     * Verifica si este sprite colisiona con otro
     * @param other El otro sprite a verificar
     * @return true si hay colisión, false en caso contrario
     */
    public boolean checkCollision(Sprite other) {
        return x < other.x + other.width &&
               x + width > other.x &&
               y < other.y + other.height &&
               y + height > other.y;
    }
    
    /**
     * Método abstracto para pintar el sprite
     * @param g Objeto Graphics para dibujar
     */
    public abstract void paint(Graphics g);
    
    // Getters y setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public javax.swing.ImageIcon getImage() { return image; }
    public void setImage(javax.swing.ImageIcon image) { this.image = image; }
    
    /**
     * Establece el contenedor gráfico al que pertenece este sprite
     * @param gContainer Contenedor gráfico
     */
    public void setGraphicContainer(GraphicContainer gContainer) {
        this.gameContainer = gContainer;
    }
}
