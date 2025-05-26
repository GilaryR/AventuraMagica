package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagica.elements.Enemigo;
import java.awt.Rectangle;

public class Cuy extends Enemigo {
    private static final int ANCHO_CUY = 45;
    private static final int ALTO_CUY = 30;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagica/images/cuy.png";

    public Cuy(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO_CUY, ALTO_CUY);
        setVelocidad(6); // Más rápido que otros enemigos
    }
}