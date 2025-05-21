
package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagica.exception.DificultadNoValidaException;
import autonoma.AventuraMagica.exception.NivelNoEncontradoException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que gestiona el estado y flujo del juego.
 * Controla los niveles, el jugador, la dificultad, y las acciones
 * como iniciar, pausar, guardar y cargar la partida.
 * 
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
public class Juego {
    private final String nombre;
    private int nivelActual;
    private String dificultad;
    private final Jugador jugador;
    private final ArrayList<Nivel> niveles;
    private final GestorSonido gestorSonido;
    private boolean enPausa;
    private boolean juegoActivo;

    /**
     * Constructor del juego
     * @param nombre Nombre del juego
     */
    public Juego(String nombre) {
        this.nombre = nombre;
        this.nivelActual = 1;
        this.dificultad = "Normal";
        this.niveles = new ArrayList<>();
        this.gestorSonido = new GestorSonido();
        this.enPausa = false;
        this.juegoActivo = false;
        this.jugador = new Jugador("Héroe", 400, 300, 50, 50);
        inicializarNiveles();
    }

    /**
     * Inicia el juego cargando recursos y comenzando el primer nivel.
     */
    public void iniciarJuego() {
        if (juegoActivo) {
            System.out.println("El juego ya está activo");
            return;
        }

        cargarNiveles();
        gestorSonido.reproducirMusica("tema_principal");
        juegoActivo = true;
        enPausa = false;
        System.out.println("¡" + nombre + " ha comenzado!");
    }

    /**
     * Pausa o reanuda el juego.
     */
    public void pausarJuego() {
        if (!juegoActivo) return;

        enPausa = !enPausa;
        if (enPausa) {
            gestorSonido.silenciar();
            System.out.println("Juego en pausa");
        } else {
            gestorSonido.reproducirMusica("tema_principal");
            System.out.println("Juego reanudado");
        }
    }

    /**
     * Guarda el estado actual de la partida.
     */
    public void guardarPartida() {
        if (!juegoActivo) {
            System.out.println("No se puede guardar: juego no activo");
            return;
        }

        try {
            jugador.guardarEstado();
            System.out.println("Partida guardada correctamente");
        } catch (Exception e) {
            System.err.println("Error al guardar partida: " + e.getMessage());
        }
    }

    /**
     * Carga una partida guardada.
     */
    public void cargarPartida() {
        System.out.println("Cargando partida guardada...");
        jugador.cargarEstado();
        niveles.forEach(Nivel::cargarNivel);
        juegoActivo = true;
        enPausa = false;
    }

    /**
     * Cambia la dificultad del juego.
     * 
     * @param nivel Nueva dificultad (Fácil, Normal, Difícil)
     * @throws DificultadNoValidaException Si la dificultad no es válida
     */
    public void cambiarDificultad(String nivel) throws DificultadNoValidaException {
        if (!nivel.equalsIgnoreCase("Fácil") &&
            !nivel.equalsIgnoreCase("Normal") &&
            !nivel.equalsIgnoreCase("Difícil")) {
            throw new DificultadNoValidaException(nivel);
        }

        this.dificultad = nivel;
        ajustarDificultad();
        System.out.println("Dificultad cambiada a: " + nivel);
    }

    /**
     * Selecciona un nivel para jugar.
     * 
     * @param nivel Número de nivel a seleccionar
     * @throws NivelNoEncontradoException Si el nivel no existe o está bloqueado
     */
    public void seleccionarNivel(int nivel) throws NivelNoEncontradoException {
        if (nivel < 1 || nivel > niveles.size()) {
            throw new NivelNoEncontradoException(nivel);
        }

        if (!niveles.get(nivel - 1).isDesbloqueado()) {
            System.out.println("El nivel " + nivel + " está bloqueado");
            return;
        }

        this.nivelActual = nivel;
        System.out.println("Nivel " + nivel + " seleccionado");
    }

    /**
     * Avanza al siguiente nivel disponible.
     */
    public void siguienteNivel() {
        if (nivelActual < niveles.size()) {
            nivelActual++;
            System.out.println("Avanzando al nivel " + nivelActual);
        } else {
            System.out.println("¡Has completado todos los niveles!");
        }
    }

    /**
     * Inicializa los niveles del juego con configuraciones predeterminadas.
     */
    private void inicializarNiveles() {
        niveles.add(new Nivel(1, "Bosque Encantado", dificultad, 800, 600, "mapas/bosque.txt"));
        niveles.add(new Nivel(2, "Cueva de Cristal", dificultad, 1000, 800, "mapas/cueva.txt"));
        niveles.add(new Nivel(3, "Castillo Oscuro", dificultad, 1200, 900, "mapas/castillo.txt"));
    }

    /**
     * Carga los niveles desbloqueados.
     */
    private void cargarNiveles() {
        System.out.println("Cargando niveles...");
        niveles.forEach(nivel -> {
            if (nivel.isDesbloqueado()) {
                nivel.cargarNivel();
            }
        });
    }

    /**
     * Ajusta los parámetros del jugador y enemigos según la dificultad.
     */
    private void ajustarDificultad() {
        switch (dificultad.toLowerCase()) {
            case "fácil" -> {
                jugador.setSaludMaxima(150);
                niveles.forEach(n -> n.getEnemigos().forEach(e -> e.setPoder((int) (e.getPoder() * 0.7))));
            }
            case "difícil" -> {
                jugador.setSaludMaxima(80);
                niveles.forEach(n -> n.getEnemigos().forEach(e -> e.setPoder((int) (e.getPoder() * 1.3))));
            }
            default -> // Normal
                jugador.setSaludMaxima(100);
        }
        jugador.curar(jugador.getSaludMaxima());
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getNivelActual() { return nivelActual; }
    public String getDificultad() { return dificultad; }
    public Jugador getJugador() { return jugador; }
    public List<Nivel> getNiveles() { return new ArrayList<>(niveles); }
    public boolean isEnPausa() { return enPausa; }
    public boolean isJuegoActivo() { return juegoActivo; }
}
