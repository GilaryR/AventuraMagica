package autonoma.AventuraMagica.elements;

import java.util.Arrays;
import java.util.List;

public class NivelIntermedio extends NivelBase {

    @Override
    protected List<String> tiposEnemigos() {
        return Arrays.asList("Tucan", "Frailejon", "Cuy");
    }

    @Override
    protected List<String> tiposArtefactos() {
        return Arrays.asList("Botella", "Esmeralda");
    }

    @Override
    protected int cantidadDe(String tipo) {
        switch (tipo) {
            case "Tucan": return 20;
            case "Frailejon": return 20;
            case "Cuy": return 10;
            case "Botella": return 40;
            case "Esmeralda": return 6;
            default: return 0;
        }
    }

    @Override
    protected int artefactosRequeridos() {
        return 40;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
