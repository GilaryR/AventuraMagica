package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagicaBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author Luisa Fernanada Henao Posada
 * @since 26-05-2025
 * @version 2.0
 * 
 * Clase abstracta que representa un artefacto en el juego.
 * Los artefactos son objetos que pueden ser recolectados por el jugador
 * y que producen algún efecto al ser recolectados.
 * Extiende de la clase Sprite para tener capacidades gráficas básicas.
 */
public abstract class Artefacto extends Sprite {

    private boolean recolectado;  // Indica si el artefacto ya fue recolectado

    /**
     * Constructor para crear un nuevo artefacto.
     * 
     * @param x Posición horizontal inicial del artefacto
     * @param y Posición vertical inicial del artefacto
     * @param rutaImagen Ruta de la imagen que representa al artefacto
     * @param ancho Ancho del artefacto en píxeles
     * @param alto Alto del artefacto en píxeles
     */
    public Artefacto(int x, int y, String rutaImagen, int ancho, int alto) {
        super(x, y, rutaImagen, ancho, alto);
        this.recolectado = false;  // por defecto no está recolectado
    }

    /**
     * Método abstracto que define el efecto específico que produce el artefacto
     * cuando es recolectado por el jugador.
     * 
     * @param jugador Referencia al jugador que recolectó el artefacto
     */
    public abstract void aplicarEfecto(Jugador jugador);

    /**
     * Dibuja el artefacto en el contexto gráfico especificado.
     * Solo se dibuja si está visible y tiene una imagen asignada.
     * 
     * @param g Contexto gráfico donde se dibujará el artefacto
     */
    @Override
    public void dibujar(Graphics g) {
        if (isVisible() && imagen != null) {
            g.drawImage(imagen, x, y, getAncho(), getAlto(), null);
        }
    }

    /**
     * Obtiene el rectángulo de colisión del artefacto.
     * 
     * @return Rectangle que representa los límites del artefacto
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, getAncho(), getAlto());
    }

    /**
     * Verifica si el artefacto colisiona con otro objeto del juego.
     * 
     * @param otro Rectángulo que representa los límites del otro objeto
     * @return true si hay colisión, false en caso contrario
     */
    public boolean verificarColision(Rectangle otro) {
        return getBounds().intersects(otro);
    }

    /**
     * Indica si el artefacto ha sido recolectado.
     * 
     * @return true si el artefacto fue recolectado, false si aún está disponible
     */
    public boolean isRecolectado() {
        return recolectado;
    }

    /**
     * Establece el estado de recolección del artefacto.
     * 
     * @param recolectado true para marcar como recolectado, false para marcar como no recolectado
     */
    public void setRecolectado(boolean recolectado) {
        this.recolectado = recolectado;
    }
}