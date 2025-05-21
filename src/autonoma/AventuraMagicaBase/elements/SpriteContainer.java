/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.AventuraMagicaBase.elements;

import autonoma.AventuraMagicaBase.elements.GraphicContainer;
import autonoma.AventuraMagicaBase.elements.Sprite;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Alejandra Ortega
 */
/**
 * Contenedor de sprites, puede contener múltiples sprites
 */
public abstract class SpriteContainer extends Sprite implements GraphicContainer {
    protected ArrayList<Sprite> sprites;
    
    /**
     * Constructor del contenedor de sprites
     * @param x Posición en el eje X
     * @param y Posición en el eje Y
     * @param height Alto del contenedor
     * @param width Ancho del contenedor
     */
    public SpriteContainer(int x, int y, int height, int width) {
        super(x, y, height, width);
        sprites = new ArrayList<>();
    }
    
    /**
     * Añade un sprite al contenedor
     * @param sprite Sprite a añadir
     * @return true si se añadió correctamente
     */
    public boolean add(Sprite sprite) {
        sprite.setGraphicContainer(this);
        return sprites.add(sprite);
    }
    
    /**
     * Elimina un sprite del contenedor por su índice
     * @param index Índice del sprite a eliminar
     */
    public void remove(int index) {
        sprites.remove(index);
    }
    
    /**
     * Elimina un sprite específico del contenedor
     * @param sprite Sprite a eliminar
     */
    public void remove(Sprite sprite) {
        sprites.remove(sprite);
    }
    
    /**
     * Obtiene el número de sprites en el contenedor
     * @return Cantidad de sprites
     */
    public int size() {
        return sprites.size();
    }
    
    /**
     * Implementación del método getBoundaries de la interfaz GraphicContainer
     * @return Rectangle con los límites del contenedor
     */
    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
}