package autonoma.AventuraMagicaBase.elements;

/**
 *
 * @author jgiugtiñut
 */
import autonoma.AventuraMagica.exception.EscrituraExcepcion;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorTextoPlano implements Escritor {
    private String ruta;

    public EscritorTextoPlano(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public void escribir(String mensaje) throws EscrituraExcepcion {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) {
            writer.write(mensaje);
            writer.newLine();
        } catch (IOException e) {
            throw new EscrituraExcepcion("No se pudo escribir en el archivo: " + ruta);
        }
    }
}
