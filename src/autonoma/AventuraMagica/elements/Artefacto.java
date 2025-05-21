package autonoma.AventuraMagica.elements;

/**
 *
 * @author Luisa Fernanda Henao
 */

import AventuraMagicaBase.elements.Sprite;

/**
 * Clase base para los artefactos mágicos del juego
 */
public abstract class Artefacto extends Sprite {
    private String nombre;
    private String descripcion;
    
    public Artefacto(int x, int y, int height, int width, String nombre, String descripcion) {
        super(x, y, height, width);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public abstract void usar();
    
    // Getters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }

    public void activar() {
    }

    boolean isRecolectado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}