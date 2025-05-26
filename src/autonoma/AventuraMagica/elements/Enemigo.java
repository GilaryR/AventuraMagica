package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagicaBase.elements.Sprite;

/**
 * Representa un enemigo en el juego que puede moverse y colisionar con el jugador.
 * Los enemigos se mueven automáticamente según su velocidad y pueden rebotar en los límites del área de juego.
 * Extiende de la clase Sprite para tener capacidades gráficas básicas.
 * 
 * @author Luisa Fernanada Henao Posada
 * @since 26-05-2025
 * @version 2.0
 */
public class Enemigo extends Sprite {
    private int velocidadX = 2;  // valor por defecto
    private int velocidadY = 0;

    /**
     * Crea un nuevo enemigo en la posición especificada.
     * 
     * @param x Posición horizontal inicial del enemigo
     * @param y Posición vertical inicial del enemigo
     * @param rutaImagen Ruta de la imagen que representa al enemigo
     * @param ancho Ancho del enemigo en píxeles
     * @param alto Alto del enemigo en píxeles
     */
    public Enemigo(int x, int y, String rutaImagen, int ancho, int alto) {
        super(x, y, rutaImagen, ancho, alto);
    }

    /**
     * Actualiza la posición del enemigo según su velocidad.
     * Incluye lógica básica de rebote cuando alcanza los límites horizontales (x=0 o x=800).
     */
    public void mover() {
        x += velocidadX;
        y += velocidadY;

        // Ejemplo para que rebote horizontalmente
        if (x < 0 || x + getAncho() > 800) {
            velocidadX = -velocidadX;
        }
    }

    /**
     * Establece la velocidad horizontal del enemigo.
     * 
     * @param velocidadX Nueva velocidad horizontal (píxeles por actualización)
     */
    public void setVelocidad(int velocidadX) {
        this.velocidadX = velocidadX;
    }

    /**
     * Obtiene la velocidad horizontal actual del enemigo.
     * 
     * @return La velocidad horizontal actual (píxeles por actualización)
     */
    public int getVelocidad() {
        return velocidadX;
    }
}