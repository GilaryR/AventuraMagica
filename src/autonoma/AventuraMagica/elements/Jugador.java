
package autonoma.AventuraMagica.elements;

import autonoma.AventuraMagica.elements.Artefacto;
import autonoma.AventuraMagica.exception.JugadorSinVidaException;
import autonoma.AventuraMagicaBase.elements.SpriteMobile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Alejandra Ortega 
 */

/**
 * Clase que representa al jugador principal del juego
 * @author Luisa Fernanda Henao
 */
public class Jugador extends SpriteMobile {
    private String nombre;
    private boolean arriba, abajo, izquierda, derecha;
    private int salud;
    private int saludMaxima;
    private int puntuacion;
    private ArrayList<Artefacto> inventario;
    private int capacidadInventario;
    private boolean invulnerable;
    private long tiempoInvulnerable;
    
    /**
     * Constructor completo del jugador
     * @param nombre Nombre del jugador
     * @param x Posición inicial en el eje X
     * @param y Posición inicial en el eje Y
     * @param height Altura del sprite del jugador
     * @param width Anchura del sprite del jugador
     */
    public Jugador(String nombre, int x, int y, int height, int width) {
        super(x, y, height, width);
        this.nombre = nombre;
        this.saludMaxima = 100;
        this.salud = saludMaxima;
        this.puntuacion = 0;
        this.inventario = new ArrayList<>();
        this.capacidadInventario = 10; // Capacidad inicial del inventario
        this.color = Color.BLUE;
        this.invulnerable = false;
        this.tiempoInvulnerable = 0;
    }
    
    /**
     * Actualiza la posición y estado del jugador
     */
    public void actualizar() {
        if (arriba && !isOutOfGraphicContainer(x, y - step, width, height)) y -= step;
        if (abajo && !isOutOfGraphicContainer(x, y + step, width, height)) y += step;
        if (izquierda && !isOutOfGraphicContainer(x - step, y, width, height)) x -= step;
        if (derecha && !isOutOfGraphicContainer(x + step, y, width, height)) x += step;
        
        // Verificar fin de invulnerabilidad
        if (invulnerable && System.currentTimeMillis() > tiempoInvulnerable) {
            invulnerable = false;
        }
    }
    
    /**
     * Maneja el evento de tecla presionada para movimiento
     * @param e Evento de teclado
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> arriba = true;
            case KeyEvent.VK_DOWN -> abajo = true;
            case KeyEvent.VK_LEFT -> izquierda = true;
            case KeyEvent.VK_RIGHT -> derecha = true;
            case KeyEvent.VK_SPACE -> usarArtefactoSeleccionado();
        }
    }
    
    /**
     * Maneja el evento de tecla liberada
     * @param e Evento de teclado
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> arriba = false;
            case KeyEvent.VK_DOWN -> abajo = false;
            case KeyEvent.VK_LEFT -> izquierda = false;
            case KeyEvent.VK_RIGHT -> derecha = false;
        }
    }
    
    /**
     * Maneja el evento de clic del ratón para interacciones
     * @param e Evento de ratón
     */
    public void mouseClicked(MouseEvent e) {
        // Implementar lógica de interacción con objetos
        if (e.getButton() == MouseEvent.BUTTON1) { // Clic izquierdo
            interactuarConEntorno(e.getX(), e.getY());
        }
    }
    
    /**
     * Añade un artefacto al inventario del jugador
     * @param artefacto Artefacto a añadir
     * @throws InventarioLlenoException Si el inventario está lleno
     */
    public void agregarArtefacto(Artefacto artefacto) throws InventarioLlenoException {
        if (inventario.size() >= capacidadInventario) {
            throw new InventarioLlenoException(capacidadInventario);
        }
        inventario.add(artefacto);
    }
    
    /**
     * Reduce la salud del jugador
     * @param cantidad Cantidad de daño a recibir
     * @throws JugadorSinVidaException Cuando la salud llega a cero
     */
    public void recibirDaño(int cantidad) throws JugadorSinVidaException {
        if (invulnerable) return;
        
        salud = Math.max(0, salud - cantidad);
        if (salud == 0) {
            throw new JugadorSinVidaException();
        }
        
        // Activar periodo de invulnerabilidad tras recibir daño
        activarInvulnerabilidad(1000); // 1 segundo de invulnerabilidad
    }
    
    /**
     * Restaura salud al jugador
     * @param cantidad Cantidad de salud a restaurar
     */
    public void curar(int cantidad) {
        salud = Math.min(saludMaxima, salud + cantidad);
    }
    
    /**
     * Aumenta la puntuación del jugador
     * @param puntos Puntos a añadir
     */
    public void sumarPuntos(int puntos) {
        puntuacion += puntos;
    }
    
    /**
     * Activa un periodo de invulnerabilidad
     * @param milisegundos Duración en milisegundos
     */
    public void activarInvulnerabilidad(long milisegundos) {
        invulnerable = true;
        tiempoInvulnerable = System.currentTimeMillis() + milisegundos;
    }
    
    /**
     * Pinta al jugador en pantalla
     * @param g Objeto Graphics para dibujar
     */
    @Override
    public void paint(Graphics g) {
        // Dibujar al jugador
        if (invulnerable) {
            // Efecto visual de invulnerabilidad (parpadeo)
            if ((System.currentTimeMillis() / 100) % 2 == 0) {
                g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
            } else {
                g.setColor(color);
            }
        } else {
            g.setColor(color);
        }
        g.fillRect(x, y, width, height);
        
        // Dibujar barra de salud
        g.setColor(Color.RED);
        g.fillRect(x, y - 15, width, 5);
        g.setColor(Color.GREEN);
        g.fillRect(x, y - 15, (int)(width * ((double)salud / saludMaxima)), 5);
        
        // Dibujar nombre del jugador
        g.setColor(Color.WHITE);
        g.drawString(nombre, x, y - 20);
    }
    
    // Métodos privados auxiliares
    private void interactuarConEntorno(int posX, int posY) {
        // Lógica para interactuar con objetos del entorno
        System.out.println("Interactuando en posición: " + posX + ", " + posY);
    }
    
    private void usarArtefactoSeleccionado() {
        if (!inventario.isEmpty()) {
            Artefacto artefacto = inventario.get(0); // Usar el primer artefacto
            artefacto.usar();
            // Implementar lógica adicional según el artefacto
        }
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public int getSalud() {
        return salud;
    }
    
    public int getSaludMaxima() {
        return saludMaxima;
    }
    
    public int getPuntuacion() {
        return puntuacion;
    }
    
    public ArrayList<Artefacto> getInventario() {
        return new ArrayList<>(inventario); // Devuelve copia para proteger encapsulamiento
    }
    
    public boolean isInvulnerable() {
        return invulnerable;
    }
    
    public void setSaludMaxima(int saludMaxima) {
        this.saludMaxima = saludMaxima;
        this.salud = Math.min(salud, saludMaxima);
    }
    
    public void setCapacidadInventario(int capacidad) {
        this.capacidadInventario = capacidad;
    }

    void guardarEstado() {
        
    }

    void cargarEstado() {
        
    }
}