package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagica.elements.Artefacto;
import autonoma.AventuraMagica.elements.Enemigo;
import autonoma.AventuraMagica.elements.Esmeralda;
import autonoma.AventuraMagica.elements.Jugador;
import autonoma.AventuraMagica.elements.Nivel;
import autonoma.AventuraMagica.elements.SimboloPregunta;
import autonoma.AventuraMagicaBase.elements.Sprite;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Controla la lógica principal del juego, gestionando entidades, colisiones y dibujo.
 * Maneja las interacciones entre jugador, enemigos, artefactos y símbolos de pregunta.
 * 
 * @author Luisa Fernanda Henao Posada
 * @version 2.0
 * @since 26-05-2025
 */
public class ControladorJuego {
    private Jugador jugador;
    private Nivel nivel;
    private List<Enemigo> enemigos;
    private List<Artefacto> artefactos;
    private List<SimboloPregunta> simbolos;
    
    /**
     * Crea un nuevo controlador de juego para el nivel especificado.
     * 
     * @param nivel El nivel que se va a controlar
     * @throws IllegalArgumentException Si el nivel es nulo
     */
    public ControladorJuego(Nivel nivel) {
        if (nivel == null) {
            throw new IllegalArgumentException("El nivel no puede ser nulo");
        }

        this.nivel = nivel;
        this.jugador = new Jugador(400, 500);
        this.enemigos = new ArrayList<>(nivel.getEnemigos());
        this.artefactos = new ArrayList<>(nivel.getArtefactos());
        this.simbolos = new ArrayList<>(nivel.getSimbolosPregunta());
    }

    /**
     * Dibuja al jugador en el contexto gráfico especificado.
     * 
     * @param g El contexto gráfico donde se dibujará
     */
    public void dibujarJugador(Graphics g) {
        if (jugador != null) {
            jugador.dibujar(g);
        }
    }

    /**
     * Dibuja todos los enemigos visibles en el contexto gráfico.
     * 
     * @param g El contexto gráfico donde se dibujarán
     */
    public void dibujarEnemigos(Graphics g) {
        for (Enemigo enemigo : enemigos) {
            if (enemigo != null && enemigo.isVisible()) {
                enemigo.dibujar(g);
            }
        }
    }

    /**
     * Dibuja todos los artefactos visibles y no recolectados.
     * 
     * @param g El contexto gráfico donde se dibujarán
     */
    public void dibujarArtefactos(Graphics g) {
        for (Artefacto artefacto : artefactos) {
            if (artefacto != null && artefacto.isVisible() && !artefacto.isRecolectado()) {
                artefacto.dibujar(g);
            }
        }
    }

    /**
     * Dibuja todos los símbolos de pregunta visibles.
     * 
     * @param g El contexto gráfico donde se dibujarán
     */
    public void dibujarSimbolos(Graphics g) {
        for (SimboloPregunta simbolo : simbolos) {
            if (simbolo != null && simbolo.isVisible()) {
                simbolo.dibujar(g);
            }
        }
    }

    /**
     * Verifica las colisiones entre el jugador y otros elementos del juego.
     * Actualiza el estado del juego según las colisiones detectadas:
     * - Con enemigos: reduce vida del jugador
     * - Con artefactos: los marca como recolectados y aplica sus efectos
     * - Con símbolos: modifica el puntaje del jugador
     */
    public void verificarColisiones() {
        // Colisiones con enemigos
        for (Enemigo enemigo : enemigos) {
            if (enemigo.isVisible() && colision(jugador, enemigo)) {
                jugador.reducirVida(10);
            }
        }

        // Colisiones con artefactos
        for (Artefacto artefacto : artefactos) {
            if (artefacto.isVisible() && !artefacto.isRecolectado() && colision(jugador, artefacto)) {
                artefacto.setRecolectado(true);
                if (artefacto instanceof Botella) {
                    jugador.recolectarBotella();
                } else if (artefacto instanceof Esmeralda) {
                    jugador.recolectarEsmeralda();
                }
            }
        }

        // Colisiones con símbolos de pregunta
        for (SimboloPregunta simbolo : simbolos) {
            if (simbolo.isVisible() && simbolo.verificarColision(jugador.getX(), jugador.getY(), 
                jugador.getAncho(), jugador.getAlto())) {
                int puntos = simbolo.manejarColision();
                if (puntos > 0) {
                    jugador.aumentarPuntaje(puntos);
                } else if (puntos < 0) {
                    jugador.disminuirPuntaje(-puntos);
                }
            }
        }
    }

    /**
     * Mueve todos los enemigos visibles según su lógica de movimiento.
     */
    public void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.isVisible()) {
                enemigo.mover();
            }
        }
    }

    /**
     * Obtiene la referencia al jugador controlado.
     * 
     * @return El objeto Jugador actual
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Verifica colisión entre jugador y artefacto.
     * @param jugador El jugador
     * @param artefacto El artefacto
     * @return true si hay colisión, false en caso contrario
     */
    private boolean colision(Jugador jugador, Artefacto artefacto) {
        return jugador.getBounds().intersects(artefacto.getBounds());
    }

    /**
     * Verifica colisión entre jugador y enemigo.
     * @param jugador El jugador
     * @param enemigo El enemigo
     * @return true si hay colisión, false en caso contrario
     */
    private boolean colision(Jugador jugador, Enemigo enemigo) {
        return jugador.getBounds().intersects(enemigo.getBounds());
    }

    /**
     * Verifica colisión entre dos sprites cualesquiera.
     * @param a Primer sprite
     * @param b Segundo sprite
     * @return true si hay colisión, false en caso contrario
     */
    private boolean colision(Sprite a, Sprite b) {
        return a.getBounds().intersects(b.getBounds());
    }
}