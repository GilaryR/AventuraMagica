package autonoma.AventuraMagica.elements;

public class Botella extends Artefacto {
    private static final int ANCHO = 40;
    private static final int ALTO = 40;
    private static final String RUTA = "/autonoma/AventuraMagica/images/botella.png";

    public Botella(int x, int y) {
        super(x, y, RUTA, ANCHO, ALTO);
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.aumentarPuntaje(15);
    }
}