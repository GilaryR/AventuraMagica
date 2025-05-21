
package autonoma.AventuraMagica.elements;

import AventuraMagica.exception.EnemigoDerrotadoException;
import autonoma.AventuraMagica.exception.EnemigoDerrotadoException;


/**
 * Clase base para los enemigos del juego con implementación completa
 * @author Gilary
 * @since 13-05-2025
 * @version 1.0
 */
public class Enemigo {
    private final int id;
    private final String nombre;
    private String tipo;
    private int vida;
    private int vidaMaxima;
    private int poder;
    private int velocidad;
    private boolean jefe;
    private boolean derrotado;
    private static int contadorId = 1; // Contador estático para IDs únicos
    
    /**
     * Constructor completo para el enemigo
     * @param id Identificador único del enemigo
     * @param nombre Nombre del enemigo
     * @param tipo Tipo/clase de enemigo (ej. "Orco", "Dragón")
     * @param vida Vida inicial del enemigo
     * @param poder Daño que inflige el enemigo
     * @param velocidad Velocidad de movimiento/ataque
     * @param jefe Indica si es un enemigo jefe
     */
    public Enemigo(int id, String nombre, String tipo, int vida, int poder, int velocidad, boolean jefe) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.poder = poder;
        this.velocidad = velocidad;
        this.jefe = jefe;
        this.derrotado = false;
    }

    /**
     * Constructor simplificado con ID autoincremental
     * @param nombre Nombre del enemigo
     * @param tipo Tipo/clase de enemigo
     * @param vida Vida inicial del enemigo
     * @param poder Daño que inflige el enemigo
     * @param velocidad Velocidad de movimiento/ataque
     * @param jefe Indica si es un enemigo jefe
     */
    public Enemigo(String nombre, String tipo, int vida, int poder, int velocidad, boolean jefe) {
        this(contadorId++, nombre, tipo, vida, poder, velocidad, jefe);
    }
    
    /**
     * Mueve al enemigo según su IA
     * @throws EnemigoDerrotadoException Si el enemigo ya está derrotado
     */
    public void mover() throws EnemigoDerrotadoException {
        if (derrotado) {
            throw new EnemigoDerrotadoException(id, nombre, "No se puede mover un enemigo derrotado");
        }
        
        // Implementación básica de movimiento
        System.out.println(nombre + " (" + tipo + ") se mueve con velocidad " + velocidad);
    }
    
    /**
     * Realiza un ataque contra el jugador
     * @param jugador Objeto Jugador a atacar
     * @throws EnemigoDerrotadoException Si el enemigo ya está derrotado
     */
    public void atacar(Jugador jugador) throws EnemigoDerrotadoException {
        if (derrotado) {
            throw new EnemigoDerrotadoException(id, nombre);
        }
        
        System.out.println(nombre + " (" + tipo + ") ataca con " + poder + " de daño");
        try {
            jugador.recibirDaño(poder);
        } catch (Exception e) {
            System.err.println("Error al atacar al jugador: " + e.getMessage());
        }
    }
    
    /**
     * Reduce la vida del enemigo
     * @param cantidad Cantidad de daño a recibir
     * @return true si el enemigo fue derrotado
     * @throws EnemigoDerrotadoException Si el enemigo ya está derrotado
     */
    public boolean recibirDaño(int cantidad) throws EnemigoDerrotadoException {
        if (derrotado) {
            throw new EnemigoDerrotadoException(id, nombre);
        }
        
        vida = Math.max(0, vida - cantidad);
        System.out.println(nombre + " recibe " + cantidad + " de daño. Vida restante: " + vida);
        
        if (vida == 0) {
            morir();
            return true;
        }
        return false;
    }
    
    /**
     * Lógica ejecutada al derrotar al enemigo
     */
    protected void morir() {
        derrotado = true;
        System.out.println("¡" + nombre + " ha sido derrotado!");
    }
    
    /**
     * Puntos que otorga al ser derrotado
     * @return Cantidad de puntos
     * @throws EnemigoDerrotadoException Si el enemigo no está derrotado
     */
    public int otorgarPuntos() throws EnemigoDerrotadoException {
        if (!derrotado) {
            throw new EnemigoDerrotadoException(id, nombre, "El enemigo debe ser derrotado primero");
        }
        return jefe ? 500 : 100;
    }
    
    /**
     * Restaura la vida del enemigo (para reinicios o fases)
     */
    public void restaurarVida() {
        vida = vidaMaxima;
        derrotado = false;
        System.out.println(nombre + " ha sido revivido");
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public int getVida() {
        return vida;
    }
    
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    
    public int getPoder() {
        return poder;
    }
    
    public int getVelocidad() {
        return velocidad;
    }
    
    public boolean isJefe() {
        return jefe;
    }
    
    public boolean isDerrotado() {
        return derrotado;
    }
    
    // Setters con validación
    public void setPoder(int poder) {
        if (poder < 0) {
            throw new IllegalArgumentException("El poder no puede ser negativo");
        }
        this.poder = poder;
    }
    
    public void setVelocidad(int velocidad) {
        if (velocidad < 0) {
            throw new IllegalArgumentException("La velocidad no puede ser negativa");
        }
        this.velocidad = velocidad;
    }
    
    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo no puede estar vacío");
        }
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return String.format("%s [%s] - Vida: %d/%d - Poder: %d - %s", 
                nombre, tipo, vida, vidaMaxima, poder, 
                derrotado ? "DERROTADO" : "ACTIVO");
    }
}
