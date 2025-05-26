package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagica.elements.Enemigo;

public class Frailejon extends Enemigo {
    private static final int ANCHO = 50;
    private static final int ALTO = 70;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagica/images/frailejon.png";

    public Frailejon(int x, int y) {
        super(x, y, RUTA_IMAGEN, ANCHO, ALTO);
        setVelocidad(2); // Esto ajusta la velocidad horizontal a 2 px por movimiento
    }
}
