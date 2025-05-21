
package autonoma.AventuraMagicaBase.elements;

/**
 *
 * @author Alejandra Ortega 
 */
import java.awt.*;

/**
 * Interfaz que define un contenedor gráfico que puede refrescarse y obtener sus límites
 */
public interface GraphicContainer {
    /**
     * Refresca el contenido del contenedor gráfico
     */
    void refresh();
    
    /**
     * Obtiene los límites del contenedor gráfico
     * @return un objeto Rectangle con los límites del contenedor
     */
    Rectangle getBoundaries();
}
