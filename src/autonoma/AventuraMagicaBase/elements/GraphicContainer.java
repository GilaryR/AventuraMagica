
package autonoma.AventuraMagicaBase.elements;
import autonoma.AventuraMagicaBase.elements.SpriteContainer;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 *
 * @author jgiugtiñut
 */
// Clase GraphicContainer


public class GraphicContainer extends JPanel {
    private SpriteContainer spriteContainer;

    public GraphicContainer(SpriteContainer spriteContainer) {
        this.spriteContainer = spriteContainer;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Sprite s : spriteContainer.getSprites()) {
           
        }
    }
}