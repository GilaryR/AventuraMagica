
package AventuraMagica.exception;
/**
 * Excepción lanzada cuando se intenta agregar un artefacto al inventario lleno
 */
public class InventarioLlenoException extends Exception {
    private int capacidadMaxima;

    public InventarioLlenoException(int capacidadMaxima) {
        super("El inventario está lleno. Capacidad máxima: " + capacidadMaxima);
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}