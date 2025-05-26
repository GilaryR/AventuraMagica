package autonoma.AventuraMagica.ui;

import autonoma.AventuraMagica.elements.Nivel;
import autonoma.AventuraMagica.elements.Jugador;
import autonoma.AventuraMagica.elements.GestorNivel;
import autonoma.AventuraMagica.elements.ControladorJuego;
import autonoma.AventuraMagica.elements.*;
import autonoma.AventuraMagica.thread.HiloJuego;
import autonoma.AventuraMagica.thread.HiloProgresoNivel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelJuego extends JPanel {
    private Image fondo;
    private HiloJuego hiloJuego;
    private HiloProgresoNivel hiloProgreso;
    private int puntaje;
    private Nivel nivel;  
    private GestorNivel gestorNivel;
    private ControladorJuego controlador;
    boolean pausa;

        public PanelJuego() {
        this.gestorNivel = new GestorNivel();
        this.nivel = gestorNivel.getNivelActual();
        this.controlador = new ControladorJuego(nivel);

        this.fondo = new ImageIcon(getClass().getResource("/autonoma/AventuraMagica/images/Fondo.jpg")).getImage();
        this.puntaje = 0;
        this.pausa = false;

        // En vez de pedir foco aquí, usar listener para foco
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                requestFocusInWindow();
            }
        });

        // Hilos (asegúrate que sólo se inician una vez)
        this.hiloJuego = new HiloJuego(this);
        this.hiloJuego.start();

        this.hiloProgreso = new HiloProgresoNivel(
            controlador.getJugador(),
            gestorNivel,
            this::cargarSiguienteNivel
        );
        this.hiloProgreso.start();

        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));

        configurarControles();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
private void configurarControles() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (pausa) return;

                int dx = 0, dy = 0;
                int velocidad = controlador.getJugador().getVelocidad();

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> dx = -velocidad;
                    case KeyEvent.VK_RIGHT -> dx = velocidad;
                    case KeyEvent.VK_UP -> dy = -velocidad;
                    case KeyEvent.VK_DOWN -> dy = velocidad;
                    case KeyEvent.VK_P -> pausa = !pausa;
                    case KeyEvent.VK_ENTER -> verificarAvanceNivel();
                }

                controlador.getJugador().mover(dx, dy, getWidth(), getHeight());
                repaint();
            }
        });
    }

    public void actualizarJuego() {
        if (!pausa && controlador != null) {
            controlador.moverEnemigos();
            controlador.verificarColisiones();
            repaint();
        }
    }
private void verificarProgreso() {
    Jugador jugador = controlador.getJugador();
    
    // Verificar si se completó el nivel actual
    if (gestorNivel.nivelActualCompletado(jugador.getBotellasRecolectadas())) {
        if (gestorNivel.esUltimoNivel()) {
            if (gestorNivel.todosNivelesCompletados(jugador.getBotellasTotales())) {
                mostrarPantallaVictoria();
            }
        } else {
            cargarSiguienteNivel();
        }
    }
}

private void cargarSiguienteNivel() {
    // Mostrar mensaje de nivel completado
    JOptionPane.showMessageDialog(this,
        "¡Nivel " + gestorNivel.getNumeroNivel() + " completado!",
        "Nivel Completado",
        JOptionPane.INFORMATION_MESSAGE);

    // Verificar si es el último nivel (nivel 3 completado)
    if (gestorNivel.esUltimoNivel()) {
        mostrarPantallaVictoria();
        detenerJuego();
        return;
    }

    // Guardar estado persistente del jugador
    String vidas = controlador.getJugador().getVidas();
    int puntaje = controlador.getJugador().getPuntaje();
    
    // Avanzar al siguiente nivel
    gestorNivel.avanzarNivel();
    nivel = gestorNivel.getNivelActual();
    controlador = new ControladorJuego(nivel);
    
    // Restaurar estado persistente
    controlador.getJugador().setVidas(vidas);
    controlador.getJugador().setPuntaje(puntaje);
    
    // Reiniciar contador de botellas para el nuevo nivel
    controlador.getJugador().resetearBotellas();
    
    repaint();
}

  public void verificarAvanceNivel() {
    if (controlador.getJugador().getBotellasRecolectadas() >= 
        nivel.getArtefactosRequeridos()) {
        cargarSiguienteNivel();
    }
}
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Dibujar fondo
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        
        // Dibujar elementos del juego
        controlador.dibujarArtefactos(g);
        controlador.dibujarEnemigos(g);
        controlador.dibujarSimbolos(g);
        controlador.dibujarJugador(g);
        
        // Dibujar HUD (información del juego)
        dibujarHUD(g);
        
        // Dibujar mensaje de pausa si es necesario
        if (pausa) {
            dibujarMensajePausa(g);
        }
    }

    private void dibujarHUD(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Información del nivel
        g.drawString("Nivel: " + gestorNivel.getNumeroNivel(), 20, 20);
        g.drawString("Puntaje: " + puntaje, 20, 45);
        g.drawString("Vidas: " + controlador.getJugador().getVidas(), 20, 70);
        
        // Progreso del nivel
        int recolectados = controlador.getJugador().getBotellasRecolectadas();
        int requeridos = gestorNivel.getNivelActual().getArtefactosRequeridos();
        g.drawString("Artefactos: " + recolectados + "/" + requeridos, 20, 95);
    }

    private void dibujarMensajePausa(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        
        String mensaje = "PAUSA";
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(mensaje)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        
        g.drawString(mensaje, x, y);
    }

    public void detenerJuego() {
        hiloJuego.detener();
        hiloProgreso.detener();   
    }
    
    private void mostrarPantallaVictoria() {
    javax.swing.SwingUtilities.invokeLater(() -> {
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "¡Felicidades! Has ganado el juego 🎉",
            "Victoria",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
        // Opcional: salir del juego o reiniciarlo
        System.exit(0);  // Cierra la aplicación
        // O podrías reiniciar el juego si quieres
    });
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
