
package autonoma.AventuraMagica.elements;

/**
 *
 * @author Alejandra Ortega
 */
/**
 * Clase para los acertijos del juego
 */
public class Acertijo {

    static boolean isResuelto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int id;
    private String descripcion;
    private String solucion;
    private boolean resuelto;
    private int valorPuntos;
    
    public Acertijo(int id, String descripcion, String solucion, int valorPuntos) {
        this.id = id;
        this.descripcion = descripcion;
        this.solucion = solucion;
        this.resuelto = false;
        this.valorPuntos = valorPuntos;
    }
    
    public String mostrarPista() {
        return descripcion;
    }
    
    public boolean verificarSolucion(String intento) {
        if (solucion.equalsIgnoreCase(intento)) {
            resolverAcertijo();
            return true;
        }
        return false;
    }
    
    public void resolverAcertijo() {
        resuelto = true;
        otorgarRecompensa();
    }
    
    public void otorgarRecompensa() {
        // Lógica para otorgar recompensa al jugador
    }
}