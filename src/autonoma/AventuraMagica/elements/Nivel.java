package autonoma.AventuraMagica.elements;


import autonoma.AventuraMagica.exception.NivelNoCompletadoException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que representa un nivel del juego con funcionalidad completa
 * @author Alejandra Ortega
 */
public class Nivel {

    static void guardarEstado() {
        
    }
    private final int id;
    private final String nombre;
    private final String dificultad;
    private final ArrayList<Enemigo> enemigos;
    private final ArrayList<Acertijo> acertijos;
    private final ArrayList<Artefacto> artefactos;
    private final ArrayList<Obstaculo> obstaculos;
    private boolean completado;
    private boolean desbloqueado;
    private final int ancho;
    private final int alto;
    private final String mapaArchivo;

    /**
     * Constructor completo del nivel
     * @param id Identificador único del nivel
     * @param nombre Nombre descriptivo del nivel
     * @param dificultad Dificultad del nivel (Fácil, Normal, Difícil)
     * @param ancho Ancho del área jugable en píxeles
     * @param alto Alto del área jugable en píxeles
     * @param mapaArchivo Ruta del archivo de mapa
     */
    public Nivel(int id, String nombre, String dificultad, int ancho, int alto, String mapaArchivo) {
        this.id = id;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.ancho = ancho;
        this.alto = alto;
        this.mapaArchivo = mapaArchivo;
        this.enemigos = new ArrayList<>();
        this.acertijos = new ArrayList<>();
        this.artefactos = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        this.completado = false;
        this.desbloqueado = (id == 1); // Solo el primer nivel desbloqueado inicialmente
    }

    /**
     * Carga el contenido del nivel desde archivos
     */
    public void cargarNivel() {
        // Implementación para cargar:
        // - Enemigos desde archivo
        // - Acertijos desde archivo
        // - Artefactos desde archivo
        // - Obstáculos desde archivo
        // - Configuración del mapa
        
        System.out.println("Cargando nivel " + nombre + " (" + dificultad + ")");
    }

    /**
     * Marca el nivel como completado y otorga recompensas
     * @throws NivelNoCompletadoException Si no se cumplen los requisitos
     */
    public void completarNivel() throws NivelNoCompletadoException {
        if (!verificarProgreso()) {
            throw new NivelNoCompletadoException(id);
        }
        
        this.completado = true;
        System.out.println("¡Nivel " + nombre + " completado!");
        
        // Otorgar recompensas por completar el nivel
        otorgarRecompensas();
    }

    /**
     * Verifica si se cumplen las condiciones para completar el nivel
     * @return true si el nivel puede completarse
     */
   public boolean verificarProgreso() {
    boolean enemigosDerrotados = enemigos.stream().allMatch(Enemigo::isDerrotado);
    boolean artefactosRecolectados = artefactos.stream().allMatch(Artefacto::isRecolectado);
    
    return enemigosDerrotados && artefactosRecolectados;
}

    /**
     * Desbloquea el siguiente nivel en la secuencia
     */
    public void desbloquearSiguienteNivel() {
        this.desbloqueado = true;
        System.out.println("Nivel " + nombre + " desbloqueado");
    }

    /**
     * Otorga recompensas por completar el nivel
     */
    private void otorgarRecompensas() {
        // Lógica para otorgar recompensas al jugador
        int puntosBase = switch (dificultad) {
            case "Difícil" -> 1000;
            case "Normal" -> 750;
            default -> 500;
        };
        
        System.out.println("Recompensa otorgada: " + puntosBase + " puntos");
    }

    /**
     * Añade un enemigo al nivel
     * @param enemigo Enemigo a añadir
     */
    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }

    /**
     * Añade un acertijo al nivel
     * @param acertijo Acertijo a añadir
     */
    public void agregarAcertijo(Acertijo acertijo) {
        acertijos.add(acertijo);
    }

    /**
     * Añade un artefacto al nivel
     * @param artefacto Artefacto a añadir
     */
    public void agregarArtefacto(Artefacto artefacto) {
        artefactos.add(artefacto);
    }

    /**
     * Añade un obstáculo al nivel
     * @param obstaculo Obstáculo a añadir
     */
    public void agregarObstaculo(Obstaculo obstaculo) {
        obstaculos.add(obstaculo);
    }

    /**
     * Obtiene una lista de enemigos vivos
     * @return Lista de enemigos no derrotados
     */
    public List<Enemigo> getEnemigosActivos() {
        return enemigos.stream()
                .filter(e -> !e.isDerrotado())
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una lista de artefactos no recolectados
     * @return Lista de artefactos disponibles
     */
    public List<Artefacto> getArtefactosDisponibles() {
        return artefactos.stream()
                .filter(a -> !a.isRecolectado())
                .collect(Collectors.toList());
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public boolean isCompletado() {
        return completado;
    }

    public boolean isDesbloqueado() {
        return desbloqueado;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public String getMapaArchivo() {
        return mapaArchivo;
    }

    public List<Enemigo> getEnemigos() {
        return new ArrayList<>(enemigos);
    }

    public List<Acertijo> getAcertijos() {
        return new ArrayList<>(acertijos);
    }

    public List<Artefacto> getArtefactos() {
        return new ArrayList<>(artefactos);
    }

    public List<Obstaculo> getObstaculos() {
        return new ArrayList<>(obstaculos);
    }
}