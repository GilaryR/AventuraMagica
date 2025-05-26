package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagica.elements.Enemigo;
import java.awt.Rectangle;

public class Tucan extends Enemigo {
    private static final int ANCHO = 60;
    private static final int ALTO = 40;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagica/images/tucan.png";

    public Tucan(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO, ALTO);
        setVelocidad(3); // Velocidad específica
    }
}