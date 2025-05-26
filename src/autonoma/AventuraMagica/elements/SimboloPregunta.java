package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagica.elements.Acertijo;
import autonoma.AventuraMagicaBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JOptionPane;

public class SimboloPregunta extends Sprite {
    private final List<Acertijo> acertijos;
    private boolean fueUsado = false;

    private static final int ANCHO = 48;  
    private static final int ALTO = 48;
    private static final String RUTA_IMAGEN = "/autonoma/AventuraMagica/images/Signo.png";

    public SimboloPregunta(int x, int y, List<Acertijo> acertijos) {
        super(x < 100 ? 100 : x, y, RUTA_IMAGEN, ANCHO, ALTO);
        this.acertijos = acertijos;
    }

    /**
     * Verifica si colisiona con el rectángulo del jugador (más limpio).
     */
    public boolean verificarColision(Rectangle jugadorBounds) {
        Rectangle simboloBounds = getBounds();
        return simboloBounds.intersects(jugadorBounds) && isVisible();
    }

    /**
     * Maneja la colisión, muestra acertijos y devuelve puntos según acierto o error.
     */
    public int manejarColision() {
        if (!fueUsado && isVisible()) {
            boolean correcto = mostrarYValidar();
            fueUsado = true;
            setVisible(false);
            return correcto ? 10 : -5;
        }
        return 0;
    }

    private boolean mostrarYValidar() {
        for (Acertijo acertijo : acertijos) {
            String respuesta = JOptionPane.showInputDialog(null,
                    acertijo.getPregunta(), "Acertijo", JOptionPane.QUESTION_MESSAGE);

            if (respuesta == null || !acertijo.verificar(respuesta.trim())) {
                JOptionPane.showMessageDialog(null, "¡Incorrecto!", "Fallaste", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "¡Correcto!", "Acertaste", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    public boolean fueUsado() {
        return fueUsado;
    }

    public void setUsado(boolean usado) {
        this.fueUsado = usado;
    }

    public void aplicarEfecto(Jugador jugador) {
        // Puedes agregar efectos al jugador aquí, si quieres
    }

    @Override
    public void dibujar(Graphics g) {
        if (isVisible()) {
            g.drawImage(imagen, x, y, getAncho(), getAlto(), null);
        }
    }

    public void moverHaciaAbajo(int dy) {
        this.y += dy;
    }

    public boolean fueraDePantalla(int alturaVentana) {
        return y > alturaVentana;
    }

    public void reiniciar(int nuevaX, int nuevaY) {
        this.x = Math.max(nuevaX, 100);
        this.y = nuevaY;
        setVisible(true);
        this.fueUsado = false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, getAncho(), getAlto());
    }

boolean verificarColision(int x, int y, int ancho, int alto) {
    Rectangle rectSimbolo = new Rectangle(this.x, this.y, this.getAncho(), this.getAlto());
    Rectangle rectOtro = new Rectangle(x, y, ancho, alto);
    return rectSimbolo.intersects(rectOtro);
}
}
