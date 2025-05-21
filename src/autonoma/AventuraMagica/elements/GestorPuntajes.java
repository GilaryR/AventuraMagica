
package autonoma.AventuraMagica.elements;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author jgiugtiñut
 */
/**
 * Clase para gestionar los puntajes del juego
 */
public class GestorPuntajes {
    private ArrayList<String> puntajes;
    private String rutaArchivo;
    private EscritorArchivoTextoPlano escritor;
    private LectorArchivoTextoPlano lector;
    
    /**
     * Constructor del gestor de puntajes
     * @param rutaArchivo Ruta donde se guarda el archivo de puntajes
     */
    public GestorPuntajes(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.puntajes = new ArrayList<>();
        this.escritor = new EscritorArchivoTextoPlano(rutaArchivo);
        this.lector = new LectorArchivoTextoPlano();
        cargarPuntajes();
    }
    
    /**
     * Carga los puntajes desde el archivo
     */
    public void cargarPuntajes() {
        try {
            puntajes = lector.leer(rutaArchivo);
        } catch (IOException e) {
            System.err.println("No se pudieron cargar los puntajes: " + e.getMessage());
            // El archivo puede no existir aún, lo cual es normal la primera vez
        }
    }
    
    /**
     * Guarda los puntajes en el archivo
     */
    public void guardarPuntajes() {
        try {
            escritor.escribir(puntajes);
        } catch (IOException e) {
            System.err.println("Error al guardar puntajes: " + e.getMessage());
        }
    }
    
    /**
     * Agrega un nuevo puntaje a la lista
     * @param nombre Nombre del jugador
     * @param puntos Puntos obtenidos
     */
    public void agregarPuntaje(String nombre, int puntos) {
        puntajes.add(nombre + "," + puntos);
        guardarPuntajes();
    }
    
    /**
     * Obtiene los mejores puntajes ordenados de mayor a menor
     * @param cantidad Cantidad de puntajes a obtener
     * @return Lista con los mejores puntajes
     */
    public ArrayList<String> obtenerMejoresPuntajes(int cantidad) {
        ArrayList<String> mejores = new ArrayList<>(puntajes);
        
        // Ordenar por puntaje (de mayor a menor)
        Collections.sort(mejores, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                try {
                    int puntos1 = Integer.parseInt(o1.split(",")[1]);
                    int puntos2 = Integer.parseInt(o2.split(",")[1]);
                    return Integer.compare(puntos2, puntos1); // Orden descendente
                } catch (Exception e) {
                    return 0; // En caso de error, mantener el orden
                }
            }
        });
        
        // Limitar a la cantidad solicitada
        if (mejores.size() > cantidad) {
            return new ArrayList<>(mejores.subList(0, cantidad));
        }
        
        return mejores; 
    }
    
    /**
     * Genera una representación de la tabla de puntajes
     * @return String con formato de tabla
     */
    public String mostrarTabla() {
        StringBuilder tabla = new StringBuilder();
        tabla.append("TABLA DE MEJORES PUNTAJES\n");
        tabla.append("-------------------------\n");
        tabla.append("Posición | Jugador | Puntos\n");
        tabla.append("-------------------------\n");
        
        ArrayList<String> mejores = obtenerMejoresPuntajes(10);
        
        for (int i = 0; i < mejores.size(); i++) {
            try {
                String[] parts = mejores.get(i).split(",");
                tabla.append(String.format("%-8d | %-7s | %-6s\n", (i + 1), parts[0], parts[1]));
            } catch (Exception e) {
                // Ignorar entradas con formato incorrecto
            }
        }
        
        return tabla.toString();
    }
}